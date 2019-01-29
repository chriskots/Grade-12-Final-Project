
public class Room1CornerTile2 extends Tile {
	
	//Room spawn from the text file
	
	public Room1CornerTile2(int id) {
		super(Assets.room2Corner, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
