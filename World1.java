import java.awt.Graphics;
import java.io.IOException;
import java.util.Scanner;

public class World1 {
	Scanner in = new Scanner(System.in);
	
	//Variables declaration
	
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Handler handler;
	private int boss = 0;
	private int bossCheck = 0;
	private int healthTester;
	private int origionalHealthTester;
	private int deathNum = 0;
	private int winCheck = 0;
	private int oldCheck = 0;
	
	private int shoes = 0;
	private int pistols = 0;
	private int shotguns = 0;
	private int rifles = 0;
	private int redHearts = 0;
	private int blueHearts = 0;
	
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	//Zombie Spawns
	private int zombieSpawn;
	//Timer
	private int timerOnlyOnce = 1;
	private FinalProject timerCheck = new FinalProject();
	private double minuites;
	private double seconds;
	private int minuitesCheck;
	private int secondsCheck;
	private String scores;
	
	public World1(Handler handler, String path){
		
		//Only allows the timer to start once
		if(timerOnlyOnce == 1){
			timerCheck.startTimer();
			timerOnlyOnce = 0;
		}
		
		this.handler = handler;
		entityManager = new EntityManager(handler, new Abraham(handler, 0, 0));//Creating Abraham
		itemManager = new ItemManager(handler);
		
		//entityManager.addEntity(new Health(handler, 0, 0));//Placement for hp
		
		entityManager.addEntity(new Rock(handler, 200, 400));//Placement for rock
		entityManager.addEntity(new Rock(handler, 900, 400));//Placement for rock
		entityManager.addEntity(new Rock(handler, 600, 675));//Placement for rock
		entityManager.addEntity(new Zombie(handler, 600, 150));//Placement for the zombie
		
		for(int x = 0; x < 47; x ++){
			entityManager.addEntity(new HealthFix(handler, x * 30, 0));//Making a place for the hp to go
		}
		
		for(int i = 0; i < 10; i ++){
			entityManager.addEntity(new Health(handler, i * 30, 0));//Placement for hp
		}
		
		origionalHealthTester = entityManager.getAbraham().getHealth();
		
		loadWorld1(path);
		
		entityManager.getAbraham().setX(spawnX);
		entityManager.getAbraham().setY(spawnY);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick(){
		
		healthTester = entityManager.getAbraham().getHealth();
		//Fixing the lag for spawning the health on the top left
		if(healthTester < origionalHealthTester || healthTester > origionalHealthTester){
			getAbrahamHP();
			origionalHealthTester = healthTester;
		}
		
		//If statement for when the zombie dies
		if(getZombieSpawn() == 1){
			entityManager.addEntity(new Zombie(handler, 600, 150));//Placement for the zombie
			setZombieSpawn(0);
		}
		//Allowing the user to fight 2 bosses
		if(boss == 19 && bossCheck <= 2){
			entityManager.addEntity(new ZombieBoss(handler, 600, 150));//Placement for the zombie boss
			boss = -2;
			bossCheck ++;
		}
				
		//Checking to see if the user wins
		if(getWinCheck() == 2){
			State.setState(handler.getGame().winState);
			minuites = timerCheck.getSecondsPassed() / 60;
			minuitesCheck = (int) Math.floor(minuites);
			seconds = timerCheck.getSecondsPassed() - (minuites * 60);
			secondsCheck = (int) Math.floor(seconds);
			scores = Integer.toString(minuitesCheck) + ":" + Integer.toString(secondsCheck);
			System.out.println("Your Time Was: " + scores);
			//I want it to call the client for the client to add the score to the server database

		}
		
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g){
		for(int y = 0; y < height; y ++){
			for(int x = 0; x < width; x ++){
				getTile(x,y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
			}
		}
		//Item
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y){
		
		if(x < 0 || y < 0 || x >= width || y >= height){
			return Tile.Room1Floor;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null){
			return Tile.Room1Floor;
		}
		return t;
	}
	
	private void loadWorld1(String path){
		
		int[] file = null;
		
		try {
			file = Utilities.loadFileAsInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = file[0];
		height = file[1];
		spawnX = file[2];
		spawnY = file[3];
		
		tiles = new int [width][height];
		for(int y = 0; y < height; y ++){
			for(int x = 0; x < width; x ++){
				tiles[x][y] = (file[(x + y * width) + 4]);
			}
		}
		
	}
	
	//Getting the hp for the top left
	public void getAbrahamHP(){
		//For statement for the health bars of the player
		for(int i = 0; i < entityManager.getAbraham().getHealth(); i ++){
			entityManager.addEntity(new HealthFix(handler, i * 30, 0));
			entityManager.removeEntity(new HealthFix(handler, i * 30, 0));
			entityManager.addEntity(new Health(handler, i * 30, 0));//Placement for hp
			//Removing the health for visual effects and the entities for less tick lag
			for(int x = entityManager.getAbraham().getHealth(); x < entityManager.getAbraham().getHealth() + 1; x ++){
				entityManager.addEntity(new HealthFix(handler, x * 30, 0));
				entityManager.removeEntity(new HealthFix(handler, x * 30, 0));
				entityManager.removeEntity(new Health(handler, x * 30, 0));
			}
		}
	}
	
	//Getters and setters
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int getZombieSpawn() {
		return zombieSpawn;
	}

	public void setZombieSpawn(int zombieSpawn) {
		setDeathNum(getDeathNum() + 1) ;
		boss ++;
		if(getDeathNum() < 19){
			this.zombieSpawn = zombieSpawn;
		}
		else{
			this.zombieSpawn = 0;
		}
	}

	public int getDeathNum() {
		return deathNum;
	}

	public void setDeathNum(int deathNum) {		
		this.deathNum = deathNum;
	}

	public int getWinCheck() {
		return winCheck;
	}

	public void setWinCheck(int winCheck) {
		this.winCheck = oldCheck + winCheck;
		oldCheck = winCheck;
	}

	public int getShoes() {
		return shoes;
	}

	public void setShoes(int shoes) {
		this.shoes = shoes;
	}

	public int getPistols() {
		return pistols;
	}

	public void setPistols(int pistols) {
		this.pistols = pistols;
	}

	public int getShotguns() {
		return shotguns;
	}

	public void setShotguns(int shotguns) {
		this.shotguns = shotguns;
	}

	public int getRifles() {
		return rifles;
	}

	public void setRifles(int rifles) {
		this.rifles = rifles;
	}

	public int getRedHearts() {
		return redHearts;
	}

	public void setRedHearts(int redHearts) {
		this.redHearts = redHearts;
	}

	public int getBlueHearts() {
		return blueHearts;
	}

	public void setBlueHearts(int blueHearts) {
		this.blueHearts = blueHearts;
	}
	
}