package fr.excilys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.excilys.model.Computer;

public interface ComputerRepository extends CrudRepository<Computer, Long>{
	
	long CountLike(String name);
	
	@Query(value = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			 +"FROM computer " 
			 +"LEFT JOIN company ON company_id = company.id " 
			 +"LIMIT ?1, ?2", nativeQuery = true)
	List<Computer> list(int numberPage, int range);
}
