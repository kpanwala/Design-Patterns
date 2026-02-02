public class Pizza {
    private String dough;
    private String sauce;
    private String cheese;
    private String topping;

    // The constructor is PRIVATE so nobody can use 'new Pizza()'
    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.topping = builder.topping;
    }

    // Static Inner Class: The "Helper"
    public static class Builder {
        private String dough;
        private String sauce;
        private String cheese;
        private String topping;

        public Builder setDough(String dough) {
            this.dough = dough;
            return this; // Returning 'this' allows us to chain methods!
        }

        public Builder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder setTopping(String topping) {
            this.topping = topping;
            return this;
        }

        // The final step that gives us the actual Pizza object
        public Pizza build() {
            return new Pizza(this);
        }
    }

    @Override
    public String toString() {
        return "Pizza with " + dough + ", " + sauce + ", " + cheese + ", and " + topping;
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        PROBLEM: 
        Imagine a Pizza class where some people want cheese, some want olives, some want extra sauce, and some want gluten-free crust. You would end up with a constructor like this:
        Pizza p = new Pizza("Thin", "Tomato", "Cheese", "No Olives", "No Onions", "No Pepperoni", true);

        The Builder Design Pattern is a creational design pattern that provides a step-by-step approach to constructing complex objects. 
         */
        Pizza myOrder = new Pizza.Builder()
                            .setDough("Thin Crust")
                            .setSauce("Spicy Marinara")
                            .setCheese("Mozzarella")
                            .setTopping("Basil")
                            .build();

        System.out.println(myOrder);
        // OUTPUT: Pizza with Thin Crust, Spicy Marinara, Mozzarella, and Basil
    }
}