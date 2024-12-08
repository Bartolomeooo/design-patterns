import java.util.List;

public class DriverService {

	private final DAO<Driver> dao = new DriverDAO();

	public Driver findAvailable() {
		return ((DriverDAO) dao).findAvailable();
	}

	public Driver findById(Long driverId) {
		return dao.findById(driverId);
	}
}