package domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String userId;
    private List<Product> products;
    private double totalPrice;

    public Order(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // Add an product to the order
    public void addProduct(Product product) {
        products.add(product);
        calculateTotalPrice();
    }

    // Calculate total price of the order
    private void calculateTotalPrice() {
        totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        calculateTotalPrice(); // Ensure total price is always up to date
        return totalPrice;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
