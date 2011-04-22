package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a puppy image.
 * 
 * Fox.png image is public domain from clker.com at
 * http://www.clker.com/clipart-24687.html
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeFox extends MazeShape {
	BufferedImage image = null;
	static String name = "fox";
	static final int size = 299;

	public MazeFox(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeFox.class
					.getResource("/image/Fox.png");
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
