import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String VEHICLES_FILE = "../data/vehicles.txt";

    // Save all vehicles to file
    public static void saveVehicles(ArrayList<Vehicle> vehicles) {
        // Create data folder if not exists
        new File("../data").mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLES_FILE))) {
            for (Vehicle v : vehicles) {
                writer.write(v.toFileString());
                writer.newLine();
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load vehicles from file
    public static ArrayList<Vehicle> loadVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        File file = new File(VEHICLES_FILE);

        if (!file.exists()) {
            System.out.println("No saved data found. Starting fresh.");
            return vehicles;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    vehicles.add(new Vehicle(parts[0], parts[1], parts[2], parts[3]));
                }
            }
            System.out.println("Loaded " + vehicles.size() + " vehicles from saved data.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        return vehicles;
    }
}
