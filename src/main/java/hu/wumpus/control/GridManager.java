package hu.wumpus.control;

import hu.wumpus.database.DataBaseManager;
import hu.wumpus.elements.InGameMenu;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * A kazamatát reprezentáló osztály.
 * 
 * @author James
 *
 */
public class GridManager {
	/**
	 * A kazamatáknak a sorainak vagy oszlopainak a száma.
	 */
	private static int NumberOfTiles;
	/**
	 * A kazamata adott területének fizikai mérete.
	 */
	private static int tileSize;
	/**
	 * A kazamata sorainak és oszlopainak értékeit tartalmazó mátrix.
	 */
	private static Tile[][] grid;
	/**
	 * A Játékos jellenlegi helye sorban.
	 */
	private static int actualRow;
	/**
	 * A Játékos jellenlegi helye oszlopban.
	 */
	private static int actualCol;
	/**
	 * A játéktábla.
	 */
	private static Pane root;
	/**
	 * A játékos pontszáma.
	 */
	private static int score;

	/**
	 * Visszaadja a játékmező mátrixát.
	 * 
	 * @return a mátrixa
	 */
	public static Tile[][] getGrid() {
		return grid;
	}

	/**
	 * Paraméter nélküli konstruktor.
	 */
	public GridManager() {

	}

	/**
	 * A játéktáblát és menüt létrehozó függvény.
	 * 
	 * @param mapName
	 *            adja meg melyik pályán vagyunk
	 * @return a játéktábla
	 */
	public Parent createContent(String mapName) {
		DataBaseManager dataBaseManager = new DataBaseManager();
		GridManager.score = 100;
		GridManager.NumberOfTiles = dataBaseManager.getSizeRowFromJson(mapName);
		String[][] valueMatrix = dataBaseManager.getDungeonFromJson(mapName);
		GridManager.tileSize = 900 / NumberOfTiles;
		GridManager.grid = new Tile[NumberOfTiles][NumberOfTiles];
		GridManager.actualCol = 1;
		GridManager.actualRow = 1;

		Rectangle backGround = new Rectangle(1200, 900);
		backGround.setFill(Color.BLACK);

		root = new Pane();
		root.getChildren().add(backGround);
		root.setPrefSize(900, 900);

		for (int col = 0; col < NumberOfTiles; col++) {
			for (int row = 0; row < NumberOfTiles; row++) {
				boolean explored;
				if (Integer.parseInt(valueMatrix[row][col]) == 9 || ((row == 1) && (col == 1))) {
					explored = true;
				} else {
					explored = false;
				}
				Tile tile = new Tile(row, col, Integer.parseInt(valueMatrix[row][col]), explored);
				grid[row][col] = tile;
				tile.SetColorForTile(ColorPicker(grid[row][col].getValue(), grid[row][col].isExplored()));
				if (row == 1 && col == 1) {
					tile.token.setVisible(true);
				}
				root.getChildren().add(tile);

			}
		}
		InGameMenu gameMenu = new InGameMenu(dataBaseManager.getTilesLog(7), "Level:  " + (NumberOfTiles - 6),
				"Score" + score);
		root.getChildren().add(gameMenu);
		return root;
	}

	/**
	 * A kazamata mezőjét reprezentáló osztály.
	 * 
	 * @author James
	 *
	 */
	private class Tile extends StackPane {
		/**
		 * A mező sor indexe.
		 */
		private int row;
		/**
		 * A mező oszlop indexe.
		 */
		private int col;
		/**
		 * A mező értéke, ami megadja mi van azon a mezőn.
		 */
		private int value;
		/**
		 * Megadja, hogy a mezőn már jártunk avagy sem.
		 */
		private boolean explored;
		/**
		 * A mező fizikai megjelenése.
		 */
		private Rectangle border = new Rectangle(tileSize - 4, tileSize - 4);
		/**
		 * Token, mely jelzi, hogy hol jár az adott játékos.
		 */
		private Circle token = new Circle(tileSize / 10);

		/**
		 * Visszaadja, hogy az adott terület fel van már e fedezve.
		 * 
		 * @return igaz vagy hamis
		 */
		public boolean isExplored() {
			return explored;
		}

		/**
		 * Visszaadja az adott területnek az értéket.
		 * 
		 * @return terület érték
		 */
		public int getValue() {
			return value;
		}

		/**
		 * Beállítja az adott terület értékét.
		 * 
		 * @param value
		 *            beállítandó érték
		 */
		public void setValue(int value) {
			this.value = value;
		}

		/**
		 * Kazamata területet létrehozó konstruktor.
		 * 
		 * @param row
		 *            sor
		 * @param col
		 *            oszlop
		 * @param value
		 *            érték
		 * @param explored
		 *            igaz vagy hamis
		 */
		public Tile(int row, int col, int value, boolean explored) {
			this.row = row;
			this.col = col;
			this.value = value;
			this.explored = explored;
			token.setVisible(false);
			getChildren().addAll(border, token);
			token.setFill(Color.RED);
			setTranslateX(row * tileSize);
			setTranslateY(col * tileSize);
			setOnMouseClicked(event -> {
				actionHandler();
			});
		}

		/**
		 * Beállítja az adott terület színét, attól függően, hogy milyen értéke van.
		 * 
		 * @param color
		 *            beállítandó szín
		 */
		public void SetColorForTile(Color color) {
			this.border.setFill(color);
		}

		/**
		 * Beállítja az összes mezőt úgy, hogy ne lehessen lépni benne.
		 */
		public void makeTheDungeonUnexplorable() {
			for (Tile[] tiles : grid) {
				for (Tile tile : tiles) {
					tile.setValue(9);
				}
			}
		}

		/**
		 * Lépést kezelő függvény.
		 */
		private void actionHandler() {
			if (canMoveThere(actualCol, actualRow, this.col, this.row, this.value)) {
				this.token.setVisible(true);
				grid[actualRow][actualCol].token.setVisible(false);
				this.explored = true;
				SetColorForTile(ColorPicker(this.value, this.explored));
				actualCol = this.col;
				actualRow = this.row;
				score -= 1;

				((InGameMenu) root.getChildren().get(root.getChildren().size() - 1)).scoreRefresher(score);
				((InGameMenu) root.getChildren().get(root.getChildren().size() - 1)).logRefresher(this.value);

				if (foundAMonster(this.value)) {
					makeTheDungeonUnexplorable();
				}
				if (foundAGold(this.value)) {
					makeTheDungeonUnexplorable();
					DataBaseManager manager = new DataBaseManager();
					manager.makeLeaderBoardFile(manager.getName(), scoreCalculator(score, NumberOfTiles));

				}
			}
		}

	}

	/**
	 * Igaz, ha a paraméterül megadott mezőn szörny vagy szakadék van.
	 * 
	 * @param value
	 *            mező értéke
	 * @return igaz vagy hamis
	 */
	public boolean foundAMonster(int value) {
		return value == 1 || value == 3 || value == 5;
	}

	/**
	 * Igaz, ha a paraméterül megadott mezőn arany van.
	 * 
	 * @param value
	 *            mező értéke
	 * @return igaz vagy hamis
	 */
	public boolean foundAGold(int value) {
		return value == 11 || value == 13;
	}

	/**
	 * Igaz, ha a paraméterül megadott mezőre tudunk lépni.
	 * 
	 * @param actC
	 *            akuális oszlop index
	 * @param actR
	 *            akuális sor index
	 * @param col
	 *            új oszlop index
	 * @param row
	 *            új sor index
	 * @param value
	 *            mező értéke
	 * @return igaz vagy hamis
	 */
	public boolean canMoveThere(int actC, int actR, int col, int row, int value) {
		return Math.abs(actC - col) + Math.abs(actR - row) == 1 && value != 9;
	}

	/**
	 * Vissza adja, hogy a játékos hány pontott szerzett a játék végén a pálya
	 * szintjétől függően.
	 * 
	 * @param score
	 *            játékban elért pont
	 * @param number
	 *            pálya szintje
	 * @return végleges pont
	 */
	public int scoreCalculator(int score, int number) {
		return score + 20 * (number - 6);
	}

	/**
	 * A paraméterül megadott mező alapján visszaadja annak színét.
	 * 
	 * @param value
	 *            mező értéke
	 * @param explored
	 *            mező felfedezetsége
	 * @return az adott mező színe
	 */
	public Color ColorPicker(int value, boolean explored) {
		if (explored) {
			switch (value) {
			case 0:
				return Color.WHITE;
			case 1:
				return Color.BLACK;
			case 2:
				return Color.YELLOW;
			case 3:
				return Color.RED;
			case 4:
				return Color.GREEN;
			case 5:
				return Color.DARKRED;
			case 6:
				return Color.DARKSLATEBLUE;
			case 9:
				return Color.DARKORANGE;
			case 11:
				return Color.GOLD;
			case 13:
				return Color.GOLD;
			}
		} else {
			return Color.CADETBLUE;
		}
		return null;
	}

}