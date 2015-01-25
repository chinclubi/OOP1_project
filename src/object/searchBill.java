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

public class searchBill extends JFrame implements Runnable {

    private JLabel searchBill;
    private JTextField TextBill;
    static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;
    private JButton Bsearch;
    private ReadBill read;

    ArrayList<Bill> aBill = new ArrayList();
     int billID = -1;
    public searchBill() {
        super("Search Bill");
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
        
        initialcomponent();


    }

    private void initialcomponent() {
    	try {
    		read = new ReadBill();
    		aBill=read.getBill();
		} catch (IOException e1) {
			System.out.println("Error to Read Bill Data!!!");
		}
        
        
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
        searchBill = new JLabel("Search Bill");
        
        TextBill = new JTextField(10);
        
        Bsearch = new JButton("search");
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
                    if (checkBill(TextBill.getText())) {
                        printBill print = new printBill(billID);
                                print.run();
                     
  //                      setVisible(false);  
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "Not Found Bill", "Wrong", JOptionPane.PLAIN_MESSAGE, null);
                    
                    }
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                    Logger.getLogger(searchBill.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                


            }
        });
        
        

        pane.add(searchBill);
        pane.add(TextBill);
        contain.add(pane);
        
        contain.add(Bsearch);
        

        JPanel boxX = new JPanel();
        boxX.setLayout(new FlowLayout());
        boxX.add(cancel);
        boxX.add(Bsearch);
        contain.add(boxX);
        box.add(contain);
        super.add(box);

    }

    public boolean checkBill(String TBill) throws FileNotFoundException, IOException {
        
       // String fname = "/project/Bill.txt";
        //File infile = new File(fname);
       
        boolean check = false;
        int inputBill = Integer.parseInt(TBill);
            // Data each bill Can add continue
             for(int i=0 ;i<aBill.size();i++ ){
                 Bill cBill = (Bill)aBill.get(i);
                 
                 billID=cBill.getBillNumber();
                 if(billID==inputBill){
                     check=true;
                     break;
                 }
                 else{
                     check=false;
                 }
             
             }
            
            
           

        
        return check;

    }

    public void run() {
        pack();
        setVisible(true);
    }
}
