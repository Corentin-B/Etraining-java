package fr.excilys.computer;

import java.sql.Date;

public class Computer {

	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private long company_id;

	public static class ComputerBuilder {

		private long idBuilder;
		private String nameBuilder;
		private Date introducedBuilder;
		private Date discontinuedBuilder;
		private long company_idBuilder;

		public ComputerBuilder setId(long id) {
			this.idBuilder = id;

			return this;
		}

		public ComputerBuilder setName(String name) {
			this.nameBuilder = name;

			return this;
		}
		
		public ComputerBuilder setIntroduced(Date introduced) {
			this.introducedBuilder = introduced;

			return this;
		}

		public ComputerBuilder setDiscontinued(Date discontinued) {
			this.discontinuedBuilder = discontinued;

			return this;
		}

		public ComputerBuilder setCompany_id(Long company_id) {
			this.company_idBuilder = company_id;

			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
	}

	public Computer(ComputerBuilder builder) {
		this.id = builder.idBuilder;
		this.name = builder.nameBuilder;
		this.introduced = builder.introducedBuilder;
		this.discontinued = builder.discontinuedBuilder;
		this.company_id = builder.company_idBuilder;
	}

	/*
	 * public Computer(long id, String name, Date introduced, Date discontinued,
	 * long company_id) { this.id = id; this.name = name; this.introduced =
	 * introduced; this.discontinued = discontinued; this.company_id = company_id; }
	 */

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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
}