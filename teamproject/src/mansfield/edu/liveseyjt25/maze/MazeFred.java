package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a Soldier image.
 * 
 * Fred.png image is public domain from clker.com at
 * http://www.clker.com/clipart-pcman-game-baddie-geek.html
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeFred extends MazeShape {
	BufferedImage image = null;
	static String name = "fred";
	static final int size = 300;

	public MazeFred(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeFred.class
					.getResource("/image/Fred.png");
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
