package breakout;
import engine.Actor;
import javafx.scene.image.Image;
public class Brick extends Actor{
	public Brick() {
		setImage(new Image(getClass().getClassLoader().getResource("breakoutresources/brick.png").toString()));
		this.setPreserveRatio(false);
	}
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
