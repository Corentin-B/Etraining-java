package fr.excilys.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.excilys.model.Computer;

public interface ComputerRepository extends CrudRepository<Computer, Long>{
	
	List<Computer> findByName(String name);	
}
