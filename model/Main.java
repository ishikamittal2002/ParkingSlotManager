import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehicle> registeredVehicles = new ArrayList<>();
    static SlotManager slotManager               = new SlotManager(20, 10);
    static EntryExitService entryExitService      = new EntryExitService(slotManager);
    static AlertService alertService              = new AlertService(entryExitService);
    static int vehicleCounter = 1;

    public static void main(String[] args) {

        // Load saved vehicles on startup
        registeredVehicles = FileHandler.loadVehicles();
        if (!registeredVehicles.isEmpty()) {
            vehicleCounter = registeredVehicles.size() + 1;
        }

        System.out.println("==========================================");
        System.out.println("   WELCOME TO PARKING SLOT MANAGER");
        System.out.println("==========================================");

        int choice = 0;
        while (choice != 8) {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> registerVehicle();
                case 2 -> vehicleEntry();
                case 3 -> vehicleExit();
                case 4 -> slotManager.displayAllSlots();
                case 5 -> entryExitService.displayActiveRecords();
                case 6 -> alertService.checkOverstay();
                case 7 -> entryExitService.displayAllRecords();
                case 8 -> {
                    FileHandler.saveVehicles(registeredVehicles);
                    System.out.println("\nGoodbye! Data saved.");
                }
                default -> System.out.println("Invalid choice. Enter 1-8.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Register Vehicle");
        System.out.println("2. Vehicle Entry");
        System.out.println("3. Vehicle Exit");
        System.out.println("4. View All Slots");
        System.out.println("5. View Active Parkings");
        System.out.println("6. Check Overstayed Vehicles");
        System.out.println("7. View Today's Log");
        System.out.println("8. Save & Exit");
        System.out.println("================================");
        System.out.print("Enter choice: ");
    }

    static void registerVehicle() {
        System.out.println("\n--- Register New Vehicle ---");
        System.out.print("Owner Name      : ");
        String name = sc.nextLine().trim();

        System.out.print("Vehicle Number  : ");
        String number = sc.nextLine().trim().toUpperCase();

        // Check duplicate
        for (Vehicle v : registeredVehicles) {
            if (v.getVehicleNumber().equals(number)) {
                System.out.println("ERROR: Vehicle " + number + " already registered!");
                return;
            }
        }

        System.out.println("Vehicle Type:");
        System.out.println("  1. Two-Wheeler");
        System.out.println("  2. Four-Wheeler");
        System.out.print("Enter choice: ");
        String typeInput = sc.nextLine().trim();
        String type = typeInput.equals("1") ? "TWO_WHEELER" : "FOUR_WHEELER";

        String id = String.format("V%03d", vehicleCounter++);
        Vehicle v = new Vehicle(id, name, number, type);
        registeredVehicles.add(v);

        System.out.println("\nSUCCESS: Vehicle Registered!");
        System.out.println("  " + v);
    }

    static void vehicleEntry() {
        System.out.println("\n--- Vehicle Entry ---");
        System.out.print("Enter Vehicle Number: ");
        String number = sc.nextLine().trim().toUpperCase();

        // Find vehicle in registered list
        Vehicle found = null;
        for (Vehicle v : registeredVehicles) {
            if (v.getVehicleNumber().equals(number)) {
                found = v;
                break;
            }
        }

        if (found == null) {
            System.out.println("WARNING: Unregistered vehicle - " + number);
            System.out.println("Please register the vehicle first (Option 1).");
            return;
        }

        System.out.println(entryExitService.vehicleEntry(found));
    }

    static void vehicleExit() {
        System.out.println("\n--- Vehicle Exit ---");
        System.out.print("Enter Vehicle Number: ");
        String number = sc.nextLine().trim().toUpperCase();
        System.out.println(entryExitService.vehicleExit(number));
    }
}

