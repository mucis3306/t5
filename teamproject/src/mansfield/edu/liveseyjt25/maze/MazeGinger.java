package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a Soldier image.
 * 
 * Borg.png image is public domain from clker.com at
 * http://www.clker.com/clipart-pcman-game-baddie-borg.html
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeGinger extends MazeShape {
	BufferedImage image = null;
	static String name = "ginger";
	static final int size = 350;

	public MazeGinger(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazeGinger.class
					.getResource("/image/g.png");
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
