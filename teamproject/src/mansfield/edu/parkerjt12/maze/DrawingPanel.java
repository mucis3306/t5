package mansfield.edu.parkerjt12.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 * MVC View: displays a panel that draws the maze and player pieces. Called from
 * AView.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 4417547627470770942L;
	private AController control;
	private MazeBoard board;
	private MazeShape human;
	private int mazeSquareSize;
	private MazeRobot robot1, robot2, robot3;
	private MazeGingerRobot ginger1, ginger2, ginger3;
	private String mytime = " ";
	private String finishTime = "";

	/**
	 * The constructor sets the size and color of the panel.
	 */
	public DrawingPanel() {
		setPreferredSize(new Dimension(MazeApp.MAZEWIDTH, MazeApp.MAZEHEIGHT));
		setBackground(Color.BLACK);
	}

	/**
	 * Called to sync the main objects should the controller change them.
	 * 
	 * @param board
	 * @param human
	 * 
	 */
	public void setup(MazeBoard board, MazeShape human, MazeRobot robot1,
	MazeRobot robot2, MazeRobot robot3, MazeGingerRobot ginger1,MazeGingerRobot
	ginger2,MazeGingerRobot ginger3)
	{
	
	//public void setup(MazeBoard board, MazeShape human) 
		this.board = board;
		this.human = human;
		this.robot1 = robot1;
		this.robot2 = robot2;
		this.robot3 = robot3;
		this.ginger1 = ginger1;
		this.ginger2 = ginger2;
		this.ginger3 = ginger3;
		calcMazeSquareSize();
	}

	/**
	 * Repaints the drawing panel.
	 */
	public void refresh(String s, String name) {
		finishTime = name;
		mytime = s;
		repaint();
	}

	/**
	 * Draws the maze board and the player piece.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);

		
		 if(control.rob1==true) 
		 {drawPlayer(robot1, g);} else
		 if(control.assimilate1==true) 
		 {drawPlayer(ginger1, g);}
		  
		 if(control.rob2==true) 
		 {drawPlayer(robot2, g);} else
		 if(control.assimilate2==true) 
		 {drawPlayer(ginger2, g);}
		 
		 if(control.rob3==true) 
		 {drawPlayer(robot3, g);} else
		 if(control.assimilate3==true) 
		 {drawPlayer(ginger3, g);}
		  
		drawPlayer(human, g); if (AController.getGameOver() == true)
		drawWinner(g,mytime,finishTime);

		g.drawString(mytime, 857, 20);
		drawPlayer(human, g);
		if (AController.gameOver == true) {
			drawWinner(g, mytime, finishTime);
		}
	}

	/**
	 * Calculates the size to draw each maze square given the display size and
	 * the maze size.
	 */
	private void calcMazeSquareSize() {
		int nomWidth = MazeApp.MAZEWIDTH / (board.getEndCol() - 1);
		int nomHeight = MazeApp.MAZEHEIGHT / (board.getEndRow() - 1);
		mazeSquareSize = nomWidth < nomHeight ? nomWidth : nomHeight;
	}

	/**
	 * Determines how to color a given maze square.
	 */
	private Color getMazeColor(char c) {
		if (c == '-' || c == 's') // c=b
			return (/* new Color(200, 200, 255) */Color.orange);// walk ways,
																// grey?
		else if (c == 'e' || c == '?')
			return (/* new Color(0, 255, 0) */Color.blue);// finishes/false
															// finishes, white?
		else if (c == 'x')
			return (/* new Color(20, 20, 200) */Color.white);// walls, black?
		return new Color(0, 0, 0);
	}

	/**
	 * Draws the maze board on the screen.
	 * 
	 * @param g
	 */
	public void drawBoard(Graphics g) {
		for (int row = 1; row < board.getEndRow(); row++) {
			for (int col = 1; col < board.getEndCol(); col++) {
				g.setColor(getMazeColor(board.getChar(row, col)));
				int x1 = (col - 1) * mazeSquareSize;
				int x2 = (row - 1) * mazeSquareSize;
				g.fillRect(x1, x2, mazeSquareSize, mazeSquareSize);
			}
		}
		Font font = new Font("Arial", Font.BOLD, 24);
		g.setFont(font);
		g.setColor(Color.black);
	}

	/**
	 * Draws a message when there is a winner.
	 * 
	 * @param g
	 */
	public void drawWinner(Graphics g, String s, String a) {
		Font font = new Font("Arial", Font.BOLD, 110);
		g.setFont(font);
		g.setColor(Color.blue);
		mytime = s;
		g.drawString("Total " + mytime, 40, 340);
		if (!a.equalsIgnoreCase(""))
			;
		{
			g.drawString(a, 80, 200);
		}
	}

	/**
	 * Draws a player's icon on the screen.
	 * 
	 * @param ms
	 * @param g
	 */
	public void drawPlayer(MazeShape ms, Graphics g) 
	{
		String name = ms.getName();
		g.setColor(ms.getColor());
		int startX = (ms.getCol() - 1) * mazeSquareSize;
		int startY = (ms.getRow() - 1) * mazeSquareSize;
		int size = mazeSquareSize;

		// /////////////////////////////////////////////////////////
		// ///////// vvvvvv delete below vvvvvvv ///////////////////
			MazeGinger mp = (MazeGinger) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0, 0,
					srcSize, srcSize, null);
		// /////// ^^^^^^^^ delete above ^^^^^^^ ////////////
	}
}
