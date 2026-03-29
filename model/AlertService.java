public class AlertService {

    private static final long OVERSTAY_LIMIT = 480; // 8 hours in minutes
    private static final long FINE_PER_HOUR  = 50;  // Rs.50 per extra hour

    private EntryExitService entryExitService;

    public AlertService(EntryExitService entryExitService) {
        this.entryExitService = entryExitService;
    }

    // Check overstayed vehicles
    public void checkOverstay() {
        System.out.println("\n========== OVERSTAY CHECK ==========");
        boolean found = false;

        for (ParkingRecord record : entryExitService.getActiveRecords().values()) {
            long duration = record.getDurationInMinutes();
            if (duration > OVERSTAY_LIMIT) {
                found = true;
                long fine = calculateFine(duration);
                System.out.println("OVERSTAY ALERT!");
                System.out.println("  Vehicle : " + record.getVehicle().getVehicleNumber());
                System.out.println("  Owner   : " + record.getVehicle().getOwnerName());
                System.out.println("  Slot    : " + record.getSlot().getSlotId());
                System.out.println("  Parked  : " + record.getReadableDuration());
                System.out.println("  Fine    : Rs." + fine);
                System.out.println("------------------------------------");
            }
        }

        if (!found) {
            System.out.println("No overstayed vehicles. All clear!");
        }
        System.out.println("=====================================");
    }

    // Calculate fine amount
    public long calculateFine(long durationMinutes) {
        if (durationMinutes <= OVERSTAY_LIMIT) return 0;
        long extraMinutes = durationMinutes - OVERSTAY_LIMIT;
        long extraHours   = (long) Math.ceil(extraMinutes / 60.0);
        return extraHours * FINE_PER_HOUR;
    }
}
