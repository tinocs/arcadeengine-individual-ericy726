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
		BallWorld bw = (BallWorld)(getWorld());
		if(bw.paused == true) {
			Paddle pad = getOneIntersectingObject(Paddle.class);
			if(pad == null) {
				java.util.List<Paddle> pads = getWorld().getObjects(Paddle.class);
				if(!pads.isEmpty() == true) {
					pad = pads.get(0);
				}
			}
			if(pad != null) {
				setX(pad.getX() + pad.getWidth() / 2 - getWidth() / 2);
				setY(pad.getY() - getHeight() - 2);
			}
			return;
		}
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
		Brick brick = getOneIntersectingObject(Brick.class);
		if(brick != null) {
			boolean leftR = false;;
			boolean topB = false;;
			if(getX() >= brick.getX() && getX() + getWidth() <= brick.getX() + brick.getWidth()) {
				leftR = true;
			}
			if(getY() >= brick.getY() && getY() + getHeight() <= brick.getY() + brick.getHeight()) {
				topB = true;
			}
			if(leftR == true) {
				dy = - dy;
			}else if (topB == true) {
				dx = -dx;
			}else {
				dy = -dy;
				dx = -dx;
			}
			getWorld().remove(brick);
			bw.getScore().setScore(bw.getScore().getScore() + 100);
		}
		
		
	}

}
