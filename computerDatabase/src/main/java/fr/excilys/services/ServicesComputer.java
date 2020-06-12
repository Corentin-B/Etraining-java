package fr.excilys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.excilys.dao.ComputerDao;
import fr.excilys.model.Computer;
import fr.excilys.repository.ComputerRepository;

@Service
public class ServicesComputer {

	@Autowired
    private static ComputerRepository computerRepository;
	
	public Computer computerSelectForUpdate(int idComputer) {

		return computerRepository.findById((long) idComputer).orElse(new Computer()) ;
		//return computerDao.selectComputerById(idComputer);
	}
	
	public List<Computer> computerSearchList(String nameComputer, int numberPage, int range, String order, String sort) {

		return computerDao.searchComputerByName(nameComputer, numberPage, range, order, sort);
	}
	
	public List<Computer> computerList(int numberPage, int range, String order, String sort) {

		if(order != null && !order.isEmpty())			
			return computerDao.listOrder(numberPage, range, order, sort);
		else
			return computerRepository.list(numberPage, range);
			//return computerDao.list(numberPage, range);		
	}
	
	public int computerGetNumber() {

		return (int) computerRepository.count();
		//return computerDao.numberPage();
	}
	
	public int computerGetNumberSearch(String name) {
		
		return (int) computerRepository.CountLike(name);
		//return computerDao.numberSearch(name);
	}
	
	public Computer computerAdd(Computer computerNew) {

		return computerRepository.save(computerNew);
		//return computerDao.add(computerNew);
	}

	public void computerRemove(int idComputer) {

		computerRepository.deleteById((long) idComputer);
		//return computerDao.remove(idComputer);
	}
	
	public Computer computerUpdate(Computer modifiedComputer) {

		return computerRepository.save(modifiedComputer);
		//return computerDao.update(modifiedComputer);
	}
}
