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
    
    private String name;
    private String phoneNumber;
    private final LinkedList<IRide> rideHistory;
    
protected Person (int id , String name ,String phoneNumber){
    this.id=id;
    this.name=name;
    this.rideHistory=new LinkedList<IRide>();
    setPhoneNumber(phoneNumber);
}

@Override
public int getId(){
    return id;
}
@Override    
public String getName(){
    return name;
}
public void setName(String name){
      this.name=name;
   }
    @Override
    public String getPhoneNumber(){
        return phoneNumber;
    }


/** 
 * set the phone number after Validating it.
 * throws illegalArgumentException if it is not Exactly 10 nuberm digits.
 */
public final void setPhoneNumber(String phoneNumber){
if (! isValidpPhoneNumber(phoneNumber)){
    throw new IllegalArgumentException("Phone number must be 10 digits"+phoneNumber);
}
this.phoneNumber=phoneNumber;
}

@Override
public LinkedList<IRide>getRideHistory(){
    return rideHistory;
}

@Override
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


    
