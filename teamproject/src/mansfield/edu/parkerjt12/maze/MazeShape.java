package mansfield.edu.parkerjt12.maze;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * MVC Model: abstract class for player shape and image icons. Keeps track of
 * position of icon in the maze.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public abstract class MazeShape {
	private int row;
	private int col;
	private Color color;
	private String name;

	/**
	 * Default constructor for the maze shape
	 */
	public MazeShape(int myRow, int myCol, String myName) {
		row = myRow;
		col = myCol;
		name = myName;
		setColor("blue");
	}

	/**
	 * returns the current row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the current col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * returns the current color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * returns the current name of the shape
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the current row to the given value that is passed into the method
	 * 
	 * @param r
	 */
	public void setRow(int r) {
		row = r;
	}

	/**
	 * set the current col to the given value that is passed into the method
	 * 
	 * @param c
	 */
	public void setCol(int c) {
		col = c;
	}

	/**
	 * set the current color to the given value that is passed into the method
	 * 
	 * @param c
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * set the current color to the given string that is passed into the method
	 * 
	 * @param colorName
	 */
	public void setColor(String colorName) {
		try {
			Field field = Class.forName("java.awt.Color")
					.getField(colorName);
			color = (Color) field.get(null);
		} catch (Exception ex) {
			color = Color.red;
		}
	}

	/**
	 * moves player up
	 */
	public void moveNorth() {
		row -= 1;
	}

	/**
	 * moves player down
	 */
	public void moveSouth() {
		row += 1;
	}

	/**
	 * moves player right
	 */
	public void moveEast() {
		col += 1;
	}

	/**
	 * moves player left
	 */
	public void moveWest() {
		col -= 1;
	}

	/**
	 * tells the current row, column, and color of the player shape
	 */
	@Override
	public String toString() {
		return ("Row = " + row + " Column = " + col + " Color = " + color);
	}
}
