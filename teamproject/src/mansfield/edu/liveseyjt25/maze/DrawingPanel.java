package mansfield.edu.liveseyjt25.maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * MVC View: displays a panel that draws the maze and player pieces. 
 * Called from AView.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 4417547627470770942L;
	private AController control;
	private MazeBoard board;
	private MazeShape human;
	private MazeRobot robot1, robot2, robot3;
	private MazeGingerRobot ginger1,ginger2, ginger3;
	private int mazeSquareSize;

	/**
	 * The constructor sets the size and color of the panel.
	 */
	public DrawingPanel() {
		setPreferredSize(new Dimension(MazeApp.MAZEWIDTH,
				MazeApp.MAZEHEIGHT));
		setBackground(Color.gray);
	}

	/**
	 * Called to sync the main objects should the controller change them.
	 * 
	 * @param board
	 * @param human
	 * @param robot
	 */
	public void setup(MazeBoard board, MazeShape human,
			MazeRobot robot1, MazeRobot robot2, MazeRobot robot3, MazeGingerRobot ginger1,MazeGingerRobot ginger2,MazeGingerRobot ginger3) {
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
	public void refresh() {
		repaint();
	}

	/**
	 * Draws the maze board and the players pieces.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		
		if(control.rob1==true)
		{drawPlayer(robot1, g);}
		else if(control.assimilate1==true)
		{drawPlayer(ginger1, g);}
		
		if(control.rob2==true)
		{drawPlayer(robot2, g);}
		else if(control.assimilate2==true)
		{drawPlayer(ginger2, g);}
		
		if(control.rob3==true)
		{drawPlayer(robot3, g);}
		else if(control.assimilate3==true)
		{drawPlayer(ginger3, g);}
		
		drawPlayer(human, g);
		if (AController.getGameOver() == true)
			drawWinner(g);
	}

	/**
	 * Calculates the size to draw each maze square given the display size 
	 * and the maze size.
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
		if (c == '-' || c == 's' || c == 'r')
			return Color.gray;
		else if (c == 'e')
			return Color.green; 
		else if (c == 'x')
			return Color.black;
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
	}

	/**
	 * Draws a message when there is a winner.
	 * 
	 * @param g
	 */
	public void drawWinner(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 200);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("VICTORY!", 35, 350);
	}

	/**
	 * Draws a player's icon on the screen.
	 * 
	 * @param ms
	 * @param g
	 */
	public void drawPlayer(MazeShape ms, Graphics g) {
		String name = ms.getName();
		g.setColor(ms.getColor());
		int startX = (ms.getCol() - 1) * mazeSquareSize;
		int startY = (ms.getRow() - 1) * mazeSquareSize;
		int size = mazeSquareSize;		
		
		if (name.equals("circle")) {
			g.fillOval(startX, startY, size, size);
		} else if (name.equals("triangle")) {
			int x2 = startX + mazeSquareSize / 2;
			int x3 = startX + mazeSquareSize;
			int y2 = startY + mazeSquareSize;
			int[] xPoints = { x2, x3, startX, x2 };
			int[] yPoints = { startY, y2, y2, startY };
			int nPoints = 4;
			g.fillPolygon(xPoints, yPoints, nPoints);
		} 
		
		else if (name.equals("fred")) {
			MazeFred mp = (MazeFred) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		
		else if (name.equals("sally")) {
			MazeSally mp = (MazeSally) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		
		else if (name.equals("dinosaur")) {
			MazeDino mp = (MazeDino) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		
		else if (name.equals("nurse")) {
			MazeNurse mk = (MazeNurse) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mk.getSize();
			g.drawImage(mk.getImage(), startX, startY, dx2, dy2, 0,
				0, srcSize, srcSize, null);
		}
		else if (name.equals("fox")) {
			MazeFox mp = (MazeFox) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("ninja")) {
			MazeNinja mp = (MazeNinja) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("samurai")) {
			MazeSamurai mp = (MazeSamurai) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("shark")) {
			MazeShark mp = (MazeShark) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("princess")) {
			MazePrincess mp = (MazePrincess) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("penguin")) {
			MazePenguin mp = (MazePenguin) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("pirate")) {
			MazePirate mp = (MazePirate) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("cowboy")) {
			MazeCowboy mp = (MazeCowboy) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("police")) {
			MazePolice mp = (MazePolice) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		} 
		else if (name.equals("soldier")) {
			MazeSoldier mp = (MazeSoldier) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("ginger")) {
			MazeGinger mp = (MazeGinger) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mp.getSize();
			g.drawImage(mp.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("robot1")) {
			MazeRobot mr = (MazeRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("robot2")) {
			MazeRobot mr = (MazeRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("robot3")) {
			MazeRobot mr = (MazeRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("ginger1")) {
			MazeGingerRobot mr = (MazeGingerRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("ginger2")) {
			MazeGingerRobot mr = (MazeGingerRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
		else if (name.equals("ginger3")) {
			MazeGingerRobot mr = (MazeGingerRobot) ms;
			int dx2 = startX + mazeSquareSize;
			int dy2 = startY + mazeSquareSize;
			int srcSize = mr.getSize();
			g.drawImage(mr.getImage(), startX, startY, dx2, dy2, 0,
					0, srcSize, srcSize, null);
		}
	}
}