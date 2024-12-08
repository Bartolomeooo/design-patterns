public class OrderProgressNotification implements Notification {

	/**
	 * 
	 * @param status
	 */
	public void display(String status) {
		System.out.println("Kolejny etap zamówienia zostal zrealizowany, status zamówienia:" + status);
	}

}