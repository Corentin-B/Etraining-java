package fr.excilys.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.excilys.dao.ComputerDao;
import fr.excilys.model.Computer;

@Service
public class ServicesComputer {

	private static ComputerDao computerDao;
	
	public ServicesComputer(ComputerDao computerDao) {
		ServicesComputer.computerDao = computerDao;
	}
	
	public static Computer computerSelectForUpdate(int idComputer) {

		return computerDao.selectComputerById(idComputer);
	}
	
	public static List<Computer> computerSearchList(String nameComputer, int numberPage, int range, String order, String sort) {

		return computerDao.searchComputerByName(nameComputer, numberPage, range, order, sort);
	}
	
	public static List<Computer> computerList(int numberPage, int range, String order, String sort) {

		if(order != null)			
			return computerDao.listOrder(numberPage, range, order, sort);
		else
			return computerDao.list(numberPage, range);
	}
	
	public static int computerGetNumber() {

		return computerDao.numberPage();
	}
	
	public static int computerGetNumberSearch(String name) {

		return computerDao.numberSearch(name);
	}
	
	public static int computerAdd(Computer computerNew) {

		return computerDao.add(computerNew);
	}

	public static int computerRemove(int idComputer) {

		return computerDao.remove(idComputer);
	}
	
	public static int computerUpdate(Computer modifiedComputer) {

		return computerDao.update(modifiedComputer);
	}
}
