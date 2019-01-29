
public class Room1WallTile2 extends Tile{
	
	//Room spawn from the text file
	
	public Room1WallTile2(int id) {
		super(Assets.room2Wall, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
