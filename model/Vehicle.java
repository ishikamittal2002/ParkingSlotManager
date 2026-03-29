public class Vehicle {
    
    // --- Fields ---
    private String vehicleId;       // Unique ID: V001, V002...
    private String ownerName;       // "Rahul Sharma"
    private String vehicleNumber;   // "MP09AB1234"
    private String type;            // "TWO_WHEELER" or "FOUR_WHEELER"
    private boolean isRegistered;   // Always true when created normally

    // --- Constructor ---
    public Vehicle(String vehicleId, String ownerName, 
                   String vehicleNumber, String type) {
        this.vehicleId = vehicleId;
        this.ownerName = ownerName;
        this.vehicleNumber = vehicleNumber.toUpperCase(); // store in caps always
        this.type = type;
        this.isRegistered = true;
    }

    // --- Getters ---
    public String getVehicleId()     { return vehicleId; }
    public String getOwnerName()     { return ownerName; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getType()          { return type; }
    public boolean isRegistered()    { return isRegistered; }

    // --- Setters (only ones you might need) ---
    public void setOwnerName(String ownerName)     { this.ownerName = ownerName; }
    public void setRegistered(boolean registered)  { this.isRegistered = registered; }

    // --- For saving to file (comma separated) ---
    public String toFileString() {
        return vehicleId + "," + ownerName + "," + vehicleNumber + "," + type;
    }

    // --- For printing on screen ---
    @Override
    public String toString() {
        return "ID: " + vehicleId +
               " | Owner: " + ownerName +
               " | Number: " + vehicleNumber +
               " | Type: " + type;
    }
}