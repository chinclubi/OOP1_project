package object;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class printBill extends JFrame {

	private Image pic1, pic2;
	private MediaTracker t;
	// Toolkit t;
	private int billid, customerType;
	private ArrayList<Bill> order;
	private ArrayList<Stuff> cargo;
	private double total;
	private Graphics g2;
	private Container c;
	private int billNum;
	private ArrayList<customerData> account;

	public printBill(int numBill) {
		super("PrintBill");
		try {
			this.order = new ReadBill().getBill();
		} catch (IOException e) {
			System.out.println("Can't Read Bill!!");
		}
		try {
			this.account = new ReadMember().getCustomer();
		} catch (IOException e) {
			System.out.println("Can't Read Member!!");
		}
		try {
			this.cargo = new ReadStore().getStuff();
		} catch (IOException e) {
			System.out.println("Can't Read Store!!");
		}
		this.billNum = getIndexBill(numBill);
		//System.out.println(order.get(billNum).toString());
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		setPreferredSize(new Dimension(520, 600));
		for(int i=0;i<account.size();i++){
			if(order.get(billNum).getCustomerID()==Integer.parseInt(account.get(i).getCustomerID())){
				order.get(billNum).setDiscountPercent(account.get(i).getCustomerType());
			}
		}
		

	}

	public void paint(Graphics g) {

		int n = 30;
		super.paint(g);
		t = new MediaTracker(this);

		ClassLoader loader = this.getClass().getClassLoader();

		URL logo = loader.getResource("images/logo1.png");
		URL bgbill = loader.getResource("images/bgbill.png");

		ImageIcon imglogo = new ImageIcon(logo);

		ImageIcon imgbgbill = new ImageIcon(bgbill);

		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentDate = df.format(c.getTime());

		// System.out.println("Current Date : " + currentDate);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g.drawImage(imglogo.getImage(), 10, 10, this);
		g.drawImage(imgbgbill.getImage(), 60, 110, 400, 400, this);

		g2.setFont(new Font("Vladimir Script", Font.PLAIN, 72));
		// g2.drawString("Tos Shop",20,60);

		g2.setFont(new Font("Arial", Font.BOLD, 20));
		Point2D.Double point1 = new Point2D.Double(20, 98);
		Point2D.Double point2 = new Point2D.Double(500, 98);
		Line2D.Double line1 = new Line2D.Double(point1, point2);
		g2.draw(line1);

		g2.drawString("Product Name", 50, 120);
		g2.drawString("Piece(s)", 400, 120);
		Point2D.Double point3 = new Point2D.Double(20, 130);
		Point2D.Double point4 = new Point2D.Double(500, 130);
		Line2D.Double line2 = new Line2D.Double(point3, point4);
		g2.draw(line2);
		// print order
		for (int i = 0; i < order.get(billNum).getCart().size(); i++) {

			g2.drawString(
					i
							+ 1
							+ "."
							+ cargo.get(getIndexName(order.get(billNum).getCart().get(i).getItemID())).getName(),
					50, 150 + (n * i));

		}
		for (int i = 0; i < order.get(billNum).getCart().size(); i++) {

			g2.drawString(""
					+ order.get(billNum).getCart().get(i).getNumItemGet(), 400,
					150 + (n * i));

		}

		g2.setFont(new Font("Arial", Font.BOLD, 16));
		g2.drawString(currentDate, 350, 88);
		g2.drawString("SubTotal:", 350, 400);
		g2.drawString(order.get(billNum).getBillTotal()+"", 430, 400);
		g2.drawString("Discount:", 350, 420);
		g2.drawString(order.get(billNum).getBillTotal()*((order.get(billNum).getDiscountPercent())/100)+"", 430, 420);
		g2.drawString("Total:", 350, 440);
		g2.drawString(order.get(billNum).getBillTotal()*((100-order.get(billNum).getDiscountPercent())/100)+"", 400, 440);

	}

	public int getIndexName(int ItemID) {
		int i;
		for (i = 0; i < cargo.size(); i++) {
			if (cargo.get(i).getItemID() == ItemID) {
				break;
			}
		}
		return i;
	}

	public int getIndexBill(int BillID) {
		int i;
		for (i = 0; i < order.size(); i++) {
			if (order.get(i).getBillNumber() == BillID) {
				break;
			}
		}
		return i;
	}

	public void run() {
		pack();
		setVisible(true);
	}
}
