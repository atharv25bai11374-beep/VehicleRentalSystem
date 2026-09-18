import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static VehicleRentalSystem system = new VehicleRentalSystem();

    public static void main(String[] args) {

        while (true) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerCustomer();
                    break;

                case 2:
                    system.displayAvailableVehicles();
                    break;

                case 3:
                    rentVehicle();
                    break;

                case 4:
                    returnVehicle();
                    break;

                case 5:
                    system.displayAllRentals();
                    break;

                case 6:
                    searchVehicle();
                    break;

                case 7:
                    System.out.println(
                        "\nThank you for using Vehicle Rental System!"
                    );

                    scanner.close();
                    return;

                case 8:
                    adminMenu();
                    break;

                default:
                    System.out.println(
                        "\nInvalid choice. Please try again."
                    );
            }
        }
    }

    // ==================== MAIN MENU ====================

    private static void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println("       VEHICLE RENTAL MANAGEMENT");
        System.out.println("==========================================");
        System.out.println("1. Register Customer");
        System.out.println("2. View Available Vehicles");
        System.out.println("3. Rent a Vehicle");
        System.out.println("4. Return a Vehicle");
        System.out.println("5. View Rental Records");
        System.out.println("6. Search Vehicle");
        System.out.println("7. Exit");
        System.out.println("8. Admin Menu");
        System.out.println("==========================================");
    }

    // ==================== CUSTOMER ====================

    private static void registerCustomer() {

        System.out.println(
            "\n========== CUSTOMER REGISTRATION =========="
        );

        String id = readString("Enter Customer ID: ");

        if (system.findCustomer(id) != null) {

            System.out.println(
                "Customer ID already exists."
            );

            return;
        }

        String name = readString("Enter Name: ");
        String phone = readString("Enter Phone: ");
        String email = readString("Enter Email: ");

        Customer customer = new Customer(
            id,
            name,
            phone,
            email
        );

        system.addCustomer(customer);

        System.out.println(
            "\nCustomer registered successfully!"
        );
    }

    // ==================== RENTAL ====================

    private static void rentVehicle() {

        System.out.println(
            "\n========== RENT VEHICLE =========="
        );

        String customerId =
            readString("Enter Customer ID: ");

        String vehicleId =
            readString("Enter Vehicle ID: ");

        int days =
            readInt("Enter number of days: ");

        String rentalId =
            "R" + System.currentTimeMillis();

        system.createRental(
            rentalId,
            customerId,
            vehicleId,
            days
        );
    }

    private static void returnVehicle() {

        System.out.println(
            "\n========== RETURN VEHICLE =========="
        );

        String rentalId =
            readString("Enter Rental ID: ");

        system.returnVehicle(rentalId);
    }

    // ==================== SEARCH ====================

    private static void searchVehicle() {

        System.out.println(
            "\n========== SEARCH VEHICLE =========="
        );

        String vehicleId =
            readString("Enter Vehicle ID: ");

        Vehicle vehicle =
            system.findVehicle(vehicleId);

        if (vehicle == null) {

            System.out.println(
                "Vehicle not found."
            );

        } else {

            vehicle.displayDetails();
        }
    }

    // ==================== ADMIN ====================

    private static void adminMenu() {

        System.out.println(
            "\n========== ADMIN LOGIN =========="
        );

        String username =
            readString("Enter admin username: ");

        String password =
            readString("Enter admin password: ");

        if (!username.equals("admin")
                || !password.equals("admin123")) {

            System.out.println(
                "Invalid admin credentials."
            );

            return;
        }

        System.out.println(
            "\nAdmin login successful!"
        );

        while (true) {

            System.out.println(
                "\n========== ADMIN MENU =========="
            );

            System.out.println("1. View All Vehicles");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("4. View All Customers");
            System.out.println("5. View Rental Records");
            System.out.println("6. Back to Main Menu");
            System.out.println(
                "==============================="
            );

            int choice =
                readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    system.displayAllVehicles();
                    break;

                case 2:
                    addVehicle();
                    break;

                case 3:
                    removeVehicle();
                    break;

                case 4:
                    system.displayAllCustomers();
                    break;

                case 5:
                    system.displayAllRentals();
                    break;

                case 6:
                    return;

                default:
                    System.out.println(
                        "Invalid choice."
                    );
            }
        }
    }

    // ==================== ADD VEHICLE ====================

    private static void addVehicle() {

        System.out.println(
            "\n========== ADD VEHICLE =========="
        );

        String type =
            readString(
                "Enter vehicle type (car/bike): "
            );

        String id =
            readString("Enter vehicle ID: ");

        String brand =
            readString("Enter brand: ");

        String model =
            readString("Enter model: ");

        double price =
            readDouble(
                "Enter rental price per day: "
            );

        Vehicle vehicle;

        if (type.equalsIgnoreCase("car")) {

            int seats =
                readInt("Enter number of seats: ");

            vehicle = new Car(
                id,
                brand,
                model,
                price,
                seats
            );

        } else if (type.equalsIgnoreCase("bike")) {

            int engineCC =
                readInt(
                    "Enter engine capacity (cc): "
                );

            vehicle = new Bike(
                id,
                brand,
                model,
                price,
                engineCC
            );

        } else {

            System.out.println(
                "Invalid vehicle type."
            );

            return;
        }

        system.addVehicle(vehicle);
    }

    // ==================== REMOVE VEHICLE ====================

    private static void removeVehicle() {

        System.out.println(
            "\n========== REMOVE VEHICLE =========="
        );

        String vehicleId =
            readString("Enter vehicle ID: ");

        system.removeVehicle(vehicleId);
    }

    // ==================== INPUT METHODS ====================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value =
                    Integer.parseInt(
                        scanner.nextLine()
                    );

                return value;

            } catch (NumberFormatException e) {

                System.out.println(
                    "Please enter a valid number."
                );
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                double value =
                    Double.parseDouble(
                        scanner.nextLine()
                    );

                if (value < 0) {

                    System.out.println(
                        "Value cannot be negative."
                    );

                    continue;
                }

                return value;

            } catch (NumberFormatException e) {

                System.out.println(
                    "Please enter a valid number."
                );
            }
        }
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }
}