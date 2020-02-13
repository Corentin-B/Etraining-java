package Services;

import java.sql.SQLException;

import Client.UserInterface;
import Computer.Computer;
import DAO.ComputerDaoImpl;
import DAO.DaoFactory;

public class ServicesComputer {

	public static void computerList() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		UserInterface.displayComputerList(computerDao.lister());
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

	public static void computerUpdate(int idComputer) {

		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);

		Computer selectedComputer = computerDao.selectionner(idComputer);
		Computer computerUpdate = UserInterface.menuUpdateComputer(selectedComputer);

		computerDao.modifier(computerUpdate);
	}
	
	public static void computerRemove() {

		int id = UserInterface.menuRemoveComputer();
		
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		computerDao.supprimer(id);
	}
}
