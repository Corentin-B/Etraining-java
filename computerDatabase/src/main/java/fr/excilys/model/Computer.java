package fr.excilys.model;

import java.sql.Date;

public class Computer {

	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	// private long company_id;
	private Company company;

	public static class ComputerBuilder {

		private long idBuilder;
		private String nameBuilder;
		private Date introducedBuilder;
		private Date discontinuedBuilder;
		private Company companyBuilder;

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

		public ComputerBuilder setCompany(Company company) {
			this.companyBuilder = company;

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
		this.company = builder.companyBuilder;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}