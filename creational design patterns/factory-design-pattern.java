abstract class Pizza {
    protected String topping;
    protected String dough;

    // Use a method to describe the pizza
    public String toString() {
        return "Dough: " + dough + " | Topping: " + topping;
    }
}

class AuthenticStylePizza extends Pizza {
    public AuthenticStylePizza() {
        this.dough = "thin crust";
        this.topping = "tomato sauce";
    }
}

class NeopolitanStylePizza extends Pizza {
    public NeopolitanStylePizza() {
        this.dough = "thick crust";
        this.topping = "marinara sauce";
    }
}

class PizzaFactory {
    public Pizza getPizza(String type) {
        if (type == null) return null;
        
        // Using equals() for string comparison
        if (type.equalsIgnoreCase("authentic")) {
            return new AuthenticStylePizza();
        } else if (type.equalsIgnoreCase("neopolitian")) {
            return new NeopolitanStylePizza();
        }
        return null; 
    }
}

class Main {
    public static void main(String[] args) {
        /*
        The Factory Design Pattern is a creational pattern that centralizes object creation, defining an interface for making objects but letting subclasses decide which specific class to instantiate, promoting loose coupling and flexibility, especially when object types aren't known until runtime, by hiding complex creation logic from the client code. It's like a real factory that produces different products (objects) based on requests, without the user needing to know the exact manufacturing process
        */
        PizzaFactory factory = new PizzaFactory();
        Pizza myPizza = factory.getPizza("authentic");
        
        if (myPizza != null) {
            // OUTPUT:
            // Dough: thin crust | Topping: tomato sauce
            System.out.println(myPizza);
        } else {
            System.out.println("Pizza type not found.");
        }
    }
}