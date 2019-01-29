import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Utilities {
	
	//Lading the file as an integer so it can be read
	
	public static int[] loadFileAsInt() throws IOException{
		
		Scanner file = new Scanner(new File("world1.txt"));
		
		String read = "";
		int x = 0;
		int[] nums = new int[100];
		
		while(file.hasNext()){
			read = file.nextLine().trim();
			nums[x] = Integer.parseInt(read);
			x++;
		}
		
		file.close();
		return nums;
	}
	
}
