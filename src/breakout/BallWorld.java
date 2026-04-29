package breakout;
import engine.Actor;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
public class BallWorld extends World{
	Score score;
	int lvl;
	int lives;
	Label livesLbl;
	Ball ball;
	Image bg;
	ImageView bgView;
	public BallWorld() {
		setPrefSize(800,600);
		lvl = 1;
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		if(getObjects(Brick.class).isEmpty() == true) {
			lvl++;
			if(lvl >= 3) {
				this.stop();
				Breakout.showTitle();
			}else {
				LevelLoader.load(this, lvl);
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
		LevelLoader.load(this, lvl);
		start();
	}
	public void livesAct() {
		if(ball != null) {
			if(ball.getY() >= this.getHeight() - ball.getHeight() / 2) {
				
			}
		}
	}
	public Score getScore() {
		return score;
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
