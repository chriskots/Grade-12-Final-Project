import java.awt.Graphics;

public class InstructionsState extends State{
	
	public InstructionsState(final Handler handler) {
		super (handler);
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		//Drawing the instructions and back button
		g.drawImage(Assets.instructions, (int) 235, (int) 0, 930, 870, null);
		g.drawImage(Assets.button_back[0], (int) 50, (int) 750, 128, 64, null);//I want to implement the hover to make it look better
	}

}
