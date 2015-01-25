package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import object.*;

public class Register extends JFrame implements Runnable {

	private JTextField usernameInput, passwordInput, firstnameInput,
			surnameInput, emailInput, phoneInput;

	ArrayList customerBase = new ArrayList();

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	static int LASTCUSTOMERID = 100000;

	boolean successful = false;

	public Register(ArrayList customerB) {
		super("Register");
		this.customerBase = customerB;
		if (this.customerBase.size() != 0) {
			customerData data = (customerData) this.customerBase
					.get(this.customerBase.size() - 1);
			this.LASTCUSTOMERID = Integer.parseInt(data.getCustomerID()) + 1;
		}
		setAlwaysOnTop(true);
		initialcomponent();
	}

	public static JPanel createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		// panel.setBorder(new TitledBorder(text));
		panel.setBackground(new Color(255, 0, 0, 0));
		panel.setOpaque(true);
		// panel.setPreferredSize(new Dimension(200,200));
		return panel;

	}

	private void initialcomponent() {
		ClassLoader loader = this.getClass().getClassLoader();
		URL bgPic = loader.getResource("images/register.png");
		setContentPane(new JLabel(new ImageIcon(bgPic)));
		setResizable(false);
		setLayout(new FlowLayout());
		setUndecorated(true);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBackground(new Color(0, 0, 0, 0));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2 - 480,
				screenSize.height / 2 - 290);
		setLocation(middle);
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
		JComponent windows = new AlphaContainer(createPanel());
		windows.setLayout(new BoxLayout(windows, BoxLayout.Y_AXIS));
		windows.setBackground(new Color(255, 0, 255, 10));

		// Close BTn
		JComponent right = new AlphaContainer(createPanel());
		right.setLayout(new FlowLayout());
		JLabel label_space = new JLabel("");
		label_space.setPreferredSize(new Dimension(240, 20));
		right.add(label_space);
		// Close BTn
		JButton closeBTN = new JButton(new ImageIcon(
				loader.getResource("images/close_reg.png")));
		closeBTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/close_reg_over.png")));
		closeBTN.setPressedIcon(null);
		closeBTN.setBorder(BorderFactory.createEmptyBorder());
		closeBTN.setContentAreaFilled(false);
		// closeBTN.setPreferredSize(new Dimension(242, 42));
		// closeBTN.setBackground(new Color(255, 0, 255, 0));
		closeBTN.setOpaque(false);
		closeBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// closeBTN.setFocusable(false);
		right.add(closeBTN);
		windows.add(right);
		// windows.add(new JLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
		// add(windows);
		// End Close Button

		// -------------1
		JComponent labelA = new AlphaContainer(createPanel());
		labelA.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceA = new JLabel("");
		label_spaceA.setPreferredSize(new Dimension(15, 20));
		labelA.add(label_spaceA);

		JLabel usernameText = new JLabel(new ImageIcon(
				loader.getResource("images/register_04.png")));
		usernameText.setPreferredSize(new Dimension(240, 43));

		usernameInput = new JTextField("username", 25);

		usernameInput.setLayout(new BorderLayout());
		usernameInput.setBorder(null);
		// usernameInput.setOpaque(false);
		usernameInput.setHorizontalAlignment(JTextField.CENTER);
		usernameInput.setPreferredSize(new Dimension(242, 43));
		usernameInput.setBackground(new Color(0, 0, 0, 0));
		usernameInput.add(usernameText, BorderLayout.LINE_START);
		usernameInput.setForeground(Color.white);
		usernameInput.setOpaque(false);
		labelA.add(usernameInput);

		usernameInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (usernameInput.getText().equals("username"))
					usernameInput.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (usernameInput.getText().equals(""))
					usernameInput.setText("username");
			}
		});

		windows.add(labelA);
		// -------------1 End
		/*
		 * //-------------2 JComponent labelB = new
		 * AlphaContainer(createPanel()); labelB.setLayout(new
		 * FlowLayout(FlowLayout.LEFT));
		 * 
		 * JLabel label_spaceB = new JLabel("");
		 * label_spaceB.setPreferredSize(new Dimension(15,20));
		 * labelB.add(label_spaceB);
		 * 
		 * JLabel spaceA = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_05.png")));
		 * 
		 * spaceA.setPreferredSize(new Dimension(240,10)); labelB.add(spaceA);
		 * windows.add(labelB); //-------------2 End
		 */
		// -------------3
		JComponent labelC = new AlphaContainer(createPanel());
		labelC.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceC = new JLabel("");
		label_spaceC.setPreferredSize(new Dimension(15, 20));
		labelC.add(label_spaceC);

		JLabel passwordText = new JLabel(new ImageIcon(
				loader.getResource("images/register_06.png")));

		passwordInput = new JPasswordField("password", 25);

		passwordInput.setLayout(new BorderLayout());
		passwordInput.setBorder(null);
		// passwordInput.setOpaque(false);
		passwordInput.setHorizontalAlignment(JTextField.CENTER);
		passwordInput.setPreferredSize(new Dimension(242, 43));
		passwordInput.setBackground(new Color(0, 0, 0, 0));
		passwordInput.add(passwordText, BorderLayout.LINE_START);
		passwordInput.setForeground(Color.white);
		passwordInput.setOpaque(false);
		labelC.add(passwordInput);

		passwordInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (passwordInput.getText().equals("password"))
					passwordInput.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (passwordInput.getText().equals(""))
					passwordInput.setText("password");
			}
		});

		labelC.add(passwordInput);
		windows.add(labelC);
		// -------------3 End
		/*
		 * //-------------4 JComponent labelD = new
		 * AlphaContainer(createPanel()); labelD.setLayout(new
		 * FlowLayout(FlowLayout.LEFT));
		 * 
		 * JLabel label_spaceD = new JLabel("");
		 * label_spaceD.setPreferredSize(new Dimension(15,20));
		 * labelD.add(label_spaceD);
		 * 
		 * JLabel spaceB = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_07.png")));
		 * spaceB.setPreferredSize(new Dimension(240,10)); labelD.add(spaceB);
		 * windows.add(labelD); //-------------4 End
		 */
		// -------------5
		JComponent labelE = new AlphaContainer(createPanel());
		labelE.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceE = new JLabel("");
		label_spaceE.setPreferredSize(new Dimension(15, 20));
		labelE.add(label_spaceE);

		JLabel firstnameText = new JLabel(new ImageIcon(
				loader.getResource("images/register_08.png")));
		labelE.add(firstnameText);
		firstnameInput = new JTextField("firstname", 25);

		firstnameInput.setLayout(new BorderLayout());
		firstnameInput.setBorder(null);
		// firstnameInput.setOpaque(false);
		firstnameInput.setHorizontalAlignment(JTextField.CENTER);
		firstnameInput.setPreferredSize(new Dimension(242, 43));
		firstnameInput.setBackground(new Color(0, 0, 0, 0));
		firstnameInput.add(firstnameText, BorderLayout.LINE_START);
		firstnameInput.setForeground(Color.white);
		firstnameInput.setOpaque(false);
		labelE.add(firstnameInput);

		firstnameInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (firstnameInput.getText().equals("firstname"))
					firstnameInput.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (firstnameInput.getText().equals(""))
					firstnameInput.setText("firstname");
			}
		});

		windows.add(labelE);
		// -------------5 End
		/*
		 * //-------------6 JComponent labelF = new
		 * AlphaContainer(createPanel()); labelF.setLayout(new
		 * FlowLayout(FlowLayout.LEADING));
		 * 
		 * JLabel label_spaceF = new JLabel("");
		 * label_spaceF.setPreferredSize(new Dimension(15,20));
		 * labelF.add(label_spaceF);
		 * 
		 * JLabel spaceD = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_09.png")));
		 * spaceD.setPreferredSize(new Dimension(240,10)); labelF.add(spaceD);
		 * windows.add(labelF); //-------------6 End
		 */
		// -------------7
		JComponent labelG = new AlphaContainer(createPanel());
		labelG.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceG = new JLabel("");
		label_spaceG.setPreferredSize(new Dimension(15, 20));
		labelG.add(label_spaceG);

		JLabel surnameText = new JLabel(new ImageIcon(
				loader.getResource("images/register_10.png")));
		surnameInput = new JTextField("surname", 25);

		surnameInput.setLayout(new BorderLayout());
		surnameInput.setBorder(null);
		// surnameInput.setOpaque(false);
		surnameInput.setHorizontalAlignment(JTextField.CENTER);
		surnameInput.setPreferredSize(new Dimension(242, 43));
		surnameInput.setBackground(new Color(0, 0, 0, 0));
		surnameInput.add(surnameText, BorderLayout.LINE_START);
		surnameInput.setForeground(Color.white);
		surnameInput.setOpaque(false);
		labelA.add(surnameInput);

		surnameInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (surnameInput.getText().equals("surname"))
					surnameInput.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (surnameInput.getText().equals(""))
					surnameInput.setText("surname");
			}
		});

		labelG.add(surnameInput);
		windows.add(labelG);
		// -------------7 End
		/*
		 * //-------------8 JComponent labelH = new
		 * AlphaContainer(createPanel()); labelH.setLayout(new
		 * FlowLayout(FlowLayout.LEADING));
		 * 
		 * JLabel label_spaceH = new JLabel("");
		 * label_spaceH.setPreferredSize(new Dimension(15,20));
		 * labelH.add(label_spaceH);
		 * 
		 * JLabel spaceE = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_11.png")));
		 * spaceE.setPreferredSize(new Dimension(240,10)); labelH.add(spaceE);
		 * windows.add(labelH); //-------------8 End
		 */
		// -------------9
		JComponent labelI = new AlphaContainer(createPanel());
		labelI.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceI = new JLabel("");
		label_spaceI.setPreferredSize(new Dimension(15, 20));
		labelI.add(label_spaceI);

		JLabel emailText = new JLabel(new ImageIcon(
				loader.getResource("images/register_12.png")));
		emailInput = new JTextField("email", 25);

		emailInput.setLayout(new BorderLayout());
		emailInput.setBorder(null);
		// emailInput.setOpaque(false);
		emailInput.setHorizontalAlignment(JTextField.CENTER);
		emailInput.setPreferredSize(new Dimension(242, 43));
		emailInput.setBackground(new Color(0, 0, 0, 0));
		emailInput.add(emailText, BorderLayout.LINE_START);
		emailInput.setForeground(Color.white);
		emailInput.setOpaque(false);
		labelA.add(emailInput);

		emailInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (emailInput.getText().equals("email"))
					emailInput.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (emailInput.getText().equals(""))
					emailInput.setText("email");
			}
		});
		labelI.add(emailInput);
		windows.add(labelI);
		// -------------9 End
		/*
		 * //-------------10 JComponent labelJ = new
		 * AlphaContainer(createPanel()); labelJ.setLayout(new
		 * FlowLayout(FlowLayout.LEADING));
		 * 
		 * JLabel label_spaceJ = new JLabel("");
		 * label_spaceJ.setPreferredSize(new Dimension(15,20));
		 * labelJ.add(label_spaceJ);
		 * 
		 * JLabel spaceF = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_13.png")));
		 * spaceF.setPreferredSize(new Dimension(240,10)); labelJ.add(spaceF);
		 * windows.add(labelJ); //-------------10 End
		 */
		// -------------11
		JComponent labelK = new AlphaContainer(createPanel());
		labelK.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceK = new JLabel("");
		label_spaceK.setPreferredSize(new Dimension(15, 20));
		labelK.add(label_spaceK);

		JLabel phoneText = new JLabel(new ImageIcon(
				loader.getResource("images/register_14.png")));
		phoneInput = new JTextField("phonenumber", 25);

		phoneInput.setLayout(new BorderLayout());
		phoneInput.setBorder(null);
		// phoneInput.setOpaque(false);
		phoneInput.setHorizontalAlignment(JTextField.CENTER);
		phoneInput.setPreferredSize(new Dimension(242, 43));
		phoneInput.setBackground(new Color(0, 0, 0, 0));
		phoneInput.add(phoneText, BorderLayout.LINE_START);
		phoneInput.setForeground(Color.white);
		phoneInput.setOpaque(false);
		labelA.add(phoneInput);

		phoneInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (phoneInput.getText().equals("phonenumber"))
					phoneInput.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (phoneInput.getText().equals(""))
					phoneInput.setText("phonenumber");
			}
		});
		labelK.add(phoneInput);
		windows.add(labelK);
		// -------------11 End

		// --------------Status JLabel
		JComponent labelSt = new AlphaContainer(createPanel());
		labelSt.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceSt = new JLabel("");
		label_spaceSt.setPreferredSize(new Dimension(15, 20));
		labelSt.add(label_spaceSt);

		JLabel StatusBg = new JLabel(new ImageIcon(
				loader.getResource("images/register_16.png")));
		final JLabel Status = new JLabel();
		Status.setPreferredSize(new Dimension(240, 40));
		Status.setMaximumSize(new Dimension(242, 45));
		Status.setLayout(new BorderLayout());
		Status.add(StatusBg, BorderLayout.LINE_START);
		Status.setVisible(true);
		Status.setForeground(Color.white);
		Status.setHorizontalAlignment(JTextField.CENTER);
		Status.setBackground(new Color(0, 0, 0, 0));
		labelSt.add(Status);
		windows.add(labelSt);
		// --------------Status JLabel End
		/*
		 * //-------------12 JComponent labelL = new
		 * AlphaContainer(createPanel()); labelL.setLayout(new
		 * FlowLayout(FlowLayout.LEADING));
		 * 
		 * JLabel label_spaceL = new JLabel("");
		 * label_spaceL.setPreferredSize(new Dimension(15,20));
		 * labelL.add(label_spaceL);
		 * 
		 * JLabel spaceG = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_15.png")));
		 * spaceG.setPreferredSize(new Dimension(240,10)); labelL.add(spaceG);
		 * windows.add(labelL); //-------------12 End
		 */
		// -------------13
		JComponent labelM = new AlphaContainer(createPanel());
		labelM.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceM = new JLabel("");
		label_spaceM.setPreferredSize(new Dimension(15, 20));
		labelM.add(label_spaceM);

		JButton clearBTN = new JButton(new ImageIcon(
				loader.getResource("images/register_17.png")));
		clearBTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/register_over_17.png")));
		clearBTN.setPressedIcon(null);
		clearBTN.setBorder(BorderFactory.createEmptyBorder());
		clearBTN.setContentAreaFilled(false);
		clearBTN.setPreferredSize(new Dimension(240, 40));
		clearBTN.setBackground(new Color(255, 0, 255, 0));

		clearBTN.setOpaque(false);
		clearBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		clearBTN.setFocusable(false);

		labelM.add(clearBTN);
		windows.add(labelM);
		// -------------13 End
		/*
		 * //-------------14 JComponent labelN = new
		 * AlphaContainer(createPanel()); labelN.setLayout(new
		 * FlowLayout(FlowLayout.LEADING));
		 * 
		 * JLabel label_spaceN = new JLabel("");
		 * label_spaceN.setPreferredSize(new Dimension(15,20));
		 * labelN.add(label_spaceN);
		 * 
		 * JLabel spaceH = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_17.png")));
		 * spaceH.setPreferredSize(new Dimension(240,10)); labelN.add(spaceH);
		 * windows.add(labelN); //-------------14 End
		 */
		// -------------15
		JComponent labelO = new AlphaContainer(createPanel());
		labelO.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel label_spaceO = new JLabel("");
		label_spaceO.setPreferredSize(new Dimension(15, 20));
		labelO.add(label_spaceO);

		JButton confirmBTN = new JButton(new ImageIcon(
				loader.getResource("images/register_19.png")));
		confirmBTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/register_over_19.png")));
		confirmBTN.setPressedIcon(null);
		confirmBTN.setBorder(BorderFactory.createEmptyBorder());
		confirmBTN.setContentAreaFilled(false);
		confirmBTN.setPreferredSize(new Dimension(240, 40));
		confirmBTN.setBackground(new Color(255, 0, 255, 0));

		confirmBTN.setOpaque(false);
		confirmBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		confirmBTN.setFocusable(false);

		labelO.add(confirmBTN);
		windows.add(labelO);
		// -------------15 End
		/*
		 * //-------------16 JComponent labelP = new
		 * AlphaContainer(createPanel()); labelP.setLayout(new
		 * FlowLayout(FlowLayout.LEADING));
		 * 
		 * JLabel label_spaceP = new JLabel("");
		 * label_spaceP.setPreferredSize(new Dimension(15,20));
		 * labelP.add(label_spaceP);
		 * 
		 * JLabel spaceJ = new JLabel(new ImageIcon(
		 * loader.getResource("images/register_19.png")));
		 * spaceJ.setPreferredSize(new Dimension(240,10)); labelP.add(spaceJ);
		 * windows.add(labelP); //-------------16 End
		 */
		// windows.add(new JLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));
		// End Close Button

		add(windows);

		confirmBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (usernameInput.getText().equals("username")
							|| usernameInput.getText().equals("")) {
						Status.setText("Please enter your username !!");
						//System.out.println("Error 1");
					} else if (passwordInput.getText().equals("password")
							|| passwordInput.getText().equals("")) {
						Status.setText("Please enter your password !!");
						//System.out.println("Error 2");
					} else if (firstnameInput.getText().equals("firstname")
							|| firstnameInput.getText().equals("")) {
						Status.setText("Please enter your firstname !!");
						//System.out.println("Error 3");
					} else if (surnameInput.getText().equals("surname")
							|| surnameInput.getText().equals("")) {
						Status.setText("Please enter your surname !!");
						//System.out.println("Error 4");
					} else if (emailInput.getText().equals("email")
							|| emailInput.getText().equals("")) {
						Status.setText("Please enter your Email !!");
						//System.out.println("Error 5");
					} else if (phoneInput.getText().equals("phonenumber")
							|| phoneInput.getText().equals("")) {
						Status.setText("Please enter your Phone Number !!");
						//System.out.println("Error 6");
					} else if (!checkUser(usernameInput.getText(),
							passwordInput.getText())) {
						writeData();
						Status.setForeground(Color.green);
						Status.setText("Successful !!");
						MessageBox messageSuccessful = new MessageBox(
								"<html><strong><font size=5>Registration successful!!</font></strong><br /> Thank you for registering to use<br /> TOS Shop.<br /></html>");
						messageSuccessful.run();
						setVisible(false);
					} else {
						Status.setForeground(Color.white);
						Status.setText("Username already exists !!");
					}
				} catch (IOException ex) {
					Logger.getLogger(Register.class.getName()).log(
							Level.SEVERE, null, ex);
				}

			}
		});
		clearBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameInput.setText("username");
				passwordInput.setText("password");
				firstnameInput.setText("firstname");
				surnameInput.setText("surname");
				emailInput.setText("email");
				phoneInput.setText("phonenumber");
			}
		});
		closeBTN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
	}

	public void writeData() {
		// customerBase.add(new
		// customerData("1","chinclubi","015335510","Chin","Soonue","chincub@gmail.com","0824822789"));
		customerBase.add(new customerData(this.LASTCUSTOMERID + "",
				this.usernameInput.getText(), this.passwordInput.getText(),
				"0", this.firstnameInput.getText(),
				this.surnameInput.getText(), this.emailInput.getText(),
				this.phoneInput.getText()));

		new WriteMember(this.customerBase);

	}

	public boolean checkUser(String Tusername, String Tpassword)
			throws IOException {
		boolean check = false;
		// ReadMember customer = new ReadMember();
		// this.customerBase = customer.getCustomer();
		for (int i = 0; i < this.customerBase.size(); i++) {
			customerData member = (customerData) this.customerBase.get(i);
			System.out.println(member.toString());
			System.out.println(member.getUser());
		}
		for (int i = 0; i < this.customerBase.size(); i++) {

			customerData member = (customerData) this.customerBase.get(i);
			if (member.getUser().equals(Tusername)) {
				check = true;
				break;
			}

		}
		return check;
	}

	public void run() {
		pack();
		setVisible(true);
	}
}
