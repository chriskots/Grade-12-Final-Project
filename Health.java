import java.awt.Graphics;

public class Health extends StaticEntity {
	
	//Health for displaying
	
	public Health(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);
		
		health = 10000;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.redHeart, (int) x, (int) y, width, height, null);
	}
	
}