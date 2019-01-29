import java.awt.Graphics;

public class HealthFix extends StaticEntity {
	
	//Health fixing for displaying
	
	public HealthFix(Handler handler, float x, float y) {
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
		g.drawImage(Assets.healthFix, (int) x, (int) y, width, height, null);
	}
	
}