
public class Room1CornerTile1 extends Tile {
	
	//Room spawn from the text file
	
	public Room1CornerTile1(int id) {
		super(Assets.room1Corner, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
