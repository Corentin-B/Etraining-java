package fr.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDatabase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String username;
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	public static class Builder {

		private long idBuilder;
		private String usernameBuilder;
		private String passwordBuilder;
		private Role roleBuilder;

		public Builder setId(long id) {
			this.idBuilder = id;

			return this;
		}

		public Builder setUsername(String username) {
			this.usernameBuilder = username;

			return this;
		}

		public Builder setPassword(String password) {
			this.passwordBuilder = password;

			return this;
		}

		public Builder setBuiler(Role role) {
			this.roleBuilder = role;

			return this;
		}

		public UserDatabase build() {
			return new UserDatabase(this);
		}
	}

	private UserDatabase(Builder builder) {
		this.id = builder.idBuilder;
		this.username = builder.usernameBuilder;
		this.password = builder.passwordBuilder;
		this.role = builder.roleBuilder;
	}

	public UserDatabase() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
