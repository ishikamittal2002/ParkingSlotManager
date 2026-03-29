import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class ParkingRecord {

    // --- Fields ---
    private String recordId;             // R001, R002...
    private Vehicle vehicle;             // which vehicle
    private ParkingSlot slot;            // which slot
    private LocalDateTime entryTime;     // when it entered
    private LocalDateTime exitTime;      // when it left (null if still parked)
    private boolean isActive;            // true = still parked

    // Formatter for readable time display
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    // --- Constructor (called when vehicle ENTERS) ---
    public ParkingRecord(String recordId, Vehicle vehicle, ParkingSlot slot) {
        this.recordId = recordId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = LocalDateTime.now(); // capture current time
        this.exitTime = null;
        this.isActive = true;
    }

    // --- Called when vehicle EXITS ---
    public void closeRecord() {
        this.exitTime = LocalDateTime.now();
        this.isActive = false;
    }

    // --- How long has the vehicle been parked? (in minutes) ---
    public long getDurationInMinutes() {
        LocalDateTime end = (exitTime != null) ? exitTime : LocalDateTime.now();
        return Duration.between(entryTime, end).toMinutes();
    }

    // --- How long in readable format: "2 hrs 15 mins" ---
    public String getReadableDuration() {
        long totalMinutes = getDurationInMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        if (hours > 0) {
            return hours + " hr " + minutes + " min";
        } else {
            return minutes + " min";
        }
    }

    // --- Getters ---
    public String getRecordId()          { return recordId; }
    public Vehicle getVehicle()          { return vehicle; }
    public ParkingSlot getSlot()         { return slot; }
    public LocalDateTime getEntryTime()  { return entryTime; }
    public LocalDateTime getExitTime()   { return exitTime; }
    public boolean isActive()            { return isActive; }

    // --- For printing on screen ---
    @Override
    public String toString() {
        String exit = (exitTime != null) ? exitTime.format(FORMATTER) : "Still Parked";
        return "Record: " + recordId +
               " | Vehicle: " + vehicle.getVehicleNumber() +
               " | Owner: " + vehicle.getOwnerName() +
               " | Slot: " + slot.getSlotId() +
               " | Entry: " + entryTime.format(FORMATTER) +
               " | Exit: " + exit +
               " | Duration: " + getReadableDuration();
    }
}