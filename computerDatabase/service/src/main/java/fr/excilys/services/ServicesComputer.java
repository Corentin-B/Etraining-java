package fr.excilys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.excilys.model.Computer;
import fr.excilys.repository.ComputerRepository;

@Service
public class ServicesComputer {

	@Autowired
    private ComputerRepository computerRepository;
	
	public Computer computerSelectForUpdate(int idComputer) {

		return computerRepository.findById((long) idComputer).orElse(new Computer()) ;
	}
	
	public List<Computer> computerSearchList(String nameComputer, int numberPage, int range, String order, String sort) {
		
		PrepareQuerry prepareQuerry = new PrepareQuerry(computerRepository);
		
		return prepareQuerry.querrySortName(nameComputer, numberPage, range, sort, order);
	}
	
	public List<Computer> computerList(int numberPage, int range, String order, String sort) {

		PrepareQuerry prepareQuerry = new PrepareQuerry(computerRepository);

		if(order != null && !order.isEmpty())			
					
			return prepareQuerry.querrySortName("", numberPage, range, sort, order);
		
		else
			return computerRepository.findBy(PageRequest.of(numberPage, range));
	}
	
	public int computerGetNumber() {

		return (int) computerRepository.count();
	}
	
	public int computerGetNumberSearch(String name) {
		
		return computerRepository.countByNameContainingIgnoreCase(name);
	}
	
	public Computer computerAdd(Computer computerNew) {

		return computerRepository.save(computerNew);
	}

	public void computerRemove(int idComputer) {

		computerRepository.deleteById((long) idComputer);
	}
	
	public Computer computerUpdate(Computer modifiedComputer) {

		return computerRepository.save(modifiedComputer);
	}
}
