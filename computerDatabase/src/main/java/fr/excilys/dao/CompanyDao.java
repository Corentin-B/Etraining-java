package fr.excilys.dao;

import java.util.List;
import java.util.Optional;

import fr.excilys.company.Company;

public interface CompanyDao {

	Optional<List<Company>> lister(int range);
}
