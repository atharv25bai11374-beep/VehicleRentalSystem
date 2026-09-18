
import java.io.*;
import java.util.ArrayList;

public class VehicleRentalSystem {

    ArrayList<Vehicle> vehicles;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    private final String DATA_FOLDER = "../data";
    private final String VEHICLE_FILE = DATA_FOLDER + "/vehicles.txt";
    private final String CUSTOMER_FILE = DATA_FOLDER + "/customers.txt";
    private final String RENTAL_FILE = DATA_FOLDER + "/rentals.txt";

    public VehicleRentalSystem() {

        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();

        createDataFolder();
        loadData();

        if (vehicles.isEmpty()) {
            loadSampleVehicles();
            saveVehicles();
        }
    }

    // ==================== DATA FOLDER ====================

    void createDataFolder() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // ==================== SAMPLE VEHICLES ====================

    void loadSampleVehicles() {

        vehicles.add(
            new Car("C001", "Toyota", "Camry", 2500, 5)
        );

        vehicles.add(
            new Car("C002", "Honda", "City", 2000, 5)
        );

        vehicles.add(
            new Car("C003", "Hyundai", "Creta", 2200, 5)
        );

        vehicles.add(
            new Bike("B001", "Yamaha", "R15", 1200, 155)
        );

        vehicles.add(
            new Bike("B002", "Royal Enfield", "Classic 350", 1500, 349)
        );

        vehicles.add(
            new Bike("B003", "KTM", "Duke 200", 1300, 199)
        );
    }

    // ==================== VEHICLES ====================

    public void displayAvailableVehicles() {

        System.out.println(
            "\n========== AVAILABLE VEHICLES =========="
        );

        boolean found = false;

        for (Vehicle vehicle : vehicles) {

            if (vehicle.isAvailable()) {
                vehicle.displayDetails();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                "No vehicles are currently available."
            );
        }
    }

    void displayAllVehicles() {

        System.out.println(
            "\n========== ALL VEHICLES =========="
        );

        if (vehicles.isEmpty()) {

            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
        }
    }

    public Vehicle findVehicle(String vehicleId) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleId()
                .equalsIgnoreCase(vehicleId)) {

                return vehicle;
            }
        }

        return null;
    }

    public boolean addVehicle(Vehicle vehicle) {

        if (findVehicle(vehicle.getVehicleId()) != null) {

            System.out.println(
                "Vehicle ID already exists."
            );

            return false;
        }

        vehicles.add(vehicle);
        saveVehicles();

        System.out.println(
            "Vehicle added "
        );

        return true;
    }

    public boolean removeVehicle(String vehicleId) {

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {

            System.out.println(
                "Vehicle not found."
            );

            return false;
        }

        if (!vehicle.isAvailable()) {

            System.out.println(
                "Cannot remove a vehicle that is currently rented."
            );

            return false;
        }

        vehicles.remove(vehicle);
        saveVehicles();

        System.out.println(
            "Vehicle removed successfully."
        );

        return true;
    }

    // ==================== CUSTOMERS ====================

    public void addCustomer(Customer customer) {

        customers.add(customer);
        saveCustomers();
    }

    Customer findCustomer(String customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId()
                .equalsIgnoreCase(customerId)) {

                return customer;
            }
        }

        return null;
    }

    public void displayAllCustomers() {

        System.out.println(
            "\n========== ALL CUSTOMERS =========="
        );

        if (customers.isEmpty()) {

            System.out.println(
                "No customers registered."
            );

            return;
        }

        for (Customer customer : customers) {
            customer.displayDetails();
        }
    }

    // ==================== RENTALS ====================

    boolean createRental(
        String rentalId,
        String customerId,
        String vehicleId,
        int days) {

        Customer customer = findCustomer(customerId);
        Vehicle vehicle = findVehicle(vehicleId);

        if (customer == null) {

            System.out.println(
                "Customer not found."
            );

            return false;
        }

        if (vehicle == null) {

            System.out.println(
                "Vehicle not found."
            );

            return false;
        }

        if (!vehicle.isAvailable()) {

            System.out.println(
                "Vehicle is already rented."
            );

            return false;
        }

        if (days <= 0) {

            System.out.println(
                "Number of days must be greater than zero."
            );

            return false;
        }

        Rental rental = new Rental(
            rentalId,
            customer,
            vehicle,
            days
        );

        rentals.add(rental);
        saveRentals();
        saveVehicles();

        System.out.println(
            "\nRental created successfully!"
        );

        rental.displayRentalDetails();

        return true;
    }

    void displayAllRentals() {

        System.out.println(
            "\n========== RENTAL RECORDS =========="
        );

        if (rentals.isEmpty()) {

            System.out.println(
                "No rental records found."
            );

            return;
        }

        for (Rental rental : rentals) {
            rental.displayRentalDetails();
        }
    }

    public boolean returnVehicle(String rentalId) {

        for (Rental rental : rentals) {

            if (rental.getRentalId()
                .equalsIgnoreCase(rentalId)) {

                if (rental.isReturned()) {

                    System.out.println(
                        "This vehicle has already been returned."
                    );

                    return false;
                }

                rental.returnVehicle();
                saveRentals();
                saveVehicles();

                System.out.println(
                    "Vehicle returned successfully."
                );

                return true;
            }
        }

        System.out.println(
            "Rental ID not found."
        );

        return false;
    }

    // ==================== SAVE VEHICLES ====================

    private void saveVehicles() {

        try (
            PrintWriter writer =
            new PrintWriter(
                new FileWriter(VEHICLE_FILE)
            )
        ) {

            for (Vehicle vehicle : vehicles) {

                if (vehicle instanceof Car) {

                    Car car = (Car) vehicle;

                    writer.println(
                        "CAR|" +
                        car.getVehicleId() + "|" +
                        car.getBrand() + "|" +
                        car.getModel() + "|" +
                        car.getPricePerDay() + "|" +
                        car.getNumberOfSeats() + "|" +
                        car.isAvailable()
                    );

                } else if (vehicle instanceof Bike) {

                    Bike bike = (Bike) vehicle;

                    writer.println(
                        "BIKE|" +
                        bike.getVehicleId() + "|" +
                        bike.getBrand() + "|" +
                        bike.getModel() + "|" +
                        bike.getPricePerDay() + "|" +
                        bike.getEngineCC() + "|" +
                        bike.isAvailable()
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                "Error saving vehicle data."
            );
        }
    }

    // ==================== SAVE CUSTOMERS ====================

    void saveCustomers() {

        try (
            PrintWriter writer =
            new PrintWriter(
                new FileWriter(CUSTOMER_FILE)
            )
        ) {

            for (Customer customer : customers) {

                writer.println(
                    customer.getCustomerId() + "|" +
                    customer.getName() + "|" +
                    customer.getPhone() + "|" +
                    customer.getEmail()
                );
            }

        } catch (IOException e) {

            System.out.println(
                "Error saving customer data."
            );
        }
    }

    // ==================== SAVE RENTALS ====================

    private void saveRentals() {

        try (
            PrintWriter writer =
            new PrintWriter(
                new FileWriter(RENTAL_FILE)
            )
        ) {

            for (Rental rental : rentals) {

                writer.println(
                    rental.getRentalId() + "|" +
                    rental.getCustomer().getCustomerId() + "|" +
                    rental.getVehicle().getVehicleId() + "|" +
                    rental.getNumberOfDays() + "|" +
                    rental.isReturned()
                );
            }

        } catch (IOException e) {

            System.out.println(
                "Error saving rental data."
            );
        }
    }

    // ==================== LOAD DATA ====================

    void loadData() {

        loadVehicles();
        loadCustomers();
        loadRentals();
    }

    private void loadVehicles() {

        File file = new File(VEHICLE_FILE);

        if (!file.exists()) {
            return;
        }

        try (
            BufferedReader reader =
            new BufferedReader(
                new FileReader(file)
            )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length < 7) {
                    continue;
                }

                String type = data[0];
                String id = data[1];
                String brand = data[2];
                String model = data[3];
                double price = Double.parseDouble(data[4]);

                boolean available =
                    Boolean.parseBoolean(data[6]);

                if (type.equals("CAR")) {

                    int seats =
                        Integer.parseInt(data[5]);

                    Car car = new Car(
                        id,
                        brand,
                        model,
                        price,
                        seats
                    );

                    car.setAvailable(available);
                    vehicles.add(car);

                } else if (type.equals("BIKE")) {

                    int engineCC =
                        Integer.parseInt(data[5]);

                    Bike bike = new Bike(
                        id,
                        brand,
                        model,
                        price,
                        engineCC
                    );

                    bike.setAvailable(available);
                    vehicles.add(bike);
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Error loading vehicle data."
            );
        }
    }

    private void loadCustomers() {

        File file = new File(CUSTOMER_FILE);

        if (!file.exists()) {
            return;
        }

        try (
            BufferedReader reader =
            new BufferedReader(
                new FileReader(file)
            )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length < 4) {
                    continue;
                }

                Customer customer = new Customer(
                    data[0],
                    data[1],
                    data[2],
                    data[3]
                );

                customers.add(customer);
            }

        } catch (Exception e) {

            System.out.println(
                "Error loading customer data."
            );
        }
    }

    private void loadRentals() {

        File file = new File(RENTAL_FILE);

        if (!file.exists()) {
            return;
        }

        try (
            BufferedReader reader =
            new BufferedReader(
                new FileReader(file)
            )
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length < 5) {
                    continue;
                }

                String rentalId = data[0];
                String customerId = data[1];
                String vehicleId = data[2];

                int days =
                    Integer.parseInt(data[3]);

                boolean returned =
                    Boolean.parseBoolean(data[4]);

                Customer customer =
                    findCustomer(customerId);

                Vehicle vehicle =
                    findVehicle(vehicleId);

                if (customer != null && vehicle != null) {

                    Rental rental = new Rental(
                        rentalId,
                        customer,
                        vehicle,
                        days
                    );

                    if (returned) {
                        rental.returnVehicle();
                    }

                    rentals.add(rental);
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Error loading rental data."
            );
        }
    }
}

