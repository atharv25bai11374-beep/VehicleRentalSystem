public class Vehicle {

    private String vehicleId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean available;

    public Vehicle(String vehicleId, String brand, String model, double pricePerDay) {

        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double calculateRentalCost(int days) {
        return pricePerDay * days;
    }

    public void displayDetails() {
        System.out.println(
            "ID: " + vehicleId +
            " | " + brand + " " + model +
            " | ₹" + pricePerDay + "/day" +
            " | " + (available ? "Available" : "Rented")
        );
    }
}