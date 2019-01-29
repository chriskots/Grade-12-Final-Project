import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Abraham extends Movable {
	
	public boolean attackingUp = false;
	public boolean attackingDown = false;
	public boolean attackingLeft = false;
	public boolean attackingRight = false;
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown; //Attack cooldown to 800 milliseconds or 0.8 seconds
	//Attacking weapon and damage
	private int weapon = 0;//Knife as default
	int damage;
	Rectangle attackRectangle = new Rectangle();
	int attackSide; //to see the sizes for each side of attacking
	int attackRectangleSizeX; //Range of the attack
	int attackRectangleSizeY; //Range of the attack
	//Inventory
	private Inventory inventory;
	private Abraham abraham;
	private int shoot = 0;
	
	public Abraham(Handler handler, float x, float y) {
		super(handler, x, y, Movable.DEFAULT_MOVABLE_WIDTH, Movable.DEFAULT_MOVABLE_HEIGHT);
		abraham = this;
		inventory = new Inventory(handler, abraham);
	}

	@Override
	public void tick() {
		//Movement
		getInput();
		move();
		getShooting();
		//Inventory
		inventory.tick();
		//If statement for the different weapons bounds
		//I need to work on when people pick up a weapon item for their weapon in game to change
		if(weapon == 1){
			bounds.x = 5;
			bounds.y = 5;
			bounds.width = 109;
			bounds.height = 112;
			//Pistol range
			attackRectangleSizeX = 50;
			attackRectangleSizeY = 500;
		}
		else if(weapon == 2){
			bounds.x = 13;
			bounds.y = 4;
			bounds.width = 102;
			bounds.height = 122;
			//Shotgun range
			attackRectangleSizeX = 200;
			attackRectangleSizeY = 200;
		}
		else if(weapon == 3){
			bounds.x = 13;
			bounds.y = 4;
			bounds.width = 102;
			bounds.height = 122;
			//Rifle range
			attackRectangleSizeX = 50;
			attackRectangleSizeY = 500;
		}
		else{
			bounds.x = 13;
			bounds.y = 4;
			bounds.width = 102;
			bounds.height = 122;
			//Knife range
			attackRectangleSizeX = 100;
			attackRectangleSizeY = 100;
		}
	}
	
	@Override
	public void die(){
		State.setState(handler.getGame().loseState);
		//I want it to wait 5 seconds to see the death screen
		//System.exit(0);//Exits the game
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		//Getting the input of the user for movement
		//And changing their speed
		if (handler.getKeyManager().up){
			yMove = -speed;
		}
		if (handler.getKeyManager().down){
			yMove = speed;
		}
		if (handler.getKeyManager().left){
			xMove = -speed;
		}
		if (handler.getKeyManager().right){
			xMove = speed;
		}
	}
	
	private void getShooting(){
		//Setting the attack timer as 0.8 seconds so they can't rapid fire shots
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		//If statement for when the user cannot shoot
		if(attackTimer < attackCooldown){
			shoot = 0;
			return;
		}
		//Allowing the shooting for the graphics to show
		shoot = 1;
		
		//Decalring the boundry for the shooting range
		Rectangle collisionBoundry = getCollisionBounds(0, 0);
		
		//If statement for when the user attacks up (changes the bounds and rectangle)
		if (handler.getKeyManager().attackUp){
			attackRectangle.width = attackRectangleSizeX;
			attackRectangle.height = attackRectangleSizeY;
			attackRectangle.x = collisionBoundry.x + collisionBoundry.width / 2 - attackRectangleSizeX /2;
			attackRectangle.y = collisionBoundry.y - attackRectangleSizeY;
			attackSide = 0;
		}
		//If statement for when the user attacks down (changes the bounds and rectangle)
		else if (handler.getKeyManager().attackDown){
			attackRectangle.width = attackRectangleSizeX;
			attackRectangle.height = attackRectangleSizeY;
			attackRectangle.x = collisionBoundry.x + collisionBoundry.width / 2 - attackRectangleSizeX /2;
			attackRectangle.y = collisionBoundry.y + collisionBoundry.height;
			attackSide = 1;
		}
		//If statement for when the user attacks left (changes the bounds and rectangle)
		else if (handler.getKeyManager().attackLeft){
			attackRectangle.width = attackRectangleSizeY;
			attackRectangle.height = attackRectangleSizeX;
			attackRectangle.x = collisionBoundry.x - attackRectangleSizeY;
			attackRectangle.y = collisionBoundry.y + collisionBoundry.height / 2 - attackRectangleSizeX / 2;
			attackSide = 2;
		}
		//If statement for when the user attacks right (changes the bounds and rectangle)
		else if (handler.getKeyManager().attackRight){
			attackRectangle.width = attackRectangleSizeY;
			attackRectangle.height = attackRectangleSizeX;
			attackRectangle.x = collisionBoundry.x + collisionBoundry.width;
			attackRectangle.y = collisionBoundry.y + collisionBoundry.height / 2 - attackRectangleSizeX / 2;
			attackSide = 3;
		}
		else{
			return;
		}
		
		//Allows the user to attack again
		attackTimer = 0;
		
		//Checks to see if the user shoots the entity
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(attackRectangle)){
				e.hurt(damage);//Damage per hit
				return;
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		//Drawing the user
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		//Draw an image where the shooting range is
		if(shoot == 1){
			g.drawImage(getCurrentShootingAnimationFrame(), attackRectangle.x, attackRectangle.y, attackRectangle.width, attackRectangle.height, null);
		}
		inventory.render(g);
	}
	
	//Animation for shooting
	private BufferedImage getCurrentShootingAnimationFrame(){
		//Knife Shot
		if(handler.getKeyManager().attackUp && weapon == 0){
			return Assets.knifeBulletUp;
		}
		else if(handler.getKeyManager().attackDown && weapon == 0){
			return Assets.knifeBulletDown;
		}
		else if(handler.getKeyManager().attackLeft && weapon == 0){
			return Assets.knifeBulletLeft;
		}
		else if(handler.getKeyManager().attackRight && weapon == 0){
			return Assets.knifeBulletRight;
		}
		
		//Pistol Shot
		if(handler.getKeyManager().attackUp && weapon == 1){
			return Assets.pistolBulletUp;
		}
		else if(handler.getKeyManager().attackDown && weapon == 1){
			return Assets.pistolBulletDown;
		}
		else if(handler.getKeyManager().attackLeft && weapon == 1){
			return Assets.pistolBulletLeft;
		}
		else if(handler.getKeyManager().attackRight && weapon == 1){
			return Assets.pistolBulletRight;
		}
		
		//Shotgun Shot
		if(handler.getKeyManager().attackUp && weapon == 2){
			return Assets.shotgunBulletUp;
		}
		else if(handler.getKeyManager().attackDown && weapon == 2){
			return Assets.shotgunBulletDown;
		}
		else if(handler.getKeyManager().attackLeft && weapon == 2){
			return Assets.shotgunBulletLeft;
		}
		else if(handler.getKeyManager().attackRight && weapon == 2){
			return Assets.shotgunBulletRight;
		}
		
		//Rifle Shot
		if(handler.getKeyManager().attackUp && weapon == 3){
			return Assets.rifleBulletUp;
		}
		else if(handler.getKeyManager().attackDown && weapon == 3){
			return Assets.rifleBulletDown;
		}
		else if(handler.getKeyManager().attackLeft && weapon == 3){
			return Assets.rifleBulletLeft;
		}
		else if(handler.getKeyManager().attackRight && weapon == 3){
			return Assets.rifleBulletRight;
		}
		
		//No animation for a better looking figures
		else{
			return Assets.noAnimation;
		}
	}
	
	//Movement images
	private BufferedImage getCurrentAnimationFrame(){
		if(weapon == 1){ //Pistol
			damage = 1;
			if(xMove < 0){
				return Assets.pistolLeft;
			}
			else if (xMove > 0){
				return Assets.pistolRight;
			}
			else if (yMove < 0){
				return Assets.pistolUp;
			}
			else{
				return Assets.pistolDown;
			}
		}
		else if(weapon == 2){ //Shotgun
			damage = 3;
			if(xMove < 0){
				return Assets.shotgunLeft;
			}
			else if (xMove > 0){
				return Assets.shotgunRight;
			}
			else if (yMove < 0){
				return Assets.shotgunUp;
			}
			else{
				return Assets.shotgunDown;
			}
		}
		else if(weapon == 3){ //Rifle
			damage = 2;
			if(xMove < 0){
				return Assets.rifleLeft;
			}
			else if (xMove > 0){
				return Assets.rifleRight;
			}
			else if (yMove < 0){
				return Assets.rifleUp;
			}
			else{
				return Assets.rifleDown;
			}
		}
		else{ //Knife
			damage = 1;
			if(xMove < 0){
				return Assets.knifeLeft;
			}
			else if (xMove > 0){
				return Assets.knifeRight;
			}
			else if (yMove < 0){
				return Assets.knifeUp;
			}
			else{
				return Assets.knifeDown;
			}
		}
		
	}
	
	//Getters and setters
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}