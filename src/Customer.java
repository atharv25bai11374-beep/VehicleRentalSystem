
public class Customer {

    String customerId;
    private String name;
    private String phone;
    private String email;

    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void displayDetails() {
        System.out.println(
            "Customer ID: " + customerId +
            " | Name: " + name +
            " | Phone: " + phone +
            " | Email: " + email
        );
    }
}


