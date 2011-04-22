package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a Ninja image.
 * 
 * Ninja.png image is public domain from clker.com at
 * http://www.clker.com/clipart-pcman-game-baddie-ninja.html
 * 
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeNinja extends MazeShape {
	BufferedImage image = null;
	static String name = "ninja";
	static final int size = 298;

	public MazeNinja(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeNinja.class
					.getResource("/image/Ninja.png");
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
