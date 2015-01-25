package object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteBill {
	private ArrayList bills = new ArrayList();
	public WriteBill(ArrayList Bill)  {
		String currentDir = new File("").getAbsolutePath();
		File billData = new File (currentDir+"\\data\\bills.txt");
		this.bills = Bill;		
		try {
			FileWriter fw = new FileWriter(billData);
			PrintWriter pw = new PrintWriter(fw);
			String data="";
			for (int i = 0; i < this.bills.size(); i++) {
				Bill billA = (Bill) this.bills.get(i);
				pw.printf("%s%n",billA.toString());
				//data += itemA.toString()+"%n ";
			}
			//pw.printf(data);
			pw.close();
		} catch (IOException e) {
			billData.getParentFile().mkdirs();
			try {
				billData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Can't Create or Write File "+billData);
			}
		}
		//System.out.println(currentDir);
		//System.out.println(memberData);
	}
	public ArrayList getBill(){
		return this.bills;
	}
}
