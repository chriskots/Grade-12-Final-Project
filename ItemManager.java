import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		items = new ArrayList<Item>();
	}
		
	//Ticking the inventory
	public void tick(){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item i = it.next();
			i.tick();
			if(i.isPickedUp()){
				it.remove();
			}
		}
	}
	
	//Rendering the inventory
	public void render (Graphics g){
		for(Item i : items){
			i.render(g);
		}
	}
	
	//Adding the item to the inventory
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}

	//Getters and setters
		
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}