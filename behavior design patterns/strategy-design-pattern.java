abstract class Payment {
    // Added public so it can be accessed anywhere
    public abstract void processPayment();
}

class CreditCardPayment extends Payment {
    @Override
    public void processPayment() {
        System.out.println("payment processed via Credit card");
    }
}

class CryptoPayment extends Payment {
    @Override
    public void processPayment() {
        System.out.println("payment processed via crypto");
    }
}

class UpiPayment extends Payment {
    @Override
    public void processPayment() {
        System.out.println("payment processed via upi");
    }
}

class PaymentStrategy {
    
    private Payment paymentStrategy;

    public void setPaymentStrategy(Payment pt) {
        this.paymentStrategy = pt;
    }
    
    public void executePayment() {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method first.");
            return;
        }
        paymentStrategy.processPayment();
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        The Strategy Design Pattern is a behavioral design pattern. It allows you to dynamically change the behavior of an object by encapsulating it into different strategies.

        This pattern enables an object to choose from multiple algorithms and behaviors at runtime, rather than statically choosing a single one.
        */

        PaymentStrategy strategy = new PaymentStrategy();
        
        // Dynamically setting the strategy
        strategy.setPaymentStrategy(new CreditCardPayment());
        strategy.executePayment();
        
        // Swapping strategy at runtime
        strategy.setPaymentStrategy(new UpiPayment());
        strategy.executePayment();
    }
}