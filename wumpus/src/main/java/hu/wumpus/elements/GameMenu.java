package hu.wumpus.elements;

import hu.wumpus.control.GridManager;
import hu.wumpus.database.DataBaseManager;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * A játéknak a menüjeit reprezentáló osztály.
 * 
 * @author James
 *
 */
public class GameMenu extends Parent {
	
	/**
	 * Játékmenüt létrehozó konstruktor.
	 */
	public GameMenu() {
		VBox mainMenu = new VBox(10);
		VBox newGameMenu = new VBox(10);
		VBox rulesMenu = new VBox(10);
		VBox inGameMenu = new VBox(10);
		VBox leaderBoardMenu = new VBox(10);
		mainMenu.setTranslateX(900);
		mainMenu.setTranslateY(400);
		newGameMenu.setTranslateX(1400);
		newGameMenu.setTranslateY(250);
		rulesMenu.setTranslateX(1300);
		rulesMenu.setTranslateY(100);
		inGameMenu.setTranslateX(1300);
		inGameMenu.setTranslateY(0);
		leaderBoardMenu.setTranslateX(900);
		leaderBoardMenu.setTranslateY(100);
		final int offset = 500;
		Rectangle backGround = new Rectangle(1200, 900);
		backGround.setFill(Color.SLATEGRAY);
		backGround.setOpacity(0.4);
		getChildren().addAll(backGround, mainMenu);
		DataBaseManager manager = new DataBaseManager();
		RuleWindow rulesWindow = new RuleWindow(manager.getTilesLog(10));
		GridManager gridManager = new GridManager();

		MenuButton backFromInGame = new MenuButton("Back To The Menu");
		backFromInGame.setTranslateX(950);
		backFromInGame.setTranslateY(-100);
		backFromInGame.setOnMouseClicked(event -> {
			animation(inGameMenu, newGameMenu, 1300, offset);
		});

		MenuButton firstButton = new MenuButton("First.Level");
		firstButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(firstButton.getText().getText()), backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton secondButton = new MenuButton("Second.Level");
		secondButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(secondButton.getText().getText()),
					backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton thirdButton = new MenuButton("Third.Level");
		thirdButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(thirdButton.getText().getText()), backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton fourthButton = new MenuButton("Fourth.Level");
		fourthButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(fourthButton.getText().getText()),
					backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton fifthButton = new MenuButton("Fifth.Level");
		fifthButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(fifthButton.getText().getText()), backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});
		MenuButton sixthButton = new MenuButton("Sixth.Level");
		sixthButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(sixthButton.getText().getText()), backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton seventhButton = new MenuButton("Seventh.Level");
		seventhButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(seventhButton.getText().getText()),
					backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton eightButton = new MenuButton("Eight.Level");
		eightButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(eightButton.getText().getText()), backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton ninthButton = new MenuButton("Ninth.Level");
		ninthButton.setOnMouseClicked(event -> {
			inGameMenu.getChildren().clear();
			inGameMenu.getChildren().addAll(gridManager.createContent(ninthButton.getText().getText()), backFromInGame);
			animation(newGameMenu, inGameMenu, offset, 1300);
		});

		MenuButton startGameButton = new MenuButton("Start Game");
		startGameButton.setOnMouseClicked(event -> {
			animation(mainMenu, newGameMenu, offset, offset);
		});

		MenuButton rulesButton = new MenuButton("Rules");
		rulesButton.setOnMouseClicked(event -> {
			animation(mainMenu, rulesMenu, offset, 1250);
		});

		MenuButton backFromLeaderBoard = new MenuButton("Back To MainMenu");
		backFromLeaderBoard.setTranslateY(30);
		backFromLeaderBoard.setAlignment(Pos.CENTER);
		backFromLeaderBoard.setOnMouseClicked(event -> {
			animation(leaderBoardMenu, mainMenu, offset, offset);
		});

		MenuButton leaderBoard = new MenuButton("Leaderboard");
		leaderBoard.setOnMouseClicked(event -> {
			leaderBoardMenu.getChildren().clear();
			LeaderBoardElement leaderBoardElement = new LeaderBoardElement();
			leaderBoardMenu.getChildren().addAll(leaderBoardElement, backFromLeaderBoard);
			animation(mainMenu, leaderBoardMenu, offset, offset);
		});

		MenuButton exitButton = new MenuButton("Exit");
		exitButton.setOnMouseClicked(event -> {
			System.exit(0);
		});

		MenuButton backFromRules = new MenuButton("Back To MainMenu");
		backFromRules.setOnMouseClicked(event -> {
			animation(rulesMenu, mainMenu, 1250, offset);
		});

		MenuButton backButton = new MenuButton("Back To MainMenu");
		backButton.setOnMouseClicked(event -> {
			animation(newGameMenu, mainMenu, offset, offset);
		});

		mainMenu.getChildren().addAll(startGameButton, rulesButton, leaderBoard, exitButton);
		newGameMenu.getChildren().addAll(firstButton, secondButton, thirdButton, fourthButton, fifthButton, sixthButton,
				seventhButton, eightButton, ninthButton, backButton);
		rulesMenu.getChildren().addAll(rulesWindow, backFromRules);
	}
	
	/**
	 * Menük közti átváltásra szolgáló animáció.
	 * 
	 * @param out az régi menü
	 * @param in a új menü
	 * @param outValue régi menü kiúsztatásának paramétere
	 * @param inValue új menü behúzsának paramétere
	 */
	public void animation(VBox out, VBox in, int outValue, int inValue) {
		getChildren().add(in);

		TranslateTransition outTransition = new TranslateTransition(Duration.seconds(0.5), out);
		outTransition.setToX(out.getTranslateX() + outValue);

		TranslateTransition inTransition = new TranslateTransition(Duration.seconds(0.5), in);
		inTransition.setToX(in.getTranslateX() - inValue);

		outTransition.play();
		inTransition.play();

		outTransition.setOnFinished(ttevent -> {
			getChildren().remove(out);
		});
	}
}
