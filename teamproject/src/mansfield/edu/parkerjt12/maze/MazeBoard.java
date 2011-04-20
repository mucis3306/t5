package mansfield.edu.parkerjt12.maze;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * MVC Model: generates a maze board as a 2D array of chars.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class MazeBoard implements MazeBoardInterface {
	private char[][] mArray;
	//private ArrayList<Dimension> startList;
	private ArrayList<Dimension> pStartList, rStartList;
	private int rstartRow = 1;
	private int rstartCol = 1;
	//private int startRow = 1; 
	private int pstartRow = 1;
	//private int startCol = 1; 
	private int pstartCol = 1;
	private int endRow, endCol, robots;
	private String version;
	private static Random gen = new Random();

	public MazeBoard(String fileName) {
		createBoard(fileName);
		endCol = mArray[0].length;
		endRow = mArray.length;
	}

	/**
	 * Creates the board for players to play on
	 */
	// maze files will have version number, height, width, number of robots
	@Override
	public void createBoard(String fileName) {
		Scanner sc = new Scanner(
				MazeBoard.class.getResourceAsStream("/maze/" + fileName));
		version = sc.nextLine();
		endRow = sc.nextInt();
		sc.nextLine();
		endCol = sc.nextInt();
		sc.nextLine();
		robots = sc.nextInt();
		sc.nextLine();
		System.out.println("r=" + endRow + "c" + endCol);
		mArray = new char[endRow][endCol];
		for (int r = 0; r < endRow; r++)
			mArray[r] = sc.nextLine().toCharArray();

		// vvv delete below vvv//
		//makeStartList();
		//chooseRandomStart();
		// ^^^ delete above ^^^//
		// //////////////////////
		
		makePStartList();
		choosePRandomStart();

		makeRStartList();
		chooseRRandomStart();
		// /////////////////////
	}

	// //////////////////////////////////////////////////////////
	
	 private void makePStartList() 
	 { 
		pStartList = new ArrayList<Dimension>();
		for (int row = 1; row < endRow - 1; row++) 
	 	{ 
		 	for (int col = 1; col < endCol - 1; col++)
		 	{ 
			 	if (mArray[row][col] == 's') 
			 		pStartList.add(new Dimension(row, col)); 
		 	} 
	 	} 
	 	pStartList.trimToSize(); 
	 }
	 
	////////////////////////////////////////////////////////////
	//////////////////vvvvv delete below vvvvvv//////////////////
	/**
	 * makes a list in an array
	 
	private void makeStartList() {
		startList = new ArrayList<Dimension>();
		for (int row = 1; row < endRow - 1; row++) {
			for (int col = 1; col < endCol - 1; col++) {
				if (mArray[row][col] == 's')
					startList.add(new Dimension(row, col));
			}
		}
		startList.trimToSize();
	}
	 */
	// ////////////////^^^^^^ delete above ^^^^^^//////////////////

	// ///////////////////////////////////////////////////////////
	// ////////////////vvvvv delete below vvvvvv//////////////////
	 /*
	private void chooseRandomStart() {
		Dimension dm = startList.get(gen.nextInt(startList.size()));
		startRow = (int) dm.getWidth();
		startCol = (int) dm.getHeight();
	}

	*/
	// ////////////////^^^^^^ delete above ^^^^^^//////////////////
	// ////////////////////////////////////////////////////////////

	
	 private void choosePRandomStart() 
	 { 
		 Dimension dm1 = pStartList.get(gen.nextInt(pStartList.size())); 
		 pstartRow = (int)
		 dm1.getWidth(); 
		 pstartCol = (int) dm1.getHeight(); 
	 }
	 
	 private void makeRStartList() 
	 { 
		 rStartList = new ArrayList<Dimension>();
		 for (int row = 1; row < endRow - 1; row++) 
		 { 
			 for (int col = 1; col < endCol - 1; col++) 
			 { 
				 if (mArray[row][col] == 'r') rStartList.add(new Dimension(row, col)); 
				 } 
			 } rStartList.trimToSize(); 			 
	 }
	 
	 private void chooseRRandomStart() 
	 { 
		 Dimension dm2 = rStartList.get(gen.nextInt(rStartList.size()));
		 rstartRow = (int) dm2.getWidth(); 
		 rstartCol = (int) dm2.getHeight(); 
	 }
	 

	
	 public int getPStartRow() { return pstartRow; }
	 
	 public int getPStartCol() { return pstartCol; }
	 
	 public int getRStartRow() { return rstartRow; }
	 
	 public int getRStartCol() { return rstartCol; }
	 
	 

	/**
	 * boolean function to tell if the player got to the finish
	 */
	@Override
	public boolean isFinish(int row, int col) {
		if (mArray[row][col] == 'e') {
			return true;
		}
		return false;
	}

	/**
	 * boolean function to tell if the player goes to a fake finish
	 * 
	 * Implemented a ? as a false finish, when the player hits the false finish,
	 * the are sent back to a random starting position
	 */
	@Override
	public boolean isFalseFinish(int row, int col) {
		if (mArray[row][col] == '?') {
			return true;
		}
		return false;
	}

	/**
	 * boolean function to tell if the player has made a vaild move
	 */
	@Override
	public boolean isValidMove(int row, int col) {
		char c = mArray[row][col];
		if (c == '-' || c == 's' || c == 'e' || c == '?') {
			return true;
		}
		return false;
	}

	/**
	 * toString method that returns a string about the starting position of the
	 * rows and cols
	 */
	@Override
	public String toString() {
		return "pstartRow" + getPStartRow() + " pstartCol" + getPStartCol() + "rstartRow" + getRStartRow() + " rstartCol" + getRStartCol();
	}

	/**
	 * tells what version of maze is currently used during that level
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * returns a character from the array of the board
	 */
	@Override
	public char getChar(int row, int col) {
		return mArray[row][col];
	}

	/**
	 * returns the end row of the board
	 */
	@Override
	public int getEndRow() {
		return endRow - 1;
	}

	/**
	 * returns the end col of the board
	 */
	@Override
	public int getEndCol() {
		return endCol - 1;
	}
}
