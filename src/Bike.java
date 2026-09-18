// This class is called Bike. It inherits from Vehicle.

// Bike adds a field for the engine size measured in cc.

public class Bike extends Vehicle {

    // Engine capacity in cc.
    int engineCC;

    // Constructor for Bike.
    // It takes the vehicle ID, brand, model, price and engine capacity.

    public Bike(String vehicleId, String brand, String model,
                double pricePerDay, int engineCC) {

        // Call the constructor of Vehicle to set fields.

        super(vehicleId, brand, model, pricePerDay);

        // Store the engine capacity for this Bike.

        this.engineCC = engineCC;
    }

    // Return the engine capacity of this Bike.

    public int getEngineCC() {

        return engineCC;
    }

    // Display all the details of this Bike, to the console.

    @Override
    public void displayDetails() {

        System.out.println(
            "Bike | ID: " + getVehicleId() +
            " | " + getBrand() + " " + getModel() +
            " | ₹" + getPricePerDay() + "/day" +
            " | Engine: " + engineCC + "cc" +
            " | " + (isAvailable() ? "Available" : "Rented")
        );
    }
}