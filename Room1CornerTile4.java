
public class Room1CornerTile4 extends Tile {
	
	//Room spawn from the text file
	
	public Room1CornerTile4(int id) {
		super(Assets.room4Corner, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
}
