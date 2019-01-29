import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public MouseManager(){
	}
	
	//Getters and setters
	
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null){
			uiManager.onMouseMove(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = true;
		}
		
		if(uiManager != null){
			uiManager.onMouseMove(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = false;
		}
		
		if(uiManager != null){
			uiManager.onMouseRelease(e);
		}
	}
	
}
