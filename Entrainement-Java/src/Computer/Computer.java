package Computer;
import java.sql.Timestamp;

public class Computer {
	
	private long id;
	private String name;
	private java.sql.Timestamp introduced;
	private java.sql.Timestamp discontinued;
	private long company_id;
	
	
	public Computer(long id, String name, Timestamp introduced, Timestamp discontinued, long company_id) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Timestamp getIntroduced() {
		return introduced;
	}
	public void setIntroduced(java.sql.Timestamp introduced) {
		this.introduced = introduced;
	}
	public java.sql.Timestamp getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(java.sql.Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
}