package Services;

import java.sql.SQLException;
import java.util.List;

import Client.UserInterface;
import Computer.Computer;
import DAO.ComputerDaoImpl;
import DAO.DaoFactory;

public class ServicesComputer {

	public static List<Computer> computerList() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		return computerDao.lister();
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

		//Computer computerUpdate = UserInterface.menuUpdateComputer(modifiedComputer);

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
