public class OrderCreatedNotification implements Notification {

	/**
	 * 
	 * @param status
	 */
	public void display(String status) {
		if (status == "Canceled") System.out.println("Zamowienie zostalo anulowane ze wzgledu na wymagania niemozliwe do spelenienia, jego status: " + status);
		else System.out.println("Zamowienie zostalo utworzone, jego status: " + status);
	}

}