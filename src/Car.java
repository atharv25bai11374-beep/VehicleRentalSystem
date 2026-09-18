public class Car extends Vehicle {

    private int numberOfSeats;

    public Car(String vehicleId, String brand, String model,
               double pricePerDay, int numberOfSeats) {

        super(vehicleId, brand, model, pricePerDay);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public void displayDetails() {
        System.out.println(
            "Car | ID: " + getVehicleId() +
            " | " + getBrand() + " " + getModel() +
            " | ₹" + getPricePerDay() + "/day" +
            " | Seats: " + numberOfSeats +
            " | " + (isAvailable() ? "Available" : "Rented")
        );
    }
}