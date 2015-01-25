package object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteStore {
	private ArrayList<Stuff> itemsDATA = new ArrayList<Stuff>();
	public WriteStore(ArrayList<Stuff> items)  {
		String currentDir = new File("").getAbsolutePath();
		File itemData = new File (currentDir+"\\data\\items.txt");
		this.itemsDATA = items;
		for(int i=0;i<itemsDATA.size();i++){
			if(itemsDATA.get(i).getNumStock()==0){
				itemsDATA.get(i).setReorder(true);
			}
		}
		try {
			FileWriter fw = new FileWriter(itemData);
			PrintWriter pw = new PrintWriter(fw);
			String data="";
			for (int i = 0; i < this.itemsDATA.size(); i++) {
				Stuff itemA = (Stuff) this.itemsDATA.get(i);
				pw.printf("%s %n",itemA.toString());
				//data += itemA.toString()+"%n ";
			}
			//pw.printf(data);
			pw.close();
		} catch (IOException e) {
			itemData.getParentFile().mkdirs();
			try {
				itemData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Can't Create or Write File "+itemData);
			}
		}
	}
	public ArrayList getStuff(){
		return this.itemsDATA;
	}
}
