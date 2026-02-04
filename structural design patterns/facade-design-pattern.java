class Kitchen {
    public void prepareDough() { System.out.println("Preparing dough..."); }
    public void addToppings() { System.out.println("Adding cheese and pepperoni..."); }
    public void bake() { System.out.println("Baking the pizza..."); }
}

class Delivery {
    public void assignDriver() { System.out.println("Assigning delivery driver..."); }
    public void sendOut() { System.out.println("Pizza is on the way!"); }
}

class PizzaFacade {
    private Kitchen kitchen;
    private Delivery delivery;

    public PizzaFacade() {
        this.kitchen = new Kitchen();
        this.delivery = new Delivery();
    }

    public void orderPizza() {
        System.out.println("--- Order Received ---");
        kitchen.prepareDough();
        kitchen.addToppings();
        kitchen.bake();
        delivery.assignDriver();
        delivery.sendOut();
        System.out.println("--- Order Complete ---");
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        The Facade Design Pattern is a structural pattern that provides a simplified interface to a complex system of classes, library, or framework. The Facade doesn't add new functionality; it just makes a complex set of sub-systems easier to use by hiding their complexity behind a single "front" class. When you order pizza, you call the Receptionist (The Facade). You don't talk to the chef, the person prepping the dough, or the delivery driver individually. The receptionist coordinates the entire "complex system" for you.
        */
        PizzaFacade receptionist = new PizzaFacade();
        
        // Simple call instead of managing 5 different objects
        receptionist.orderPizza();
        
        /* OUTPUT:
        --- Order Received ---
        Preparing dough...
        Adding cheese and pepperoni...
        Baking the pizza...
        Assigning delivery driver...
        Pizza is on the way!
        --- Order Complete ---
        */
    }
}