package mansfield.edu.liveseyjt25.maze;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * MVC View: sets up a GUI view for the application. It creates a frame and 
 * main panel. On the main panel goes a drawingPanel that will hold the 
 * maze. In addition, a control panel is placed at the bottom of the 
 * main panel.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 */
public class AView {
	JFrame frame;
	JPanel mainPanel;
	ControlPanel controlPanel;
	DrawingPanel drawingPanel;
	
	/**
	 * The constructor for the AView class. It is called from MazeApp.
	 */
	public AView() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		controlPanel = new ControlPanel();
		drawingPanel = new DrawingPanel();

		mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(drawingPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);

		frame = new JFrame("Ginger Force: Ginger Limit Exceeded");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(mainPanel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Called from the controller to add a key listener to the frame.
	 * 
	 * @param kl
	 *            a KeyListener passed from AController
	 */
	public void addKeyListener(KeyListener kl) {
		frame.addKeyListener(kl);
	}
}
