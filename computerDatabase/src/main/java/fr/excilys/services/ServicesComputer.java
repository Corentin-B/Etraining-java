package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.excilys.dao.ComputerDao;
import fr.excilys.model.Computer;

@Service
public class ServicesComputer {

	private ComputerDao computerDao;
	
	public ServicesComputer(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
	
	public Computer computerSelectForUpdate(int idComputer) {

		return computerDao.selectComputerById(idComputer);
	}
	
	public List<Computer> computerSearchList(String nameComputer, int numberPage, int range, String order, String sort) {

		return computerDao.searchComputerByName(nameComputer, numberPage, range, order, sort);
	}
	
	public List<Computer> computerList(int numberPage, int range, String order, String sort) {

		if(order != null && !order.isEmpty())			
			return computerDao.listOrder(numberPage, range, order, sort);
		else
			return computerDao.list(numberPage, range);		
	}
	
	public int computerGetNumber() {

		return computerDao.numberPage();
	}
	
	public int computerGetNumberSearch(String name) {

		return computerDao.numberSearch(name);
	}
	
	public int computerAdd(Computer computerNew) {

		return computerDao.add(computerNew);
	}

	public int computerRemove(int idComputer) {

		return computerDao.remove(idComputer);
	}
	
	public int computerUpdate(Computer modifiedComputer) {

		return computerDao.update(modifiedComputer);
	}
}
