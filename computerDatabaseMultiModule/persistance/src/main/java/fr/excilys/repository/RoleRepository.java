package fr.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.excilys.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
