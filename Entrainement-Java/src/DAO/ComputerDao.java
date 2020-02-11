package DAO;

import java.util.List;

import Computer.Computer;

public interface ComputerDao {

	void ajouter(Computer computer);
	
	void supprimer(Computer computer);
	
	void modifier(Computer computer);

	List<Computer> lister();
}
