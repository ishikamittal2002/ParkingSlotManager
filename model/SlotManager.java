import java.util.HashMap;
import java.util.ArrayList;

public class SlotManager {

    private HashMap<String, ParkingSlot> allSlots = new HashMap<>();

    // Constructor — creates all slots at startup
    public SlotManager(int twoWheelerCount, int fourWheelerCount) {
        for (int i = 1; i <= twoWheelerCount; i++) {
            String id = String.format("T%03d", i);
            allSlots.put(id, new ParkingSlot(id, "TWO_WHEELER"));
        }
        for (int i = 1; i <= fourWheelerCount; i++) {
            String id = String.format("F%03d", i);
            allSlots.put(id, new ParkingSlot(id, "FOUR_WHEELER"));
        }
    }

    // Find first free slot for given vehicle type
    public ParkingSlot getAvailableSlot(String vehicleType) {
        for (ParkingSlot slot : allSlots.values()) {
            if (slot.getSlotType().equals(vehicleType) && !slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }

    // Assign slot to vehicle
    public boolean assignSlot(String slotId, String vehicleId) {
        ParkingSlot slot = allSlots.get(slotId);
        if (slot != null && !slot.isOccupied()) {
            slot.occupy(vehicleId);
            return true;
        }
        return false;
    }

    // Free up a slot
    public boolean freeSlot(String slotId) {
        ParkingSlot slot = allSlots.get(slotId);
        if (slot != null && slot.isOccupied()) {
            slot.vacate();
            return true;
        }
        return false;
    }

    // Count free slots by type
    public int countFreeSlots(String vehicleType) {
        int count = 0;
        for (ParkingSlot slot : allSlots.values()) {
            if (slot.getSlotType().equals(vehicleType) && !slot.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    // Display all slots
    public void displayAllSlots() {
        System.out.println("\n========== ALL PARKING SLOTS ==========");
        System.out.println("--- TWO WHEELERS ---");
        for (ParkingSlot slot : allSlots.values()) {
            if (slot.getSlotType().equals("TWO_WHEELER")) {
                System.out.println(slot);
            }
        }
        System.out.println("--- FOUR WHEELERS ---");
        for (ParkingSlot slot : allSlots.values()) {
            if (slot.getSlotType().equals("FOUR_WHEELER")) {
                System.out.println(slot);
            }
        }
        System.out.println("Two-Wheeler Free : " + countFreeSlots("TWO_WHEELER"));
        System.out.println("Four-Wheeler Free: " + countFreeSlots("FOUR_WHEELER"));
        System.out.println("========================================");
    }

    public HashMap<String, ParkingSlot> getAllSlots() { return allSlots; }
}