
public class Room1WallTile1 extends Tile{
	
	//Room spawn from the text file
	
	public Room1WallTile1(int id) {
		super(Assets.room1Wall, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
