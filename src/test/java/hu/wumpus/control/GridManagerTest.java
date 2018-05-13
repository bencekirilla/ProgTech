package hu.wumpus.control;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.paint.Color;

public class GridManagerTest {

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
}
