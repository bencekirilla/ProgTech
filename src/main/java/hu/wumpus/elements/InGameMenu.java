package hu.wumpus.elements;

import hu.wumpus.database.DataBaseManager;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Játék közbeni állapotot mutató menüt reprezentáló osztály.
 * 
 * @author James
 *
 */
public class InGameMenu extends Parent {
	/**
	 * Eseményeket ismertető ablak.
	 */
	private Label eventLogger;
	/**
	 * Adott pályának a szintjét kiíró panel.
	 */
	private Label level;
	/**
	 * Pontot szerepeltető panel.
	 */
	private Label scoreboard;

	/**
	 * Játék közbeni menüt létrehozó konstruktor.
	 * 
	 * @param forLog eseményablak
	 * @param forLevel szintjelző panel
	 * @param forScore pontjelző panel
	 */
	public InGameMenu(String forLog, String forLevel, String forScore) {
		VBox menu = new VBox(20);
		this.eventLogger = new Label(275, 400, forLog);
		this.level = new Label(275, 30, forLevel);
		this.scoreboard = new Label(275, 30, forScore);
		menu.setTranslateX(910);
		menu.setTranslateY(10);
		menu.getChildren().addAll(eventLogger, level, scoreboard);
		getChildren().add(menu);

	}
	
	/**
	 * Befrissíti a pontot, miután a játékos lépett.
	 * 
	 * @param value frissitése érték
	 */
	public void scoreRefresher(int value) {
		scoreboard.setText("Score:" + value);
	}
	
	/**
	 * Befrissíti az eseményablakot, miután a játékos lépett.
	 * 
	 * @param value a rálépett mező értéke
	 */
	public void logRefresher(int value) {
		DataBaseManager dataBaseManager = new DataBaseManager();
		eventLogger.setText(dataBaseManager.getTilesLog(value));
	}
	
	/**
	 * Játék közbeni menüt alkotó elemeket reprezentáló osztály.
	 * 
	 * @author James
	 *
	 */
	private class Label extends StackPane {
		/**
		 * Adott elemen feltüntett szöveg
		 */
		private Text text;
		
		/**
		 * Beállítja az adott elem szövegét a paraméterül megadott szöveget.
		 * 
		 * @param value beállítandó szöveg
		 */
		public void setText(String value) {
			text.setText(value);
		}
		
		/**
		 * Játék közbeni menüt alkotó elemet létrehozó konstruktor.
		 * 
		 * @param width ablak széllesége
		 * @param hight ablak magasság
		 * @param name ablak tartalma
		 */
		public Label(int width, int hight, String name) {
			text = new Text(name);
			text.getFont();
			text.setFont(Font.font(20));
			text.setFill(Color.BLACK);

			Rectangle rectangle = new Rectangle(width, hight);
			rectangle.setFill(Color.WHITE);
			rectangle.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.TOP_LEFT);
			getChildren().addAll(rectangle, text);
		}
	}

}
