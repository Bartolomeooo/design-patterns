package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import presenter.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Skipped")
class CombinedJMockitTests {


    @Mock
    private DriverDAO driverDAO;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetDriver_WithMocks() {
        Order mockOrder = mock(Order.class);
        Driver mockDriver = mock(Driver.class);

        mockOrder.setDriver(mockDriver);

        verify(mockOrder, times(1)).setDriver(mockDriver);
    }

    @Test
    void testSetAndGetStatus_WithMocks() {
        Order mockOrder = mock(Order.class);

        mockOrder.setStatus("Pending");
        verify(mockOrder, times(1)).setStatus("Pending");

        mockOrder.setStatus("Active");
        verify(mockOrder, times(1)).setStatus("Active");

        mockOrder.setStatus("Canceled");
        verify(mockOrder, times(1)).setStatus("Canceled");
    }

    @Test
    void testReportProgress_Success_WithMocks() {
        // Arrange
        Order mockOrder = mock(Order.class);
        Driver mockDriver = mock(Driver.class);
        Vehicle mockVehicle = mock(Vehicle.class);

        when(mockOrder.getStatus()).thenReturn("Active", "Execution Started", "Conformity confirmed", "Shipment Started");
        when(mockOrder.getDriver()).thenReturn(mockDriver);
        when(mockOrder.getVehicle()).thenReturn(mockVehicle);

        // Act & Assert transitions
        orderService.reportProgress(mockOrder); // Active -> Execution Started
        verify(mockOrder, times(1)).setStatus("Execution Started");

        orderService.reportProgress(mockOrder); // Execution Started -> Conformity confirmed
        verify(mockOrder, times(1)).setStatus("Conformity confirmed");

        orderService.reportProgress(mockOrder); // Conformity confirmed -> Shipment Started
        verify(mockOrder, times(1)).setStatus("Shipment Started");

        orderService.reportProgress(mockOrder); // Shipment Started -> Order Completed
        verify(mockOrder, times(1)).setStatus("Order Completed");

        // Verify driver/vehicle availability updates
        verify(mockDriver, times(1)).setAvailable(true);
        verify(mockVehicle, times(1)).setAvailable(true);
    }

    @Test
    void testFindAvailableDriver_WithMocks() {
        DriverDAO mockDriverDAO = driverDAO;

        DriverService driverService = new DriverService(mockDriverDAO);

        Driver mockedDriver = new Driver(1L, true, "Mocked Driver");
        Mockito.when(mockDriverDAO.findAvailable()).thenReturn(mockedDriver);

        Driver availableDriver = driverService.findAvailable();

        assertNotNull(availableDriver, "Dostępny kierowca nie powinien być nullem.");
        assertEquals(1L, availableDriver.getDriverId(), "ID dostępnego kierowcy powinno wynosić 1.");
        assertTrue(availableDriver.isAvailable(), "Kierowca powinien być dostępny.");

        Mockito.verify(mockDriverDAO, times(1)).findAvailable();
    }
}
