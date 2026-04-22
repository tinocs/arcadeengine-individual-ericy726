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
		move(dx,dy);
		if(getX() <= 0) {
			dx = -dx;
		}else if(getX() + getWidth() >= getWorld().getWidth()) {
			dx = -dx;
		}
		if(getY() <= 0) {
			dy = -dy;
		}else if(getY() + getHeight() >= getWorld().getHeight()) {
			dy = -dy;
		}
		Paddle pad = getOneIntersectingObject(Paddle.class);
		if(pad != null) {
			dy = -dy;
		}
	}

}
