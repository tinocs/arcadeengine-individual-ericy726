package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text{
	int score;
	public Score() {
		score = 0;
		setFont(Font.font(24));
		updateDisplay();
	}
	public int getScore() {
		return score;
	}
	public void setScore(int newScore) {
		score = newScore;
		updateDisplay();
	}
	public void updateDisplay() {
		setText("Score: " + score);
	}
}
