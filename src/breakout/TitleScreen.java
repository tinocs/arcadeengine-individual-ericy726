package breakout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TitleScreen extends VBox{
	public TitleScreen() {
		setAlignment(Pos.CENTER);
		setSpacing(50);
		setPrefSize(1000,800);
		Text title = new Text("Breakout");
		title.setFont(new Font(80));
		Button play = new Button("play");
		play.setOnAction(new PlayHandler());
		getChildren().addAll(title,play);
	}
	private class PlayHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			Breakout.startGame();
		}
		
	}
}
