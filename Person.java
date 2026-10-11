/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 96654
 */
public abstract class Person implements IPerson {
   
    private final int id;
    private sting name;
    private string phoneNumber;
    private final LinkedList<IRide> rideHistory;
    
protected Person (int id , String n ,String phN){
    this.id=id;
    name=n;
    rideHistory=new LinkedList<IRide>();
    setPhoneNumber(phoneNumber);
}

public int getid(){
    return id;
}
    
public String getName(){
    return name;
}
    public void setName(String name){
      this.name=name
   }
    public String getPhoneNumber(){
        return phoneNumber;
    }


/** 
 * set the phone number after Validating it.
 * throws illegalArgumentException if it is not Exactly 10 num digits.
 */
public final void setPhoneNumber(String phoneNumber){
if (! isValidpPhoneNumber(phoneNumber)){
    throw new illegalArgumentException("Phone number must be 10 digits"+phoneNumber)
}
this.phoneNumber=phoneNumber;
}

public LinkedList<IRide>getRideHistory(){
    return rideHistory;
}

public String toString(){
    return "ID"+id+" Name" +name+"Phone number"+phoneNumber;
}
            
/*
checks that phone num is not null , has length 10 digits
*/
private static boolean isValidpPhoneNumber(String phone){
    if (phone ==null || phone.length()!=10)
        return false;

for (int i=0 ; i<phone.length(); i++){
char c= phone.charAt(i);
if(c<'0'|| c>'9')
    return false;
}
return true;
}

}
    
