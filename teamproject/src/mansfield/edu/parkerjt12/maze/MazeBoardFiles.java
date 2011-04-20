package mansfield.edu.parkerjt12.maze;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MVC Model: generates a list of maze file names.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 * 
 * 
 */
public class MazeBoardFiles {
	private ArrayList<String> mazeFileList;
	private int index;

	/**
	 * Creates the board for players to play on
	 */
	public MazeBoardFiles() {
		mazeFileList = new ArrayList<String>();
		createFileList();
		mazeFileList.trimToSize();
		index = 0;
	}

	/**
	 * Scans the board into the game
	 */
	private void createFileList() {
		Scanner sc = new Scanner(
				MazeBoardFiles.class.getResourceAsStream("/mazelist.txt"));
		while (sc.hasNextLine())
			mazeFileList.add(sc.nextLine());
	}

	/**
	 * returns the arraylist for the file being loaded
	 */
	public ArrayList<String> getFileList() {
		return mazeFileList;
	}

	/**
	 * returns the name of the current filename
	 */
	public String getMazeFileName() {
		return mazeFileList.get(index);
	}

	/**
	 * goes to the list next in the list
	 */
	public void nextMaze() {
		if (index < mazeFileList.size() - 1)
			++index;
	}

	// ADDED THIS METHOD in order to look at
	// the next maze and then come back to the
	// previous maze

	/**
	 * goes back on in the list of mazes
	 */
	public void previousMaze() {
		if (index < mazeFileList.size() - 1)
			index = index - 1;
	}

	/**
	 * determines where the lastmaze is
	 */
	public void lastMaze() {
		if (index > 0)
			--index;
	}

	/**
	 * returns the index of which maze it is currently on
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * sets the index to the indicated value that is passed into the method
	 * 
	 * @param i
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * tell what maze it is currently on
	 */
	public String toString() {
		return "maze board:" + getMazeFileName();
	}
}