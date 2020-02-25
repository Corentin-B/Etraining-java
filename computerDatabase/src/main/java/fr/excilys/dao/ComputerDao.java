package fr.excilys.dao;

import java.util.List;
import java.util.Optional;

import fr.excilys.model.Computer;

public interface ComputerDao {

	void ajouter(Computer computer);
	
	void supprimer(int id);
	
	void modifier(Computer computer);
	
	Optional<Computer> selectionner(int idComputer);

	Optional<List<Computer>> lister(int numberPage, int range);

	int numberPage();
}
