public class TestModels {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("===== Testing Vehicle =====");
        Vehicle v = new Vehicle("V001", "Rahul Sharma", "MP09AB1234", "TWO_WHEELER");
        System.out.println(v);
        System.out.println("File format: " + v.toFileString());

        System.out.println("\n===== Testing ParkingSlot =====");
        ParkingSlot slot = new ParkingSlot("T001", "TWO_WHEELER");
        System.out.println(slot); // should show FREE

        slot.occupy("V001");
        System.out.println(slot); // should show OCCUPIED

        slot.vacate();
        System.out.println(slot); // should show FREE again

        System.out.println("\n===== Testing ParkingRecord =====");
        ParkingSlot slot2 = new ParkingSlot("T002", "TWO_WHEELER");
        slot2.occupy("V001");

        ParkingRecord record = new ParkingRecord("R001", v, slot2);
        System.out.println(record); // should show entry time, Still Parked

        Thread.sleep(2000); // wait 2 seconds to simulate time passing

        record.closeRecord();
        System.out.println(record); // should show exit time and duration
    }
}