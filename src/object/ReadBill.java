package object;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ReadBill {
	private ArrayList<Bill> bills = new ArrayList<Bill>();
	public ReadBill() throws IOException {
		String currentDir = new File("").getAbsolutePath();
		File billData = new File (currentDir+"\\data\\bills.txt");
		//System.out.println(currentDir);
		//System.out.println(memberData);
		if(billData.exists()){
			Scanner input = new Scanner(billData);
			while(input.hasNextLine()){
				String text = input.nextLine();
				String arr[] = text.split("#");
				for(int j=0;j<arr.length;j++){
					//System.out.println(arr[j]);
				}
				int billNumber,customerID;
				Date date;
				double totalBill;
				ArrayList<Cart> cart = new ArrayList<Cart>();
				billNumber = Integer.parseInt(arr[0]);
				customerID = Integer.parseInt(arr[1]);
				DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
				try {
					date = formatter.parse(arr[2]);
				} catch (ParseException e) {
					date = new Date();
				}
				totalBill = Double.parseDouble(arr[3]);
				for(int i=4;i<arr.length;i++){
					String txt[] = arr[i].split("_");
					for(int j=0;j<txt.length;j++){
						//System.out.println("txt["+j+"]"+txt[j]);
					}
					cart.add(new Cart(Integer.parseInt(txt[0]),Integer.parseInt(txt[1])));
				}
				bills.add(new Bill(billNumber,customerID,date,totalBill,cart));
			}
			input.close();
		}else{
			billData.getParentFile().mkdirs();
			billData.createNewFile();
		}
	}
	public ArrayList<Bill> getBill(){
		return this.bills;
	}
}
