package dao;

import java.util.List;

import company.Company;

public interface CompanyDao {

	List<Company> lister(int range);
}
