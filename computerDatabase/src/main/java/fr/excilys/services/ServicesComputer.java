package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import fr.excilys.model.Computer;
import fr.excilys.dao.ComputerDaoImpl;
import fr.excilys.dao.DaoFactory;

public class ServicesComputer {

	private static Logger logger = Logger.getLogger(ServicesComputer.class);

	public static List<Computer> computerList(int numberPage, int range) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		return computerDao.lister(numberPage, range);
	}

	public static void computerAdd(Computer computerNew) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		computerDao.ajouter(computerNew);
	}

	public static Computer computerSelectForUpdate(int idComputer) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		return computerDao.selectionner(idComputer).get();
	}

	public static void computerUpdate(Computer modifiedComputer) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		computerDao.modifier(modifiedComputer);
	}

	public static void computerRemove(int idComputer) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		computerDao.supprimer(idComputer);
	}

	public static int computerGetNumber() {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		return computerDao.numberPage();
	}

	private static DaoFactory getDaoFacotry() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			logger.debug(e);
		}
		return dao;
	}
}
