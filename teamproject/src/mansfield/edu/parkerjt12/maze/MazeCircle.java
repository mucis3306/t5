package mansfield.edu.parkerjt12.maze;

/**
 * MVC Model: represents a circle icon.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class MazeCircle extends MazeShape {
	static String name = "circle";

	public MazeCircle(int myRow, int myCol) {
		super(myRow, myCol, name);
	}
}