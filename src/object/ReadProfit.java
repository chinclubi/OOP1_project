package object;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadProfit {
	private double profits=0;
	public ReadProfit() throws IOException {
		String currentDir = new File("").getAbsolutePath();
		File profitsData = new File (currentDir+"\\data\\profits.txt");
		if(profitsData.exists()){
			Scanner input = new Scanner(profitsData);
			profits = Double.parseDouble(input.nextLine());
			input.close();
		}else{
			profitsData.getParentFile().mkdirs();
			profitsData.createNewFile();
		}
	}
	public double getProfit(){
		return this.profits;
	}
}
