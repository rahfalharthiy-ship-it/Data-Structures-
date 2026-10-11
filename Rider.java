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
@Override
    public String getEmail() {
        return email;
    }
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
    
    @Override
    public int compareTo(IRider other){
        return Integer.compare(getId(), other.getId());
        
    }
    
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

