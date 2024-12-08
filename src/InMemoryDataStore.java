import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStore {
    private static InMemoryDataStore instance;
    private final Map<Long, Order> orders = new HashMap<>();
    private final Map<Long, Driver> drivers = new HashMap<>();
    private final Map<Long, Client> clients = new HashMap<>();
    private final Map<Long, Vehicle> vehicles = new HashMap<>();

    private InMemoryDataStore() {
        initializeDrivers();
        initializeVehicles();
        initializeClients();
    }

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

    private void initializeDrivers() {
        drivers.put(1L, new Driver(1L, true, "Jan Kowalski"));
        drivers.put(2L, new Driver(2L, true, "Anna Nowak"));
        drivers.put(3L, new Driver(3L, false, "Piotr Wiśniewski"));
    }

    private void initializeVehicles() {
        vehicles.put(1L, new Vehicle(1L, true));
        vehicles.put(2L, new Vehicle(2L, true));
        vehicles.put(3L, new Vehicle(3L, false));
    }

    private void initializeClients() {
        clients.put(1L, new Client(1L, "Jan", "523423465"));
        clients.put(2L, new Client(2L, "Bogdan", "780980345"));
        clients.put(3L, new Client(3L, "Janusz", "567897234"));
    }
}
