package presenter;

import model.Route;

public class ShortestDistanceStrategy implements RoutingStrategy {

	public Route calculateRoute() {
		return new Route("Super short");
	}

}