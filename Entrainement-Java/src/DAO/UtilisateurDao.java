package DAO;

import java.util.List;

import Company.Company;

public interface UtilisateurDao {

	void ajouter(Company utilisateur);

	List<Company> lister();
}