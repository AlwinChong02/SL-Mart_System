package domain;

import java.util.List;

public class Controller {
    private IDataStore dataLists;
    private int orderCount = 1;

    public Controller(IDataStore dataLists) {
        this.dataLists = dataLists;
    }

    public List<Product> getProducts() {
        return dataLists.getProducts();
    }

    public IDataStore getDataLists() {
        return dataLists;
    }

    private String generateOrderId() {
        String orderIdPrefix = "OR";
        String orderIdNumber = String.format("%03d", orderCount++);
        return orderIdPrefix + orderIdNumber;
    }

    public Order createOrder(String userId) {
        // Generate a unique order ID
        String orderId = generateOrderId();
        Order order = new Order(orderId, userId);
        dataLists.addOrder(order);
        return order;
    }

    public void addProductToOrder(Order order, String productId, int quantity) {
        Product product = dataLists.findProductById(productId); // find product by productId
        product.setQuantity(product.getQuantity() - quantity); // Reduce inventory quantity
        order.addProduct(new Product(productId, product.getName(), quantity, product.getPrice())); // Add product to order
    }

    public boolean loginUser(String userId, String password) {
        User user = dataLists.findUserById(userId);
        if (user != null && user.verifyLoginDetails(userId, password)) {
            return true;
        }
        return false;
    }

    public boolean signUpUser(String userId, String name, String email, String password, String phoneNo, String address) {
        User user = new Customer(userId, name, email, password, phoneNo, address);
        dataLists.addUser(user);
        return true;

    }

    public boolean addProductToInventory(String productId, String name, int quantity, double price) {
        Product product = new Product(productId, name, quantity, price);
        dataLists.addProduct(product);
        return true;
    }
    
    public boolean updateInventory(String productId, int quantity) {
        Product product = dataLists.findProductById(productId);
        if (product != null) {
            product.updateQuantity(quantity);
            return true;
        }
        return false;
    }

    public boolean updateOrder(String orderId, String productId, int newQuantity) {
        Order order = dataLists.findOrderById(orderId);
        if (order != null) {
            for (Product product : order.getProducts()) {
                if (product.getProductId().equals(productId)) {
                    // update the quantity of the product in the inventory
                    Product inventoryProduct = dataLists.findProductById(productId);
                    if (inventoryProduct != null) {
                        int diffQuantity = product.getQuantity() - newQuantity;
                        product.setQuantity(newQuantity); // Update new order quantity
                        inventoryProduct.setQuantity(inventoryProduct.getQuantity() + diffQuantity); // Update inventory
                        return true;
                    }
                }
            }
        }
        return false; // Order or product not found
    }
   
    public String generateInventoryAndSalesReport(User user, IDataStore dataLists) {
        if (user == null || !(user instanceof Manager)) {
            System.out.println("====================================================================");
            System.out.println("\tYou are not authorized to generate inventory report.");
            System.out.println("\t Please contact your manager for assistance.");
            System.out.println("====================================================================");
            
            return "";
        } else
            return ((Manager)user).generateInventoryAndSalesReport(user, dataLists);
    }

    public User findUserById(String userId) {
        return dataLists.findUserById(userId);
    }

    public Payment makePayment(double amount,String orderId ) {
        Payment payment = new EWallet(amount, orderId);
        payment.processPayment();

        return payment;
    }

    public Payment makePayment(double amount, String cardNumber, String cardHolder) {
        Payment payment = new Card(amount, cardNumber, cardHolder);
        payment.processPayment();

        return payment;
    }

    public void showBillingStatement(Payment payment) {
        payment.showBillingStatement();
    }
}
