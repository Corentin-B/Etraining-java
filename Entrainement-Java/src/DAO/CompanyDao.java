package DAO;

import java.util.List;

import Company.Company;

public interface CompanyDao {

	List<Company> lister(int range);
}
