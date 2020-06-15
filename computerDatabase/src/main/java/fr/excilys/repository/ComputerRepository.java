package fr.excilys.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.excilys.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long>{
	
	int countByNameContainingIgnoreCase(String nameComputer);
	
	List<Computer> findBy(Pageable pageable);
	
	List<Computer> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
