package presenter;

public class OrderCreatedNotification implements Notification {

	/**
	 * 
	 * @param status
	 */
	public void display(String status) {
		if (status == "Canceled") System.out.println("The order has been canceled due to impossible requirements, its status: " + status);
		else System.out.println("The order has been created, its status: " + status);
	}

}