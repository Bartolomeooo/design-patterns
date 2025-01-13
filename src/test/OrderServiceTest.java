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

@Tag("Control")
class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        InMemoryDataStore.getInstance().getOrders().clear();
    }

    /**
     * Test 1: initializeOrder()
     *
     * Checks if a new Order can be properly initialized with a non-null OrderDetails
     * and an existing client. Expects the Order to be saved as "Pending".
     */
    @Test
    void testInitializeOrder_Success() {
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

    /**
     * Checks if initializeOrder(...) throws an exception when provided with null OrderDetails.
     */
    @Test
    void testInitializeOrder_NullOrderDetails_ShouldThrow() {
        // Expect
        assertThrows(IllegalArgumentException.class, () -> {
            // Act: Attempt to initialize an order with null details
            orderService.initializeOrder(1L, null);
        }, "Should throw IllegalArgumentException for null OrderDetails.");
    }

    /**
     * Test 2: replaceDriver()
     *
     * Verifies the successful scenario of swapping drivers and checks that no exception is thrown
     * if the new driver exists in the data store.
     */
    @Test
    void testReplaceDriver_Success() {
        // Arrange
        InMemoryDataStore.getInstance().getDrivers().put(2L, new Driver(2L, true, "Test Driver 2"));
        OrderDetails details = new OrderDetails(
                "CityA", "CityB", "Cargo",
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(5),
                500.0
        );
        orderService.initializeOrder(1L, details);
        Order order = orderService.getPendingOrder();

        // Assume that driver with ID=2 also exists in the InMemoryDataStore (e.g., Anna Nowak).
        // Act & Assert
        assertDoesNotThrow(() -> orderService.replaceDriver(order, 2L),
                "Replacing driver with ID=2 should not throw if the driver exists.");
        assertEquals(2L, order.getDriver().getDriverId(),
                "The new driver's ID should now be 2 after replacement.");
    }

    /**
     * Checks if replaceDriver(...) throws NoSuchElementException when the driver ID does not exist.
     */
    @Test
    void testReplaceDriver_NoSuchDriver_ShouldThrow() {
        // Arrange
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

    /**
     * Test 3: reportProgress()
     *
     * Validates the workflow of an order through these statuses:
     *  Active -> Execution Started -> Conformity confirmed -> Shipment Started -> Order Completed
     * Also checks that a vehicle and driver are released after completion (isAvailable = true).
     */
    @Test
    void testReportProgress_Success() {
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

    /**
     * Ensures that reportProgress(...) throws IllegalArgumentException when the order is null.
     */
    @Test
    void testReportProgress_NullOrder_ShouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> orderService.reportProgress(null),
                "Should throw IllegalArgumentException when order is null.");
    }
}
