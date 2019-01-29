import java.awt.image.BufferedImage;

public class Assets {
	//Declaring the buffered images
	public static BufferedImage knifeRight, knifeDown, knifeLeft, knifeUp;
	public static BufferedImage pistolRight, pistolDown, pistolLeft, pistolUp;
	public static BufferedImage shotgunRight, shotgunDown, shotgunLeft, shotgunUp;
	public static BufferedImage rifleRight, rifleDown, rifleLeft, rifleUp;
	public static BufferedImage shoe, redHeart, blueHeart;
	public static BufferedImage healthFix;
	public static BufferedImage zombieRight, zombieDown, zombieLeft, zombieUp;
	public static BufferedImage room1Floor, room1Wall, room1Corner;
	public static BufferedImage room2Wall, room2Corner;
	public static BufferedImage room3Wall, room3Corner;
	public static BufferedImage room4Wall, room4Corner;
	public static BufferedImage rock;
	public static BufferedImage pistol, shotgun, rifle;
	public static BufferedImage [] button_start;
	public static BufferedImage [] button_instructions;
	public static BufferedImage [] button_exit;
	public static BufferedImage [] button_back;
	public static BufferedImage menu, instructions;
	public static BufferedImage [] blank;
	public static BufferedImage zombieBossRight, zombieBossDown, zombieBossLeft, zombieBossUp;
	public static BufferedImage knifeBulletRight, knifeBulletDown, knifeBulletLeft, knifeBulletUp;
	public static BufferedImage pistolBulletRight, pistolBulletDown, pistolBulletLeft, pistolBulletUp;
	public static BufferedImage shotgunBulletRight, shotgunBulletDown, shotgunBulletLeft, shotgunBulletUp;
	public static BufferedImage rifleBulletRight, rifleBulletDown, rifleBulletLeft, rifleBulletUp;
	public static BufferedImage noAnimation;
	public static BufferedImage deadImage, winImage;

	
	public static void init(){
		//Sprite sheet declarations
		SpriteSheet sheet = new SpriteSheet (ImageLoader.load("/textures/SpriteSheet.png"));
		SpriteSheet menuSheet = new SpriteSheet (ImageLoader.load("/textures/Menu.png"));
		SpriteSheet instructionSheet = new SpriteSheet (ImageLoader.load("/textures/Instructions.png"));
		
		SpriteSheet knifeSheet = new SpriteSheet (ImageLoader.load("/textures/Knife.png"));
		SpriteSheet pistolSheet = new SpriteSheet (ImageLoader.load("/textures/PistolBullet.png"));
		SpriteSheet shotgunSheet = new SpriteSheet (ImageLoader.load("/textures/ShotgunBlast.png"));
		SpriteSheet rifleSheet = new SpriteSheet (ImageLoader.load("/textures/RifleBullet.png"));
		
		SpriteSheet winSheet = new SpriteSheet (ImageLoader.load("/textures/WinScreen.png"));
		SpriteSheet deadSheet = new SpriteSheet (ImageLoader.load("/textures/YouAreDead.png"));
		
		//Croping the images from the sprite sheets
		
		noAnimation = sheet.crop(140, 474, 1, 1);
		
		knifeBulletRight = knifeSheet.crop(153, 70, 126, 21);
		knifeBulletDown = knifeSheet.crop(175, 98, 21, 126);
		knifeBulletLeft = knifeSheet.crop(35, 156, 126, 21);
		knifeBulletUp = knifeSheet.crop(101, 12, 21, 126);
		
		pistolBulletRight = pistolSheet.crop(264, 116, 211, 66);
		pistolBulletDown = pistolSheet.crop(309, 206, 65, 210);
		pistolBulletLeft = pistolSheet.crop(59, 311, 211, 66);
		pistolBulletUp = pistolSheet.crop(144, 46, 65, 210);
		
		shotgunBulletRight = shotgunSheet.crop(113, 12, 39, 78);
		shotgunBulletDown = shotgunSheet.crop(96, 99, 78, 39);
		shotgunBulletLeft = shotgunSheet.crop(42, 76, 39, 78);
		shotgunBulletUp = shotgunSheet.crop(10, 18, 78, 39);
		
		
		rifleBulletRight = rifleSheet.crop(192, 108, 236, 39);
		rifleBulletDown = rifleSheet.crop(312, 177, 38, 236);
		rifleBulletLeft = rifleSheet.crop(38, 300, 236, 39);
		rifleBulletUp = rifleSheet.crop(135, 36, 38, 236);
		
		deadImage = deadSheet.crop(0, 0, 1200, 900);
		winImage = winSheet.crop(0, 0, 1200, 900);
		
		blank = new BufferedImage[2];
		blank[0] = sheet.crop(551, 319, 1, 1);
		blank[1] = sheet.crop(551, 319, 1, 1);
		
		button_start = new BufferedImage[2];
		button_start[0] = sheet.crop(1105, 162, 210, 145);
		button_start[1] = sheet.crop(1330, 162, 210, 145);
		
		button_instructions = new BufferedImage[2];
		button_instructions[0] = sheet.crop(1107, 470, 210, 145);
		button_instructions[1] = sheet.crop(1330, 474, 210, 145);
		
		button_exit = new BufferedImage[2];
		button_exit[0] = sheet.crop(1105, 319, 210, 145);
		button_exit[1] = sheet.crop(1330, 319, 210, 145);
		
		button_back = new BufferedImage[2];
		button_back[0] = sheet.crop(1105, 6, 210, 145);
		button_back[1] = sheet.crop(1330, 6, 210, 145);
		
		menu = menuSheet.crop(100, 0, 780, 485);
		instructions = instructionSheet.crop(0, 0, 500, 500);
		
		knifeRight = sheet.crop(5, 6, 131, 145);
		knifeDown = sheet.crop(140, 6, 131, 145);
		knifeLeft = sheet.crop(278, 6, 131, 145);
		knifeUp = sheet.crop(414, 6, 131, 145);
		
		pistolRight = sheet.crop(551, 6, 131, 145);
		pistolDown = sheet.crop(686, 6, 131, 145);
		pistolLeft = sheet.crop(823, 6, 131, 145);
		pistolUp = sheet.crop(960, 6, 131, 145);
		
		shotgunRight = sheet.crop(5, 162, 131, 145);
		shotgunDown = sheet.crop(140, 162, 131, 145);
		shotgunLeft = sheet.crop(278, 162, 131, 145);
		shotgunUp = sheet.crop(414, 162, 131, 145);
		
		rifleRight = sheet.crop(551, 162, 131, 145);
		rifleDown = sheet.crop(686, 162, 131, 145);
		rifleLeft = sheet.crop(823, 162, 131, 145);
		rifleUp = sheet.crop(960, 162, 131, 145);
				
		shoe = sheet.crop(5, 319, 131, 145);
		redHeart = sheet.crop(823, 319, 131, 145);
		blueHeart = sheet.crop(960, 319, 131, 145);
		
		healthFix = sheet.crop(5, 474, 1, 1);
		
		zombieRight = sheet.crop(278, 474, 131, 140);
		zombieDown = sheet.crop(414, 474, 131, 140);
		zombieLeft = sheet.crop(551, 474, 131, 140);
		zombieUp = sheet.crop(686, 474, 131, 140);
		
		room1Floor = sheet.crop(823, 474, 131, 145);
		room1Wall = sheet.crop(960, 474, 131, 145);
		room1Corner = sheet.crop(418, 630, 127, 145);
		
		room2Wall = sheet.crop(140, 630, 131, 145);
		room2Corner = sheet.crop(552, 630, 150, 145);
		
		room3Wall = sheet.crop(5, 630, 131, 145);
		room3Corner = sheet.crop(710, 630, 125, 145);
		
		room4Wall = sheet.crop(278, 630, 131, 145);
		room4Corner = sheet.crop(850, 630, 135, 145);
		
		rock = sheet.crop(185, 842, 80, 80);
		
		pistol = sheet.crop(278, 785, 131, 145);
		shotgun = sheet.crop(415, 785, 131, 145);
		rifle = sheet.crop(552, 785, 150, 145);
		
		zombieBossRight = sheet.crop(1187, 650, 128, 138);
		zombieBossDown = sheet.crop(1325, 650, 128, 140);
		zombieBossLeft = sheet.crop(1191, 800, 128, 140);
		zombieBossUp = sheet.crop(1325, 805, 128, 140);

		
	}
	
}
