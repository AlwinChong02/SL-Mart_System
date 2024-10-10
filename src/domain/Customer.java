package domain;


public class Customer extends User{
    private String address;

    public Customer() {

    }
    public Customer(String userId, String name, String email, String password, String phoneNo, String address) {
        super(userId, name, email, password, phoneNo);
        this.address = address;

        
    }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }



}