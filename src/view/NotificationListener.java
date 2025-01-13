package org.example.view;

import model.Order;
import presenter.Notification;

public interface NotificationListener {

	/**
	 * 
	 * @param notification
	 * @param order
	 */
	void notify(Notification notification, Order order);
}