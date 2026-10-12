/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 96654
 */
public class Rider extends Person implements IRider{
    private String email;
    private String homeCity;

    public Rider(int riderId, String name, String email, String phoneNumber, String homeCity) {
        super(riderId, name, phoneNumber);
        this.email=email;
        this.homeCity=homeCity;
        
    }
    
   //return the rider’s email address.
@Override
    public String getEmail() {
        return email;
    }
    
  //return the rider’s home city.  
@Override
    public String getHomeCity() {
        return homeCity;
    }
@Override
    public void setEmail(String email) {
        this.email = email;
    }
@Override
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }
    
    /**
* Compares this rider with another rider based on rider ID.
* Returns a negative integer, zero, or a positive integer as this
* rider’s ID is less than, equal to, or greater than the other
* rider’s ID. This ordering must be consistent with equality by ID.
*/
    @Override
    public int compareTo(IRider other){
        return Integer.compare(getId(), other.getId());
        
    }
    
    //Tow Riders are equale when have the same Id
    @Override
    public boolean equals(Object obj){
        if (this== obj)
            return true;
       if(!(obj instanceof IRider))
       return false;
       
       return getId() == ((IRider) obj).getId();
      
    }
    
    @Override
    public int hashCode(){
        return getId();
    }
    
    @Override
    public String toString(){
        return "Rider ["+ super.toString()+ "Email: "+email+" Home City:"+homeCity +"]";
    }
}

