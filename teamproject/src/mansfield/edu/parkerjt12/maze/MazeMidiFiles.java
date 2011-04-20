package mansfield.edu.parkerjt12.maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * MVC Model: retrieves a midi music filename.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class MazeMidiFiles {
	private ArrayList<String> midiFileList;
	private int index;
	private static Random gen = new Random();

	/**
	 * Sets up the Midifile list for use
	 */
	public MazeMidiFiles() {
		midiFileList = new ArrayList<String>();
		createFileList();
		midiFileList.trimToSize();
		randomSong();
	}

	/**
	 * Creates the midi file list
	 */
	private void createFileList() {
		Scanner sc = new Scanner(
				MazeMidiFiles.class.getResourceAsStream("/midilist.txt"));
		while (sc.hasNextLine())
			midiFileList.add(sc.nextLine());
	}

	/**
	 * returns the midi file list
	 */
	public ArrayList<String> getFileList() {
		return midiFileList;
	}

	/**
	 * Returns the name of the current file that is being used
	 */
	public String getMidiFileName() {
		return midiFileList.get(index);
	}

	/**
	 * Chooses a random song
	 */
	public void randomSong() {
		index = gen.nextInt(midiFileList.size());
	}

	/**
	 * Chooses the next song in the song list
	 */
	public void nextSong() {
		if (index < midiFileList.size() - 1)
			++index;
	}

	/**
	 * goes to the last song in the list
	 */
	public void lastSong() {
		if (index > 0)
			--index;
	}

	/**
	 * return the current index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * sets the index to the given value that is passed in
	 * 
	 * @param i
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * shows the current midi song name as a string
	 */
	@Override
	public String toString() {
		return "midi song: " + getMidiFileName();
	}
}
