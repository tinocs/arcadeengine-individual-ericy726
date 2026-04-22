package breakout;
import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
public class Paddle extends Actor{
	public Paddle() {
		setImage(new Image(getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString()));
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		if(getWorld().isKeyPressed(KeyCode.LEFT) == true) {
			if(getX() > 0) {
				move(-6,0);
			}
		}
		if(getWorld().isKeyPressed(KeyCode.RIGHT) == true) {
			if(getX() < getWorld().getWidth()) {
				move(6,0);
			}
		}
	}
	

}
