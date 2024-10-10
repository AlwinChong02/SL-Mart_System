package domain;


public class Card extends Payment {
    private String cardNumber;
    private String cardHolderName;

    // Constructor
    public Card(double amount, String cardNumber, String cardHolderName) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    // Implement show billing statement
    @Override
    public void showBillingStatement() {
        System.out.println("Billing Statement for Card Payment:");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Amount Due: $" + getAmount());
    }

    // Getters and setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}