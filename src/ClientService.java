public class ClientService {

	private DAO<Client> dao = new ClientDAO();

	/**
	 * @param clientId
	 */
	public Client findById(Long clientId) {
		return dao.findById(clientId);
	}

}