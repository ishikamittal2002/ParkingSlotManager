import java.util.HashMap;
import java.util.ArrayList;

public class EntryExitService {

    // Active parkings: vehicleNumber -> ParkingRecord
    private HashMap<String, ParkingRecord> activeRecords = new HashMap<>();

    // All records including exited ones
    private ArrayList<ParkingRecord> allRecords = new ArrayList<>();

    private SlotManager slotManager;
    private int recordCounter = 1;

    public EntryExitService(SlotManager slotManager) {
        this.slotManager = slotManager;
    }

    // Vehicle Entry
    public String vehicleEntry(Vehicle vehicle) {
        // Already parked?
        if (activeRecords.containsKey(vehicle.getVehicleNumber())) {
            return "ERROR: Vehicle " + vehicle.getVehicleNumber() + " is already parked!";
        }

        // Find available slot
        ParkingSlot slot = slotManager.getAvailableSlot(vehicle.getType());
        if (slot == null) {
            return "ERROR: No slots available for " + vehicle.getType();
        }

        // Create record
        String recordId = String.format("R%03d", recordCounter++);
        ParkingRecord record = new ParkingRecord(recordId, vehicle, slot);

        // Assign slot
        slotManager.assignSlot(slot.getSlotId(), vehicle.getVehicleId());

        // Save record
        activeRecords.put(vehicle.getVehicleNumber(), record);
        allRecords.add(record);

        return "SUCCESS: Entry logged!\n" +
               "  Slot Assigned : " + slot.getSlotId() + "\n" +
               "  Entry Time    : " + record.getEntryTime();
    }

    // Vehicle Exit
    public String vehicleExit(String vehicleNumber) {
        ParkingRecord record = activeRecords.get(vehicleNumber.toUpperCase());
        if (record == null) {
            return "ERROR: No active parking found for: " + vehicleNumber;
        }

        // Close record
        record.closeRecord();

        // Free the slot
        slotManager.freeSlot(record.getSlot().getSlotId());

        // Remove from active
        activeRecords.remove(vehicleNumber.toUpperCase());

        long fine = 0;
        if (record.getDurationInMinutes() > 480) {
            long extra = record.getDurationInMinutes() - 480;
            fine = (long) Math.ceil(extra / 60.0) * 50;
        }

        String result = "SUCCESS: Exit logged!\n" +
               "  Vehicle  : " + vehicleNumber + "\n" +
               "  Slot     : " + record.getSlot().getSlotId() + " is now FREE\n" +
               "  Duration : " + record.getReadableDuration();

        if (fine > 0) {
            result += "\n  OVERSTAY FINE: Rs." + fine;
        }

        return result;
    }

    // Display currently parked vehicles
    public void displayActiveRecords() {
        System.out.println("\n========== CURRENTLY PARKED ==========");
        if (activeRecords.isEmpty()) {
            System.out.println("No vehicles currently parked.");
        } else {
            for (ParkingRecord r : activeRecords.values()) {
                System.out.println(r);
            }
        }
        System.out.println("=======================================");
    }

    // Display full day log
    public void displayAllRecords() {
        System.out.println("\n========== TODAY'S FULL LOG ==========");
        if (allRecords.isEmpty()) {
            System.out.println("No records yet.");
        } else {
            for (ParkingRecord r : allRecords) {
                System.out.println(r);
            }
        }
        System.out.println("=======================================");
    }

    public HashMap<String, ParkingRecord> getActiveRecords() { return activeRecords; }
    public ArrayList<ParkingRecord> getAllRecords()           { return allRecords; }
}

