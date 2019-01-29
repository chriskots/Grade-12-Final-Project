
public class Room1WallTile3 extends Tile{
	
	//Room spawn from the text file
	
	public Room1WallTile3(int id) {
		super(Assets.room3Wall, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
