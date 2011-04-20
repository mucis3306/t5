package mansfield.edu.parkerjt12.maze;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * MVC View: displays a panel containing various controls allowing the player to
 * control the look and feel of the game.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 3930070147267264232L;
	// added square as another option that the player may choose to use
	private String[] playerShapeStrings = { "ginger"};
	
	JTextField nameTF;
	JButton lastBtn, nextBtn, againBtn, musicOnOffBtn;

	/**
	 * The constructor for the ControlPanel class. Called from AView. Places
	 * various controls on the panel.
	 */
	public ControlPanel() {

		JLabel nameLbl = new JLabel("Player Name:");
		this.add(nameLbl);

		nameTF = new JTextField("", 12);
		this.add(nameTF);

		musicOnOffBtn = new JButton("Music On/Off");
		this.add(musicOnOffBtn);

	}

	public String getNameTF() {
		return nameTF.getText();
	}


	/**
	 * Callback for the nameTF ActionListener. Called from AController.
	 * 
	 * @param al
	 */
	void addnameTFActionListener(ActionListener al) {
		nameTF.addActionListener(al);
	}

	// ////////////////////////////////////////////////////
	void addMusicBtnActionListener(ActionListener al) {
		musicOnOffBtn.addActionListener(al);
	}
	// ///////////////////////////////////////////////////
}
