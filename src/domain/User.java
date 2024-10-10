package domain;

public abstract class User {
    //data instance
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phoneNo;
    
    //constructor
    public User() {
    }
    public User(String userId, String name, String email, String password, String phoneNo) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    //getter 
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhoneNo() { return phoneNo; }

    //setter
    public void setUserId(String userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    // Verify login details
    public boolean verifyLoginDetails(String userId, String password) {
        return this.userId.equals(userId) && this.password.equals(password);
    }
}
