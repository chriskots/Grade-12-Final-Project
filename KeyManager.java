import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right;
	public boolean attackUp, attackDown, attackLeft, attackRight;
	
	public KeyManager(){
		keys = new boolean [256];
		justPressed = new boolean [keys.length];
		cantPress = new boolean[keys.length];
	}
	
	//Ticking the keys for movement and shooting
	public void tick(){
		for(int i = 0; i < keys.length; i ++){
			if (cantPress[i] && !keys[i]){
				cantPress[i] = false;
			}
			else if (justPressed[i]){
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i]){
				justPressed[i] = true;
			}
		}
		
		//Moving keys
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		//Attacking keys
		attackUp = keys[KeyEvent.VK_UP];
		attackDown = keys[KeyEvent.VK_DOWN];
		attackLeft = keys[KeyEvent.VK_LEFT];
		attackRight = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
			return;
		}
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
			return;
		}
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length){
			return false;
		}
		return justPressed[keyCode];
	}
	
}
