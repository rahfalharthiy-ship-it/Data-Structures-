// Stores rides in a LinkedList sorted alphabetically by pickup location.
public class RideList implements IRideList {

    private LinkedList<IRide> rides;
    private int size;

    public RideList() {
        rides = new LinkedList<IRide>();
        size = 0;
    }

    // Inserts in alphabetical order by pickup location (uses Ride.compareTo)
    @Override
    public boolean addRide(IRide ride) {
        if (ride == null)
            return false;

        if (rides.empty()) {
            rides.insert(ride);
            size++;
            return true;
        }

        rides.findFirst();
        while (true) {
            IRide r = rides.retrieve();
            if (ride.compareTo(r) < 0) {
                // put the new ride here and move r one step forward
                rides.update(ride);
                rides.insert(r);
                size++;
                return true;
            }
            if (rides.last())
                break;
            rides.findNext();
        }
        rides.insert(ride);   // goes at the end
        size++;
        return true;
    }

    @Override
    public boolean removeRideById(int rideId) {
        if (rides.empty())
            return false;
        rides.findFirst();
        while (true) {
            if (rides.retrieve().getRideId() == rideId) {
                rides.remove();
                size--;
                return true;
            }
            if (rides.last())
                return false;
            rides.findNext();
        }
    }

    @Override
    public LinkedList<IRide> getAllAlphabetically() {
        LinkedList<IRide> result = new LinkedList<IRide>();
        if (rides.empty())
            return result;
        rides.findFirst();
        while (true) {
            result.insert(rides.retrieve());
            if (rides.last())
                break;
            rides.findNext();
        }
        return result;
    }

    @Override
    public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        if (rides.empty())
            return result;
        rides.findFirst();
        while (true) {
            if (rides.retrieve().getPickupLocation().equals(pickupLocation))
                result.insert(rides.retrieve());
            if (rides.last())
                break;
            rides.findNext();
        }
        return result;
    }

    @Override
    public LinkedList<IRide> findByRiderName(String riderFullName) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        if (rides.empty())
            return result;
        rides.findFirst();
        while (true) {
            if (hasRiderName(rides.retrieve(), riderFullName))
                result.insert(rides.retrieve());
            if (rides.last())
                break;
            rides.findNext();
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    // Checks the rider of a private ride, or every participant of a shared ride
    private boolean hasRiderName(IRide ride, String riderFullName) {
        if (ride instanceof IPrivateRide) {
            IRider r = ((IPrivateRide) ride).getRider();
            return r != null && r.getName().equals(riderFullName);
        }
        LinkedList<IRider> riders = ((ISharedRide) ride).getParticipants();
        if (riders.empty())
            return false;
        riders.findFirst();
        while (true) {
            if (riders.retrieve().getName().equals(riderFullName))
                return true;
            if (riders.last())
                return false;
            riders.findNext();
        }
    }
}
