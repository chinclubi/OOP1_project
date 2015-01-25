package object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteProfits {
	private double profits;

	public WriteProfits(double prifits) {
		String currentDir = new File("").getAbsolutePath();
		File profiteData = new File(currentDir + "\\data\\profits.txt");
		this.profits = prifits;
		try {
			FileWriter fw = new FileWriter(profiteData);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(profits+"");
			pw.close();
		} catch (IOException e) {
			profiteData.getParentFile().mkdirs();
			try {
				profiteData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Can't Create or Write File "
						+ profiteData);
			}
		}
		// System.out.println(currentDir);
		// System.out.println(memberData);
	}

	public double getProfits() {
		return this.profits;
	}
}
