package domain;

import java.util.ArrayList;
import java.util.List;

public class DataLists implements IDataStore{
    private List<User> users;
    private List<Product> products;
    private List<Order> orders;

    public DataLists() {
        this.users = new ArrayList<User>();
        this.products = new ArrayList<Product>();
        this.orders = new ArrayList<Order>();
    }

    // Add a new user
    public void addUser(User user) {
        users.add(user);
    }
    // Add a new product
    public void addProduct(Product product) {
        products.add(product);
    }
    // Add a new order
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Retrieve all users
    public List<User> getUsers() {
        return users;
    }
    // Retrieve all products
    public List<Product> getProducts() {
        return products;
    }
    // Retrieve all orders
    public List<Order> getOrders() {
        return orders;
    }

    // Find a user by userId
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null; // User not found
    }

    // Find an product by productId
    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Product not found
    }

    public Order findOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null; // Order not found
    }

    public void preloadTestData() {
        // Preloading some test users

        // Customer( userId,  name,  email,  password,  phoneNo,  address)
        addUser(new Customer("customer01", "Albert Tom", "customer@yahoo.com", "albert123", "1234567890", "address1"));
        addUser(new Customer("customer02", "Jerry Lim", "customer@gmail.com", "jerry021", "1234567890", "address1234"));
        
        // Manager( userId,  name, password)
        addUser(new Manager("manager01", "John Doe","john123"));

        // Employee( userId,  name, password)
        addUser(new Employee("employee01", "KMX","kmx123"));
        addUser(new Employee("employee02", "Steve","steve456"));
        addUser(new Employee("employee03", "Kenny","jane789"));
        
        
        // Preloading some test products
        // Product( productId,  name,  quantity,  price)
        addProduct(new Product("p001", "Apple", 100, 1.0));
        addProduct(new Product("p002", "Banana", 200, 0.5));
        addProduct(new Product("p003", "Orange", 150, 0.75));
        addProduct(new Product("p004", "Grapes", 120, 2.0));
        
    }
}
