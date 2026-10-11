/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 96654
 */
public abstract class Ride implements IRide {
    
    private static int nextRideId=1;/*genrator for unice ride*/
   
    
    private final int rideId;
    private String pickupLocation;
    private String dropoffLocation;
    private final IDateTime pickupTime; /*typed as IdateTime so Ride depends on the interface, not an implements*/
    private final IDateTime dropoffTime;
    private IDriver driver;
    
    /*creetes a ride and issinds it 
    throws c if any argument is null 
    or drop of time if is earlier then pickup time*/
    
   protected Ride (String pickupLocation , IDateTime pickupTime , IDateTime dropoffTime; ,String dropoffLocation , IDriver driver){
    if (pickupLocation ==null ||dropoffLocation==null|| driver==null ||dropoffTime==null || pickupTime==null) 
        throw new illegalArgumentException("Ride fileds must not be null");
    
    if (dropoffTime.compareTo(pickupTime)<0)
        throw new illegalArgumentException("Drop of time connt before pickup time")
               
          this.rideId=nextRideId++;
          this.pickupLocation=pickupLocation;
          this.pickupTime=pickupTime;
          this.dropoffTime=dropoffTime;
          this.dropoffLocation=dropoffLocation;
          this.driver=driver;   
}
   
  public int getRideId(){
      return rideId;
  } 
  Public String getPickupLocation(){
      return pickupLocation;
  }
 
    public void setPickupLocation(String dropoffLocation){
      if(pickupLocation==null){
       throw new illegalArgumentException("Pickup of Location  must not be null")}
      this.pickupLocation=pickupLocation;
  }
  
         
  
  
  public String getPickupTime(){
      return pickupTime
  }
   
  public IDateTime getDropoffTime(){
      return dropoffTime;
     
  }
  public String getDropoffLocation(){
      return dropoffLocation;
  }
  public void setDropoffLocation(String dropoffLocation){
      if(dropoffLocation==null){
       throw new illegalArgumentException("Drop of Location  must not be null")}
      this.dropoffLocation=dropoffLocation;
  }

  public void setDriver(IDriver driver){
      if(driver==null)
    throw new illegalArgumentException ("Driver must not be null")

            this.driver=driver
      }
  
   public int compareTo(IRide other){
       return
    pickupLocation.compareToIgnoreCase(other.getPickupLocation());
   }
   
   public String toString(){
       return "Ride"+ rideId+" / from:"+pickupLocation+"("+pickupTime+")  --> To:"+dropoffLocation+"("+dropoffTime")"+
       " | Driver "+ driver.getName()+ "ID: "+ driver.getId();
       
  }
}