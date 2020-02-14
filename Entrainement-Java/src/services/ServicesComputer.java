package services;

import java.sql.SQLException;
import java.util.List;

import client.UserInterface;
import computer.Computer;
import dao.ComputerDaoImpl;
import dao.DaoFactory;

public class ServicesComputer {

	public static List<Computer> computerList(int range) {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		return computerDao.lister(range);
	}

	public static void computerAdd() {

		Computer newComputer = UserInterface.menuCreationComputer();

		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		computerDao.ajouter(newComputer);
	}

	public static Computer computerSelectForUpdate(int idComputer) {

		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		return computerDao.selectionner(idComputer);
	}

	public static void computerUpdate(Computer modifiedComputer) {

		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		computerDao.modifier(modifiedComputer);
	}

	public static void computerRemove(int idComputer) {

		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		computerDao.supprimer(idComputer);
	}
}
