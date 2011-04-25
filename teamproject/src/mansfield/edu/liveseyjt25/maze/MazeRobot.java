package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a robot player that wanders through the maze.
 * 
 * Monster1.png image is public domain from clker.com at 
 * http://www.clker.com/clipart-15325.html
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeRobot extends MazeShape {
	BufferedImage image = null;
	private Random gen = new Random();
	private AController control;
	private AView view;
	static String name1 = "robot1";
	static String name2 = "robot2";
	static String name3 = "robot3";
	static final int size = 170;

	public MazeRobot(int myRow, int myCol) {
		super(myRow, myCol, name1);
		try {
			java.net.URL imageURL = MazeRobot.class
					.getResource("/image/pixel-man.png");
				
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
