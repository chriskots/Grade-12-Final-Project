import java.awt.Graphics;

public class MenuState extends State{
	
	private UIManager uiManager;
		
	public MenuState(final Handler handler){
		super (handler);
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		//Adding the start button
		uiManager.addObject(new UIImageButton(600, 200, 128, 64, Assets.button_start, new ClickListener(){

			@Override
			public void onClick() {
				//Calls the game for playing
				State.setState(handler.getGame().gameState);
			}
		}));
		
		//Adding the instructions button
		uiManager.addObject(new UIImageButton(600, 400, 128, 64, Assets.button_instructions, new ClickListener(){

			@Override
			public void onClick() {
				//Calls the instruction screen
				State.setState(handler.getGame().instructionsState);
			}
		}));
		
		//Blank
		uiManager.addObject(new UIImageButton(50, 750, 128, 64, Assets.blank, new ClickListener(){

			@Override
			public void onClick() {
				//Calls the menu screen
				State.setState(handler.getGame().menuState);
			}
		}));
		
		//Adding the exit button
		uiManager.addObject(new UIImageButton(600, 600, 128, 64, Assets.button_exit, new ClickListener(){

			@Override
			public void onClick() {
				System.exit(0);//Exiting the game
			}
		}));
		
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menu, (int) 0, (int) 0, 1400, 870, null);
		uiManager.render(g);
	}

}
