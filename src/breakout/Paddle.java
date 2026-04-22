package breakout;
import engine.Actor;
import javafx.scene.image.Image;
public class Paddle extends Actor{
	public Paddle() {
		setImage(new Image(getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString()));
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
