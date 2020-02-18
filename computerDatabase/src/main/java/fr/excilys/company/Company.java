package fr.excilys.company;

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
}