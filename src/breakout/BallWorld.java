package breakout;
import engine.Actor;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import engine.Sound;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class BallWorld extends World{
	Score score;
	int lvl;
	int lives;
	Label livesLbl;
	Ball ball;
	Image bg;
	ImageView bgView;
	Sound bounceSound;
	Sound brickSound;
	Sound lossSound;
	boolean paused;
	javafx.scene.text.Text msg;
	public BallWorld() {
		setPrefSize(800,600);
		lvl = 1;
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		if(paused == true) {
			if(isKeyPressed(KeyCode.SPACE)) {
				paused = false;
				getChildren().remove(msg);
			}
			return;
		}
		if(getObjects(Brick.class).isEmpty() == true) {
			lvl++;
			if(lvl >= 3) {
				this.stop();
				Breakout.showTitle();
			}else {
				LevelLoader.load(this, lvl);
				paused = true;
				msg = new javafx.scene.text.Text("press space to start level" + lvl);
				msg.setX(getWidth() / 2);
				msg.setY(getHeight() / 2);
				getChildren().add(msg);
			}
		}
		if(ball != null && ball.getY() >= getHeight() - ball.getHeight()) {
			lives --;
			livesLbl.setText("Lives: " + lives);
			if(lives <= 0) {
				this.stop();
				Breakout.showTitle();
				
			}else {
				paused = true;
				msg = new javafx.scene.text.Text("press space to continue");
				msg.setX(getWidth() / 2);
				msg.setY(getHeight() / 2);
				getChildren().add(msg);
			}
		}
	}

	@Override
	protected void onDimensionsInitialized() {
		// TODO Auto-generated method stub
		
		bg = new Image(getClass().getClassLoader()
		        .getResource("breakoutresources/background.png").toString());
		bgView = new ImageView(bg);
		bgView.setX(-(bg.getWidth() - getWidth()) / 2);
		bgView.setY(0);
		getChildren().add(bgView);
		lives = 3;
		livesLbl = new Label("lives: " + lives);
		livesLbl.setLayoutX(getWidth() - 200);
		livesLbl.setLayoutY(75);
		getChildren().add(livesLbl);
		ball = new Ball();
		ball.setX(getWidth() / 2 - ball.getWidth() / 2);
		ball.setY(getHeight() / 2 - ball.getHeight() / 2);
		add(ball);
		Paddle pad = new Paddle();
		pad.setX(getWidth() / 3);
		pad.setY(6 * getHeight() / 7);
		add(pad);
		
//		setOnMouseMoved(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				pad.setX(event.getX() - pad.getWidth() / 2);
//			}
//		});
		score = new Score();
		score.setX(100);
		score.setY(100);
		getChildren().add(score);
		paused = true;
		msg = new javafx.scene.text.Text("press space to start");
		msg.setX(getWidth() / 2);
		msg.setY(getHeight() / 2);
		getChildren().add(msg);
		LevelLoader.load(this, lvl);
		start();
	}
	public Score getScore() {
		return score;
	}
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean pause) {
		paused = pause;
	}
	public void scroll(double dx) {
		// For now, only move the background by the OPPOSITE of dx.
		// For example, if dx was 5 then you would move the background by -5.
		double currX = bgView.getX() - dx;
		double min = -(bg.getWidth() - getWidth());
		double max = 0;
		if(currX >= min && currX <= max) {
			bgView.setX(bgView.getX() - dx);
			for(Actor actor: getObjects(Actor.class)) {
				actor.move(-dx,0);
			}
		}
	}


}
