package model;

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

    public void initializeDrivers() {
        drivers.put(1L, new Driver(1L, true, "Jan Kowalski"));
        drivers.put(2L, new Driver(2L, true, "Anna Nowak"));
        drivers.put(3L, new Driver(3L, false, "Piotr Wiśniewski"));
        drivers.put(4L, new Driver(4L, true, "Maria Zielińska"));
        drivers.put(5L, new Driver(5L, false, "Robert Lewandowski"));
        drivers.put(6L, new Driver(6L, true, "Krzysztof Krawczyk"));
        drivers.put(7L, new Driver(7L, true, "Ewa Farna"));
        drivers.put(8L, new Driver(8L, false, "Adam Małysz"));
        drivers.put(9L, new Driver(9L, true, "Magdalena Cielecka"));
        drivers.put(10L, new Driver(10L, true, "Zbigniew Stonoga"));
    }

    public void initializeVehicles() {
        vehicles.put(1L, new Vehicle(1L, true));
        vehicles.put(2L, new Vehicle(2L, true));
        vehicles.put(3L, new Vehicle(3L, false));
        vehicles.put(4L, new Vehicle(4L, true));
        vehicles.put(5L, new Vehicle(5L, true));
        vehicles.put(6L, new Vehicle(6L, true));
        vehicles.put(7L, new Vehicle(7L, false));
        vehicles.put(8L, new Vehicle(8L, true));
        vehicles.put(9L, new Vehicle(9L, true));
        vehicles.put(10L, new Vehicle(10L, true));
    }

    public void initializeClients() {
        clients.put(1L, new Client(1L, "Jan", "523423465"));
        clients.put(2L, new Client(2L, "Bogdan", "780980345"));
        clients.put(3L, new Client(3L, "Paweł", "567897234"));
        clients.put(4L, new Client(4L, "Katarzyna", "678543212"));
        clients.put(5L, new Client(5L, "Aleksandra", "123456789"));
        clients.put(6L, new Client(6L, "Michał", "987654321"));
        clients.put(7L, new Client(7L, "Joanna", "111222333"));
        clients.put(8L, new Client(8L, "Marcin", "444555666"));
        clients.put(9L, new Client(9L, "Barbara", "777888999"));
        clients.put(10L, new Client(10L, "Wojciech", "000111222"));
    }

}
