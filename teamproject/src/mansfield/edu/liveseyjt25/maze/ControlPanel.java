package mansfield.edu.liveseyjt25.maze;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * MVC View: displays a panel containing various controls allowing the 
 * player to control the look and feel of the game.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author Jason Livesey
 */
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 3930070147267264232L;
	private String[] playerShapeStrings = { "fred", "sally", "cowboy", "dinosaur", "fox", "ninja", "nurse","penguin","pirate","police","princess","samurai","shark","soldier","ginger", "circle", "triangle" };
	private String[] playerColorStrings = { "red", "black", "blue", "cyan",
			"gray", "green", "magenta", "orange", "pink", "white", "yellow"};
	JComboBox playerColorCB;
	JComboBox playerShapeCB;
	JTextField nameTF;
	JButton lastBtn, nextBtn, againBtn, fastBtn, midBtn, slowBtn, musicOnOffBtn;

	/**
	 * The constructor for the ControlPanel class. Called from AView. Places
	 * various controls on the panel.
	 */
	public ControlPanel() {

		slowBtn = new JButton("Slow");
		this.add(slowBtn);
		
		midBtn = new JButton("Medium");
		this.add(midBtn);
		
		fastBtn = new JButton("Fast");
		this.add(fastBtn);
		
		JLabel nameLbl = new JLabel("Player Name:");
		this.add(nameLbl);

		nameTF = new JTextField("", 12);
		this.add(nameTF);
		
		musicOnOffBtn = new JButton("Music On/Off");
		this.add(musicOnOffBtn);

		playerColorCB = new JComboBox(playerColorStrings);
		this.add(playerColorCB);

		playerShapeCB = new JComboBox(playerShapeStrings);
		this.add(playerShapeCB);

		lastBtn = new JButton("Easier Maze");
		this.add(lastBtn);

		nextBtn = new JButton("Harder Maze");
		this.add(nextBtn);

		againBtn = new JButton("Play Again");
		this.add(againBtn);
	}
	
	public void setNameTF(String name){
		nameTF.setText(name);
	}

	/**
	 * Callback for the lastBtn ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addSlowBtnActionListener(ActionListener al) {
		slowBtn.addActionListener(al);
	}
	
	/**
	 * Callback for the lastBtn ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addMidBtnActionListener(ActionListener al) {
		midBtn.addActionListener(al);
	}
	
	/**
	 * Callback for the lastBtn ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addFastBtnActionListener(ActionListener al) {
		fastBtn.addActionListener(al);
	}
	
	/**
	 * Callback for the lastBtn ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addLastBtnActionListener(ActionListener al) {
		lastBtn.addActionListener(al);
	}

	/**
	 * Callback for the nextBtn ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addNextBtnActionListener(ActionListener al) {
		nextBtn.addActionListener(al);
	}

	/**
	 * Callback for the againBtn ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addAgainBtnActionListener(ActionListener al) {
		againBtn.addActionListener(al);
	}

	/**
	 * Callback for the player color combo box ActionListener. Called from
	 * AController.
	 * 
	 * @param al
	 */
	void addPlayerColorCBActionListener(ActionListener al) {
		playerColorCB.addActionListener(al);
	}

	/**
	 * Callback for the player shape combo box ActionListener. Called from
	 * AController.
	 * 
	 * @param al
	 */
	void addPlayerShapeCBActionListener(ActionListener al) {
		playerShapeCB.addActionListener(al);
	}
	
	void addMusicBtnActionListener(ActionListener al){
		musicOnOffBtn.addActionListener(al);
	}

	/**
	 * Gets the player color currently selected by the combo box.
	 * 
	 * @return String colorName
	 */
	public String getPlayerColorName() {
		return ((String) playerColorCB.getSelectedItem());
	}

	/**
	 * Gets the player shape icon currently selected by the combo box.
	 * 
	 * @return String shapeName
	 */
	public String getPlayerShapeName() {
		return ((String) playerShapeCB.getSelectedItem());
	}
}
