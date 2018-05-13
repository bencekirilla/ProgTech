package wumpus;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hu.wumpus.control.GridManager;
import hu.wumpus.database.DataBaseManager;
import javafx.scene.paint.Color;

public class DungeonTester {

	@Test
	public void colorPickerTesting() {

		GridManager gridManager = new GridManager();
		assertEquals(Color.GOLD, gridManager.ColorPicker(13, true));
		assertEquals(Color.WHITE, gridManager.ColorPicker(0, true));
		assertEquals(Color.CADETBLUE, gridManager.ColorPicker(100, false));
	}

	@Test
	public void scoreCalculatorTester() {
		GridManager gridManager = new GridManager();

		assertEquals(120, gridManager.scoreCalculator(100, 7));
		assertEquals(40, gridManager.scoreCalculator(-20, 9));
		assertEquals(80, gridManager.scoreCalculator(0, 10));
	}

	@Test
	public void movmentTester() {
		GridManager gridManager = new GridManager();

		assertEquals(true, gridManager.canMoveThere(2, 2, 2, 1, 4));
		assertEquals(false, gridManager.canMoveThere(1, 0, 0, 0, 9));
		assertEquals(false, gridManager.canMoveThere(4, 4, 5, 5, 4));
	}

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
