package hu.wumpus.elements;

import hu.wumpus.database.DataBaseManager;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Eredmény táblát reprezentáló osztály.
 * 
 * @author James
 *
 */
public class LeaderBoardElement extends Parent {
	
	/**
	 * Eredménytáblát létrehozó konstruktor.
	 */
	public LeaderBoardElement() {
		DataBaseManager manager = new DataBaseManager();
		VBox names = new VBox(10);
		VBox scores = new VBox(10);
		HBox table = new HBox(5);
		String[] nameValues = manager.getLeaderBoardValues("names");
		String[] scoreValues = manager.getLeaderBoardValues("scores");
		for (int i = 0; i < 10; i++) {
			Panel leftPanel = new Panel(nameValues[i]);
			names.getChildren().add(leftPanel);
			Panel rightPanel = new Panel(scoreValues[i]);
			scores.getChildren().add(rightPanel);
		}
		table.getChildren().addAll(names,scores);
		getChildren().add(table);
	}
	
	/**
	 * Az eredménytábla egy adott celláját reprezentáló osztály.
	 * 
	 * @author James
	 *
	 */
	private class Panel extends StackPane {
		/**
		 * Cellát megadó alakzat.
		 */
		Rectangle border;
		/**
		 * Cellán lévő szöveg.
		 */
		Text text;
		/**
		 * Cellát létrehozó konstruktor.
		 * 
		 * @param description a cellán lévő szöveg
		 */
		public Panel(String description) {
			this.text = new Text(description);
			this.text.getFont();
			this.text.setFont(Font.font(25));
			this.text.setFill(Color.BLACK);

			this.border = new Rectangle(200, 50);
			this.border.setOpacity(0.4);
			this.border.setFill(Color.WHITE);
			
			getChildren().addAll(border, text);
		}
	}
}
