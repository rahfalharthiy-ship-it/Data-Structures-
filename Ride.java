
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
    
   protected Ride (String pickupLocation , IDateTime pickupTime , IDateTime dropoffTime ,String dropoffLocation , IDriver driver){
    if (pickupLocation ==null ||dropoffLocation==null|| driver==null ||dropoffTime==null || pickupTime==null) 
        throw new IllegalArgumentException("Ride fileds must not be null");
    
    if (dropoffTime.compareTo(pickupTime)<0)
        throw new IllegalArgumentException("Drop of time connt before pickup time");
               
          this.rideId=nextRideId++;
          this.pickupLocation=pickupLocation;
          this.pickupTime=pickupTime;
          this.dropoffTime=dropoffTime;
          this.dropoffLocation=dropoffLocation;
          this.driver=driver;   
}
   
   @Override
  public int getRideId(){
      return rideId;
  } 
  @Override
  public String getPickupLocation(){
      return pickupLocation;
  }
 @Override
    public void setPickupLocation(String pickupLocation){
      if(pickupLocation==null){
       throw new IllegalArgumentException("Pickup of Location  must not be null");
      }
      this.pickupLocation=pickupLocation;
  }
  
         
  
  @Override
  public IDateTime getPickupTime(){
      return pickupTime;
  }
   @Override
  public IDateTime getDropoffTime(){
      return dropoffTime;
     
  }
  @Override
  public String getDropoffLocation(){
      return dropoffLocation;
  }
  @Override
  public void setDropoffLocation(String dropoffLocation){
      if(dropoffLocation==null){
       throw new IllegalArgumentException("Drop of Location  must not be null");
      }
      this.dropoffLocation=dropoffLocation;
  }
 @Override
public IDriver getDriver(){
    return driver;
} 
  
@Override
  public void setDriver(IDriver driver){
      if(driver==null)
    throw new IllegalArgumentException ("Driver must not be null");

            this.driver=driver;
      }
  @Override
   public int compareTo(IRide other){
       return
    pickupLocation.compareToIgnoreCase(other.getPickupLocation());
   }
   
   @Override
   public String toString(){
       return "Ride"+ rideId+" / from:"+pickupLocation+"("+pickupTime+")  --> To:"+dropoffLocation+"("+dropoffTime+") | Driver: "+ driver.getName()+ "ID: "+ driver.getId();
       
  }
}
