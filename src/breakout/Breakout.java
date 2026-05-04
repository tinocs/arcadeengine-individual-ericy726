package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application {
	static Stage primaryStage;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage = stage;
		primaryStage.setTitle("breakout");
		showTitle();
		primaryStage.show();
	}
	public static void showGameOver() {
		Alert lose = new Alert(AlertType.INFORMATION, "You Lose!", ButtonType.OK);
		lose.show();
		Breakout.showTitle();
	}
	public static void showWin() {
		Alert win = new Alert(AlertType.INFORMATION, "You Win!", ButtonType.OK);
		win.show();
		Breakout.showTitle();
	}
	public static void showTitle() {
		TitleScreen screen = new TitleScreen();
		Scene scene = new Scene(screen,1000,800);
		primaryStage.setScene(scene);
	}
	public static void startGame() {
		BorderPane rootPane = new BorderPane();
		BallWorld world = new BallWorld();
		rootPane.setCenter(world);
		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		world.start();
	}

}
