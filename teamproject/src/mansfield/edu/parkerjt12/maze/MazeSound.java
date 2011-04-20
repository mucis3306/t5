package mansfield.edu.parkerjt12.maze;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * MVC Model: sets up and plays short sound clips.
 * 
 * License: CC BY-SA 3.0 http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * @author John Phillips
 * @author Jay Parker
 */
public class MazeSound {
	private AudioInputStream popSound, blipSound, roarSound, resistSound, borgSound;
	private AudioInputStream poorFinishSound, goodFinishSound, locutusSound;
	private Clip popSoundClip, blipSoundClip, roarSoundClip, resistSoundClip, borgSoundClip;
	private Clip poorFinishSoundClip, goodFinishSoundClip, locutusSoundClip;

	/**
	 * reads in all the sound effects and prepares them to be played
	 */
	public MazeSound() {
		try {
			popSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/pop.wav"));
			popSoundClip = AudioSystem.getClip();
			popSoundClip.open(popSound);

			blipSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/blip.wav"));
			blipSoundClip = AudioSystem.getClip();
			blipSoundClip.open(blipSound);

			poorFinishSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/crowdohh.wav"));
			poorFinishSoundClip = AudioSystem.getClip();
			poorFinishSoundClip.open(poorFinishSound);

			goodFinishSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/hitcrowdcheer.wav"));
			goodFinishSoundClip = AudioSystem.getClip();
			goodFinishSoundClip.open(goodFinishSound);

			
			 borgSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/borg.wav"));
			 borgSoundClip =AudioSystem.getClip(); borgSoundClip.open(borgSound);
			 
			 resistSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/resist.wav")); 
			 resistSoundClip = AudioSystem.getClip(); resistSoundClip.open(resistSound);
			
			 locutusSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/locutus1ringtone.wav"));
			 locutusSoundClip = AudioSystem.getClip();
			 locutusSoundClip.open(locutusSound);
			 
			 roarSound = AudioSystem.getAudioInputStream(MazeSound.class.getResourceAsStream("/sound/sound006.wav")); roarSoundClip =
			 AudioSystem.getClip(); 
			 roarSoundClip.open(roarSound);
			 
		} catch (Exception e) {
			System.out.println("Error with sound file");
		}
	}

	/**
	 * Plays the popping noise for the players movement
	 */
	public void pop() 
	{
		popSoundClip.setFramePosition(0);
		popSoundClip.start();
	}

	/**
	 * plays the blip noise for when the player hits the wall
	 */
	public void blip() 
	{
		blipSoundClip.setFramePosition(0);
		blipSoundClip.start();
	}

	/**
	 * plays the boo when the player hits a false finish
	 */
	public void boo() 
	{
		poorFinishSoundClip.setFramePosition(0);
		poorFinishSoundClip.start();
	}

	/**
	 * plays cheer when the player gets to the finish of that level
	 */
	public void cheer() 
	{
		goodFinishSoundClip.setFramePosition(0);
		goodFinishSoundClip.start();
	}
	
	 public void death() 
	 { 
		 roarSoundClip.setFramePosition(0);
		 roarSoundClip.start(); 
	 }
	 
	 public void resist() 
	 { 
		 resistSoundClip.setFramePosition(0);
		 resistSoundClip.start(); 
	} 
	 
	 public void locutus() 
	 {
		 locutusSoundClip.setFramePosition(0); 
		 locutusSoundClip.start(); 
	 }
	 
	 public void borg() 
	 { 
		 borgSoundClip.setFramePosition(0); 
		 borgSoundClip.start(); 
	 }
}
