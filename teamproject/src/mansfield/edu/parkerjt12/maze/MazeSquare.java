package mansfield.edu.parkerjt12.maze;

/**
 * MVC Model: represents a square icon.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
// it was added to give the player another option
// to use to navigate the maze
public class MazeSquare extends MazeShape {
	static String name = "square";

	public MazeSquare(int myRow, int myCol) {
		super(myRow, myCol, name);
	}
}
