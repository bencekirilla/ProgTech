package hu.wumpus.elements;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Menügombot reprezentáló osztály.
 * 
 * @author James
 *
 */
public class MenuButton extends StackPane {
	/**
	 * Menügombon lévő szöveg.
	 */
	private Text text;

	/**
	 * Visszaadja a gombon lévő feliratot.
	 * 
	 * @return gomb felirata
	 */
	public Text getText() {
		return text;
	}
	
	/**
	 * Menügombot létrehozó konsruktor.
	 * 
	 * @param name gomb felirata
	 */
	public MenuButton(String name) {
		text = new Text(name);
		text.getFont();
		text.setFont(Font.font(20));
		text.setFill(Color.WHITE);

		Rectangle rectangle = new Rectangle(275, 30);
		rectangle.setOpacity(0.6);
		rectangle.setFill(Color.BLACK);
		rectangle.setEffect(new GaussianBlur(3.5));

		setAlignment(Pos.CENTER_LEFT);
		getChildren().addAll(rectangle, text);

		setOnMouseEntered(event -> {
			rectangle.setTranslateX(10);
			text.setTranslateX(10);
			rectangle.setFill(Color.WHITE);
			text.setFill(Color.BLACK);
		});

		setOnMouseExited(event -> {
			rectangle.setTranslateX(0);
			text.setTranslateX(0);
			rectangle.setFill(Color.BLACK);
			text.setFill(Color.WHITE);
		});

		DropShadow dropShadow = new DropShadow(50, Color.WHITE);
		dropShadow.setInput(new Glow());
		setOnMousePressed(event -> setEffect(dropShadow));
		setOnMouseReleased(event -> setEffect(null));
	}
}
