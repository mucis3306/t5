package mansfield.edu.parkerjt12.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a robot player that wanders through the maze.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 */
public class MazeGingerRobot extends MazeShape {
	BufferedImage image = null;
	private Random gen = new Random();
	private AController control;
	private AView view;
	static String name1 = "ginger1";
	static String name2 = "ginger2";
	static String name3 = "ginger3";
	static final int size = 297;

	public MazeGingerRobot(int myRow, int myCol) {
		super(myRow, myCol, name1);
		try {
			java.net.URL imageURL = MazeGingerRobot.class
					.getResource("/image/GF Character R.png");

			image = ImageIO.read(imageURL);

		} catch (IOException ioe) {
		}
	}

	public int getSize() {
		return size;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void moveRobot(MazeBoard board) {
		int dir;

		dir = gen.nextInt(4);

		if (dir == 0) {
			if (board.isValidMove(getRow() - 1, getCol()) == true) {
				moveNorth();
			}
		} else if (dir == 1) {
			if (board.isValidMove(getRow(), getCol() + 1) == true) {
				moveEast();
			}
		} else if (dir == 2) {
			if (board.isValidMove(getRow() + 1, getCol()) == true) {
				moveSouth();
			}
		} else if (dir == 3) {
			if (board.isValidMove(getRow(), getCol() - 1) == true) {
				moveWest();
			}
		}
	}
}
