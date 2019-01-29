import java.awt.Graphics;

public class Rock extends StaticEntity {
	
	//Rock for collision from the entity
	
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		
		bounds.x = -3;
		bounds.y = 9;
		bounds.width = 112;
		bounds.height = 50;
		health = 100000;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) x, (int) y, width, height, null);
	}
	
}