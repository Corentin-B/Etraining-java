package fr.excilys.dao;

import java.util.List;

import fr.excilys.model.Company;

public interface CompanyDao {

	List<Company> lister(int range);
}
