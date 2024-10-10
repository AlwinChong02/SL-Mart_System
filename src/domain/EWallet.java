package domain;

public class EWallet extends Payment {
    private String ownerId;

    // Constructor
    public EWallet(double amount, String ownerId) {
        super(amount);
        this.ownerId = ownerId;
    }

    // Implement show billing statement
    @Override
    public void showBillingStatement() {
        System.out.println("Billing Statement for EWallet Payment:");
        System.out.println("Owner ID: " + ownerId);
        System.out.println("Amount Due: $" + getAmount());
    }

    // Getter
    public String getOwnerId() {
        return ownerId;
    }
}