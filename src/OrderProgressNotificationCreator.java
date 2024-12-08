public class OrderProgressNotificationCreator extends NotificationCreator {

	@Override
	public Notification createNotification() {
		return new OrderProgressNotification();
	}

}