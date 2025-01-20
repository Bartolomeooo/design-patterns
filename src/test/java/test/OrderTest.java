package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import model.Driver;
import model.Order;

@Tag("Entity")
class OrderTest {

    private Order order;
    private Driver driver;

    @BeforeEach
    void setUp() {
        // We initialize simple objects for each test
        // Setting OrderDetails to null for now
        order = new Order(null);
        driver = new Driver(101L, true, "Test Driver");
    }

    @Test
    void testSetAndGetDriver() {
        // Assign a Driver to the Order
        order.setDriver(driver);
        assertNotNull(order.getDriver(), "Driver should not be null after assignment.");
        assertEquals(101L, order.getDriver().getDriverId(), "The driver's ID should be 101.");
        assertTrue(order.getDriver().isAvailable(), "The driver should be available initially.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pending", "Active", "Canceled"})
    void testSetAndGetStatus(String status) {
        // Check if Order can store and retrieve different statuses
        order.setStatus(status);
        assertEquals(status, order.getStatus(),
                "The retrieved status should match the one that was set.");
    }

    @Test
    void testToString() {
        // Set some fields to check if toString() provides correct output
        order.setOrderId(999L);
        order.setDriver(driver);
        order.setStatus("Pending");

        String text = order.toString();

        // Verify that certain fragments are included in the string representation
        assertTrue(text.contains("orderId=999"), "toString() should contain the correct Order ID.");
        assertTrue(text.contains("driver=101"), "toString() should display the driver's ID.");
        assertTrue(text.contains("status='Pending'"), "toString() should include the 'Pending' status.");
    }
}
