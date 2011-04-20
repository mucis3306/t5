package mansfield.edu.parkerjt12.maze;

//Midi files are public domain from http://www.mutopiaproject.org/

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 * MVC Model: plays a midi music file given a filename.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class MazeMidi {
	private Sequencer sequencer;
	private Sequence song;
	private boolean onOff = true;

	/**
	 * Sets up the midi file to be played
	 * 
	 * @param fileName
	 */
	public MazeMidi(String fileName) {
		try {
			song = MidiSystem.getSequence(MazeMidi.class
					.getResourceAsStream("/music/" + fileName));
			sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(song);
			sequencer.open();
			sequencer.start();
		} catch (Exception e) {
			System.out.println("Error with midi file");
		}
	}

	/**
	 * starts the playing of the midi file
	 */
	public void start() {
		sequencer.start();
		onOff = true;
	}

	/**
	 * Stops the playing of the current midi file
	 */
	public void stop() {
		sequencer.stop();
		onOff = false;
	}

	public boolean getOnOff() {
		return onOff;
	}
}
