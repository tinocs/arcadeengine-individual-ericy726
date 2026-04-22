package breakout;
import engine.World;
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
		start();
	}

}
