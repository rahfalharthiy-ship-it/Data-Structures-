package com.mycompany.ds;

public class PrivateRide extends Ride implements IPrivateRide {
  
private IRider rider;
 
//construct
public PrivateRide(String pickupLocation,IDateTime pickupTime,IDateTime dropoffTime,String dropoffLocation,IDriver driver,IRider rider){
super(pickupLocation,pickupTime,dropoffTime,dropoffLocation,driver);
this.rider=rider;
}
 
// Returns the rider assigned to this private ride.
@Override
public IRider getRider() {
return rider;
}

// Sets the rider assigned to this private ride.
@Override
public void setRider(IRider rider) {
    this.rider=rider;
}

// Checks whether a rider participates in this ride.
@Override
public boolean hasRider(int riderId) {
    return rider != null && rider.getId() == riderId;
}

@Override
public String toString() {
   return "Private Ride #" + getRideId()+ " | " + getPickupLocation() + " (" + getPickupTime().format() + ")"+ " -> " + getDropoffLocation() + " (" + getDropoffTime().format() + ")"+ " | Driver: " + (getDriver() == null ? "none" : getDriver().getName())+ " | Rider: " + (rider == null ? "none" : rider.getName());
}

}
