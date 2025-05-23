package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import presenter.Notification;
import presenter.OrderService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Order")
class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        InMemoryDataStore.getInstance().getOrders().clear();
        InMemoryDataStore.getInstance().getDrivers().clear();
        InMemoryDataStore.getInstance().getVehicles().clear();
        InMemoryDataStore.getInstance().getClients().clear();
    }

    @Test
    void testInitializeOrder_Success() {
        InMemoryDataStore.getInstance().getDrivers().put(1L, new Driver(1L, true, "Test Driver 1"));
        InMemoryDataStore.getInstance().getVehicles().put(1L, new Vehicle(1L, true));
        InMemoryDataStore.getInstance().getClients().put(1L, new Client(1L, "Client 1", "123456789"));

        // Arrange
        OrderDetails details = new OrderDetails(
                "CityA", "CityB", "Cargo",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(5),
                1000.0
        );

        // Act
        orderService.initializeOrder(1L, details);

        // Assert
        Order created = orderService.getPendingOrder();
        assertNotNull(created, "A 'Pending' order should have been created.");
        assertEquals("Pending", created.getStatus(), "Status should be 'Pending' after initialization.");
        assertNotNull(created.getDriver(), "A Driver should be assigned automatically.");
        assertNotNull(created.getVehicle(), "A Vehicle should be assigned automatically.");
        assertEquals(details, created.getOrderDetails());
        assertEquals(1L, created.getOrderId());
    }

    @Test
    void testInitializeOrder_NullOrderDetails_ShouldThrow() {
        InMemoryDataStore.getInstance().getClients().put(1L, new Client(1L, "Client 1", "123456789"));

        // Expect
        assertThrows(IllegalArgumentException.class, () -> {
            // Act: Attempt to initialize an order with null details
            orderService.initializeOrder(1L, null);
        }, "Should throw IllegalArgumentException for null OrderDetails.");
    }

    @Test
    void testReplaceDriver_Success() {
        InMemoryDataStore.getInstance().getDrivers().put(1L, new Driver(1L, false, "Test Driver 1"));
        InMemoryDataStore.getInstance().getDrivers().put(2L, new Driver(2L, true, "Test Driver 2"));
        InMemoryDataStore.getInstance().getVehicles().put(1L, new Vehicle(1L, true));
        InMemoryDataStore.getInstance().getClients().put(1L, new Client(1L, "Client 1", "123456789"));

        OrderDetails details = new OrderDetails(
                "CityA", "CityB", "Cargo",
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(5),
                500.0
        );
        orderService.initializeOrder(1L, details);
        Order order = orderService.getPendingOrder();

        // Act & Assert
        assertDoesNotThrow(() -> orderService.replaceDriver(order, 2L),
                "Replacing driver with ID=2 should not throw if the driver exists.");
        assertEquals(2L, order.getDriver().getDriverId(),
                "The new driver's ID should now be 2 after replacement.");
    }

    @Test
    void testReplaceDriver_NoSuchDriver_ShouldThrow() {
        InMemoryDataStore.getInstance().getDrivers().put(1L, new Driver(1L, true, "Test Driver 1"));
        InMemoryDataStore.getInstance().getVehicles().put(1L, new Vehicle(1L, true));
        InMemoryDataStore.getInstance().getClients().put(1L, new Client(1L, "Client 1", "123456789"));

        OrderDetails details = new OrderDetails(
                "X", "Y", "Cargo",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                200.0
        );
        orderService.initializeOrder(1L, details);
        Order order = orderService.getPendingOrder();

        // Act & Assert
        assertThrows(NoSuchElementException.class,
                () -> orderService.replaceDriver(order, 9999L),
                "Should throw NoSuchElementException if the driver with ID=9999 doesn't exist.");
    }

    @Test
    void testReportProgress_Success() {
        InMemoryDataStore.getInstance().getDrivers().put(1L, new Driver(1L, false, "Test Driver 1"));
        InMemoryDataStore.getInstance().getVehicles().put(1L, new Vehicle(1L, false));
        InMemoryDataStore.getInstance().getClients().put(1L, new Client(1L, "Client 1", "123456789"));

        // Arrange: initialize and set an order to "Active"
        OrderDetails details = new OrderDetails(
                "A", "B", "Cargo",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                300.0
        );
        orderService.initializeOrder(1L, details);
        Order pending = orderService.getPendingOrder();
        orderService.createOrder(pending);  // This sets status to "Active"

        assertEquals("Active", pending.getStatus(), "Order should now be 'Active'.");

        // 1) Active -> Execution Started
        Notification n1 = orderService.reportProgress(pending);
        assertEquals("Execution Started", pending.getStatus());
        assertNotNull(n1, "Notification should not be null.");

        // 2) Execution Started -> Conformity confirmed
        Notification n2 = orderService.reportProgress(pending);
        assertEquals("Conformity confirmed", pending.getStatus());
        assertNotNull(n2);

        // 3) Conformity confirmed -> Shipment Started
        Notification n3 = orderService.reportProgress(pending);
        assertEquals("Shipment Started", pending.getStatus());
        assertNotNull(n3);

        // 4) Shipment Started -> Order Completed
        Notification n4 = orderService.reportProgress(pending);
        assertEquals("Order Completed", pending.getStatus());
        assertNotNull(n4);

        // After completion, driver and vehicle should be available again
        assertTrue(pending.getDriver().isAvailable(), "Driver should be set available after completion.");
        assertTrue(pending.getVehicle().isAvailable(), "Vehicle should be set available after completion.");
    }
}
