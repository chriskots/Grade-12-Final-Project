import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Zombie extends Movable{
	
	private int time = 0;
	private Random random = new Random();
		
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown; //Attack cooldown to 800 milliseconds or 0.8 seconds
	static Rectangle attackRectangle = new Rectangle();
	int attackRectangleSize = 225; //Range of the attack
	
	public Zombie(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		
		//I need to fix the bounds when the zombie changes his sprite
		bounds.x = 0;
		bounds.y = 10;
		bounds.width = 185;
		bounds.height = 100;
		health = 10;//10 health for the zombie
	}

	@Override
	public void tick() {
		getMovement();
		move();
	}
	
	private void getMovement(){
		time ++;
		
		if(time % (random.nextInt(50) + 30) == 0){
			xMove = random.nextInt(3) - 1;//Random from -1 to 1
			yMove = random.nextInt(3) - 1;//Random from -1 to 1
			
			//Add or subtract more if the random number is 1 or -1 to add more speed
			if(xMove == 1){
				xMove = 2; //Temporary zombie speed for x
			}
			if(xMove == -1){
				xMove = -2; //Temporary zombie speed for x
			}
			if(yMove == 1){
				yMove = 2; //Temporary zombie speed for y
			}
			if(yMove == -1){
				yMove = -2; //Temporary zombie speed for y
			}
			
		}
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown){
			return;
		}
		
		Rectangle collisionBoundry = getCollisionBounds(0, 0);
		
		attackRectangle.width = attackRectangleSize;
		attackRectangle.height = attackRectangleSize;
		
		attackRectangle.x = collisionBoundry.x + collisionBoundry.width / 2 - attackRectangleSize /2;
		attackRectangle.y = collisionBoundry.y + collisionBoundry.height / 2 - attackRectangleSize / 2;
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(attackRectangle)){
				e.hurt(1);//Damage per hit
				return;
			}
		}
		
	}
	
	@Override
	public void die(){
		
		//Randomizing a drop
		Random rand = new Random();
		int random = rand.nextInt(500) + 1;
		
		//Checking to see if the zombie drops an item
		if(random <= 25 && handler.getWorld().getShoes() == 0){
			handler.getWorld().getItemManager().addItem(Item.shoeItem.createNew((int)x, (int)y));
			handler.getWorld().setShoes(1);
		}
		else if (random > 25 && random <= 50 && handler.getWorld().getPistols() == 0){
			handler.getWorld().getItemManager().addItem(Item.pistolWeapon.createNew((int)x, (int)y));
			handler.getWorld().setPistols(1);
		}
		else if (random > 50 && random <= 75 && handler.getWorld().getShotguns() == 0){
			handler.getWorld().getItemManager().addItem(Item.shotgunWeapon.createNew((int)x, (int)y));
			handler.getWorld().setShotguns(1);
		}
		else if (random > 75 && random <= 100 && handler.getWorld().getRifles() == 0){
			handler.getWorld().getItemManager().addItem(Item.rifleWeapon.createNew((int)x, (int)y));
			handler.getWorld().setRifles(1);
		}
		else if (random > 100 && random <= 150 && handler.getWorld().getRedHearts() == 0){
			handler.getWorld().getItemManager().addItem(Item.redHeartItem.createNew((int)x, (int)y));
			handler.getWorld().setRedHearts(1);
		}
		else if (random > 150 && random <= 175 && handler.getWorld().getBlueHearts() == 0){
			handler.getWorld().getItemManager().addItem(Item.blueHeartItem.createNew((int)x, (int)y));
			handler.getWorld().setBlueHearts(1);
		}
		
		handler.getWorld().setZombieSpawn(1);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrameZombie(), (int) x, (int) y, width, height, null);
	}
	  
	//Getting the animation from the zombie
	  private BufferedImage getCurrentAnimationFrameZombie(){
		  	if(xMove < 0){
				return Assets.zombieLeft;
			}
			else if (xMove > 0){
				return Assets.zombieRight;
			}
			else if (yMove < 0){
				return Assets.zombieUp;
			}
			else{
				return Assets.zombieDown;
			}
	  }

	
}
