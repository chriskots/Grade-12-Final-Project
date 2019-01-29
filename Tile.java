import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Static
	
	public static Tile[] tiles = new Tile[256];
	public static Tile Room1Floor = new Room1FloorTile(1);
	public static Tile Room1Wall = new Room1WallTile1(2);
	public static Tile Room2Wall = new Room1WallTile2(3);
	public static Tile Room3Wall = new Room1WallTile3(4);
	public static Tile Room4Wall = new Room1WallTile4(5);
	public static Tile Room1Corner = new Room1CornerTile1(6);
	public static Tile Room2Corner = new Room1CornerTile2(7);
	public static Tile Room3Corner = new Room1CornerTile3(8);
	public static Tile Room4Corner = new Room1CornerTile4(9);
	//Add more tiles
	
	//CLASS
	
	public static final int TILE_WIDTH = 200, TILE_HEIGHT = 125;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	//Tile rendering
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
