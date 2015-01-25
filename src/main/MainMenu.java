package main;

import object.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainMenu extends JFrame implements Runnable {
	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;
	private boolean isLogin = false, isAdmin = false;

	private ArrayList<customerData> customer = new ArrayList<customerData>();

	final JLabel label_a = new JLabel("");

	public MainMenu() {
		this.setTitle("Welcome");
		// setType(JFrame.Type.UTILITY);
		ReadMember customerB;
		try {
			customerB = new ReadMember();
			this.customer = customerB.getCustomer();
		} catch (IOException e1) {
			System.out.println("Error to Read member Data!!!");
		}

		ClassLoader loader = this.getClass().getClassLoader();
		URL bgPic = loader.getResource("images/bg.png");
		setContentPane(new JLabel(new ImageIcon(bgPic)));

		setIconImage(Toolkit.getDefaultToolkit().getImage("images/logo.png"));

		setSize(1024, 576);

		setResizable(false);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setAlwaysOnTop(true);
		setUndecorated(true);
		// setBounds(20,20,400,400);
		// setUndecorated(false);
		// setState(JFrame.ICONIFIED);
		setVisible(true);
		// setType(Type.POPUP);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Start Click Move Window
		mouseDownScreenCoords = null;
		mouseDownCompCoords = null;
		setLayout(new FlowLayout());
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
		// End Windows move
		Login();
	}

	public void setLogin(boolean parameter) {
		this.isLogin = parameter;
	}

	public boolean getLogin() {
		return this.isLogin;
	}

	public void setAdmin(boolean parameter) {
		this.isAdmin = parameter;
	}

	public boolean getAdmin() {
		return this.isAdmin;
	}

	public static JPanel createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		// panel.setBorder(new TitledBorder(text));
		panel.setBackground(new Color(255, 0, 0, 0));
		panel.setOpaque(true);
		// panel.setPreferredSize(new Dimension(200,200));
		return panel;

	}

	public void setStatusReg() {
		label_a.setForeground(Color.white);
		label_a.setText("<html><font size='-2'><p align=right>Register Successful !!</p></font></html>");
	}

	private void Login() {
		final ClassLoader loader = this.getClass().getClassLoader();

		final JComponent main = new AlphaContainer(createPanel());
		main.setLayout(new BorderLayout());

		JComponent box = new AlphaContainer(createPanel());
		box.setLayout(new FlowLayout());
		// box.setOpaque(false);
		// box.setBackground(new Color(255,255,255,0));
		// box.setLocation(2000, 20);
		JLabel label_space = new JLabel(" ");
		label_space.setPreferredSize(new Dimension(965, 20));
		box.add(label_space);
		// Close Button
		ImageIcon imgClose = new ImageIcon(
				loader.getResource("images/close_btn.png"));
		Image imgCloseB = imgClose.getImage();
		// imgCloseB.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);

		ImageIcon imgCloseH = new ImageIcon(
				loader.getResource("images/close_btn_hover.png"));
		Image imgCloseHB = imgCloseH.getImage();
		// imgCloseHB.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);

		ImageIcon imgCloseP = new ImageIcon(
				loader.getResource("images/close_btn_press.png"));
		Image imgClosePB = imgCloseP.getImage();
		// imgClosePB.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);

		JButton closeBTN = new JButton(
				new ImageIcon(imgCloseB.getScaledInstance(20, 20,
						java.awt.Image.SCALE_SMOOTH)));
		closeBTN.setRolloverIcon(new ImageIcon(imgCloseHB.getScaledInstance(20,
				20, java.awt.Image.SCALE_SMOOTH)));
		closeBTN.setPressedIcon(new ImageIcon(imgClosePB.getScaledInstance(20,
				20, java.awt.Image.SCALE_SMOOTH)));

		closeBTN.setBorder(null);
		closeBTN.setContentAreaFilled(false);
		closeBTN.setBorderPainted(false);
		closeBTN.setOpaque(false);
		closeBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeBTN.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		box.add(closeBTN);
		// End Close Button
		// Start Login GUI
		JComponent boxLogin = new AlphaContainer(createPanel());
		boxLogin.setBackground(new Color(255, 255, 0, 0));
		boxLogin.setLayout(new BoxLayout(boxLogin, BoxLayout.Y_AXIS));
		boxLogin.setPreferredSize(new Dimension(350, 369));
		boxLogin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		boxLogin.add(new JLabel(" "));
		boxLogin.add(new JLabel(" "));
		boxLogin.add(new JLabel(" "));
		boxLogin.add(new JLabel(" "));

		JComponent login_1 = new AlphaContainer(createPanel());
		login_1.setBackground(new Color(255, 0, 255, 0));
		login_1.setPreferredSize(new Dimension(320, 54));
		login_1.setLayout(new BoxLayout(login_1, BoxLayout.X_AXIS));
		login_1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		login_1.add(new JLabel(new ImageIcon(loader
				.getResource("images/login_01.png"))));
		boxLogin.add(login_1);

		JComponent login_2 = new AlphaContainer(createPanel());
		// login_2.setBackground(new Color(255,0,0,10));
		login_2.setPreferredSize(new Dimension(320, 238));
		login_2.setLayout(new BoxLayout(login_2, BoxLayout.X_AXIS));
		login_2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		login_2.add(new JLabel(new ImageIcon(loader
				.getResource("images/login_02.png"))));

		JComponent login_from = new AlphaContainer(createPanel());
		login_from.setLayout(new BoxLayout(login_from, BoxLayout.Y_AXIS));
		login_from.setBackground(new Color(255, 0, 255, 110));

		// login_from.add(new JLabel(new
		// ImageIcon(loader.getResource("images/login_08.png"))));
		login_from.setPreferredSize(new Dimension(242, 238));
		login_from.setMaximumSize(new Dimension(242, 238));
		login_from.setMinimumSize(new Dimension(242, 238));
		// login_from.setOpaque(true);
		// login_from.setMaximumSize(new Dimension(242, 244));
		login_from.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		// Username input
		final JTextField usernameInput = new JTextField("");
		usernameInput.setLayout(new BorderLayout());
		usernameInput.setBorder(null);
		// usernameInput.setOpaque(false);
		// usernameInput.setMargin(new Insets(-50, 50, -50, -50));
		usernameInput.setHorizontalAlignment(JTextField.CENTER);
		usernameInput.setPreferredSize(new Dimension(242, 51));
		usernameInput.setBackground(new Color(0, 0, 0, 0));
		JLabel label = new JLabel(new ImageIcon(
				loader.getResource("images/login_03.png")));
		usernameInput.add(label, BorderLayout.LINE_START);
		usernameInput.setForeground(Color.white);
		usernameInput.setOpaque(false);
		login_from.add(usernameInput);
		// End Username input
		// login_from.add(new JLabel(new
		// ImageIcon(loader.getResource("images/login_03.png"))));

		// Password input
		final JPasswordField passwordInput = new JPasswordField(10);
		passwordInput.setLayout(new BorderLayout());
		passwordInput.setBorder(null);
		passwordInput.setPreferredSize(new Dimension(242, 51));
		// passwordInput.setOpaque(false);
		// passwordInput.setMargin(new Insets(-50, 50, -50, -50));
		passwordInput.setHorizontalAlignment(JTextField.CENTER);
		passwordInput.setBackground(new Color(0, 0, 0, 0));

		JLabel labelB = new JLabel(new ImageIcon(
				loader.getResource("images/login_05.png")));
		passwordInput.add(labelB, BorderLayout.LINE_START);
		passwordInput.setForeground(Color.white);
		passwordInput.setOpaque(false);
		login_from.add(passwordInput);
		// End Password input

		// login_from.add(new JLabel(new
		// ImageIcon(loader.getResource("images/login_05.png"))));

		JLabel login_bg = new JLabel(new ImageIcon(
				loader.getResource("images/login_06.png")));
		// login_bg.setMinimumSize(new Dimension(242,45));
		// login_bg.setPreferredSize(new Dimension(242, 45));
		label_a.setPreferredSize(new Dimension(242, 45));
		// label_a.setMinimumSize(new Dimension(242,45));
		label_a.setMaximumSize(new Dimension(242, 45));
		label_a.setLayout(new BorderLayout());
		label_a.add(login_bg, BorderLayout.LINE_START);
		label_a.setVisible(true);
		label_a.setForeground(Color.white);
		label_a.setHorizontalAlignment(JTextField.RIGHT);
		// label_a.setText("Status");

		login_from.add(label_a);
		// Start Sign in Button
		JButton SignBTN = new JButton(new ImageIcon(
				loader.getResource("images/login_07.png")));
		SignBTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/login_over_07.png")));
		SignBTN.setPressedIcon(null);
		// SignBTN.setRolloverIcon(new
		// ImageIcon(loader.getResource("images/login_over_07.png")));
		// SignBTN.setPressedIcon(new
		// ImageIcon(loader.getResource("images/login_over_07.png")));
		SignBTN.setBorder(BorderFactory.createEmptyBorder());
		SignBTN.setContentAreaFilled(false);
		SignBTN.setPreferredSize(new Dimension(242, 42));
		SignBTN.setBackground(new Color(255, 0, 255, 100));
		// SignBTN.setMargin(new Insets(0, 0, 0, 0));
		// SignBTN.setBorderPainted(false);
		SignBTN.setOpaque(false);
		SignBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		SignBTN.setFocusable(false);
		// End Sign in button
		login_from.add(SignBTN);// Sign in Button

		login_from.add(new JLabel(new ImageIcon(loader
				.getResource("images/login_08.png"))));

		// Start register Button
		JButton regBTN = new JButton(new ImageIcon(
				loader.getResource("images/login_09.png")));
		regBTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/login_over_09.png")));
		regBTN.setPressedIcon(null);
		// regBTN.setRolloverIcon(new
		// ImageIcon(loader.getResource("images/login_over_09.png")));
		// regBTN.setPressedIcon(new
		// ImageIcon(loader.getResource("images/login_over_09.png")));
		regBTN.setBorder(BorderFactory.createEmptyBorder());
		regBTN.setContentAreaFilled(false);
		// regBTN.setBackground(new Color(255,0,255,0));
		regBTN.setPreferredSize(new Dimension(242, 42));
		// regBTN.setMargin(new Insets(0, 0, 0, 0));
		// regBTN.setBorderPainted(false);
		regBTN.setOpaque(false);
		regBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		regBTN.setFocusable(false);
		// End register button
		login_from.add(regBTN);// register Button

		login_2.add(login_from);
		login_2.add(new JLabel(new ImageIcon(loader
				.getResource("images/login_04.png"))));
		boxLogin.add(login_2);

		JComponent login_3 = new AlphaContainer(createPanel());
		login_3.setBackground(new Color(255, 0, 255, 0));
		login_3.setPreferredSize(new Dimension(320, 25));
		login_3.setLayout(new BoxLayout(login_3, BoxLayout.X_AXIS));
		login_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		login_3.add(new JLabel(new ImageIcon(loader
				.getResource("images/login_10.png"))));
		boxLogin.add(login_3);

		JComponent flowLogin = new AlphaContainer(createPanel());
		flowLogin.setLayout(new FlowLayout(FlowLayout.CENTER));
		flowLogin.add(boxLogin);
		// flowLogin.setBackground(new Color(255,255,255,0));
		// flowLogin.setPreferredSize(new Dimension(1020, 500));
		// End Login GUI

		JComponent menu = new AlphaContainer(createPanel());
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		menu.setPreferredSize(new Dimension(200, 100));
		JLabel Logo = new JLabel(new ImageIcon(
				loader.getResource("images/logo.png")));
		// menu.setBackground(new Color(255,255,255,0));
		menu.add(Logo);

		// JLabel Login = new JLabel(new
		// ImageIcon(loader.getResource("images/login.png")));

		// main.add(new JLabel(""), BorderLayout.EAST);
		// main.add(new JLabel(""), BorderLayout.WEST);
		main.add(box, BorderLayout.NORTH);
		main.add(menu, BorderLayout.SOUTH);
		main.add(flowLogin, BorderLayout.CENTER);
		main.setPreferredSize(new Dimension(1020, 570));
		main.setOpaque(false);
		// main.removeAll();
		// main.setBackground(new Color(255, 255, 255, 0));
		// JComponent test = new AlphaContainer(createPanel());
		// test.add(usernameInput);
		// test.add(passwordInput);
		add(main, BorderLayout.CENTER);

		// add(test,BorderLayout.EAST);

		SignBTN.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				ReadMember customerB;
				try {
					customerB = new ReadMember();
					customer = customerB.getCustomer();
				} catch (IOException e1) {
					System.out.println("Error to Read member Data!!!");
				}
				try {
					if (checklogin(usernameInput.getText(),
							passwordInput.getText())) {
						// System.out.println("Login Successful");
						// setVisible(false);
						dispose();
						//System.out.println(customer.get(getIndexUser(usernameInput.getText())).getCustomerType());
						if((customer.get(getIndexUser(usernameInput.getText())).getCustomerType()).equals("isADMIN")){
							Admin admin = new Admin(usernameInput.getText(),customer);
							admin.run();
						}else{
							StoreShop store = new StoreShop(usernameInput.getText(),customer);
							store.run();
						}
					} else {
						label_a.setForeground(Color.white);
						label_a.setText("<html><font size='-2'><p align=right>Wrong Username or Password!!<br /> Please try again.</p></font></html>");
						// System.out.println("Login Error!!!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		closeBTN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		regBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register regis = new Register(customer);
				regis.run();
			}
		});
	}

	public boolean checklogin(String Tusername, String Tpassword)
			throws IOException {
		boolean check = false;
		for (int i = 0; i < this.customer.size(); i++) {
			customerData member = (customerData) this.customer.get(i);
			// System.out.println(member.getUser());
			if (member.getUser().equals(Tusername)
					&& member.getPassword().equals(Tpassword)) {
				check = true;
				break;
			}

		}
		return check;

	}
	public int getIndexUser(String user){
		int i;
		for(i=0;i<customer.size();i++){
			if(customer.get(i).getUser().equals(user)){
				break;
			}
		}
		return i;
	}

	public static void main(String args[]) {
		MainMenu mainWindows = new MainMenu();
		mainWindows.run();
	}

	@Override
	public void run() {
		pack();
		setVisible(true);
	}
}
