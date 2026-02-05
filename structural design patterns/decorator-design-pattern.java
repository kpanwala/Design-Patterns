public class Main {
    public static void main(String[] args) {
        /*
        The Decorator Design Pattern is like going to Starbucks. You start with a basic coffee, and then you "decorate" it with sprinkles, milk, or caramel.
        */
        // 1. Start with plain coffee
        Beverage myOrder = new PlainCoffee(); 

        // 2. Wrap it in Milk
        myOrder = new Milk(myOrder); 

        // 3. Wrap it in Caramel
        myOrder = new Caramel(myOrder);

        System.out.println("Order: " + myOrder.getDescription());
        System.out.println("Total: $" + myOrder.getCost());
        
        /* OUTPUT:
        Order: Plain Coffee, Milk, Caramel
        Total: $3.25
        */
    }
}
class Milk extends ToppingDecorator {
    public Milk(Beverage newBeverage) { super(newBeverage); }

    public String getDescription() {
        return tempBeverage.getDescription() + ", Milk";
    }

    public double getCost() {
        return tempBeverage.getCost() + 0.50; // Adds $0.50
    }
}

class Caramel extends ToppingDecorator {

    public Caramel(Beverage newBeverage) { super(newBeverage); }

    public String getDescription() {
        return tempBeverage.getDescription() + ", Caramel";
    }

    public double getCost() {
        return tempBeverage.getCost() + 0.75; // Adds $0.75
    }
}

abstract class ToppingDecorator implements Beverage {
    protected Beverage tempBeverage; // The coffee we are wrapping

    public ToppingDecorator(Beverage newBeverage) {
        tempBeverage = newBeverage;
    }
}

interface Beverage {
    String getDescription();
    double getCost();
}

class PlainCoffee implements Beverage {
    public String getDescription() { return "Plain Coffee"; }
    public double getCost() { return 2.00; }
}