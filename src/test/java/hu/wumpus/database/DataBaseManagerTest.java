package hu.wumpus.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataBaseManagerTest {

	@Test
	public void dataBaseSizeTester() {
		DataBaseManager manager = new DataBaseManager();

		assertEquals(15, manager.getSizeRowFromJson("Ninth.Level"));
		assertEquals(11, manager.getSizeRowFromJson("Fifth.Level"));
	}

	@Test
	public void dataBaseNameTester() {
		DataBaseManager manager = new DataBaseManager();
		manager.setName("Bence");
		assertEquals("Bence", manager.getName());
		manager.setName("Alfa Béta");
		assertEquals("Alfa Béta", manager.getName());
	}

}
