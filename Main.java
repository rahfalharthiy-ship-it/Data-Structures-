import java.util.Scanner;

// Command-line menu: the only class that reads input and prints output.
public class Main {

    private static Scanner input = new Scanner(System.in);
    private static RideSharingSystem system = new RideSharingSystem();

    public static void main(String[] args) {
        // Load the three CSV files before showing the menu
        system.loadRidersFromCSV("riders_100.csv");
        system.loadDriversFromCSV("drivers_30.csv");
        system.loadRidesFromCSV("rides_40.csv");

        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:   // List all riders
                    break;
                case 2:   // Search for riders by home city
                    break;
                case 3:   // Search for riders by name
                    break;
                case 4:   // Add a rider
                    break;
                case 5:   // List all drivers
                    break;
                case 6:   // Search for drivers by vehicle type
                    break;
                case 7:   // Add a driver
                    break;
                case 8:   // List all rides alphabetically by pickup location
                    break;
                case 9:   // Search for rides by pickup location
                    break;
                case 10:  // Search for rides by rider name
                    break;
                case 11:  // List the participants of a shared ride by pickup location
                    break;
                case 12:  // Add a ride (private or shared)
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);
    }

    // Prints the menu options in the required order
    private static void printMenu() {
        System.out.println();
        System.out.println("========== Ride-Sharing System ==========");
        System.out.println("1. List all riders.");
        System.out.println("2. Search for riders by home city.");
        System.out.println("3. Search for riders by name.");
        System.out.println("4. Add a rider.");
        System.out.println("5. List all drivers.");
        System.out.println("6. Search for drivers by vehicle type.");
        System.out.println("7. Add a driver.");
        System.out.println("8. List all rides alphabetically by pickup location.");
        System.out.println("9. Search for rides by pickup location.");
        System.out.println("10. Search for rides by rider name.");
        System.out.println("11. List the participants of a shared ride by pickup location.");
        System.out.println("12. Add a ride.");
        System.out.println("0. Exit the program.");
    }

    // Prints every element's toString(), one per line, or a no-results message
    private static <T> void printList(LinkedList<T> list) {
        if (list == null || list.empty()) {
            System.out.println("No results found.");
            return;
        }
        list.findFirst();
        while (true) {
            System.out.println(list.retrieve());
            if (list.last())
                break;
            list.findNext();
        }
    }

    // Reads a whole number; asks again if the input is not a number
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Reads a line of text
    private static String readText(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }
}
