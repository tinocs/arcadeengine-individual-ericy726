package breakout;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class BallWorld extends World{
	public BallWorld() {
		setPrefSize(1000,800);
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
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

		start();
	}

}
