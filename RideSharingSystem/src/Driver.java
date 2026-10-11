
public class Driver extends Person implements IDriver {

    private String vehiclePlate;
    private VehicleType vehicleType;

    public Driver(int driverId, String name, String phoneNumber,
            String vehiclePlate, VehicleType vehicleType) {
        super(driverId, name, phoneNumber);   // ID is set once here
        setVehiclePlate(vehiclePlate);
        this.vehicleType = vehicleType;
    }

    @Override
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    // Plate must be 3 uppercase letters + 4 digits (e.g., ABC1234)
    @Override
    public void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || vehiclePlate.length() != 7) {
            throw new IllegalArgumentException("Invalid vehicle plate: " + vehiclePlate);
        }
        for (int i = 0; i < 7; i++) {
            char c = vehiclePlate.charAt(i);
            if (i < 3 && (c < 'A' || c > 'Z')) {
                throw new IllegalArgumentException("Invalid vehicle plate: " + vehiclePlate);
            }
            if (i >= 3 && (c < '0' || c > '9')) {
                throw new IllegalArgumentException("Invalid vehicle plate: " + vehiclePlate);
            }
        }
        this.vehiclePlate = vehiclePlate;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    // Compare by driver ID
    @Override
    public int compareTo(IDriver other) {
        return getId() - other.getId();
    }

    @Override
    public String toString() {
        return "Driver [ID: " + getId() + ", Name: " + getName() + ", Phone: " + getPhoneNumber()
                + ", Plate: " + vehiclePlate + ", Vehicle: " + vehicleType + "]";
    }
}
