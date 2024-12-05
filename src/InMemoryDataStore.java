import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStore {
    private static InMemoryDataStore instance;
    private final Map<Long, Order> orders = new HashMap<>();
    private final Map<Long, Driver> drivers = new HashMap<>();
    private final Map<Long, Client> clients = new HashMap<>();
    private final Map<Long, Vehicle> vehicles = new HashMap<>();

    private InMemoryDataStore() {}

    public static synchronized InMemoryDataStore getInstance() {
        if (instance == null) {
            instance = new InMemoryDataStore();
        }
        return instance;
    }

    public Map<Long, Order> getOrders() {
        return orders;
    }

    public Map<Long, Driver> getDrivers() {
        return drivers;
    }

    public Map<Long, Client> getClients() {
        return clients;
    }

    public Map<Long, Vehicle> getVehicles() {
        return vehicles;
    }
}