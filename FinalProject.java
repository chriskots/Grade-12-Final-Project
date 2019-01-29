import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FinalProject {

	//Timer
	Timer timer = new Timer();
	private int secondsPassed = 0;
	//Runner for the timer tasks
	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			secondsPassed ++;
		}};
	
	public static void main(String[] args){
		
		//Music
		File Music = new File("gameMusic.WAV");
		PlaySound(Music);
		
		//The Binding of Abraham
		
		Game game = new Game("The Binding of Abraham", 1400, 870);
		game.start();
		
	}
	
	//Starting the timer
	public void startTimer(){
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	//Playing the audio clips
	static void PlaySound(File Sound){
		try{
			Clip music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(Sound));
			music.start();
						
		} catch(Exception e){
			
		}
	}

	//Getting the seconds passed for a timer
	public int getSecondsPassed() {
		return secondsPassed;
	}
}