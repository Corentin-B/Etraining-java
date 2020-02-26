package fr.excilys.dao;

import java.util.List;
import java.util.Optional;

import fr.excilys.model.Company;

public interface CompanyDao {

	List<Company> lister(int range);
}
