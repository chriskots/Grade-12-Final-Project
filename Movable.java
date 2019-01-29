
public abstract class Movable extends Entity {
	
	//Devault variables for all entities
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_MOVABLE_WIDTH = 128,
							DEFAULT_MOVABLE_HEIGHT = 128;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Movable(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		
	}
	
	//Movement
	public void move(){
		if(!checkEntityCollisions(xMove, 0f)){
			moveX();
		}
		if(!checkEntityCollisions(0f, yMove)){
			moveY();
		}
	}
	
	//Movement for different directions (left and right)
	public void moveX(){
		if(xMove > 0){//Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
				x += xMove;
			}
			else{
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		}
		else if (xMove < 0){//Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
				x += xMove;
			}
			else{
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
		
	}
	
	//Movement for different directions (up and down)
	public void moveY(){
		if(yMove < 0){//Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.width) / Tile.TILE_WIDTH, ty)){
				y += yMove;
			}
			else{
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
		else if (yMove > 0){//Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.width) / Tile.TILE_WIDTH, ty)){
				y += yMove;
			}
			else{
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	//Collision detection
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x,y).isSolid();
	}
	
	//Getters and setters
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
