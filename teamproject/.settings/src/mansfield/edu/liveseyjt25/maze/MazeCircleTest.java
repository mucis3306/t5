package mansfield.edu.liveseyjt25.maze;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class MazeCircleTest {
	
	MazeCircle mc;
	
	@Before
	public void setUp() throws Exception {
		mc = new MazeCircle(5,10);
	}

	@Test
	public void testGetRow() {
		assertTrue(mc.getRow()==5);
	}

	@Test
	public void testGetCol() {
		assertTrue(mc.getCol()==10);
	}

	@Test
	public void testGetColor() {
		mc.setColor("green");
		assertTrue(mc.getColor().equals(Color.green));
	}

	@Test
	public void testGetName() {
		assertTrue(mc.getName().equals("circle"));
	}

	@Test
	public void testSetRow() {
		mc.setRow(15);
		int myrow = mc.getRow();
		assertTrue(myrow == 15);
	}

	@Test
	public void testSetCol() {
		mc.setCol(15);
		int mycol = mc.getCol();
		assertTrue(mycol == 15);
	}

	@Test
	public void testSetColorColor() {
		mc.setColor("red");
		assertTrue(mc.getColor().equals(Color.red));
	}

	@Test
	public void testSetColorString() {
		mc.setColor("red");
		assertTrue(mc.getColor().equals(Color.red));
	}

	@Test
	public void testMoveNorth() {
		mc.moveNorth();
		assertTrue(mc.getRow()==4);
	}

	@Test
	public void testMoveSouth() {
		mc.moveSouth();
		assertTrue(mc.getRow()==6);
	}

	@Test
	public void testMoveEast() {
		mc.moveEast();
		assertTrue(mc.getCol()==11);
	}

	@Test
	public void testMoveWest() {
		mc.moveWest();
		assertTrue(mc.getCol()==9);
	}
}

