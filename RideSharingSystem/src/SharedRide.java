// A shared ride (carpool): one driver and several riders.
public class SharedRide extends Ride implements ISharedRide {

    private LinkedList<IRider> participants;

    public SharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
                      String dropoffLocation, IDriver driver) {
        super(pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
        participants = new LinkedList<IRider>();
    }

    @Override
    public LinkedList<IRider> getParticipants() {
        return participants;
    }

    // Adds the rider at the end; fails if already in this ride
    @Override
    public boolean addParticipant(IRider rider) {
        if (rider == null || hasRider(rider.getId()))
            return false;
        if (!participants.empty()) {
            participants.findFirst();
            while (!participants.last())
                participants.findNext();
        }
        participants.insert(rider);
        return true;
    }

    @Override
    public boolean removeParticipantById(int riderId) {
        if (participants.empty())
            return false;
        participants.findFirst();
        while (true) {
            if (participants.retrieve().getId() == riderId) {
                participants.remove();
                return true;
            }
            if (participants.last())
                return false;
            participants.findNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return participants.empty();
    }

    @Override
    public boolean hasRider(int riderId) {
        if (participants.empty())
            return false;
        participants.findFirst();
        while (true) {
            if (participants.retrieve().getId() == riderId)
                return true;
            if (participants.last())
                return false;
            participants.findNext();
        }
    }

    @Override
    public String toString() {
        String riders = "";
        if (!participants.empty()) {
            participants.findFirst();
            while (true) {
                IRider r = participants.retrieve();
                riders += r.getId() + " " + r.getName();
                if (participants.last())
                    break;
                riders += ", ";
                participants.findNext();
            }
        }
        return "Shared Ride [ID: " + getRideId()
                + ", From: " + getPickupLocation() + " at " + getPickupTime().format()
                + ", To: " + getDropoffLocation() + " at " + getDropoffTime().format()
                + ", Driver: " + getDriver().getId() + " " + getDriver().getName()
                + ", Riders: " + riders + "]";
    }
}
