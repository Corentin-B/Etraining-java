package fr.excilys.model;

public class DoPostParameter {
	private String computerId;
	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;

	public DoPostParameter(String computerId, String computerName, String introduced, String discontinued,
			String companyId) {
		this.computerId = computerId;
		this.computerName = computerName;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public String getComputerId() {
		return computerId;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String getCompanyId() {
		return companyId;
	}
}