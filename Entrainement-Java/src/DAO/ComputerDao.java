package DAO;

import java.util.List;

import Computer.Computer;

public interface ComputerDao {

	void ajouter(Computer computer);
	
	void supprimer(int Id);
	
	void modifier(Computer computer);
	
	Computer selectionner(int Id);

	List<Computer> lister();
}
