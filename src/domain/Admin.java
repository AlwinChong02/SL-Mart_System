package domain;


public class Admin extends User {

    public Admin() {
    }
    public Admin(String userID, String name, String password) {
        super(userID, name, null, password, null);
    }
}