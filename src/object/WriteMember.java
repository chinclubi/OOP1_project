package object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteMember {
	private ArrayList customer = new ArrayList();

	public WriteMember(ArrayList member) {
		String currentDir = new File("").getAbsolutePath();
		File memberData = new File(currentDir + "\\data\\members.txt");
		this.customer = member;
		try {
			FileWriter fw = new FileWriter(memberData);
			PrintWriter pw = new PrintWriter(fw);
			String data = "";
			for (int i = 0; i < this.customer.size(); i++) {

				customerData memberA = (customerData) this.customer.get(i);
				data += memberA.toString() + " ";

			}
			pw.println(data);
			pw.close();
		} catch (IOException e) {
			memberData.getParentFile().mkdirs();
			try {
				memberData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Can't Create or Write File "
						+ memberData);
			}
		}
		// System.out.println(currentDir);
		// System.out.println(memberData);
	}

	public ArrayList getCustomer() {
		return this.customer;
	}
}
