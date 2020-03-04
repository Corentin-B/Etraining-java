package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.excilys.model.Computer;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoFactory;

public class ServicesComputer {

	private static Logger logger = Logger.getLogger(ServicesComputer.class);

	public static boolean computerAdd(Computer computerNew) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.add(computerNew);
	}

	public static boolean computerRemove(int idComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.remove(idComputer);
	}
	
	public static boolean computerUpdate(Computer modifiedComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.update(modifiedComputer);
	}

	public static int computerGetNumber() {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.numberPage();
	}
	
	public static Computer computerSelectForUpdate(int idComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.selectComputerById(idComputer).get();
	}
	
	public static List<Computer> computerSearchList(String nameComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.searchComputerByName(nameComputer);
	}
	
	public static List<Computer> computerList(int numberPage, int range) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.list(numberPage, range);
	}

	private static DaoFactory getDaoFactory() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return dao;
	}
}
