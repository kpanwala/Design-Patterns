// 1. The Target Interface: What our modern app expects
interface JsonStockProvider {
    void displayStockJson();
}

// 2. The Adaptee: The legacy service providing XML data
class LegacyXmlService {
    public String getStockXmlData() {
        return "<?xml version=\"1.0\"?><stock><ticker>AAPL</ticker><price>150.25</price></stock>";
    }
}

// 3. The Adapter: Bridges LegacyXmlService to JsonStockProvider
class StockDataAdapter implements JsonStockProvider {
    private LegacyXmlService legacyService;

    public StockDataAdapter(LegacyXmlService service) {
        this.legacyService = service;
    }

    @Override
    public void displayStockJson() {
        // Step A: Get the XML data from the legacy service
        String xmlData = legacyService.getStockXmlData();
        
        // Step B: Convert XML to JSON (Simplified logic for the example)
        String jsonData = convertXmlToJson(xmlData);
        
        // Step C: Provide the data in the format the app understands
        System.out.println("Processing JSON Data...");
        System.out.println("Result: " + jsonData);
    }

    private String convertXmlToJson(String xml) {
        // In a real app, you'd use a library like Jackson or Gson here
        // For this example, we'll do a simple string replacement
        return xml.replace("<?xml version=\"1.0\"?><stock><ticker>", "{ \"ticker\": \"")
                  .replace("</ticker><price>", "\", \"price\": ")
                  .replace("</price></stock>", " }");
    }
}

// 4. Client Code
public class Main {
    public static void main(String[] args) {
        /*
        Adapter Design Pattern is a structural pattern that acts as a bridge between two incompatible interfaces, allowing them to work together. It is especially useful for integrating legacy code or third-party libraries into a new system.
        */
        // The service we currently have
        LegacyXmlService legacyService = new LegacyXmlService();

        // The adapter makes the legacy service compatible with our JSON interface
        JsonStockProvider stockDashboard = new StockDataAdapter(legacyService);

        // The client only interacts with the JsonStockProvider interface
        System.out.println("Requesting stock update for Dashboard...");
        stockDashboard.displayStockJson();
        
        /* OUTPUT:
        Requesting stock update for Dashboard...
        Processing JSON Data...
        Result: { "ticker": "AAPL", "price": 150.25 }
        */
    }
}