import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	
	//Getter and setter for the states
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public State (Handler handler){
		this.handler = handler;
	}
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
