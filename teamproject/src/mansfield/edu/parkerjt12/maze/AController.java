package mansfield.edu.parkerjt12.maze;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import mansfield.edu.liveseyjt25.maze.MazeBorgRobot;
import mansfield.edu.liveseyjt25.maze.MazeRobot;

/**
 * MVC Controller: receives a handle to the View and controls everything that
 * happens as the program runs. It maintains a separate thread that allows for
 * simple animation. In addition, AController handles all of the events
 * generated when buttons are clicked or keys are pressed.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class AController implements Runnable {
	private static final Graphics Graphics = null;
	private AView view;
	private MazeBoard board;
	private MazeBoardFiles mazeFiles;
	private MazeMidi midi;
	private MazeMidiFiles midiFiles;
	private MazeSound sound;
	private MazeShape human;
	private long startTime = 0;
	private long endTime = 0;
	private float time = 0;
	private String totalTime = "Time: 0.0s";
	public float tTime = 0;
	private String finishTime;
	private Thread runner;
	private MazeRobot robot1, robot2, robot3; 
	private MazeGingerRobot ginger1, ginger2, ginger3;

	int robots = 3;
	static boolean rob1 = true;
	static boolean rob2 = true;
	static boolean rob3 = true;

	static boolean assimilate1 = false;
	static boolean assimilate2 = false;
	static boolean assimilate3 = false;

	public void Revive() {
		rob1 = true;
		rob2 = true;
		rob3 = true;
		robots = 3;
		assimilate1 = false;
		assimilate2 = false;
		assimilate3 = false;
	}

	/**
	 * The animation delay is in milliseconds. The value should be >= to 1. It
	 * was origanly set to 500 and now is 100
	 */
	private int delay = 50; // set to 100

	/**
	 * gameOver needs to be public static so that it can be read from the View.
	 */
	public static boolean gameOver = false;

	/**
	 * The constructor for the AController class. It is called from MazeApp.
	 * 
	 * @param view
	 *            the MVC view is passed in from MazeApp.java. This allows the
	 *            controller to interact with any part of the view or
	 *            controlPanel or drawingPanel.
	 */
	public AController(AView view) {
		this.view = view;
		mazeFiles = new MazeBoardFiles();
		midiFiles = new MazeMidiFiles();
		setupBoardAndPlayers();
		setupListeners();
		runner = new Thread(this);
		runner.start();
	}

	/**
	 * Used to set up a new board, player pieces, sounds, and music.
	 */
	private void setupBoardAndPlayers() {
		startTime = System.currentTimeMillis();
		gameOver = false;
		board = new MazeBoard(mazeFiles.getMazeFileName());
		// /////////////////////////////////////////////////
		 int r = board.getPStartRow();
		 int c = board.getPStartCol();
		 int rr = board.getRStartRow();
		 int rc = board.getRStartCol();
		 robot1 = new MazeRobot(rr, rc);
		 robot2 = new MazeRobot(rr, rc);
		 robot3 = new MazeRobot(rr, rc);
		 ginger1 = new MazeGingerRobot(rr, rc); // change borg to ginger
		 ginger2 = new MazeGingerRobot(rr, rc); // change borg to ginger
		 ginger3 = new MazeGingerRobot(rr, rc); // change borg to ginger
		// /////// vvvvv delete below vvvvv ////////////////
		//int r = board.getStartRow();
		//int c = board.getStartCol();
		// /////// ^^^^^^ delete above ^^^^^ ///////////////
		String shape = "circle"; // change to ginger
		// /////////////////////////////////////////////////
		if (human != null)
			shape = view.controlPanel.getPlayerShapeName();
		setNewShape(r, c, shape);
		sound = new MazeSound();
		if (midi != null)
			midi.stop();
		midiFiles.randomSong();
		midi = new MazeMidi(midiFiles.getMidiFileName());
		refresh();
	}

	/**
	 * Creates a new MazeShape object for the human player.
	 * 
	 * @param r
	 *            the board row the object will sit on
	 * @param c
	 *            the board column the object will sit on
	 * @param shape
	 *            the shape the object will take
	 */
	private void setNewShape(int r, int c, String shape) {
		if (shape.equals("ginger"))
			human = new MazeGinger(r, c); // get rid of
		else if (shape.equals("puppy"))
			human = new MazePuppy(r, c);// change circle to ginger
		human.setColor(view.controlPanel.getPlayerColorName());
		// /////////////////////////////////////////////////////
		// view.drawingPanel.setup(board, human, robot1, robot2, robot3, borg1,
		// borg2, borg3); //change borg to ginger
		// /////////////////////////////////////////////////////
		view.drawingPanel.setup(board, human/* , robot */);
	}

	/**
	 * Repaints the drawingPanel and returns focus to the frame which listens
	 * for key presses.
	 */
	private void refresh() {
		// updates the time on the board
		view.drawingPanel.refresh(totalTime, finishTime);
		view.frame.requestFocus();
		finishTime = view.controlPanel.getNameTF();
	}

	/**
	 * Checks to see if the someone has reached the maze end.
	 */
	private void checkFinish() {
		//if (board.isFinish(human.getRow(), human.getCol()) == true) 
		if(robots == 0 && board.isFinish(human.getRow(), human.getCol()) == true)
		{
			gameOver = true;

			if(human.getName()=="ginger")
			sound.borg();
			else sound.cheer();

			// this is the time management for the timer objects,
			// there are two timer objects, one is for the timing
			// for each level, the other object is for the total
			// time for completing all the mazes
			endTime = System.currentTimeMillis();
			time = ((endTime - startTime) / 1000);
			tTime = tTime + time;
			totalTime = ("Time: " + tTime + "s");

			// ////////////DEBUG INFORMATION ON CONSOLE/////////////
			System.out.println("That took " + time + " seconds");
			// System.out.println(mazeFiles.getMazeFileName());
			// /////////////////////////////////////////////////////
			mazeFiles.nextMaze();
			String s = mazeFiles.getMazeFileName();
			mazeFiles.previousMaze();

			if (!s.equalsIgnoreCase("m9999.txt")) {
				// tells the program to go to the next maze
				mazeFiles.nextMaze();
				setupBoardAndPlayers();
			}
			sound.cheer();
		}
	}

	
	 //unlock code when borg is changed to ginger 
	 void killRobot() 
	 { 
		 if(human.getRow()== robot1.getRow() && human.getCol()== robot1.getCol() && rob1 == true) 
		 {
			 assimilate1 = true; rob1 = false; refresh(); 
			 sound.resist(); 
			 robots--; 
		 }
		 
		 if (human.getRow()== robot2.getRow() && human.getCol()== robot2.getCol() && rob2 == true) 
		 {
			 assimilate2 = true; rob2 = false; refresh(); 
			 sound.resist(); 
			 robots--;
			 robots--; 
		}  
		if (human.getRow()== robot3.getRow() && human.getCol()==robot3.getCol() && rob3 == true)
		{ 
			assimilate3 = true;
			rob3 = false;
			refresh();
			sound.resist(); 
			robots--;
		} 
	  }
	 

	/**
	 * Checks to see if player has touched a false finish and resets the player
	 * to a random start
	 */
	private void checkFalseFinish() {
		if (board.isFalseFinish(human.getRow(), human.getCol()) == true) {
			board = new MazeBoard(mazeFiles.getMazeFileName());
			int r = board.getStartRow();
			int c = board.getStartCol();
			String shape = view.controlPanel.getPlayerShapeName();
			setNewShape(r, c, shape);
			refresh();
			sound.boo();
		}
	}

	/**
	 * Adds animation to the game allowing a robot object to move around on the
	 * screen.
	 */
	@Override
	public void run() {
		while (true) {

			String s = mazeFiles.getMazeFileName();
			// System.out.println(mazeFiles.getMazeFileName());

			// if the program hits the last file in the maze
			// list (m9999.txt) then the system will sleep
			// for 10000 milliseconds then exit
			if (s.equalsIgnoreCase("m9999.txt")) {
				view.drawingPanel
						.drawWinner(Graphics, totalTime, finishTime);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
				}
				System.exit(0);
				System.out.println("THIS!");
			}

			if (!gameOver) {
				 robot1.moveRobot(board);
				 ginger1.setCol(robot1.getCol()); 
				 ginger1.setRow(robot1.getRow());
				 robot2.moveRobot(board);
				 ginger2.setCol(robot2.getCol());
				 ginger2.setRow(robot2.getRow());
				 robot3.moveRobot(board);
				 ginger3.setCol(robot3.getCol());
				 ginger3.setRow(robot3.getRow());
				 
				checkFinish();
				checkFalseFinish();
				view.drawingPanel.refresh(totalTime, finishTime);
			}

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Adds listeners to the controls on the controlPanel. This is necessary
	 * since the View is created before the Controller. Hence, after the
	 * Controller is created we add the Controller's listeners back on to the
	 * View's controls using these callbacks.
	 */
	private void setupListeners() {
		view.addKeyListener(new MyKeyListener());
		view.addMenu1MIListener(new Menu1MIListener());
		view.controlPanel
				.addnameTFActionListener(new nameTFActionListener());
		view.controlPanel
				.addPlayerColorCBActionListener(new PlayerColorCBActionListener());
		view.controlPanel
				.addPlayerShapeCBActionListener(new PlayerShapeCBActionListener());
		view.controlPanel
				.addMusicBtnActionListener(new MusicBtnActionListener());
	}

	/**
	 * The color combo box allows the player to choose different colors for the
	 * simple shape objects that represent the player on the screen.
	 */
	private class PlayerColorCBActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			human.setColor(view.controlPanel.getPlayerColorName());
			refresh();
		}
	}

	/**
	 * Will reset the focus on the nameTF
	 */
	private class nameTFActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			refresh();
		}
	}

	/**
	 * This method is for the new song action in the file menu system. It
	 * changes the song at random
	 */
	private class Menu1MIListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sound = new MazeSound();
			if (midi != null)
				midi.stop();
			midiFiles.randomSong();
			midi = new MazeMidi(midiFiles.getMidiFileName());
			refresh();
		}
	}

	/**
	 * The shape combo box allows the player to choose different shapes or
	 * images to represent the player on the screen.
	 */
	private class PlayerShapeCBActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setNewShape(human.getRow(), human.getCol(),
					view.controlPanel.getPlayerShapeName());
			refresh();
		}
	}

	/**
	 * When musicOnOffBtn is clicked the midi files stop playing and the sounds
	 * get set to stop playing.
	 */
	private class MusicBtnActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (midi.getOnOff()) {
				midi.stop();
			} else {
				midi.start();
			}
			refresh();
		}
	}

	/**
	 * Detects when arrow keys are pressed and moves the player icon through the
	 * maze. Sounds are used to indicate whether the maze wall is hit.
	 */
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			int keyCode = ke.getKeyCode();
			boolean hitWall = true;
			int r = human.getRow();
			int c = human.getCol();

			if (keyCode == KeyEvent.VK_RIGHT) {
				if (board.isValidMove(r, c + 1) == true) {
					human.moveEast();
					hitWall = false;
				}
			} else if (keyCode == KeyEvent.VK_LEFT) {
				if (board.isValidMove(r, c - 1) == true) {
					human.moveWest();
					hitWall = false;
				}
			} else if (keyCode == KeyEvent.VK_UP) {
				if (board.isValidMove(r - 1, c) == true) {
					human.moveNorth();
					hitWall = false;
				}
			} else if (keyCode == KeyEvent.VK_DOWN) {
				if (board.isValidMove(r + 1, c) == true) {
					human.moveSouth();
					hitWall = false;
				}
			}

			if (hitWall == true)
				sound.blip();
			else
				sound.pop();

			refresh();
		}
	}
}