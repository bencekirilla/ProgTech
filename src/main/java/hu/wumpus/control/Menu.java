package hu.wumpus.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Az applikáció fő osztálya, ami elindítja az alkalmazást.
 * 
 * @author James
 *
 */
public class Menu extends Application {
	
	/**
	 * Az applikáció kezdését kezelő függvény.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/NameWindow.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	/**
	 * A main.
	 * 
	 * @param args program argumentumok
	 */
	public static void main(String[] args) {
		launch(args);
	}
}