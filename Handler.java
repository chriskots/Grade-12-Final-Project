
public class Handler {
	
	//Declaring variables
	private Game game;
	private World1 world;
	
	public Handler(Game game){
		this.game = game;
	}
	
	//Getters and setters from the handler
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World1 getWorld() {
		return world;
	}

	public void setWorld(World1 world) {
		this.world = world;
	}
	
}
