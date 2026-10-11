/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 96654
 */
public class RiderList implements IRiderList {
    
    private static class Node{
        private final IRider data;
        private Node next;
        
        private Node(IRider data){
            this.data=data;
       
        }
    }
    private Node head; 
    private int size;
    
    //adds rider
    @Override
    public boolean add(IRider rider){
        if (rider==null)
            return false;
    
    //Pass1 reject dublicate ID or Email
    for (Node cur =head; cur!=null; cur=cur.next){
    if (cur.data.getId()==rider.getId())
        return false;
    if (rider.getEmail()!=null && rider.getEmail().equalsIgnoreCase(cur.data.getEmail()))
        return false;
}
    //Pass 2 find the position that keeps list stored by ID
  Node newNode=new Node(rider);
  if (head== null || rider.compareTo(head.data)<0){
  newNode.next=head;
  head=newNode;
}
  else{
  Node cur =head;
  while(cur.next!= null&& cur.next.data.compareTo(rider)<0){
  cur=cur.next;
  }
  newNode.next =cur.next;
  cur.next=newNode;
  
}
  size++;
  return true;
    }  
    
    
// find a ridy by Id or null if absent
  @Override
public IRider findById(int riderId){
    for( Node cur=head ; cur!=null; cur=cur.next){
        if (cur.data.getId()== riderId)
            return cur.data;
        
        if(cur.data.getId()> riderId)
            break;// list is stord     
    }
    return null;
    } 
@Override
public LinkedList<IRider> findByName(String fullName){
   LinkedList<IRider> result = new LinkedList<IRider>();
   for (Node cur =head ; cur!=null ; cur=cur.next){
       if(matchesName(cur.data , fullName))
       result.insert(cur.data);
   }
   return result;
     
   }
@Override
public IRider findByEmail(String email){
   for (Node cur =head ; cur!=null ; cur=cur.next){
       if(matchesEmail(cur.data , email))
return cur.data;
        }
   return null;
     
   }

@Override
public LinkedList<IRider> findByHomeCity(String homeCity){
   LinkedList<IRider> result = new LinkedList<IRider>();
   for (Node cur =head ; cur!=null ; cur=cur.next){
       if(matchesName(cur.data , homeCity))
       result.insert(cur.data);
   }
   return result;
     
   }



  // Rtturn evrey  rider from the home city.
    @Override
public LinkedList<IRider> getAll(){
   LinkedList<IRider> result = new LinkedList<IRider>();
   for (Node cur =head ; cur!=null ; cur=cur.next){
       result.insert(cur.data);
   }
   return result;
     
   }


 @Override
public boolean removeById(int riderId){
    Node prev = null;
    Node cur= head;
    while(cur!=null){
        if (cur.data.getId()==riderId){
            Unlink(prev ,cur);
            return true;
        }
        if(cur.data.getId()>riderId)
            break;
        prev =cur;
        cur=cur.next;
       
        }
    return false;
}
//Remove the ridr whith given Email
 @Override
public boolean removeByEmail(String email){
    Node prev = null;
    Node cur= head;
    while(cur!=null){
        if (matchesEmail(cur.data ,email)){
            Unlink(prev ,cur);
            return true;
        }
        prev =cur;
        cur=cur.next;
       
        }
    return false;
}
@Override
public int removeByName(String fullName){
    int removed=0;
    Node prev = null;
    Node cur= head;
while(cur !=null){
    if(matchesName(cur.data , fullName)){
 Unlink(prev ,cur);
 removed++;}
    
 else{
         prev=cur;
         }
 cur=cur.next;
 
         }
    return removed;
    }
@Override
public int removeByHomeCity(String homeCity){
    int removed=0;
    Node prev = null;
    Node cur= head;
while(cur !=null){
    if(matchesCity(cur.data , homeCity)){
 Unlink(prev ,cur);
 removed++;}
    
 else{
         prev=cur;
         }
 cur=cur.next;
 
         }
    return removed;
    }
// return the number of riders stord
@Override
public int size(){
    return size;
    
}
private void Unlink(Node prev , Node cur)
{
    if(prev==null)
        head=cur.next;
    else 
        prev.next= cur.next;
    size--;
}
private static boolean matchesName(IRider rider ,String fullName){
    return rider.getName()!=null&& rider.getName().equals(fullName);
}

 private static boolean matchesEmail(IRider rider ,String email){
    return rider.getEmail()!=null&& rider.getEmail().equalsIgnoreCase(email);
}
private static boolean matchesCity(IRider rider ,String city){
    return rider.getHomeCity()!=null&& rider.getHomeCity().equalsIgnoreCase(city);
}
}
