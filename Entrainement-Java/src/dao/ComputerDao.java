package dao;

import java.util.List;

import computer.Computer;

public interface ComputerDao {

	void ajouter(Computer computer);
	
	void supprimer(int id);
	
	void modifier(Computer computer);
	
	Computer selectionner(int idComputer);

	List<Computer> lister(int range);
}
