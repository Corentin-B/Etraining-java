package fr.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import fr.excilys.model.UserDatabase;

public interface UserRepository extends JpaRepository<UserDatabase, Long> {

	UserDatabase findFirstByUsername(String username);

	void save(User user);
}
