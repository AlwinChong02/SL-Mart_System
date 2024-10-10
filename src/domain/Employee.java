package domain;

public class Employee extends Admin {

    public Employee() {
    }
    public Employee(String userID, String name, String password) {
        super(userID, name, password); //super to Admin
    }
}
