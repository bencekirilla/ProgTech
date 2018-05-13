package hu.wumpus.elements;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * A szabály menüpontot reprezántáló és definiáló a osztály.
 * 
 * @author James
 *
 */
public class RuleWindow extends StackPane {
	/**
	 * A szabály leírását tartalmazó mező.
	 */
	private Text text;

	/**
	 * Az osztályt létrehozó, beállító konsturktor.
	 * 
	 * @param description a szabály leírása
	 */
	public RuleWindow(String description) {
		text = new Text(description);
		text.getFont();
		text.setFont(Font.font(20));
		text.setFill(Color.WHITE);

		Rectangle rectangle = new Rectangle(1100, 700);
		rectangle.setOpacity(0.6);
		rectangle.setFill(Color.BLACK);
		rectangle.setEffect(new GaussianBlur(3.5));

		setAlignment(Pos.TOP_LEFT);
		getChildren().addAll(rectangle, text);
	}
}