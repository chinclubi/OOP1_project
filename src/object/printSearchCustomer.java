/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class printSearchCustomer extends JFrame {
    /* Object columName[]={"customerID","Username","FirstName","LastName","Email","Phone"};
  
     JTable tb;
     public printSearchBill(String customerID,String username,String firstname,String lastname ,String email ,String phone){
       
        
     Object data[][] = {{customerID,username,firstname,lastname,email,phone}};
     Container c = getContentPane();
     tb=new JTable(data,columName);
     JScrollPane scPane=new JScrollPane(tb);
     tb.setFont(new Font("Cordia New",0,20));
     c.add(scPane);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
    
     }*/

    Image pic1, pic2;
    MediaTracker t;
    //Toolkit t;
    int billid, customerType;
    // ArrayList order;
    double total;
    Graphics g2;
    private Container c;
    String customerID, username, firstname, lastname, email, phone;

    public printSearchCustomer(String customerID, String username, String firstname, String lastname, String email, String phone) {

        super("Customer");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.customerID = customerID;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(224, 224, 224, 0));
        setPreferredSize(new Dimension(850, 900));


    }

    public void paint(Graphics g) {
        String profile1;
        String profile2;
        URL profile;
        ImageIcon imgprofile;
        int n = 30;
        super.paint(g);
        //  t = new MediaTracker(this);
        profile1 = "images/" + customerID + ".jpg";
        //  profile2="images/searchPerson1.png";
        ClassLoader loader = this.getClass().getClassLoader();

        URL timeline = loader.getResource("images/searchCustomer.jpg");
        URL menubar = loader.getResource("images/menubar.png");
        try {
            profile = loader.getResource(profile1);
            imgprofile = new ImageIcon(profile);
        } catch (NullPointerException e) {
            profile = loader.getResource("images/searchPerson1.png");
            imgprofile = new ImageIcon(profile);

        }

        URL panel = loader.getResource("images/searchCustomerpanel.jpg");
        URL comment = loader.getResource("images/commentBox2.jpg");
        URL searchdata = loader.getResource("images/searchdata.jpg");
        ImageIcon imgtimeline = new ImageIcon(timeline);
        ImageIcon imgmenubar = new ImageIcon(menubar);

        ImageIcon imgpanel = new ImageIcon(panel);
        ImageIcon imgcomment = new ImageIcon(comment);
        ImageIcon imgsearchdata = new ImageIcon(searchdata);

        // Calendar c = Calendar.getInstance();

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g.drawImage(imgtimeline.getImage(), 0, 45, 850, 315, this);
        g.drawImage(imgmenubar.getImage(), 0, 30, 850, 28, this);
        g.drawImage(imgpanel.getImage(), 0, 315, this);
        g.drawImage(imgprofile.getImage(), 22, 223, 135, 135, this);
        g.drawImage(imgcomment.getImage(), 430, 370, this);
        g.drawImage(imgsearchdata.getImage(), 25, 370, this);
        //g2.setFont(new Font("Vladimir Script", Font.PLAIN, 72));
        // g2.drawString("Tos Shop",20,60);

        g2.setFont(new Font("Tahoma", Font.BOLD, 14));
        g2.setColor(Color.DARK_GRAY);
        // Point2D.Double point1 = new Point2D.Double(0, 98);
        // Point2D.Double point2 = new Point2D.Double(900, 98);
        // Line2D.Double line1 = new Line2D.Double(point1, point2);
        // g2.draw(line1);

        //g2.drawString("CustomerID", 50, 300);
        g2.drawString(customerID, 150, 460);
        // g2.drawString("Username",50, 380);
        g2.drawString(username, 150, 508);
        // g2.drawString("FirstName", 50, 460);
        g2.drawString(firstname, 150, 556);
        // g2.drawString("LastName", 50, 540);
        g2.drawString(lastname, 150, 600);
        //g2.drawString("Email", 50, 620);
        g2.drawString(email, 150, 646);
        // g2.drawString("Phone", 50,700);
        g2.drawString(phone, 150, 696);
        // Point2D.Double point3 = new Point2D.Double(0, 130);
        // Point2D.Double point4 = new Point2D.Double(900, 130);
        // Line2D.Double line2 = new Line2D.Double(point3, point4);
        //  g2.draw(line2);



    }

    public void run() {
        pack();
        setVisible(true);
    }
}
