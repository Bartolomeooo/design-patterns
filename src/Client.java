public class Client {

	private Long clientId;
	private String name;
	private String phoneNumber;

	public Client(Long clientId, String name, String phoneNumber) {
		this.clientId = clientId;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public Long getClientId() {
		return clientId;
	}
}