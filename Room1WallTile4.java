
public class Room1WallTile4 extends Tile{
	
	//Room spawn from the text file
	
	public Room1WallTile4(int id) {
		super(Assets.room4Wall, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
