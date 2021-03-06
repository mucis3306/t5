package mansfield.edu.liveseyjt25.maze;

/**
 * MVC Model: interface defining methods all maze board generators will have.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public interface MazeBoardInterface {
	public void createBoard(String fileName);

	public boolean isFinish(int row, int col);

	public boolean isValidMove(int row, int col);

	public int getStartRow();

	public int getStartCol();

	public int getEndRow();

	public int getEndCol();

	public char getChar(int row, int col);

	public String getVersion();

	@Override
	public String toString();
}
