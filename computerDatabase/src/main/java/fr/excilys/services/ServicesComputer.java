package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.excilys.model.Computer;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoFactory;

public class ServicesComputer {

	private static Logger logger = Logger.getLogger(ServicesComputer.class);

	public static List<Computer> computerList(int numberPage, int range) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.lister(numberPage, range);
	}

	public static void computerAdd(Computer computerNew) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		computerDao.ajouter(computerNew);
	}

	public static Computer computerSelectForUpdate(int idComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.selectionner(idComputer).get();
	}

	public static void computerUpdate(Computer modifiedComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		computerDao.modifier(modifiedComputer);
	}

	public static void computerRemove(int idComputer) {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		computerDao.supprimer(idComputer);
	}

	public static int computerGetNumber() {

		ComputerDao computerDao = new ComputerDao(getDaoFactory());
		return computerDao.numberPage();
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
