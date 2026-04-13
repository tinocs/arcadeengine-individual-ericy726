package engine;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{
	public Actor() {
		super();
	}
	public abstract void act(long now);
	public void move(double dx,
            double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	public World getWorld() {
		return (World)(getParent());
	}
	public double getWidth() {
		return this.getBoundsInParent().getWidth();
	}
	public double getHeight() {
		return this.getBoundsInParent().getHeight();
	}
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
		java.util.List<A> objects = new ArrayList<>();
		for(Actor actor: this.getWorld().getObjects(cls)) {
			if(actor != this && actor.getBoundsInParent().intersects(getBoundsInParent()) == true) {
				objects.add(cls.cast(actor));
			}
		}
		return objects;
	}
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		for(Actor actor: this.getWorld().getObjects(cls)) {
			if(actor != this && actor.getBoundsInParent().intersects(getBoundsInParent()) == true) {
				return cls.cast(actor);
			}
		}
		return null;
	}
	public void addedToWorld() {
		
	}
}
