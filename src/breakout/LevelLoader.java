package breakout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class LevelLoader {
	public static void load(BallWorld world, int lvl) {
		String fileName = "src/breakoutresources/level" + lvl + ".txt";
		try {
			File f = new File(fileName);
			Scanner in = new Scanner(f);
			int r = in.nextInt();
			int c = in.nextInt();
			double w = 50;
			double h = 25;
			for(int row = 0;row < r;row++) {
				String nextLine = in.nextLine();
				for(int col = 0;col < c;col++) {
					if(col < nextLine.length() && nextLine.charAt(col) == '1') {
						Brick b = new Brick();
						b.setFitWidth(w);
						b.setFitHeight(h);
						b.setX(col * w);
						b.setY(row * h + 100);
						world.add(b);
					}
				}
			}
			in.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
