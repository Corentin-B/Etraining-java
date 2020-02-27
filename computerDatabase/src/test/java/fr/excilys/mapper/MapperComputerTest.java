package fr.excilys.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@RunWith(MockitoJUnitRunner.class)
public class MapperComputerTest {

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
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testGetComputerFromResultSet() {

		Company companyRef = new Company.Builder()
										.setId(1l)
										.setName("companyname")
										.build();
		
        Computer computerRef = new Computer.Builder()
								   	       .setId(1l)
								   	       .setName("computername")
								   	       .setIntroduced(LocalDate.parse("2002-02-02"))
								   	       .setDiscontinued(LocalDate.parse("2020-02-02"))
				//				   	       .setCompany(companyRef)
								   	       .build();
		
        Computer computerTest = new Computer.Builder()
        									.build();
				
        // Define the behavior of the mock for each getString method's call
        try {
			Mockito.when(resultSet.getLong("id")).thenReturn(1l);
		} catch (SQLException e1) {
			fail("resultSet.getLong");
		}
        try {
			Mockito.when(resultSet.getString("name")).thenReturn("computername");
		} catch (SQLException e2) {
			fail("resultSet.getString");
		}
        /*try {
			Mockito.when(resultSet.getTimestamp("introduced")).thenReturn(LocalDate.parse("2002-02-02"));
		} catch (SQLException e3) {
			fail("resultSet.getDateIntroduced");
		}
        try {
			Mockito.when(resultSet.getTimestamp("discontinued")).thenReturn(LocalDate.parse("2020-02-02"));
		} catch (SQLException e4) {
			fail("resultSet.getDateDiscontinued");
		}*/
        try {
			Mockito.when(resultSet.getObject("company")).thenReturn(null);
		} catch (SQLException e5) {
			fail("resultSet.getObject");
		}
        
		try {
			computerTest = MapperComputer.getInstance().getComputerFromResultSet(resultSet);
		} catch (SQLException e) {
			fail("getComputerFromResultSet");
		}		
        assertEquals(computerRef, computerTest);	
	}
}
