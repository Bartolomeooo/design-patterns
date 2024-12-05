import java.util.Map;

public class ClientDAO implements DAO<Client> {
	private final Map<Long, Client> clients = InMemoryDataStore.getInstance().getClients();
	/**
	 * 
	 * @param clientId
	 */
	public Client findById(Long clientId) {
		return clients.get(clientId);
	}

	/**
	 * 
	 * @param client
	 */
	public void save(Client client) {
		clients.put(client.getClientId(), client);
	}

	/**
	 * 
	 * @param clientId
	 */
	public void delete(Long clientId) {
		clients.remove(clientId);
	}

}