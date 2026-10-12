/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/* A private ride Scheduled wiht exactly one ridee.
 */
public class PrivateRide extends Ride implements IPrivateRide {
    
    private IRider rider;

    
    public PrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, IDriver driver, IRider rider) {
        super(pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
        if(rider == null){
        throw new IllegalArgumentException ("Rider must not null");   
    }
        this.rider=rider;
    }

    @Override
    public IRider getRider() {
        return rider;
    }
@Override
    public void setRider(IRider rider) {
        if(rider == null)
        throw new IllegalArgumentException ("Rider must not null"); 
        
        this.rider = rider;
    }
    
    
    // True if the given ID  if this rides rider.
     @Override     
  public boolean hasRider(int riderId){
      return rider.getId()==riderId;
  }
  
  @Override
  public String toString(){
      return "["+ super.toString()+"Rider :"+rider.getName()+"ID: "+rider.getId()+"]";
    
   
    
}
}
