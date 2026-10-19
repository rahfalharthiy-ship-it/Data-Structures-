package com.mycompany.datastructure;

import java.io.File;
import java.util.Scanner;

public class RideSharingSystem implements IRideSharingSystem {

private final IRiderList riderList = new RiderList();
private final IDriverList driverList = new DriverList();
private final IRideList rideList = new RideList();

    // Loads riders from a CSV file.
   // Returns true if loading succeeds; false otherwise.
    @Override
   
    public boolean loadRidersFromCSV(String ridersFilePath) {
        try {
        Scanner read=new Scanner(new File(ridersFilePath));
       while(read.hasNextLine()){
       String[] d=read.nextLine().trim().split(",");
       IRider r = new Rider(
       Integer.parseInt(d[0]),//id
               d[1],//name
               d[3],//phone
               d[2],//emil
               d[4]);//homecity
       addRider(r);
       }
        read.close();
        return true;
        
        }catch(Exception e){
        return false;
        }
        }

    // Loads drivers from a CSV file.
   // Returns true if loading succeeds; false otherwise.
    @Override
    public boolean loadDriversFromCSV(String driversFilePath) {
        try {
        Scanner read=new Scanner(new File(driversFilePath));
       while(read.hasNextLine()){
       String[] d=read.nextLine().trim().split(",");
       IDriver driver = new Driver(
       Integer.parseInt(d[0]),//id
               d[1],//name
               d[2],//phone
               d[3],//vehicle plate
               VehicleType.valueOf(d[4].trim().toUpperCase())
       );
       addDriver(driver);
       }
        read.close();
        return true;
        
        }catch(Exception e){
        return false;}
        }
    
     // Loads rides from a CSV file. All referenced riders and the assigned driver must already exist. It must enforce conflict rules
    // Returns true if loading succeeds; false otherwise.
    @Override
    public boolean loadRidesFromCSV(String ridesFilePath) {
         try {
        Scanner read=new Scanner(new File(ridesFilePath));
       while(read.hasNextLine()){
       String[] d=read.nextLine().trim().split(",");
       
       String rideType=d[0];
       String pickuplocation=d[1];
       
       DateTime pickupTime = DateTime.parse(d[2]);
       DateTime dropoffTime = DateTime.parse(d[3]);
       
       String dropoffLocation=d[4];
     
       int driverId = Integer.parseInt(d[5]);
      
       if(rideType.equalsIgnoreCase("PRIVATE")){
          int riderId = Integer.parseInt(d[6]);
          schedulePrivateRide(pickuplocation,pickupTime,dropoffTime,dropoffLocation,riderId,driverId);}
       else if(rideType.equalsIgnoreCase("SHARED")){
           String[] riderStrings=d[6].split(";");
           int[]riderIds = new int[riderStrings.length];
           
           for(int i=0;i<riderStrings.length;i++)
               riderIds[i]=Integer.parseInt(riderStrings[i].trim());
           scheduleSharedRide(pickuplocation,pickupTime,dropoffTime,dropoffLocation,riderIds,driverId);
        }
       }
       
        read.close();
        return true;
        
        }catch(Exception e){
        return false;}
     }

    //Adds a rider to the system after enforcing uniqueness by ID. Returns true if added; false if duplicate ID or invalid
    @Override
    public boolean addRider(IRider rider) {
         return riderList.add(rider);
    }
    
    //Adds a driver to the system after enforcing uniqueness by ID and by vehicle plate number (no two drivers may share the same plate).
    @Override
    public boolean addDriver(IDriver driver) {
       return driverList.add(driver);
    }

    //Searches for a rider by ID.
    @Override
    public IRider searchRiderById(int riderId) {
        return riderList.findById(riderId);
    }

    //Searches for a rider by email.
    @Override
    public IRider searchRiderByEmail(String email) {
        return riderList.findByEmail(email);
    }

    //Searches for all riders whose full name exactly matches the given name.
    @Override
    public LinkedList<IRider> searchRidersByName(String fullName){
        return riderList.findByName(fullName);
    }

    //Searches for all riders from the specified home city.
    @Override
    public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
        return riderList.findByHomeCity(homeCity);
    }

    //Returns all riders stored in the system.
    @Override
    public LinkedList<IRider> getAllRiders() {
        return riderList.getAll();
    }
    
    //Searches for a driver by ID.
    @Override
    public IDriver searchDriverById(int driverId) {
        return driverList.findById(driverId);
    }

     //Searches for a driver by vehicle plate.
    @Override
    public IDriver searchDriverByVehiclePlate(String vehiclePlate) {
        return driverList.findByVehiclePlate(vehiclePlate);
    }

    //Searches for all drivers with the specified vehicle type.
    @Override
    public LinkedList<IDriver>  searchDriversByVehicleType(VehicleType vehicleType){
        return driverList.findByVehicleType(vehicleType);
    }

    //Returns all drivers stored in the system.
    @Override
    public LinkedList<IDriver> getAllDrivers() {
    return driverList.getAll();   
    }

    /**
     * Removes a rider and performs cascade deletion of associated rides:
     * - All private rides involving the rider are deleted.
     * - The rider is removed from any shared rides.
     * - Shared rides with no remaining participants are deleted.
     * It returns true if the rider was found and removed; false otherwise
     */
    @Override
    public boolean removeRider(int riderId) {
      if (riderList.findById(riderId) == null)
        return false;

    LinkedList<IRide> allRides =getAllRidesAlphabetically();

    if (!allRides.empty()) {
        allRides.findFirst();

        while (true) {
            IRide ride = allRides.retrieve();

            if (ride instanceof IPrivateRide) {
                IPrivateRide privateRide =(IPrivateRide) ride; 
                if (privateRide.hasRider(riderId)) {
                    rideList.removeRideById(ride.getRideId());
                    allRides = rideList.getAllAlphabetically();

                    if (allRides.empty())
                        break;

                    allRides.findFirst();
                    continue;
                }

            } else if (ride instanceof ISharedRide) {
                ISharedRide sharedRide =(ISharedRide) ride;

                if (sharedRide.hasRider(riderId)) {
                    sharedRide.removeParticipantById(riderId);

                    if (sharedRide.isEmpty()) {
                        rideList.removeRideById(ride.getRideId());
                        allRides = rideList.getAllAlphabetically();

                        if (allRides.empty())
                            break;

                        allRides.findFirst();
                        continue;
                    }
                }
            }

            if (allRides.last())
                break;

            allRides.findNext();
        }
    }

    return riderList.removeById(riderId);
    }

    /*
     * Removes a driver and performs cascade deletion of their assigned rides:
     * - All private rides assigned to the driver are deleted.
     * - All shared rides assigned to the driver are deleted.
     * It returns true if the driver was found and removed; false otherwise
     */
    @Override
    public boolean removeDriver(int driverId) {
    if (driverList.findById(driverId) == null)
        return false;

    LinkedList<IRide> allRides =rideList.getAllAlphabetically();

    if (!allRides.empty()) {
        allRides.findFirst();

        while (true) {
            IRide ride = allRides.retrieve();

            if (ride.getDriver().getId() == driverId) {
                rideList.removeRideById(ride.getRideId());
                allRides = rideList.getAllAlphabetically();

                if (allRides.empty())
                    break;

                allRides.findFirst();
                continue;
            }
            if (allRides.last())
                break;
            allRides.findNext();
        }
    }

    return driverList.removeById(driverId); 
    }

    /**
    Checks whether two time intervals overlap.
    The first interval is [p1, d1] and the second is [p2, d2], where
    p is the pickup time and d is the dropoff time.
    Returns true if the intervals overlap; false otherwise. 
    */
    private boolean isOverlap(IDateTime pl, IDateTime d1, IDateTime p2,IDateTime d2) {
        return pl.compareTo(d2) < 0 && p2.compareTo(d1) < 0;
    }
    
    /**
    Checks whether a new ride conflicts with any ride in the given history.
    It traverses the ride list and compares the new pickup/dropoff times
    with each existing ride using isOverlap().
    Returns true if at least one ride overlaps; false if the list is
    empty or no conflict is found. 
    */
    private boolean conflictsWith(LinkedList<IRide> rideHistory, IDateTime pickupTime, IDateTime dropoffTime){
    if (rideHistory.empty()) 
        return false;
    
    rideHistory.findFirst();
    while(true) {
       IRide ride = rideHistory.retrieve();
       if (isOverlap(pickupTime, dropoffTime, ride.getPickupTime(), ride.getDropoffTime())) 
       return true;

       if (!rideHistory.last())
       rideHistory.findNext();
       else 
           return false;
    }
    }
    
    /**
     * Schedules a private ride for one rider with one driver.
     * Requirements:
     * - The rider must exist.
     * - The driver must exist.
     * - The ride must not conflict with the rider's current schedule.
     * - The ride must not conflict with the driver's current schedule.
     * It returns true if scheduled; false otherwise
     */
    @Override
    public boolean schedulePrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, int riderId, int driverId) {
       
    IRider rider = searchRiderById(riderId);
    IDriver driver = searchDriverById(driverId);

    if (rider == null || driver == null|| pickupLocation == null|| dropoffLocation == null|| pickupTime == null || dropoffTime == null|| dropoffTime.compareTo(pickupTime) < 0) 
        return false;
    
    if (conflictsWith(driver.getRideHistory(), pickupTime, dropoffTime) || conflictsWith(rider.getRideHistory(), pickupTime, dropoffTime)) 
        return false;

    PrivateRide ride = new PrivateRide(pickupLocation, pickupTime, dropoffTime,dropoffLocation, driver, rider);
    if (!rideList.addRide(ride)) 
    return false;
    
    rider.getRideHistory().insert(ride);
    driver.getRideHistory().insert(ride);
    return true;
}
    
    /**
     * Schedules a shared ride for multiple riders with one driver.
     * Requirements:
     * - All riders must exist.
     * - The driver must exist.
     * - The ride must not conflict with any listed rider's schedule.
     * - The ride must not conflict with the driver's current schedule.
     * It returns true if scheduled; false otherwise
     */
    @Override
    public boolean scheduleSharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, int[] riderIds, int driverId) {
    if (riderIds == null || riderIds.length == 0|| pickupLocation == null|| dropoffLocation == null|| pickupTime == null|| dropoffTime == null|| dropoffTime.compareTo(pickupTime) < 0) 
        return false;
    
    IDriver driver = searchDriverById(driverId);

    if (driver == null|| conflictsWith(driver.getRideHistory(), pickupTime, dropoffTime))
        return false;
    
    IRider[] riders=new IRider[riderIds.length];
    for (int i = 0; i < riderIds.length; i++) {
        riders[i]=searchRiderById(riderIds[i]);
          if ( riders[i]== null) 
            return false;}
       
        if(conflictsWith(driver.getRideHistory(), pickupTime, dropoffTime))
            return false;
        
        for (int i = 0; i < riderIds.length; i++) {
          if(conflictsWith(riders[i].getRideHistory(), pickupTime, dropoffTime))
            return false;
        }
    
    SharedRide ride = new SharedRide(pickupLocation, pickupTime, dropoffTime,dropoffLocation, driver);

    for (int i=0;i<riderIds.length;i++) {
        ride.addParticipant(riders[i]);
        riders[i].getRideHistory().insert(ride);
    }
        driver.getRideHistory().insert(ride);
        rideList.addRide(ride);
        return true;
    }
    //Returns all rides (private and shared) alphabetically ordered by pickup location.
    @Override
    public LinkedList<IRide> getAllRidesAlphabetically() {
       return rideList.getAllAlphabetically();                  
    }
    
    //Searches for all rides whose pickup location matches the given location (may return multiple if pickup locations repeat).
    @Override
    public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
      return rideList.findByPickupLocation(pickupLocation);
    }
    
    //Searches for all rides that involve a rider with the given name.
    @Override
    public LinkedList<IRide> searchRidesByRiderName(String riderName) {
        return rideList.findByRiderName(riderName);
    }

    //Returns all riders registered on a shared ride identified by its pickup location.
    @Override
    public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {
        LinkedList<IRider> empty = new LinkedList<IRider>();
        LinkedList<IRide> matches = rideList.findByPickupLocation(pickupLocation);
        if (matches.empty()) return empty;
        matches.findFirst();
        while (true) {
            IRide ride = matches.retrieve();
            if (ride instanceof ISharedRide) 
                return ((ISharedRide) ride).getParticipants();
            if (matches.last()) 
                return empty;
            matches.findNext();
        }
    }     
    

  
}