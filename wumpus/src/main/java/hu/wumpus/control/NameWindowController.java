package hu.wumpus.control;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import hu.wumpus.database.DataBaseManager;
import hu.wumpus.elements.GameMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Játékosnak a nevét bekérő ablakot irányító oztály.
 * 
 * @author James
 *
 */
public class NameWindowController implements Initializable {
	
	/**
	 * A játék menüje.
	 */
	private GameMenu gameMenu;

	/**
	 * A nevet bekérő ablak beviteli mezője.
	 */
	@FXML
	private TextField nameInputPanel;
	
	/**
	 * A nevet lementő gomb.
	 */
	@FXML
	private Button nameAcceptButton;
	
	/**
	 * A gombra való kattintást kezelő függvény.
	 * 
	 * @param event kattintási esemény
	 * @throws IOException a IO kivétel
	 * @throws URISyntaxException URI kivétel
	 */
	@FXML
	private void getNameFromForm(ActionEvent event) throws IOException, URISyntaxException {
		
		DataBaseManager manager = new DataBaseManager();
		if (nameInputPanel.getText() != null) {
			manager.setName(nameInputPanel.getText());
		} else {
			manager.setName("Player");
		}

		Stage primaryStage = new Stage();

		Pane root = new Pane();
		root.setPrefSize(1200, 900);

		URL url = getClass().getResource("/DBackground.jpg");
		InputStream inputStream = Files.newInputStream(Paths.get(url.toURI()));		
		Image img = new Image(inputStream);
		inputStream.close();

		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(1200);
		imageView.setFitHeight(900);

		gameMenu = new GameMenu();

		root.getChildren().addAll(imageView, gameMenu);
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show ();
		Stage st = (Stage) nameAcceptButton.getScene().getWindow();
		st.close();

		
	}
	/**
	 * Örökölt függvény, amely az inicializáláshoz szükséges.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}
