package app;

import domain.*;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements IAuthentication {

    private Controller controller;
    private Scanner scanner;
    private User currentUser;

    public ConsoleUI(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            //main menu
            System.out.println("Welcome to SL Inventory Management System");
            System.out.println("1. Login/SignUp");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void login() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean isSuccess = controller.loginUser(userId, password);
        if (isSuccess) {
            System.out.println("Login successful.");
            currentUser = controller.findUserById(userId);
            if (currentUser instanceof Admin) { //Manager and Employee are subclass of Admin
                displayAdminMenu();
            } else if(currentUser instanceof Customer){
                displayCustomerMenu();
            } 
        } else {
            System.out.println("Login failed. Please try again.");

            System.out.println("Are you a new user? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                signUp();
            } else 
                return;
        }
    }

    @Override
    public void signUp() {
        System.out.println("========================");
        System.out.println("\tSign Up");
        System.out.println("========================");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNo = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        boolean isSuccess = controller.signUpUser(userId, name, email, password, phoneNo, address);
        if (isSuccess) {
            System.out.println("Sign Up successful.");
        } else {
            System.out.println("Sign Up failed. Please try again.");
        }
    }

    private void displayAdminMenu() {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add New Product");
            System.out.println("2. Update Inventory");
            System.out.println("3. Update Order");
            System.out.println("4. Generate Inventory and Sales Report");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewProductUI();
                    break;
                case 2:
                    updateInventoryUI();
                    break;
                case 3:
                    updateOrderUI();
                    break;
                case 4:
                    System.out.println(controller.generateInventoryAndSalesReport(currentUser, controller.getDataLists()));
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void updateInventoryUI() {
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean isSuccess = controller.updateInventory(productId, quantity);
        if (isSuccess) {
            System.out.println("Inventory updated successfully.");
        } else {
            System.out.println("Failed to update the inventory.");
        }
    }

    private void displayCustomerMenu() {
        while (true) {
            System.out.println("\nCustomer Menu");
            System.out.println("1. Make Order");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeOrderUI();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return; // Exit the method
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void addNewProductUI() { 
        System.out.println("Add New Product");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        scanner.nextLine(); // Consume newline

        boolean isSuccess = controller.addProductToInventory(id,name,quantity,price);
        if (isSuccess) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add the product.");
        }
    }

    private void updateOrderUI() {
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean isSuccess = controller.updateOrder(orderId, productId, quantity);
        if (isSuccess) {
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("Failed to update the order.");
        }
    }

    private void makeOrderUI() {
        System.out.println("\nAvailable Products:");
        List<Product> products = controller.getProducts();  //get all products

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %s - Price: %.2f, Quantity Available: %d\n", (i + 1), product.getName(),
                    product.getPrice(), product.getQuantity()); //display all products
        }

        // user will enter the product number to order the product or exit the order menu
        System.out.println("\nEnter the product selection you want to order or 0 to finish:");
        int choice;
        Order order = controller.createOrder(currentUser.getUserId()); 

        while ((choice = scanner.nextInt()) != 0) {
            if (choice < 1 || choice > products.size()) {
                System.out.println("Invalid product number. Please try again.");
                continue;
            }

            Product selectedProduct = products.get(choice - 1);
            System.out.println("You selected: " + selectedProduct.getName());
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity > selectedProduct.getQuantity()) {
                System.out.println("Not enough stock available. Please try another product: ");
                continue;
            }

            // user will enter the product number to order the product or complete the order
            controller.addProductToOrder(order, selectedProduct.getProductId(), quantity);
            System.out.println(quantity + " x " + selectedProduct.getName() + " added to your order.");
            System.out.println("Total price: " + order.getTotalPrice());
            System.out.println("\nEnter the number of another product you want to order or 0 to finish:");
        }

        scanner.nextLine(); // Consume newline
        if (order.getProducts().isEmpty()) {
            System.out.println("No products ordered. Exiting order menu.");
        } else {
            System.out.println("Order completed. Total price: " + order.getTotalPrice());
            makePayment(order.getTotalPrice(),order.getOrderId());
        }
    }

    private void makePayment(double amount, String orderId) {
        System.out.println("Please select a payment method for your order:");
        System.out.println("1. EWallet");
        System.out.println("2. Card");
        System.out.print("Enter choice: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Payment payment;

        switch (paymentChoice) {
            case 1:
                System.out.print("Enter EWallet Owner ID: ");
                String ownerId = scanner.nextLine();
                payment = controller.makePayment(amount, ownerId);
                break;
            case 2:
                System.out.print("Enter Card Number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter Card Holder Name: ");
                String cardHolderName = scanner.nextLine();
                payment = controller.makePayment(amount, cardNumber, cardHolderName);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        controller.showBillingStatement(payment);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

}
