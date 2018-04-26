package View;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu extends Application {

	private GameMenu gameMenu;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new Pane();
		root.setPrefSize(1000, 800);

		InputStream inputStream = Files.newInputStream(
				Paths.get("C:\\Users\\James\\eclipse-workspace\\wumpus\\src\\main\\resources\\DBackground.jpg"));
		Image img = new Image(inputStream);
		inputStream.close();

		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(1000);
		imageView.setFitHeight(800);

		gameMenu = new GameMenu();
		gameMenu.setVisible(false);

		root.getChildren().addAll(imageView, gameMenu);

		Scene scene = new Scene(root);
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				if (!gameMenu.isVisible()) {
					FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), gameMenu);
					fadeTransition.setFromValue(0);
					fadeTransition.setToValue(1);
					gameMenu.setVisible(true);
					fadeTransition.play();
				}
			} else {
				FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), gameMenu);
				fadeTransition.setFromValue(0);
				fadeTransition.setToValue(1);
				fadeTransition.setOnFinished(evt -> gameMenu.setVisible(false));
				fadeTransition.play();
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private class GameMenu extends Parent {
		public GameMenu() {
			VBox mainMenu = new VBox(10);
			VBox newGameMenu = new VBox(10);
			VBox rulesMenu = new VBox(10);

			mainMenu.setTranslateX(700);
			mainMenu.setTranslateY(400);

			newGameMenu.setTranslateX(1200);
			newGameMenu.setTranslateY(250);
			
			rulesMenu.setTranslateX(1200);
			rulesMenu.setTranslateY(50);

			final int offset = 500;

			MenuButton startGameButton = new MenuButton("Start Game");
			startGameButton.setOnMouseClicked(event -> {
				getChildren().add(newGameMenu);

				TranslateTransition translateTransitionOnMainMenu = new TranslateTransition(Duration.seconds(0.25),
						mainMenu);
				translateTransitionOnMainMenu.setToX(mainMenu.getTranslateX() + offset);

				TranslateTransition translateTransitionOnStartGame = new TranslateTransition(Duration.seconds(0.5),
						newGameMenu);
				translateTransitionOnStartGame.setToX(newGameMenu.getTranslateX() - offset);

				translateTransitionOnMainMenu.play();
				translateTransitionOnStartGame.play();

				translateTransitionOnMainMenu.setOnFinished(ttevent -> {
					getChildren().remove(mainMenu);
				});
			});

			MenuButton rulesButton = new MenuButton("Rules");
			rulesButton.setOnMouseClicked(event -> {
				getChildren().add(rulesMenu);
				TranslateTransition translateTransitionOnMainMenu = new TranslateTransition(Duration.seconds(0.5),
						mainMenu);
				translateTransitionOnMainMenu.setToX(mainMenu.getTranslateX() + offset);
				
				TranslateTransition translateTransitionOnRuleMenu = new TranslateTransition(Duration.seconds(0.5),
						rulesMenu);
				translateTransitionOnRuleMenu.setToX(newGameMenu.getTranslateX() - 1150);
				
				translateTransitionOnMainMenu.play();
				translateTransitionOnRuleMenu.play();

				translateTransitionOnRuleMenu.setOnFinished(ttevent -> {
					getChildren().remove(mainMenu);
				});
			});
			RuleWindow rulesWindow = new RuleWindow(
					"These are the rules."
					);
			

			MenuButton leaderBoard = new MenuButton("Leaderboard");

			MenuButton exitButton = new MenuButton("Exit");
			exitButton.setOnMouseClicked(event -> {
				System.exit(0);
			});

			mainMenu.getChildren().addAll(startGameButton, rulesButton, leaderBoard, exitButton);
			Rectangle backGround = new Rectangle(1000, 800);
			backGround.setFill(Color.SLATEGRAY);
			backGround.setOpacity(0.4);

			getChildren().addAll(backGround, mainMenu);

			MenuButton firstButton = new MenuButton("First Level");
			MenuButton secondButton = new MenuButton("Second Level");
			MenuButton thirdButton = new MenuButton("Third Level");
			MenuButton fourthButton = new MenuButton("Fourth Level");
			MenuButton fifthButton = new MenuButton("Fifth Level");
			MenuButton sixthButton = new MenuButton("Sixth Level");
			MenuButton seventhButton = new MenuButton("Seventh Level");
			MenuButton eightButton = new MenuButton("Eight Level");
			MenuButton ninthButton = new MenuButton("Ninth Level");
			MenuButton backButton = new MenuButton("Back To MainMenu");
			MenuButton backFromRules = new MenuButton("Back To MainMenu");
			backFromRules.setOnMouseClicked(event -> {
				getChildren().add(mainMenu);

				TranslateTransition translateTransitionOnRules = new TranslateTransition(Duration.seconds(0.5),
						rulesMenu);
				translateTransitionOnRules.setToX(newGameMenu.getTranslateX() + offset);

				TranslateTransition translateTransitionOnMainMenu = new TranslateTransition(Duration.seconds(0.5),
						mainMenu);
				translateTransitionOnMainMenu.setToX(mainMenu.getTranslateX() - offset);

				translateTransitionOnRules.play();
				translateTransitionOnMainMenu.play();

				translateTransitionOnRules.setOnFinished(ttevent -> {
					getChildren().remove(rulesMenu);
				});
			});
			
			backButton.setOnMouseClicked(event -> {
				getChildren().add(mainMenu);

				TranslateTransition translateTransitionOnStartGame = new TranslateTransition(Duration.seconds(0.5),
						newGameMenu);
				translateTransitionOnStartGame.setToX(newGameMenu.getTranslateX() + offset);

				TranslateTransition translateTransitionOnMainMenu = new TranslateTransition(Duration.seconds(0.5),
						mainMenu);
				translateTransitionOnMainMenu.setToX(mainMenu.getTranslateX() - offset);

				translateTransitionOnStartGame.play();
				translateTransitionOnMainMenu.play();

				translateTransitionOnStartGame.setOnFinished(ttevent -> {
					getChildren().remove(newGameMenu);
				});
			});
			newGameMenu.getChildren().addAll(firstButton, secondButton, thirdButton, fourthButton, fifthButton,
					sixthButton, seventhButton, eightButton, ninthButton, backButton);
			rulesMenu.getChildren().addAll(rulesWindow,backFromRules);
		}
	}

	private static class RuleWindow extends StackPane {
		private Text text;

		public RuleWindow(String description) {
			text = new Text(description);
			text.setFont(text.getFont().font(20));
			text.setFill(Color.WHITE);

			Rectangle rectangle = new Rectangle(900, 700);
			rectangle.setOpacity(0.6);
			rectangle.setFill(Color.BLACK);
			rectangle.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.TOP_LEFT);
			getChildren().addAll(rectangle, text);
		}
	}

	private static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont().font(20));
			text.setFill(Color.WHITE);

			Rectangle rectangle = new Rectangle(250, 30);
			rectangle.setOpacity(0.6);
			rectangle.setFill(Color.BLACK);
			rectangle.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.CENTER_LEFT);
			setRotate(-0.5);
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

	public static void main(String[] args) {
		launch(args);
	}
}