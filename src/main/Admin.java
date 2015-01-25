package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import object.*;
public class Admin extends JFrame implements Runnable {
	private ArrayList<customerData> customer;
	private String currentUser;
	static String[] userLogin = new String[8];
	private double profits=0;
	private JButton button6;
	public Admin(String username,ArrayList<customerData> user){
		super.setPreferredSize(new Dimension(500,300));
		super.setTitle("Administrator Panel..");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2-250,
				screenSize.height / 2-250);
		super.setLocation(middle);
		super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
            	MainMenu mainWindows = new MainMenu();
        		mainWindows.run();
            }
        });
		
		ReadProfit profitB;
		try {
			profitB = new ReadProfit();
			profits = profitB.getProfit();
		} catch (IOException e1) {
			System.out.println("Error to Read profits Data!!!");
		}
		customer = user;
		currentUser = username;
		userLogin = getDataAccount(username);
		
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(6,1));
		pane.setPreferredSize(new Dimension(500,200));
		
		JButton button1 = new JButton("Take Order");
		
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				StoreShop store = new StoreShop(currentUser,customer);
				store.run();
				dispose();
			}
		});
		JButton button2 = new JButton("Restock");
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Restock store = new Restock();
				store.run();
			}
		});
		JButton button3 = new JButton("Print stock");
		button3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				PrintStock printStore = new PrintStock();
				printStore.run();
			}
		});
		JButton button4 = new JButton("Search Customer");
		button4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new searchCustomer().run();
				//printSearchCustomer customer = new printSearchCustomer(userLogin[0],userLogin[1],userLogin[4],userLogin[5],userLogin[6],userLogin[7]);
			}
		});
		JButton button5 = new JButton("Search Bill");
		button5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new searchBill().run();
			}
		});
		button6 = new JButton("<html>Clear Profit<br />Profit : "+this.profits+" Baht(s)</html>");
		button6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new WriteProfits(0.00);
				profits=0;
				button6.setText("<html>Clear Profit<br />Profit : "+profits+" Baht(s)</html>");
			}
		});
		pane.add(button1);
		pane.add(button2);
		pane.add(button3);
		pane.add(button4);
		pane.add(button5);
		pane.add(button6);
		
		add(pane);
		
	}
	private String[] getDataAccount(String username) {
		String[] memberData = new String[8];
		for (int i = 0; i < this.customer.size(); i++) {
			if (customer.get(i).getUser().equals(username)) {
				memberData[0] = customer.get(i).getCustomerID();
				memberData[1] = customer.get(i).getUser();
				memberData[2] = customer.get(i).getPassword();
				memberData[3] = customer.get(i).getCustomerType();
				memberData[4] = customer.get(i).getName();
				memberData[5] = customer.get(i).getLastName();
				memberData[6] = customer.get(i).getEmail();
				memberData[7] = customer.get(i).getTel();
			}
		}
		return memberData;
	}
	public void run(){
		pack();
		setVisible(true);
	}
}
