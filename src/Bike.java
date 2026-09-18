public class Bike extends Vehicle {

    private int engineCC;

    public Bike(String vehicleId, String brand, String model,
                double pricePerDay, int engineCC) {

        super(vehicleId, brand, model, pricePerDay);
        this.engineCC = engineCC;
    }

    public int getEngineCC() {
        return engineCC;
    }

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