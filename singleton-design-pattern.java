class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;

    private DatabaseConnection() {
        this.connectionString = "jdbc:mysql://localhost:3306/my_database";
        System.out.println("--- Database Connection Initialized ---");
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) { // First check (no locking)
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        /*
        The Singleton Design Pattern ensures that a class has only one instance and provides a global access point to it. It is used when we want centralized control of resources, such as managing database connections, configuration settings or logging. 
        */

        // You CANNOT do this: 
        // DatabaseConnection db = new DatabaseConnection(); // Error!

        // You MUST do this:
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        // Verify they are exactly the same object in memory
        if (db1 == db2) {
            System.out.println("Both variables point to the same instance.");
        }
    }
}