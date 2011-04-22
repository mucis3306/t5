package mansfield.edu.liveseyjt25.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MVC Model: represents a player icon as a Princess image.
 * 
 * Princess.png image is public domain from clker.com at
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * http://www.clker.com/clipart-pcman-game-baddie-princess.html
 * 
 * @author Jason Livesey
 */
public class MazePrincess extends MazeShape {
	BufferedImage image = null;
	static String name = "princess";
	static final int size = 299;

	public MazePrincess(int myRow, int myCol) {
		super(myRow, myCol, name);
		try {
			java.net.URL imageURL = MazePrincess.class
					.getResource("/image/Princess.png");
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
