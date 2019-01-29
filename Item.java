import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {
	
	//Handler
	public static Item[] items = new Item[256];
	public static Item shoeItem = new Item (Assets.shoe, "Shoe", 0);
	public static Item redHeartItem = new Item (Assets.redHeart, "Red Heart", 2);
	public static Item blueHeartItem = new Item (Assets.blueHeart, "Blue Heart", 3);
	
	//Weapons
	public static Item pistolWeapon = new Item(Assets.pistol, "Pistol", 5);
	public static Item shotgunWeapon = new Item(Assets.shotgun, "Shotgun", 6);
	public static Item rifleWeapon = new Item(Assets.rifle, "Rifle", 7);
	
	//Class
	public static final int ITEMWIDTH = 75, ITEMHEIGHT = 75;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
		
	public Item(BufferedImage texture, String name, int id){
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
	}
	
	//Ticking the items
	public void tick(){
		if(handler.getWorld().getEntityManager().getAbraham().getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getAbraham().getInventory().addItem(this);
		}
	}
	
	//Checking to see if the user is over top of it and then runs the other render method
	public void render (Graphics g){
		if(handler == null){
			return;
		}
		render(g, (int) x, (int) y);
	}
	
	//Rendering the texture
	public void render (Graphics g, int x, int y){
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	//Creating a new item for pick up
	public Item createNew(int x, int y){
		
		Item i = new Item (texture, name, id);
		i.setPosition(x, y);
		return i;
		
	}
	
	//Getters and setters
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}
	
}
