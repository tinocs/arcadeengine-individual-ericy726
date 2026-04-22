package breakout;
import engine.Actor;
import javafx.scene.image.Image;
public class Ball extends Actor{
	double dx;
	double dy;
	public Ball() {
		setImage(new Image(getClass().getClassLoader().getResource("breakoutresources/ball.png").toString()));
		dx = 4;
		dy = 4;
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
