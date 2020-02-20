package fr.excilys.model;

public class Company {

	private long id;
	private String name;

	public static class CompanyBuilder {

		private long idBuilder;
		private String nameBuilder;

		public CompanyBuilder setId(long id) {
			this.idBuilder = id;

			return this;
		}

		public CompanyBuilder setName(String name) {
			this.nameBuilder = name;

			return this;
		}

		public Company build() {
			return new Company(this);
		}
	}

	private Company(CompanyBuilder builder) {
		this.id = builder.idBuilder;
		this.name = builder.nameBuilder;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}