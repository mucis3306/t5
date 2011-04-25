package mansfield.edu.liveseyjt25.maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * MVC Controller: receives a handle to the View and controls everything
 * that happens as the program runs. It maintains a separate thread that 
 * allows for simple animation. In addition, AController handles all of 
 * the events generated when buttons are clicked or keys are pressed.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class AController implements Runnable {
	private AView view;
	private MazeBoard board;
	private MazeBoardFiles mazeFiles;
	private MazeMidi midi;
	private MazeMidiFiles midiFiles;
	private MazeSound sound;
	private MazeShape human;
	private MazeRobot robot1, robot2, robot3; 
	private MazeGingerRobot ginger1, ginger2, ginger3;
	private Thread runner;
	private static boolean gameover = false;
	
	int robots = 3;
	static boolean rob1 = true;
	static boolean rob2 = true;
	static boolean rob3 = true;
	
	static boolean assimilate1 = false;
	static boolean assimilate2 = false;
	static boolean assimilate3 = false;
	
	public void Revive()
	{
		rob1 = true;
		rob2 = true;
		rob3 = true;
		robots = 3;
		assimilate1 = false;
		assimilate2 = false;
		assimilate3 = false;
	}
	
	
	
	/**
	 * The animation delay is in milliseconds. The value should be >= to 1.
	 */
	
	private int delay = 100;//speed of bug
	
	/**
	 * gameOver needs to be public static so that it can be read from
	 * the View.
	 */
	public static boolean gameOver = false;
	
	/**
	 * The constructor for the AController class. It is called from MazeApp.
	 * 
	 * @param view the MVC view is passed in from MazeApp.java. This allows
	 * the controller to interact with any part of the view or controlPanel 
	 * or drawingPanel. 
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
		gameOver = true;
		board = new MazeBoard(mazeFiles.getMazeFileName());
		int r = board.getPStartRow();
		int c = board.getPStartCol();
		int rr = board.getRStartRow();
		int rc = board.getRStartCol();
		robot1 = new MazeRobot(rr, rc);
		robot2 = new MazeRobot(rr, rc);
		robot3 = new MazeRobot(rr, rc);
		ginger1 = new MazeGingerRobot(rr, rc);
		ginger2 = new MazeGingerRobot(rr, rc);
		ginger3 = new MazeGingerRobot(rr, rc);
		String shape = "ginger";
		if(human!=null) shape = view.controlPanel.getPlayerShapeName();
		setNewShape(r,c,shape);
		sound = new MazeSound();
		if (midi != null) midi.stop();
		midiFiles.randomSong();
		midi = new MazeMidi(midiFiles.getMidiFileName());
		midi.stop();
		refresh();
		gameOver = false;
	}
	
	/**
	 * Creates a new MazeShape object for the human player.
	 * @param r the board row the object will sit on
	 * @param c the board column the object will sit on
	 * @param shape the shape the object will take
	 */
	private void setNewShape(int r,int c, String shape) {
			 
	         
		if (shape.equals("ginger")) human = new MazeGinger(r,c);
		view.drawingPanel.setup(board, human, robot1, robot2, robot3, ginger1, ginger2, ginger3);
	}
	
	/**
	 * Repaints the drawingPanel and returns focus to the frame which
	 * listens for key presses.
	 */
	private void refresh() {
		view.drawingPanel.refresh();
		view.frame.requestFocus();
	}
	
	public static boolean getGameOver(){
		return gameOver;
	}
	
	
	/**
	 * Checks to see if the someone has reached the maze end.
	 */
	private void checkFinish() {
		if (robots == 0 && board.isFinish(human.getRow(), human.getCol()) == true) {
			gameOver = false;
			
			if(human.getName()=="ginger")
				sound.borg();
			else sound.cheer();
			Revive();
			mazeFiles.nextMaze();
			String s = mazeFiles.getMazeFileName();
			mazeFiles.previousMaze();
			
			
			if(!s.equalsIgnoreCase("m9999.txt"))
			{	
				//tells the program to go to the next maze
				mazeFiles.nextMaze();
				setupBoardAndPlayers();
			}
			if(s.equalsIgnoreCase("m9999.txt"))
			{
				midi.stop();
				gameOver = true;
			}
			
		}
	}
	
	private void checkFalseFinish() 
	{
		if (board.isFalseFinish(human.getRow(), human.getCol()) == true) 
		{
		
			int r = board.getPStartRow();
			int c = board.getPStartCol();
			String shape = "ginger";
			if(human!=null) shape = view.controlPanel.getPlayerShapeName();
			setNewShape(r,c,shape);
			refresh();
		}
	}
	
	void killRobot(){
		if (human.getRow()== robot1.getRow() && human.getCol()== robot1.getCol() && rob1 == true) {
			
			if(human.getName()=="ginger")
			{
				assimilate1 = true;
				rob1 = false;
				refresh();
				sound.resist();
				robots--;
			}
			
			else
			{
				sound.death();
				rob1 = false;
				robots--;
			}
		}
		if (human.getRow()== robot2.getRow() && human.getCol()== robot2.getCol() && rob2 == true) {
			
			if(human.getName()=="ginger")
			{
				assimilate2 = true;
				rob2 = false;
				refresh();
				sound.resist();
				robots--;
			}
			else
			{
				sound.death();
				rob2 = false;
				robots--;
			}
		}
		if (human.getRow()== robot3.getRow() && human.getCol()== robot3.getCol() && rob3 == true) {
			
			if(human.getName()=="ginger")
			{
				assimilate3 = true;
				rob3 = false;
				refresh();
				sound.resist();
				robots--;
			}
			else
			{
				sound.death();
				robots--;
				rob3 = false;}
		}
	}
	
	/**
	 * Adds animation to the game allowing a robot object to move around
	 * on the screen.
	 */
	@Override
	public void run() {
		while(true) {
			if(!gameOver) {
				robot1.moveRobot(board);
				ginger1.setCol(robot1.getCol()); ginger1.setRow(robot1.getRow());
				robot2.moveRobot(board);
				ginger2.setCol(robot2.getCol()); ginger2.setRow(robot2.getRow());
				robot3.moveRobot(board);
				ginger3.setCol(robot3.getCol()); ginger3.setRow(robot3.getRow());
				checkFinish();
				checkFalseFinish();
				view.drawingPanel.refresh();
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) { }
		}
	}
	
	/**
	 * Adds listeners to the controls on the controlPanel. This is 
	 * necessary since the View is created before the Controller. 
	 * Hence, after the Controller is created we add the Controller's
	 * listeners back on to the View's controls using these callbacks.
	 */
	private void setupListeners() {
		view.addKeyListener(new MyKeyListener());
		view.controlPanel.addLastBtnActionListener(
				new LastBtnActionListener());
		view.controlPanel.addNextBtnActionListener(
				new NextBtnActionListener());
		view.controlPanel.addAgainBtnActionListener(
				new AgainBtnActionListener());
		view.controlPanel.addPlayerShapeCBActionListener(
				new PlayerShapeCBActionListener());
		view.controlPanel.addSlowBtnActionListener(
				new SlowBtnActionListener());
		view.controlPanel.addMidBtnActionListener(
				new MidBtnActionListener());
		view.controlPanel.addFastBtnActionListener(
				new FastBtnActionListener());
		view.controlPanel.addMusicBtnActionListener(
				new MusicBtnActionListener());
	}
	
	/**
	 * When lastBtn is clicked the previous (simpler) maze file is loaded and
	 * the game is restarted.
	 */
	
	private class LastBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			mazeFiles.lastMaze();
			setupBoardAndPlayers();
			Revive();
		}
	}
	
	private class SlowBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			delay = 500;
			refresh();
		}
	}
	
	private class MidBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			delay = 100;
			refresh();
		}
	}
	
	private class FastBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			delay = 50;
			refresh();
		}
	}
	
	/** 
	 * When nextBtn is clicked the next (harder) maze file is loaded and the
	 * game is restarted.
	 */
	private class NextBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			mazeFiles.nextMaze();
			setupBoardAndPlayers();
			Revive();
		}
	}
	
	/**
	 * When the againBtn is clicked the same maze game is started over.
	 */
	private class AgainBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			setupBoardAndPlayers();
			Revive();
		}
	}



	/**
	 * The shape combo box allows the player to choose different shapes or
	 * images to represent the player on the screen.
	 */
	private class PlayerShapeCBActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			setNewShape(human.getRow(), human.getCol(), 
					view.controlPanel.getPlayerShapeName());
			if(human.getName()=="ginger")
			{
				sound.locutus();
			}
			refresh();
		}
	}
 
	/**
	 * When musicOnOffBtn is clicked the midi files stop playing and the sounds get set to stop playing.
	 */
	private class MusicBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(midi.getOnOff())
			{
				midi.stop();
			}
			else
			{
				midi.start();
			}
			refresh();
		}	
	}
	
	/**
	 * Detects when arrow keys are pressed and moves the player icon through
	 * the maze. Sounds are used to indicate whether the maze wall is hit.
	 */
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			int keyCode = ke.getKeyCode();
			boolean hitWall = true;
			int r = human.getRow();
			int c = human.getCol();
			
			if (keyCode == KeyEvent.VK_RIGHT) {
				if (board.isValidMove(r, c+1) == true) {
					human.moveEast();
					killRobot();
					hitWall = false;
				}
			}
			else if (keyCode == KeyEvent.VK_LEFT) {
				if (board.isValidMove(r, c-1) == true) {
					human.moveWest();
					killRobot();
					hitWall = false;
				}
			}
			else if (keyCode == KeyEvent.VK_UP) {
				if (board.isValidMove(r-1, c) == true) {
					human.moveNorth();
					killRobot();
					hitWall = false;
				}
			}
			else if (keyCode == KeyEvent.VK_DOWN) {
				if (board.isValidMove(r+1, c) == true) {
					human.moveSouth();
					killRobot();
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