package presenter;

import model.Route;

public class FastestRouteStrategy implements RoutingStrategy {

	public Route calculateRoute() {
		return new Route("Super fast");
	}
}