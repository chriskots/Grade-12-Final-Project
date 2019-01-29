import java.awt.Graphics;

public class LoseState extends State {
	
	//Displaying the screen for when they lose
	
	public LoseState(final Handler handler) {
		super (handler);
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.deadImage, (int) 0, (int) 0, 1400, 870, null);
	}
	
}
