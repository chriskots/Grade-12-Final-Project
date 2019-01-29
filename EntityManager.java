import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
	
	//Declaring variables
	private Handler handler;
	private Abraham abraham;
	private ArrayList<Entity> entities;
	//Compariator for rendering
	private Comparator<Entity> renderSort = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b){
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
				return -1;
			}
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Abraham abraham){
		this.handler = handler;
		this.abraham = abraham;
		entities = new ArrayList<Entity>();
		addEntity(abraham);
	}
	
	//Ticking the entities for the screen
	public void tick(){
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.tick();
			if(!e.isActive()){
				it.remove();
			}
		}
		entities.sort(renderSort);//Sorting the enities in the correct order
	}
	
	//Rendering the entities
	public void render (Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	//Adding and removing entities for less lag
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	//Getter and Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Abraham getAbraham() {
		return abraham;
	}

	public void setAbraham(Abraham abraham) {
		this.abraham = abraham;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
