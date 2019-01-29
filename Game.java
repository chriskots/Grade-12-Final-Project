import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	
	//Declaring variables
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	public State instructionsState;
	public State winState;
	public State loseState;
	
	//Imputs
	private KeyManager keyManager;
	private MouseManager mouseManager;
		
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	//Program for the in it method
	private void init(){
		//Displaying the frames
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
				
		handler = new Handler(this);
		
		//Declaring the states
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		instructionsState = new InstructionsState(handler);
		winState = new WinState(handler);
		loseState = new LoseState(handler);
		State.setState(menuState); //Calls the menu state first
	}
	
	//Ticking the game
	private void tick (){
		
		keyManager.tick();
		
		if (State.getState() != null){
			State.getState().tick();
		}
		
	}
	
	//Rendering the canvas and frames
	private void render (){
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clearing Screen
		g.clearRect(0, 0, width, height);
		//Drawing
		
		if (State.getState() != null){
			State.getState().render(g);
		}
				
		//End Drawing
		bs.show();
		g.dispose();
		
	}
	
	//Running the program
	public void run(){
		
		init();
		
		//Allowing only 60 frames per second to allow every computer to run it the same
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1){
				tick();
				render();
				delta --;
			}
			
			if (timer >= 1000000000){
				//Limiting to 60 ticks and frames per second
				timer = 0;
			}
		}
		
		stop();
	}
	
	//Getters and setters
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	//Starting
	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	//Stoping
	public synchronized void stop(){
		if (!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}