package object;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import object.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class searchCustomer extends JFrame implements Runnable {

    private JLabel searchCustomer;
    private JTextField TextCustomer;
    private ArrayList<customerData> customer;
    static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;
    private JButton Bsearch;
    String currentDir = new File("").getAbsolutePath();
    File customerData = new File (currentDir+"\\data\\members.txt");
    ReadMember customerB;
	
    public searchCustomer() {
        super("Search Customer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2 -100,
				screenSize.height / 2-100);
		setLocation(middle);
		setUndecorated(true);
		mouseDownScreenCoords = null;
		mouseDownCompCoords = null;
		addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				mouseDownCompCoords = null;
			}

			public void mousePressed(MouseEvent e) {
				mouseDownCompCoords = e.getPoint();
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y
						- mouseDownCompCoords.y);
			}
		});
        try {
    		customerB = new ReadMember();
    		this.customer = customerB.getCustomer();
    	} catch (IOException e1) {
    		System.out.println("Error to Read member Data!!!");
    	}
        
        initialcomponent();


    }

    private void initialcomponent() {

        FlowLayout layout = new FlowLayout();
        Container contain = new Container();
        JPanel box = new JPanel();
        box.setBorder(BorderFactory.createLineBorder(
				Color.black, 1));
        contain.setLayout(new BoxLayout(contain, BoxLayout.Y_AXIS));
        
        JPanel pane = new JPanel();
        pane.setLayout(layout);
        JPanel pane1 = new JPanel();
        pane1.setLayout(layout);
        searchCustomer = new JLabel("Search Customer");
        
        TextCustomer = new JTextField(10);
        
        Bsearch = new JButton("Search");
        JButton cancel = new JButton("Cancel");
       
         ClassLoader loader = this.getClass().getClassLoader();

        Bsearch.setHorizontalAlignment(JTextField.CENTER);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        Bsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (checkBill(TextCustomer.getText())) {
                       // Store store = new Store();
                    	for(int i=0;i<customer.size();i++){
                        	if(TextCustomer.getText().equals(customer.get(i).getCustomerID())){
                        		printSearchCustomer cus = new printSearchCustomer(customer.get(i).getCustomerID(),customer.get(i).getUser(),customer.get(i).getName(),customer.get(i).getLastName(),customer.get(i).getEmail(),customer.get(i).getTel());
                        		cus.run();
                        		break;
                        	}
                        }
                    	
                        setVisible(false);  
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "Not Found Customer", "Wrong", JOptionPane.PLAIN_MESSAGE, null);
                    
                    }
                } catch (FileNotFoundException ex) {
                    customerData.getParentFile().mkdirs();
			try {
				customerData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Can't Create or Write File "+customerData);
			}
                }
                
                


            }
        });
        
        

        pane.add(searchCustomer);
        pane.add(TextCustomer);
        contain.add(pane);
        
        
        
        JPanel boxX = new JPanel();
        boxX.setLayout(new FlowLayout());
        boxX.add(cancel);
        boxX.add(Bsearch);
        contain.add(boxX);
        box.add(contain);
        super.add(box);


    }

    public boolean checkBill(String TcustomerID) throws FileNotFoundException {
        
        boolean check = false;
        for(int i=0;i<customer.size();i++){
        	if(TcustomerID.equals(customer.get(i).getCustomerID())){
        		check = true;
        	}
        }
        return check;

    }

    public void run() {
        pack();
        setVisible(true);
    }
}
