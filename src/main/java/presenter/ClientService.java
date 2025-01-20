package presenter;

import model.Client;
import model.ClientDAO;
import model.DAO;

public class ClientService {

	private DAO<Client> dao = new ClientDAO();

	/**
	 * @param clientId
	 */
	public Client findById(Long clientId) {
		return dao.findById(clientId);
	}

}