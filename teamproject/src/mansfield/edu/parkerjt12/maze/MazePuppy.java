package mansfield.edu.parkerjt12.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a puppy image.
 * 
 * Puppy image by John Phillips
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class MazePuppy extends MazeShape {
	BufferedImage image = null;
	static String name = "puppy";
	static final int size = 90;

	// / change to ginger req.
	/**
	 * reads the image into the puppy class
	 */
	public MazePuppy(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazePuppy.class
					.getResource("/image/puppygrass.jpg");
			image = ImageIO.read(imageURL);
		} catch (IOException ioe) {
		}
	}

	/**
	 * returns the size of the image as an int
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the loaded image
	 */
	public BufferedImage getImage() {
		return image;
	}
}
