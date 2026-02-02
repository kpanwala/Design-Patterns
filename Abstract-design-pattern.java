interface Dough { String getDescription(); }
interface Sauce { String getDescription(); }

class ThinCrustDough implements Dough {
    public String getDescription() { return "Thin Crust Dough"; }
}

class ThickCrustDough implements Dough {
    public String getDescription() { return "Thick Crust Dough"; }
}

class MarinaraSauce implements Sauce {
    public String getDescription() { return "Marinara Sauce"; }
}

class HawaianSauce implements Sauce {
    public String getDescription() { return "hawaian Sauce"; }
}

interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() { return new ThinCrustDough(); }
    public Sauce createSauce() { return new MarinaraSauce(); }
}


class HawaianIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() { return new ThickCrustDough(); }
    public Sauce createSauce() { return new HawaianSauce(); }
}


abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;

    abstract void prepare(); // Each pizza will prepare itself using a factory

    void bake() { System.out.println("Baking " + name); }
    
    public String toString() {
        return name + " with " + dough.getDescription() + " and " + sauce.getDescription();
    }
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        An Abstract Factory design pattern is a creational pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes, acting as a "factory of factories" to produce sets of consistent products, like UI elements for different operating systems (Windows/Mac) or game assets for different levels (Jedi/Sith). It decouples client code from object creation, promoting flexibility, modularity, and easier switching between product families, making systems more robust and reusable.  
        */

        PizzaIngredientFactory nyFactory = new NYPizzaIngredientFactory();

        Pizza myCheesePizza = new CheesePizza(nyFactory);
        myCheesePizza.name = "New York Style Cheese Pizza";
        myCheesePizza.prepare();
        myCheesePizza.bake();
        System.out.println("Result: " + myCheesePizza);

        System.out.println("---");

        PizzaIngredientFactory hawaianFactory = new HawaianIngredientFactory();

        Pizza myDeepDish = new CheesePizza(hawaianFactory);
        myDeepDish.name = "Hawaian pizza";
        myDeepDish.prepare();
        myDeepDish.bake();
        System.out.println("Result: " + myDeepDish);

        /*
        OUTPUT:

            Preparing New York Style Cheese Pizza
            Baking New York Style Cheese Pizza
            Result: New York Style Cheese Pizza with Thin Crust Dough and Marinara Sauce
            ---
            Preparing Hawaian pizza
            Baking Hawaian pizza
            Result: Hawaian pizza with Thick Crust Dough and hawaian Sauce
        */
    }
}