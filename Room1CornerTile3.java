
public class Room1CornerTile3 extends Tile {
	
	//Room spawn from the text file
	
	public Room1CornerTile3(int id) {
		super(Assets.room3Corner, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
