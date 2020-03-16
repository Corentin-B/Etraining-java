package fr.excilys.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.excilys.model.Company;

@RunWith(MockitoJUnitRunner.class)
public class MapperCompanyTest {

	@Mock
	private ResultSet resultSet;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetInstance() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetCompanyFromResultSet() {

		Company companyRef = new Company.Builder()
										.setId(1l)
										.setName("companyname")
										.build();

		Company companyTest = new Company.Builder()
										 .build();

		// Define the behavior of the mock for each getString method's call
		try {
			Mockito.when(resultSet.getLong("id")).thenReturn(1l);
		} catch (SQLException e5) {
			fail("resultSet.getLong");
		}
		try {
			Mockito.when(resultSet.getString("name")).thenReturn("companyname");
		} catch (SQLException e4) {
			fail("resultSet.getString");
		}

		/*try {
			companyTest = MapperCompany.getCompanyFromResultSet(resultSet);
		} catch (SQLException e) {
			fail("getComputerFromResultSet");
		}*/

		assertEquals(companyRef, companyTest);
	}
}
