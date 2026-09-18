public class Rental {

    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int numberOfDays;
    private double totalCost;
    private boolean returned;

    public Rental(String rentalId, Customer customer,
                  Vehicle vehicle, int numberOfDays) {

        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.numberOfDays = numberOfDays;
        this.totalCost = vehicle.calculateRentalCost(numberOfDays);
        this.returned = false;

        vehicle.setAvailable(false);
    }

    public String getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isReturned() {
        return returned;
    }

    public void returnVehicle() {
        if (!returned) {
            returned = true;
            vehicle.setAvailable(true);
        }
    }

    public void displayRentalDetails() {
        System.out.println("----------------------------------------");
        System.out.println("Rental ID    : " + rentalId);
        System.out.println("Customer     : " + customer.getName());
        System.out.println("Vehicle      : " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("Days         : " + numberOfDays);
        System.out.println("Total Cost   : ₹" + totalCost);
        System.out.println("Status       : " + (returned ? "Returned" : "Active"));
        System.out.println("----------------------------------------");
    }
}