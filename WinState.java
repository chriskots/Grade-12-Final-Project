import java.awt.Graphics;

//State for the winning image

public class WinState extends State{
	
	public WinState(final Handler handler) {
		super (handler);
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.winImage, (int) 0, (int) 0, 1400, 870, null);
	}
	
}
