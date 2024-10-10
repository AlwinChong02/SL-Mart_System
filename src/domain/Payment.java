package domain;

public abstract class Payment {
    private double amount;
    private boolean paymentStatus;

    // Constructor
    public Payment(double amount) {
        this.amount = amount;
        this.paymentStatus = false;
    }

    // Abstract method to show billing statement
    public abstract void showBillingStatement();

    // Getter for amount
    public double getAmount() {
        return amount;
    }
    public void processPayment() {
        
        paymentStatus = true; // Payment is successful
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }
}