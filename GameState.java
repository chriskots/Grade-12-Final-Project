import java.awt.Graphics;

public class GameState extends State{
	
	//Declaring the world variable
	private World1 world;
	
	public GameState(Handler handler){
		super (handler);
		world = new World1(handler, "world1.txt"); //Creating a new world based off the text file
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}
	
	@Override
	public void render(Graphics g) {
		//rendering the world and the back button
		world.render(g);
		g.drawImage(Assets.button_back[0], (int) 50, (int) 750, 128, 64, null);
	}
	
	
	
}