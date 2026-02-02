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
        PizzaFactory factory = new PizzaFactory();
        Pizza myPizza = factory.getPizza("authentic");
        
        if (myPizza != null) {
            System.out.println(myPizza);
        } else {
            System.out.println("Pizza type not found.");
        }
    }
}