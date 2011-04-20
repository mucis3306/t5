/**
 * 
 */
package mansfield.edu.parkerjt12.maze;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jay Parker
 * 
 *         License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 *         This is the JUnit test case that tests all the methods within
 *         MazeCircle and its superclass MazeShape After testing each method,
 *         they all are running correctly
 */
public class TestMazeCircle {
	// using the mazecircle class
	MazeCircle mc;

	@Before
	public void setUp() throws Exception {
		mc = new MazeCircle(5, 10);
	}

	// CAN NOT BE TESTED BECAUSE ITS A CONSTRUCTOR
	/*
	 * @Test public void testMazeCircle() {
	 * 
	 * }
	 */

	// CAN NOT BE TESTED BECAUSE ITS A CONSTRUCTOR
	/*
	 * @Test public void testMazeShape() {
	 * 
	 * }
	 */

	/**
	 * This method tests the getRow function that is contained in the MazeShape
	 * class
	 */
	@Test
	public void testGetRow() {
		assertTrue(mc.getRow() == 5);
	}

	/**
	 * This method tests the getCol function that is contained in the MazeShape
	 * class
	 */
	@Test
	public void testGetCol() {
		assertTrue(mc.getCol() == 10);
	}

	/**
	 * This method tests the getColor function that is contained in the
	 * MazeShape class
	 */
	@Test
	public void testGetColor() {
		mc.setColor("green");
		assertTrue(mc.getColor().equals(Color.green));
	}

	/**
	 * This method tests the getName function that is contained in the MazeShape
	 * class, the name is the shape
	 */
	@Test
	public void testGetName() {
		assertTrue(mc.getName().equals("circle"));
	}

	/**
	 * This method tests the setRow function that is contained in the MazeShape
	 * class
	 */
	@Test
	public void testSetRow() {
		mc.setRow(15);
		int myrow = mc.getRow();
		assertTrue(myrow == 15);
	}

	/**
	 * This method tests the setCol function that is contained in the MazeShape
	 * class
	 */
	@Test
	public void testSetCol() {
		mc.setCol(10);
		int mycol = mc.getCol();
		assertTrue(mycol == 10);
	}

	/**
	 * This method tests the SetColor function that is contained in the
	 * MazeShape class, this class receives a color property
	 */
	@Test
	public void testSetColorColor() {
		mc.setColor(Color.green);
		assertTrue(mc.getColor().equals(Color.green));
	}

	/**
	 * This method tests the setColor function that is contained in the
	 * MazeShape class, this method receives a string instead of a color
	 */
	@Test
	public void testSetColorString() {
		mc.setColor("blue");
		assertTrue(mc.getColor().equals(Color.blue));
	}

	/**
	 * This method tests the moveNorth function that is contained in the
	 * MazeShape class
	 */
	@Test
	public void testMoveNorth() {
		int myrow1 = mc.getRow();
		mc.moveNorth();
		int myrow2 = mc.getRow();
		assertTrue(myrow1 == myrow2 + 1);
	}

	/**
	 * This method tests the moveSouth function that is contained in the
	 * MazeShape class
	 */
	@Test
	public void testMoveSouth() {
		int myrow1 = mc.getRow();
		mc.moveSouth();
		int myrow2 = mc.getRow();
		assertTrue(myrow1 == myrow2 - 1);
	}

	/**
	 * This method tests the moveEast function that is contained in the
	 * MazeShape class
	 */
	@Test
	public void testMoveEast() {
		int mycol1 = mc.getCol();
		mc.moveEast();
		int mycol2 = mc.getCol();
		assertTrue(mycol1 == mycol2 - 1);
	}

	/**
	 * This method tests the moveWest function that is contained in the
	 * MazeShape class
	 */
	@Test
	public void testMoveWest() {
		int mycol1 = mc.getCol();
		mc.moveWest();
		int mycol2 = mc.getCol();
		assertTrue(mycol1 == mycol2 + 1);
	}

	/**
	 * This method tests the toString function that is contained in the
	 * MazeShape class that displays all the information regarding the circle
	 * position and color
	 */
	@Test
	public void testToString() {
		int row = mc.getRow();
		int col = mc.getCol();
		Color color = mc.getColor();
		String a = mc.toString();
		String b = "Row = " + row + " Column = " + col + " Color = " + color;
		assertTrue(a.equalsIgnoreCase(b));

	}

}
