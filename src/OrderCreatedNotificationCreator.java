public class OrderCreatedNotificationCreator extends NotificationCreator {

	@Override
	public Notification createNotification() {
		return new OrderCreatedNotification();
	}

}