package mansfield.edu.liveseyjt25.maze;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MVC Model: generates a list of maze file names.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 */
public class MazeBoardFiles {
	private ArrayList<String> mazeFileList;
	private int index;
	
	public MazeBoardFiles() {
		mazeFileList = new ArrayList<String>();
		createFileList();
		mazeFileList.trimToSize();
		index = 0;
	}
	
	private void createFileList() {
		Scanner sc = new Scanner(
				MazeBoardFiles.class.getResourceAsStream("/mazelist.txt"));
		while(sc.hasNextLine()) mazeFileList.add(sc.nextLine());
	}
	
	public ArrayList<String> getFileList() {
		return mazeFileList;
	}
	
	public String getMazeFileName() {
		return mazeFileList.get(index);
	}
	
	public void nextMaze() {
		if(index < mazeFileList.size()-1) ++index;
	}
	
	public void previousMaze() 
	{
		if(index < mazeFileList.size()-1) index = index -1 ;
	}
	
	public void lastMaze() {
		if(index > 0) --index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int i) {
		index = i;
	}
	
	public String toString() {
		return "maze board:" + getMazeFileName();
	}
}