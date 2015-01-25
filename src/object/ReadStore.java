package object;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadStore {
	private ArrayList<Stuff> stuff = new ArrayList<Stuff>();
	public ReadStore() throws IOException {
		String currentDir = new File("").getAbsolutePath();
		File itemData = new File (currentDir+"\\data\\items.txt");
		//System.out.println(currentDir);
		//System.out.println(memberData);
		if(itemData.exists()){
			Scanner input = new Scanner(itemData);
			String name,url,icon ,Species ,Attribute,description ,Race, HP, Recovery, Attack,Cost;
			int numStock,ItemID;
			double price,buyingPrice;
			boolean reorder;
			//System.out.println("ReadItem");
			while(input.hasNextLine()){
				String text = input.nextLine();
				String arr[] = text.split("#");
				ItemID = Integer.parseInt(arr[0]);
				//System.out.println(ItemID+"_1");
				
				name = arr[1];
				//System.out.println(name+"_2");
				//url = input.next();
				//icon = input.next();
				description = arr[2];
				//System.out.println(description+"_3");
				numStock = Integer.parseInt(arr[3]);
				//System.out.println(numStock+"_4");
				price = Double.parseDouble(arr[4]);
				//System.out.println(price+"_5");
				buyingPrice = Double.parseDouble(arr[5]);
				//System.out.println(buyingPrice+"_6");
				reorder = Boolean.parseBoolean(arr[6]);
				//System.out.println(reorder+"_7");
				Species = arr[7];
				//System.out.println(Species+"_8");
				Attribute = arr[8];
				//System.out.println(Attribute+"_9");
				Race = arr[9];
				//System.out.println(Race+"_10");
				HP = arr[10];
				//System.out.println(HP+"_11");
				Recovery = arr[11];
				//System.out.println(Recovery+"_12");
				Attack = arr[12];
				//System.out.println(Attack+"_13");
				Cost = arr[13];
				//System.out.println(Cost+"_14");
				for(int i=0;i<arr.length;i++){
					//System.out.print(arr[i]+"_");
				}
				//System.out.println();
				stuff.add(new Stuff(ItemID,name,description,numStock,price,buyingPrice,reorder,Species,Attribute,Race,HP,Attack,Recovery,Cost));
			}
			input.close();
		}else{
			itemData.getParentFile().mkdirs();
			itemData.createNewFile();
		}
	}
	public ArrayList<Stuff> getStuff(){
		return this.stuff;
	}
}
