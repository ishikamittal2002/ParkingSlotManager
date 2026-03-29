public class ParkingSlot {

    // --- Fields ---
    private String slotId;              // "T001" = two-wheeler, "F001" = four-wheeler
    private String slotType;            // "TWO_WHEELER" or "FOUR_WHEELER"
    private boolean isOccupied;         // is someone parked here?
    private String assignedVehicleId;   // which vehicle is parked here (null if free)

    // --- Constructor ---
    public ParkingSlot(String slotId, String slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isOccupied = false;        // empty by default
        this.assignedVehicleId = null;  // no vehicle by default
    }

    // --- Occupy this slot (vehicle enters) ---
    public void occupy(String vehicleId) {
        this.isOccupied = true;
        this.assignedVehicleId = vehicleId;
    }

    // --- Vacate this slot (vehicle exits) ---
    public void vacate() {
        this.isOccupied = false;
        this.assignedVehicleId = null;
    }

    // --- Getters ---
    public String getSlotId()             { return slotId; }
    public String getSlotType()           { return slotType; }
    public boolean isOccupied()           { return isOccupied; }
    public String getAssignedVehicleId()  { return assignedVehicleId; }

    // --- For printing on screen ---
    @Override
    public String toString() {
        String status;
        if (isOccupied) {
            status = "🔴 OCCUPIED (Vehicle: " + assignedVehicleId + ")";
        } else {
            status = "🟢 FREE";
        }
        return "Slot: " + slotId + " | Type: " + slotType + " | " + status;
    }
}
