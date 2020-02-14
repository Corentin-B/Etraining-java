package DAO;

import java.util.List;

import Computer.Computer;

public interface ComputerDao {

	void ajouter(Computer computer);
	
	void supprimer(int id);
	
	void modifier(Computer computer);
	
	Computer selectionner(int idComputer);

	List<Computer> lister(int range);
}
