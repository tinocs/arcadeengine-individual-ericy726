package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
public abstract class World extends Pane{
	
	private AnimationTimer myTimer;
	private boolean stop;
	private Set<KeyCode> pressed;
	private boolean widthInit;
	private boolean heightInit;
	private boolean dimensionInit;
	public World() {
		pressed = new HashSet<>();
		widthInit = false;
		heightInit = false;
		dimensionInit = false;
		stop = true;
		widthProperty().addListener(new WidthListener());
		heightProperty().addListener(new HeightListener());
		setOnKeyPressed(e -> pressed.add(e.getCode()));
		setOnKeyReleased(e-> pressed.remove(e.getCode()));
		sceneProperty().addListener(new SceneListener());
		myTimer = new GameTimer();
		
	}
	public void start() {
		myTimer.start();
		stop = false;
	}
	public void stop() {
		myTimer.stop();
		stop = true;
	}
	public boolean isStopped() {
		return stop;
	}
	public void add(Actor actor) {
		getChildren().add(actor);
		actor.addedToWorld();
		
	}
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls){
		List<A> objects = new ArrayList<>();
		for(int i = 0;i<getChildren().size();i++) {
			if(cls.isInstance(getChildren().get(i)) == true) {
				objects.add(cls.cast(getChildren().get(i)));
			}
		}
		return objects;
	}
	public <A extends Actor> java.util.List<A> getObjectsAt(double x,
            double y,
            java.lang.Class<A> cls){
		List<A> objects = new ArrayList<>();
		for(A actor:getObjects(cls)) {
			if(actor.getBoundsInParent().contains(x,y) == true) {
				objects.add(actor);
			}
		}
		return objects;
	}
	public boolean isKeyPressed(KeyCode code) {
		if(pressed.contains(code) == true) {
			return true;
		}
		return false;
	}
	private class GameTimer extends AnimationTimer{

		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			act(now);
			for(Actor actor:getObjects(Actor.class)) {
				if(getChildren().contains(actor) == true) {
					actor.act(now);
				}
			}
		}
		
	}
	private class WidthListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			// TODO Auto-generated method stub
			if(!widthInit == true && newValue.doubleValue() > 0) {
				widthInit = true;
				if(widthInit == true && heightInit == true && !dimensionInit == true) {
					dimensionInit = true;
					onDimensionsInitialized();
				}
			}
			
		}
		
	}
	private class HeightListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			// TODO Auto-generated method stub
			if(!heightInit == true && newValue.doubleValue() > 0) {
				heightInit = true;
				if(widthInit == true && heightInit == true && !dimensionInit == true) {
					dimensionInit = true;
					onDimensionsInitialized();
				}
			}
			
		}
		
	}
	
	private class SceneListener implements ChangeListener<Scene>{

		@Override
		public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
			// TODO Auto-generated method stub
			if(newValue != null) {
				requestFocus();
			}
		}
		
	}
	public abstract void act(long now);
	protected abstract void onDimensionsInitialized();
	
}