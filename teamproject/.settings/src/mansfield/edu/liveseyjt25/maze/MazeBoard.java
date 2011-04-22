package mansfield.edu.liveseyjt25.maze;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * MVC Model: generates a maze board as a 2D array of chars.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class MazeBoard implements MazeBoardInterface {
	private char[][] mArray;
	private ArrayList<Dimension> pStartList, rStartList;
	private int pstartRow = 1;
	private int pstartCol = 1;
	private int rstartRow = 1;
	private int rstartCol = 1;
	private int endRow, endCol, robots;
	private String version;
	private static Random gen = new Random();

	public MazeBoard(String fileName) {
		createBoard(fileName);
		endCol = mArray[0].length;
		endRow = mArray.length;
	}

	@Override
	public void createBoard(String fileName) {
		Scanner sc = new Scanner(
				MazeBoard.class.getResourceAsStream("/maze/" + fileName));
		version = sc.nextLine();
		endRow = sc.nextInt();
		sc.nextLine();
		endCol = sc.nextInt();
		sc.nextLine();
		robots =  sc.nextInt();
		sc.nextLine();
		System.out.println("r=" + endRow + "c" + endCol);
		mArray = new char[endRow][endCol];
		for (int r = 0; r < endRow; r++)
			mArray[r] = sc.nextLine().toCharArray();

		makePStartList();
		choosePRandomStart();
		
		makeRStartList();
		chooseRRandomStart();
	}

	private void makePStartList() {
		pStartList = new ArrayList<Dimension>();
		for (int row = 1; row < endRow - 1; row++) {
			for (int col = 1; col < endCol - 1; col++) {
				if (mArray[row][col] == 's')
					pStartList.add(new Dimension(row, col));
			}
		}
		pStartList.trimToSize();
	}

	private void choosePRandomStart() {
		Dimension dm1 = pStartList.get(gen.nextInt(pStartList.size()));
		pstartRow = (int) dm1.getWidth();
		pstartCol = (int) dm1.getHeight();
	}
	
	private void makeRStartList() {
		rStartList = new ArrayList<Dimension>();
		for (int row = 1; row < endRow - 1; row++) {
			for (int col = 1; col < endCol - 1; col++) {
				if (mArray[row][col] == 'r')
					rStartList.add(new Dimension(row, col));
			}
		}
		rStartList.trimToSize();
	}
	
	private void chooseRRandomStart() {
		Dimension dm2 = rStartList.get(gen.nextInt(rStartList.size()));
		rstartRow = (int) dm2.getWidth();
		rstartCol = (int) dm2.getHeight();
	}

	@Override
	public boolean isFinish(int row, int col) {
		if (mArray[row][col] == 'e') {
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidMove(int row, int col) {
		char c = mArray[row][col];
		if (c == '-' || c == 's' || c =='r' || c == 'e') {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "pstartRow" + getPStartRow() + " pstartCol" + getPStartCol() + 
		       "rstartRow" + getRStartRow() + " rstartCol" + getRStartCol();
	}

	@Override
	public String getVersion() {
		return version;
	}

	public int getPStartRow() {
		return pstartRow;
	}

	public int getPStartCol() {
		return pstartCol;
	}
	
	public int getRStartRow() {
		return rstartRow;
	}

	public int getRStartCol() {
		return rstartCol;
	}

	@Override
	public char getChar(int row, int col) {
		return mArray[row][col];
	}

	@Override
	public int getEndRow() {
		return endRow - 1;
	}

	@Override
	public int getEndCol() {
		return endCol - 1;
	}

	@Override
	public int getStartRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStartCol() {
		// TODO Auto-generated method stub
		return 0;
	}
}
