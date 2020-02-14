package dao;

import java.util.List;
import java.util.Optional;

import company.Company;

public interface CompanyDao {

	Optional<List<Company>> lister(int range);
}
