package services;

import java.sql.SQLException;
import java.util.List;

import client.UserInterface;
import computer.Computer;
import dao.ComputerDaoImpl;
import dao.DaoFactory;

public class ServicesComputer {

	public static List<Computer> computerList(int range) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		return computerDao.lister(range);
	}

	public static void computerAdd() {

		Computer newComputer = UserInterface.menuCreationComputer();

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		computerDao.ajouter(newComputer);
	}

	public static Computer computerSelectForUpdate(int idComputer) {

		ComputerDaoImpl computerDao = new ComputerDaoImpl(getDaoFacotry());
		return computerDao.selectionner(idComputer);
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
			e.printStackTrace();
		}
		return dao;
	}
}
