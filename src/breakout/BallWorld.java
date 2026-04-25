package breakout;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class BallWorld extends World{
	Score score;
	int lvl;
	public BallWorld() {
		setPrefSize(1000,800);
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
		Ball ball = new Ball();
		ball.setX(getWidth() / 2 - ball.getWidth() / 2);
		ball.setY(getHeight() / 2 - ball.getHeight() / 2);
		add(ball);
		Paddle pad = new Paddle();
		pad.setX(getWidth() / 3);
		pad.setY(getHeight() / 4);
		add(pad);
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				pad.setX(event.getX() - pad.getWidth() / 2);
			}
		});
		score = new Score();
		score.setX(100);
		score.setY(100);
		getChildren().add(score);
		LevelLoader.load(this, lvl);
		start();
	}
	public Score getScore() {
		return score;
	}

}
