package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a Soldier image.
 * 
 * Dino.png image is public domain from clker.com at
 * http://www.clker.com/clipart-cartoon-stegosaurus.html
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeDino extends MazeShape {
	BufferedImage image = null;
	static String name = "dinosaur";
	static final int size = 300;

	public MazeDino(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeDino.class
					.getResource("/image/Dino.png");
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
}
