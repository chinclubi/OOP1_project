package object;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadMember {
	private ArrayList<customerData> customer = new ArrayList<customerData>();
	public ReadMember() throws IOException {
		String currentDir = new File("").getAbsolutePath();
		File memberData = new File (currentDir+"\\data\\members.txt");
		//System.out.println(currentDir);
		//System.out.println(memberData);
		if(memberData.exists()){
			Scanner input = new Scanner(memberData);
			String customerID,username,password,customerType,firstname,surname,email,phonenumber;
			while(input.hasNext()){
				customerID = input.next();
				username = input.next();
				password = input.next();
				customerType = input.next();
				firstname = input.next();
				surname = input.next();
				email = input.next();
				phonenumber = input.next();
				customer.add(new customerData(customerID,username,password,customerType,firstname,surname,email,phonenumber));
			}
			input.close();
		}else{
			memberData.getParentFile().mkdirs();
			memberData.createNewFile();
		}
	}
	public ArrayList<customerData> getCustomer(){
		return this.customer;
	}
	
	
	
	
}
