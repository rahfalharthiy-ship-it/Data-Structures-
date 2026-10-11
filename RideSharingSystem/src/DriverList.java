// Stores drivers in a LinkedList sorted by driver ID.
public class DriverList implements IDriverList {

    private LinkedList<IDriver> drivers;
    private int size;

    public DriverList() {
        drivers = new LinkedList<IDriver>();
        size = 0;
    }

    // Inserts in sorted order by ID; fails if the ID or plate already exists
    @Override
    public boolean add(IDriver driver) {
        if (driver == null)
            return false;
        if (findById(driver.getId()) != null || findByVehiclePlate(driver.getVehiclePlate()) != null)
            return false;

        if (drivers.empty()) {
            drivers.insert(driver);
            size++;
            return true;
        }

        drivers.findFirst();
        while (true) {
            IDriver d = drivers.retrieve();
            if (driver.compareTo(d) < 0) {
                // put the new driver here and move d one step forward
                drivers.update(driver);
                drivers.insert(d);
                size++;
                return true;
            }
            if (drivers.last())
                break;
            drivers.findNext();
        }
        drivers.insert(driver);   // largest ID: add at the end
        size++;
        return true;
    }

    @Override
    public IDriver findById(int driverId) {
        if (drivers.empty())
            return null;
        drivers.findFirst();
        while (true) {
            if (drivers.retrieve().getId() == driverId)
                return drivers.retrieve();
            if (drivers.last())
                return null;
            drivers.findNext();
        }
    }

    @Override
    public LinkedList<IDriver> findByName(String fullName) {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        if (drivers.empty())
            return result;
        drivers.findFirst();
        while (true) {
            if (drivers.retrieve().getName().equals(fullName))
                result.insert(drivers.retrieve());
            if (drivers.last())
                break;
            drivers.findNext();
        }
        return result;
    }

    @Override
    public IDriver findByVehiclePlate(String vehiclePlate) {
        if (drivers.empty())
            return null;
        drivers.findFirst();
        while (true) {
            if (drivers.retrieve().getVehiclePlate().equals(vehiclePlate))
                return drivers.retrieve();
            if (drivers.last())
                return null;
            drivers.findNext();
        }
    }

    @Override
    public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        if (drivers.empty())
            return result;
        drivers.findFirst();
        while (true) {
            if (drivers.retrieve().getVehicleType() == vehicleType)
                result.insert(drivers.retrieve());
            if (drivers.last())
                break;
            drivers.findNext();
        }
        return result;
    }

    @Override
    public LinkedList<IDriver> getAll() {
        LinkedList<IDriver> result = new LinkedList<IDriver>();
        if (drivers.empty())
            return result;
        drivers.findFirst();
        while (true) {
            result.insert(drivers.retrieve());
            if (drivers.last())
                break;
            drivers.findNext();
        }
        return result;
    }

    // ID is unique: removes at most one driver
    @Override
    public boolean removeById(int driverId) {
        if (findById(driverId) == null)
            return false;
        drivers.remove();   // findById left current on the driver
        size--;
        return true;
    }

    // Plate is unique: removes at most one driver
    @Override
    public boolean removeByVehiclePlate(String vehiclePlate) {
        if (findByVehiclePlate(vehiclePlate) == null)
            return false;
        drivers.remove();
        size--;
        return true;
    }

    // Removes every driver with this name; returns how many were removed
    @Override
    public int removeByName(String fullName) {
        int count = 0;
        while (findFirstByName(fullName)) {
            drivers.remove();
            count++;
        }
        size -= count;
        return count;
    }

    // Removes every driver with this vehicle type; returns how many were removed
    @Override
    public int removeByVehicleType(VehicleType vehicleType) {
        int count = 0;
        while (findFirstByType(vehicleType)) {
            drivers.remove();
            count++;
        }
        size -= count;
        return count;
    }

    @Override
    public int size() {
        return size;
    }

    // Moves current to the first driver with this name; false if none
    private boolean findFirstByName(String fullName) {
        if (drivers.empty())
            return false;
        drivers.findFirst();
        while (true) {
            if (drivers.retrieve().getName().equals(fullName))
                return true;
            if (drivers.last())
                return false;
            drivers.findNext();
        }
    }

    // Moves current to the first driver with this type; false if none
    private boolean findFirstByType(VehicleType vehicleType) {
        if (drivers.empty())
            return false;
        drivers.findFirst();
        while (true) {
            if (drivers.retrieve().getVehicleType() == vehicleType)
                return true;
            if (drivers.last())
                return false;
            drivers.findNext();
        }
    }
}
