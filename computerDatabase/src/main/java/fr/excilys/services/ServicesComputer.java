package fr.excilys.services;

import java.sql.SQLException;
import java.util.List;

import fr.excilys.model.Computer;
import fr.excilys.dao.ComputerDaoImpl;
import fr.excilys.dao.DaoFactory;
import fr.excilys.defaultLogger.Logger;

public class ServicesComputer {

	public static List<Computer> computerList(int range) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		return computerDao.lister(range).get();
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

	private static DaoFactory getDaoFacotry() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			Logger.writeLog("ERROR", "can't get DAO Connection");
		}
		return dao;
	}
}
