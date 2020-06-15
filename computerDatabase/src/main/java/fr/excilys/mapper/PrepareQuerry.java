package fr.excilys.mapper;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import fr.excilys.model.Computer;
import fr.excilys.repository.ComputerRepository;

public class PrepareQuerry {
	
	ComputerRepository computerRepository;
	
	public PrepareQuerry(ComputerRepository computerRepository) {
		// TODO Auto-generated constructor stub
		this.computerRepository = computerRepository;
	}
	
	public List<Computer> querrySortName(String nameComputer, int numberPage, int range, String sort, String order) {
		
		if ("desc".equals(sort))
			return querryOrderByNameAsc(nameComputer, numberPage, range, order);
		else
			return querryOrderByNameDesc(nameComputer, numberPage, range, order);
	}
	
	
	private List<Computer> querryOrderByNameAsc(String nameComputer, int numberPage, int range, String order) {
		
		if ("introduced".equals(order))
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("introduced").ascending()));
			else if ("discontinued".equals(order))
				return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("discontinued").ascending()));
		else if ("company_id".equals(order))
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("company_id").ascending()));
		else
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("name").ascending()));
	}
	
	private List<Computer> querryOrderByNameDesc(String nameComputer, int numberPage, int range, String order) {
		
		if ("introduced".equals(order))
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("introduced").descending()));
		else if ("discontinued".equals(order))
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("discontinued").descending()));
		else if ("company_id".equals(order))
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("company_id").descending()));
		else
			return computerRepository.findByNameContainingIgnoreCase(nameComputer, PageRequest.of(numberPage, range, Sort.by("name").descending()));
	}
}
