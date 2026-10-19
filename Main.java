package com.mycompany.datastructure;


import java.util.Scanner;

// Command-line menu: the only class that reads input and prints output.
public class Main {

    private static Scanner input = new Scanner(System.in);
    private static RideSharingSystem system = new RideSharingSystem();

    public static void main(String[] args) {
        // Load the three CSV files before showing the menu
        system.loadRidersFromCSV("riders_100.csv");
        system.loadDriversFromCSV("drivers_30.csv");
        system.loadRidesFromCSV("rides_40.csv");

        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:   // List all riders
                    printList(system.getAllRiders());                
                    break;
                    
                case 2:   // Search for riders by home city 
                    String city = readText("Home City: ");
                    printList(system.searchRidersByHomeCity(city));     
                    break;
                    
                case 3:   // Search for riders by name
                String searchName=readText("Rider full name");
                printList(system.searchRidersByName(searchName));
                    break;
                    
                case 4:   // Add a rider 
                    try {
                        int riderId = readInt("Rider ID: ");
                        String riderName = readText("Full name: ");
                        String riderPhone = readText("Phone (10 digits): ");
                        String riderEmail = readText("Email: ");
                        String homeCity = readText("Home city: ");
                        Rider rider = new Rider(riderId, riderName,riderEmail,riderPhone, homeCity);
                        if (system.addRider(rider))
                            System.out.println("Rider added successfully.");
                         else
                            System.out.println("Failed: duplicate rider ID.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed: " + e.getMessage());
                    }
                    break;
                    
                case 5:   // List all drivers
                    printList(system.getAllDrivers());
                    break;
                    
                case 6:   // Search for drivers by vehicle type 
                    try {
                        String vType = readText("Vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN): ");
                        printList(system.searchDriversByVehicleType(
                        VehicleType.valueOf(vType.toUpperCase())));
                    } catch (IllegalArgumentException e) {
                       System.out.println("Failed: invalid vehicle type.");
                    }
                    break;
                    
                case 7:   // Add a driver
                    try {
                        int id = readInt("Driver ID: ");
                        String name = readText("Full name: ");
                        String phone = readText("Phone (10 digits): ");
                        String plate = readText("Vehicle plate (e.g., ABC1234): ");
                        String type = readText("Vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN): ");
                        Driver driver = new Driver(id, name, phone, plate, VehicleType.valueOf(type.toUpperCase()));
                        if (system.addDriver(driver))
                            System.out.println("Driver added successfully.");
                        else
                            System.out.println("Failed: duplicate driver ID or vehicle plate.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed: " + e.getMessage());
                    }
                    break;
                    
                case 8:   // List all rides alphabetically by pickup location 
                    printList(system.getAllRidesAlphabetically());
                    break;
                    
                case 9:   // Search for rides by pickup location
                    String location = readText("Pickup location: ");
                    printList(system.searchRidesByPickupLocation(location));
                    break;
                    
                case 10:  // Search for rides by rider name
                    String riderFullName=readText("Rider Full name");
                    printList(system.searchRidesByRiderName(riderFullName));
                    break;
                case 11:  // List the participants of a shared ride by pickup location
                    String sharedLocation = readText("Pickup location: ");
                    printList(system.getSharedRideParticipants(sharedLocation));
                    break;
                case 12: { // Add a ride (private or shared)
               String rideType =readText("Ride type (PRIVATE or SHARED: ").toUpperCase();
                if(!rideType.equals("PRIVATE")&& !rideType.equals("SHARED")){
                   System.out.println("Faild: ride type mus be PRIVATE or SHARED ");
                  break; 
                }
                
                
                try{
                    String pickupLocation=readText("Pickup Location");
                    IDateTime pickupTime= DateTime.parse(readText("Pickup date/ time(MM/DD/YYYY HH:MM):"));
                    IDateTime dropoffTime= DateTime.parse(readText("Drop-of date/ time(MM/DD/YYYY HH:MM):"));
                    String dropoffLocation= readText("Drop-of Location: ");
                    int driverId= readInt("Driver ID");
                    
                    boolean scheduled;
                    if(rideType.equals("PRIVATE")){// praivate has exactly one ride
                       int riderId= readInt("Rider ID:");
                       scheduled=system.schedulePraivateRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation, riderId, driverId);
                    }
                       else{ //sheard ride needs more than one rider
                        int count= readInt=("Number of Riders at Least 2");
                        if(count<2){
                          System.out.println("Faild: Shared needes at Least 2 riders");
                        break;}
                        
                        int[] riderIds= new int[count];
                    for (int i=0 ; i<count; i++){
                        riderIds[i]=readInt("Rider ID: " +(i+1));
                    }//for
                    scheduled=system.scheduleSharedRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation, riderIds, driverId);
                    } //else      
                    
                    if(scheduled)
        System.out.println("Ride scheduled Successfully ");
                    else
               System.out.println("Failed : rider not found  ");
                }//try
                catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e){
                   System.out.println("Failed :inavlid data/time.Use Format MM/DD/YYYY  HH:MM "); 
                }
                            
                    break;
                }
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);
    }

    // Prints the menu options in the required order
    private static void printMenu() {
        System.out.println();
        System.out.println("========== Ride-Sharing System ==========");
        System.out.println("1. List all riders.");
        System.out.println("2. Search for riders by home city.");
        System.out.println("3. Search for riders by name.");
        System.out.println("4. Add a rider.");
        System.out.println("5. List all drivers.");
        System.out.println("6. Search for drivers by vehicle type.");
        System.out.println("7. Add a driver.");
        System.out.println("8. List all rides alphabetically by pickup location.");
        System.out.println("9. Search for rides by pickup location.");
        System.out.println("10. Search for rides by rider name.");
        System.out.println("11. List the participants of a shared ride by pickup location.");
        System.out.println("12. Add a ride.");
        System.out.println("0. Exit the program.");
    }

    // Prints every element's toString(), one per line, or a no-results message
    private static <T> void printList(LinkedList<T> list) {
        if (list == null || list.empty()) {
            System.out.println("No results found.");
            return;
        }
        list.findFirst();
        while (true) {
            System.out.println(list.retrieve());
            if (list.last())
                break;
            list.findNext();
        }
    }

    // Reads a whole number; asks again if the input is not a number
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Reads a line of text
    private static String readText(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }
}

