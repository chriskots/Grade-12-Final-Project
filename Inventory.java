import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {
	
	//Declaring variables
	private Handler handler;
	private ArrayList<Item> inventoryItems;
	boolean onceBlue = true;
	boolean onceRed = true;
	boolean onceShoe = true;
	Abraham abraham;
	
	public Inventory(Handler handler, Abraham abraham){
		this.handler = handler;
		this.abraham = abraham;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void tick(){
		//Inventory
		for(Item i : inventoryItems){
			
			//I need to find a way to allow more than one pick up (health more than once)
			
			//When they pick up the pistol
			if(i.getName().equals("Pistol")){
				//Change the weapon to 1
				abraham.setWeapon(1);
			}
			//When they pick up the shotgun
			else if(i.getName().equals("Shotgun")){
				//Change the weapon to 2
				abraham.setWeapon(2);
			}
			//When they pick up the rifle
			else if(i.getName().equals("Rifle")){
				//Change the weapon to 3
				abraham.setWeapon(3);
			}
			//When they pick up the blue heart
			else if(i.getName() == "Blue Heart" && onceBlue == true){
				//Add 2 HP (but only once)
				abraham.health += 2;
				onceBlue = false;
			}
			//When they pick up the red heart
			else if(i.getName() == "Red Heart" && onceRed == true){
				//Add 1 HP (but only once)
				abraham.health += 1;
				onceRed = false;
			}
			//When they pick up the shoe
			else if(i.getName() == "Shoe" && onceShoe == true){
				//Change the speed (but only once)
				abraham.setSpeed(abraham.getSpeed() + 1.0f);
				onceShoe = false;
			}
		}
	}
	
	public void render (Graphics g){
	}
	
	//Inventory Methods
	
	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	//Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}

