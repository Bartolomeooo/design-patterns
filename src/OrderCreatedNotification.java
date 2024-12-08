public class OrderCreatedNotification implements Notification {

	/**
	 * 
	 * @param status
	 */
	public void display(String status) {
		System.out.println("Zamowienie zostalo utworzone, jego status: " + status);
	}

}