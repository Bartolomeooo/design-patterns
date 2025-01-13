package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import model.Vehicle;
import model.VehicleDAO;
import model.InMemoryDataStore;

import java.util.NoSuchElementException;

@Tag("DAO")
class VehicleDAOTest {

    private VehicleDAO vehicleDAO;

    @BeforeEach
    void setUp() {
        // We create a new DAO object that uses the Singleton.
        vehicleDAO = new VehicleDAO();

        // Clear the vehicles map in the Singleton so that each test starts with an empty storage.
        InMemoryDataStore.getInstance().getVehicles().clear();
    }

    @Test
    void testSaveAndFindById() {
        // Create a sample vehicle and save it
        Vehicle v1 = new Vehicle(100L, true);
        vehicleDAO.save(v1);

        // Check if it can be retrieved from the DAO
        Vehicle found = vehicleDAO.findById(100L);
        assertNotNull(found);
        assertEquals(100L, found.getVehicleId());
        assertTrue(found.isAvailable());
    }

    @Test
    void testFindById_NonExistent_ShouldThrowException() {
        // We did not save any vehicle with ID=999, so we expect an exception here
        assertThrows(NoSuchElementException.class,
                () -> vehicleDAO.findById(999L),
                "Should throw NoSuchElementException if a vehicle with ID=999 doesn't exist.");
    }

    @Test
    void testSave_NullVehicle_ShouldThrowException() {
        // Trying to save null – VehicleDAO.save(...) now throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> vehicleDAO.save(null),
                "Should throw IllegalArgumentException when attempting to save a null vehicle.");
    }

    @Test
    void testDelete() {
        // Save a vehicle, then remove it
        Vehicle v2 = new Vehicle(200L, false);
        vehicleDAO.save(v2);

        // Verify that it can be found (it should)
        Vehicle found = vehicleDAO.findById(200L);
        assertNotNull(found);

        // Now we delete it
        vehicleDAO.delete(200L);

        // After deletion, calling findById(...) should throw NoSuchElementException
        assertThrows(NoSuchElementException.class,
                () -> vehicleDAO.findById(200L),
                "Should throw NoSuchElementException after deleting the vehicle.");
    }

    @Test
    void testDelete_NonExistent_ShouldThrowException() {
        // Attempt to remove a vehicle with ID=999, which does not exist
        assertThrows(NoSuchElementException.class,
                () -> vehicleDAO.delete(999L),
                "Should throw NoSuchElementException because the vehicle does not exist.");
    }

    @Test
    void testFindAvailable() {
        // Add two vehicles: one unavailable, one available
        Vehicle v1 = new Vehicle(10L, false);
        Vehicle v2 = new Vehicle(11L, true);

        vehicleDAO.save(v1);
        vehicleDAO.save(v2);

        Vehicle available = vehicleDAO.findAvailable();
        assertNotNull(available);
        assertEquals(11L, available.getVehicleId());
    }
}
