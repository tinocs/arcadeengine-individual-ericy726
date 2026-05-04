package breakout;
import engine.Actor;
import engine.Sound;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
public class Ball extends Actor{
	double dx;
	double dy;
	Sound bounceSound;
	Sound brickSound;
	Sound lossSound;
	public Ball() {
		setImage(new Image(getClass().getClassLoader().getResource("breakoutresources/ball.png").toString()));
		dx = 2;
		dy = 2;
		bounceSound = new Sound("/breakoutresources/ball_bounce.wav");
		brickSound = new Sound("/breakoutresources/brick_hit.wav");
		lossSound = new Sound("/breakoutresources/lose_life.wav");
		
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
			bounceSound.play();
		}else if(getX() + getWidth() >= getWorld().getWidth()) {
			dx = -dx;
			bounceSound.play();
		}
		if(getY() <= 0) {
			dy = -dy;
			bounceSound.play();
		}else if(getY() + getHeight() >= getWorld().getHeight()) {
			dy = -dy;
			bounceSound.play();
		}
		Paddle pad = getOneIntersectingObject(Paddle.class);
		if(pad != null) {
			dy = -dy;
			bounceSound.play();
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
			brickSound.play();
			FadeTransition fader = new FadeTransition(Duration.millis(200), brick);
			fader.setFromValue(1);
			fader.setToValue(0);
			ScaleTransition scaler = new ScaleTransition(Duration.millis(200),brick);
			scaler.setFromX(1);
			scaler.setToX(1.5);
			scaler.setFromY(1);
			scaler.setToY(1.5);
			ParallelTransition pTrans = new ParallelTransition(fader,scaler);
			pTrans.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					getWorld().remove(brick);
				}
				
			});
			pTrans.play();
			bw.getScore().setScore(bw.getScore().getScore() + 100);
		}
		
		
	}

}
