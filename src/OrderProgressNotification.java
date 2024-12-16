public class OrderProgressNotification implements Notification {

	/**
	 * 
	 * @param status
	 */
	public void display(String status) {
		System.out.println("The next stage of the order has been completed, order status:" + status);
	}

}