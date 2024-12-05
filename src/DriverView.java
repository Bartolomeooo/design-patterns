public class DriverView implements IDriverView {

	private Long driverId;
	private IOrderController orderController;

	/**
	 * 
	 * @param driverId
	 */
	public DriverView(Long driverId) {
		this.driverId = driverId;
	}

	public void reportProgress() {
		// TODO - implement DriverView.reportProgress
		throw new UnsupportedOperationException();
	}

	private void displayOrder() {
		// TODO - implement DriverView.displayOrder
		throw new UnsupportedOperationException();
	}

	private void reportProblem() {
		// TODO - implement DriverView.reportProblem
		throw new UnsupportedOperationException();
	}

}