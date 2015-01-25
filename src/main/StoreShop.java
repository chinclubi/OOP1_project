package main;

import object.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StoreShop extends JFrame implements Runnable {
	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;
	static int lastCustomerID = 0;
	static int LastBillID = 1000;
	ArrayList<customerData> customerBase = new ArrayList<customerData>();

	ArrayList<Stuff> cargo = new ArrayList<Stuff>();
	ArrayList<Cart> Cart = new ArrayList<Cart>();
	ArrayList<Cart> CartBill = new ArrayList<Cart>();
	ArrayList<Bill> bill = new ArrayList<Bill>();

	static String[] userLogin = new String[8];

	private JLabel cartTxt, totalPriceTxt;
	private JButton CartClear_BTN;
	private JLabel[] showPrice;
	private JButton AddItem = new JButton();
	private JComponent scrollPane;
	
	private double profits; 

	public StoreShop(String usernameLogin, ArrayList<customerData> account) {
		/*
		 * cargo.add(new Stuff(210, "Loki God of Chaos", "N/A", 80, 500, 150,
		 * false, "Dark", "Leader Skill : Dark Attack x 200%.", "God", "3096",
		 * "409", "1540", "13")); //cargo.add(new Stuff(412,
		 * "Necromancer Master Endor", "N/A", 0, 700, 150, true, "Dark",
		 * "Leader Skill : Dark Attack x 200%.", "Human", "2128", "383", "1314",
		 * "12")); new WriteStore(cargo);
		 */
		this.setTitle("Welcome");
		// setType(JFrame.Type.UTILITY);
		customerBase = account;
		/*
		 * ReadMember customerB; try { customerB = new ReadMember();
		 * this.customerBase = customerB.getCustomer(); } catch (IOException e1)
		 * { System.out.println("Error to Read member Data!!!"); }
		 */
		ReadStore itemB;
		try {
			itemB = new ReadStore();
			this.cargo = itemB.getStuff();
		} catch (IOException e1) {
			System.out.println("Error to Read store Data!!!");
		}
		for(int i=0;i<cargo.size();i++){
			//System.out.println(cargo.get(i).toString());
		}
		
		ReadProfit profitB;
		try {
			profitB = new ReadProfit();
			profits = profitB.getProfit();
		} catch (IOException e1) {
			System.out.println("Error to Read profits Data!!!");
		}
		
		ReadBill billB;
		try {
			billB = new ReadBill();
			this.bill = billB.getBill();
		} catch (IOException e1) {
			System.out.println("Error to Read bill Data!!!");
		}
		for (int i = 0; i < bill.size(); i++) {
			//System.out.println(bill.get(i).toString());
		}
		showPrice = new JLabel[cargo.size()];
		userLogin = getDataAccount(usernameLogin);
		/*
		 * for (int i = 0; i < userLogin.length; i++) {
		 * System.out.println(userLogin[i]); }
		 */
		ClassLoader loader = this.getClass().getClassLoader();

		URL bgPic = loader.getResource("images/bg_store.png");
		setContentPane(new JLabel(new ImageIcon(bgPic)));

		setSize(1024, 576);

		setResizable(false);
		setLayout(new FlowLayout());
		setUndecorated(true);

		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setBackground(new Color(0, 0, 0, 0));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		Store();

	}

	public JPanel createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		// panel.setBorder(new TitledBorder(text));
		panel.setBackground(new Color(255, 0, 0, 0));
		panel.setOpaque(true);
		// panel.setPreferredSize(new Dimension(200,200));
		return panel;

	}

	public JScrollPane createScroll(Component tests) {
		JScrollPane panel = new JScrollPane(tests);

		UIManager.put("ScrollBar.background", new Color(255, 255, 255, 150));
		UIManager
				.put("ScrollBar.thumbHighlight", new Color(150, 150, 150, 250));
		UIManager.put("ScrollBar.thumbShadow", new Color(150, 150, 150, 250));
		UIManager.put("ScrollBar.thumbDarkShadow",
				new Color(150, 150, 150, 250));
		UIManager.put("ScrollBar.thumbLightShadow ", new Color(150, 150, 150,
				250));
		UIManager.put("ScrollBar.thumb", new Color(150, 150, 150, 250));
		UIManager.put("ScrollBar.track", new Color(255, 255, 255, 150));

		UIManager.put("ScrollBar.width", 10);

		panel.setPreferredSize(new Dimension(20, 0));
		panel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected JButton createDecreaseButton(int orientation) {
				return createZeroButton();
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return createZeroButton();
			}

			private JButton createZeroButton() {
				JButton jbutton = new JButton();
				jbutton.setPreferredSize(new Dimension(0, 0));
				jbutton.setMinimumSize(new Dimension(0, 0));
				jbutton.setMaximumSize(new Dimension(0, 0));
				return jbutton;
			}
		});
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.getViewport().setOpaque(false);
		panel.setBorder(null);

		return panel;

	}

	private String[] getDataAccount(String username) {
		String[] memberData = new String[8];
		for (int i = 0; i < this.customerBase.size(); i++) {
			if (customerBase.get(i).getUser().equals(username)) {
				memberData[0] = customerBase.get(i).getCustomerID();
				memberData[1] = customerBase.get(i).getUser();
				memberData[2] = customerBase.get(i).getPassword();
				memberData[3] = customerBase.get(i).getCustomerType();
				memberData[4] = customerBase.get(i).getName();
				memberData[5] = customerBase.get(i).getLastName();
				memberData[6] = customerBase.get(i).getEmail();
				memberData[7] = customerBase.get(i).getTel();
			}
		}
		return memberData;
	}

	private void Store() {
		ClassLoader loader = this.getClass().getClassLoader();

		JComponent main = new AlphaContainer(createPanel());
		main.setLayout(new BorderLayout());

		// Top
		// Menu---------------------------------------------------------------
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
		// closeBTN.setMargin(new Insets(0, 0, 0, 10));
		closeBTN.setBorderPainted(false);
		closeBTN.setOpaque(false);
		closeBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeBTN.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		box.add(closeBTN);
		// End Close Button
		// End Top
		// Menu---------------------------------------------------------------
		// Bottom
		// Menu---------------------------------------------------------------
		JComponent menu = new AlphaContainer(createPanel());
		// JPanel menu = new JPanel();
		// menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		menu.setPreferredSize(new Dimension(200, 100));
		JLabel Bottom = new JLabel();
		Bottom.setBorder(BorderFactory.createEmptyBorder(0, 500, 0, 0));
		Bottom.setText("<html><u>Hint!!!</u> : <u>\"Left\"</u> Click for add item to cart , <u>\"Right\"</u> Click for remove item from cart.</html>");
		Bottom.setForeground(Color.white);
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(Bottom);
		// End Bottom
		// Menu---------------------------------------------------------------
		main.add(box, BorderLayout.NORTH);
		main.add(menu, BorderLayout.SOUTH);

		scrollPane = new AlphaContainer(createScroll(StoreGUI()));

		scrollPane.setSize(new Dimension(720, 570));

		scrollPane.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 20));

		JComponent xAxis = new AlphaContainer(createPanel());
		xAxis.setLayout(new BoxLayout(xAxis, BoxLayout.X_AXIS));

		JComponent JCart = new AlphaContainer(createPanel());
		// JPanel JCart = new JPanel();
		JCart.setLayout(new BoxLayout(JCart, BoxLayout.Y_AXIS));
		JCart.setPreferredSize(new Dimension(345, 100));

		CartClear_BTN = new JButton(new ImageIcon(
				loader.getResource("images/cart_btn_01.png")));

		CartClear_BTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/cart_btn_over_01.png")));
		CartClear_BTN.setPressedIcon(null);
		CartClear_BTN.setBorder(BorderFactory.createEmptyBorder());
		CartClear_BTN.setContentAreaFilled(false);
		CartClear_BTN.setPreferredSize(new Dimension(242, 42));
		CartClear_BTN.setBackground(new Color(255, 0, 255, 100));
		CartClear_BTN.setOpaque(false);
		CartClear_BTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CartClear_BTN.setFocusable(false);

		JButton CartConfirm_BTN = new JButton(new ImageIcon(
				loader.getResource("images/cart_btn_02.png")));
		CartConfirm_BTN.setRolloverIcon(new ImageIcon(loader
				.getResource("images/cart_btn_over_02.png")));
		CartConfirm_BTN.setPressedIcon(null);
		CartConfirm_BTN.setBorder(BorderFactory.createEmptyBorder());
		CartConfirm_BTN.setContentAreaFilled(false);
		CartConfirm_BTN.setPreferredSize(new Dimension(242, 42));
		CartConfirm_BTN.setBackground(new Color(255, 0, 255, 100));
		CartConfirm_BTN.setOpaque(false);
		CartConfirm_BTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CartConfirm_BTN.setFocusable(false);

		JCart.setBorder(BorderFactory.createEmptyBorder(15, 30, 0, 0));

		JComponent Center = new AlphaContainer(createPanel());
		// JPanel Center = new JPanel();
		Center.setLayout(new FlowLayout(FlowLayout.CENTER));
		JComponent ButtonCart = new AlphaContainer(createPanel());
		// JPanel ButtonCart = new JPanel();
		ButtonCart.setLayout(new BoxLayout(ButtonCart, BoxLayout.Y_AXIS));
		ButtonCart.add(CartConfirm_BTN);
		ButtonCart.add(CartClear_BTN);
		Center.add(ButtonCart);

		JLabel cartIcon = new JLabel(new ImageIcon(
				loader.getResource("images/Cart.png")));

		JComponent Left = new AlphaContainer(createPanel());
		Left.setLayout(new FlowLayout(FlowLayout.CENTER));
		Left.add(cartIcon);

		JComponent Left2 = new AlphaContainer(createPanel());
		Left2.setLayout(new FlowLayout(FlowLayout.LEFT));
		Left2.setPreferredSize(new Dimension(290, 200));
		cartTxt = new JLabel();
		totalPriceTxt = new JLabel();
		updateCart();
		cartTxt.setForeground(Color.white);
		totalPriceTxt.setForeground(Color.white);
		Left2.add(cartTxt);

		JComponent Left3 = new AlphaContainer(createPanel());
		Left3.setLayout(new FlowLayout(FlowLayout.LEFT));
		Left3.setPreferredSize(new Dimension(290, 10));
		Left3.add(new JLabel(new ImageIcon(loader
				.getResource("images/Line.png"))));

		JComponent Left4 = new AlphaContainer(createPanel());
		Left4.setLayout(new FlowLayout(FlowLayout.CENTER));
		Left4.setPreferredSize(new Dimension(290, 30));
		Left4.add(totalPriceTxt);

		JComponent Left5 = new AlphaContainer(createPanel());
		Left5.setLayout(new FlowLayout(FlowLayout.LEFT));
		Left5.setPreferredSize(new Dimension(290, 10));
		Left5.add(new JLabel(new ImageIcon(loader
				.getResource("images/Line.png"))));

		JCart.add(Left);
		JCart.add(Left2);
		JCart.add(Left3);
		JCart.add(Left4);
		JCart.add(Left5);
		JCart.add(Center);

		xAxis.add(JCart);
		xAxis.add(scrollPane);

		main.add(xAxis, BorderLayout.CENTER);
		main.setPreferredSize(new Dimension(1020, 570));
		main.setOpaque(false);
		add(main, BorderLayout.CENTER);
		final ImageIcon souldout = new ImageIcon(
				loader.getResource("images/soldout.png"));
		CartClear_BTN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < Cart.size(); i++) {
					cargo.get(getIndexName(Cart.get(i).getItemID())).addStock(
							Cart.get(i).getNumItemGet());
				}
				for (int i = 0; i < showPrice.length; i++) {
					Stuff itemStuff = (Stuff) cargo.get(i);
					if (itemStuff.isReorder()) {
						showPrice[i].setText("");
						showPrice[i].setIcon(souldout);

					} else {
						showPrice[i].setIcon(null);
						showPrice[i]
								.setText("<html><br /><br /><br /><b><font color='white'>In Stock : "
										+ itemStuff.getNumStock()
										+ " Piece<br />"
										+ itemStuff.getPrice()
										+ " Baht / Piece</font></b></html>");
					}
				}
				Cart.clear();
				updateCart();
			}

		});
		CartConfirm_BTN.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (Cart.size() == 0) {
					cartTxt.setText("Please select item(s).");
				} else {
					if (bill.size() != 0) {
						LastBillID = (bill.get(bill.size() - 1).getBillNumber()) + 1;
					}
					Bill newBill = new Bill(LastBillID, Integer
							.parseInt(userLogin[0]), Cart);
					
					double billTotal = 0;
					for (int i = 0; i < Cart.size(); i++) {
						billTotal += cargo.get(
								getIndexName(Cart.get(i).getItemID()))
								.getPrice()*Cart.get(i).getNumItemGet();
						profits += (cargo.get(getIndexName(Cart.get(i).getItemID())).getPrice() - cargo.get(getIndexName(Cart.get(i).getItemID())).getBuyingPrice())*Cart.get(i).getNumItemGet();
					}
					newBill.setBillTotal(billTotal);
					newBill.setDiscountPercent(userLogin[3]);
					//System.out.println(userLogin[3]+"===");
					bill.add(newBill);
					for (int i = 0; i < bill.size(); i++) {
						// System.out.println(bill.get(i).toString());
					}
					new WriteProfits(profits);
					new WriteBill(bill);
					new printBill(LastBillID).run();
					Cart.clear();
					updateCart();
				}
			}

		});
		closeBTN.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				// System.exit(0);
				if(userLogin[3].equals("isADMIN")){
					Admin admin = new Admin(userLogin[1],customerBase);
					new WriteStore(cargo);
					admin.run();
				}else{
					MainMenu mainWindows = new MainMenu();
					new WriteStore(cargo);
					mainWindows.run();
				}
				dispose();
			}
		});
		AddItem.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				AddCard addCard = new AddCard(userLogin[1],cargo,customerBase);
				addCard.run();
				dispose();
			}
		});

	}

	private JComponent StoreGUI() {
		ClassLoader loader = this.getClass().getClassLoader();
		JComponent mainStore = new AlphaContainer(createPanel());
		mainStore.setLayout(new FlowLayout(FlowLayout.LEFT));

		int numItem = 0;
		if (cargo.size() != 0) {
			numItem = cargo.size() - 1;
		}

		ImageIcon imgA = new ImageIcon(loader.getResource("card/addNew.png"));
		Image imgBA = imgA.getImage();
		ImageIcon imgAB = new ImageIcon(
				loader.getResource("card/addNewOver.png"));
		Image imgBAB = imgAB.getImage();

		AddItem.setIcon(new ImageIcon(imgBA.getScaledInstance(200, 300,
				java.awt.Image.SCALE_SMOOTH)));
		AddItem.setBorder(null);
		AddItem.setContentAreaFilled(false);
		AddItem.setBorderPainted(false);
		AddItem.setOpaque(false);

		AddItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		AddItem.setPreferredSize(new Dimension(200, 300));
		AddItem.setMaximumSize(new Dimension(200, 300));
		AddItem.setMinimumSize(new Dimension(200, 300));
		AddItem.setRolloverIcon(new ImageIcon(imgBAB.getScaledInstance(200,
				300, java.awt.Image.SCALE_SMOOTH)));

		JComponent yStore = new AlphaContainer(createPanel());
		yStore.setLayout(new BoxLayout(yStore, BoxLayout.Y_AXIS));

		for (int i = 0; i < Math.ceil(cargo.size() / 3.0); i++) {

			JComponent xStore = new AlphaContainer(createPanel());
			xStore.setLayout(new FlowLayout(FlowLayout.LEFT));

			for (int j = 0; j < 3; j++) {
				if (numItem >= 0) {

					final Stuff itemStuff = (Stuff) cargo.get(cargo.size()
							- numItem - 1);
					final JButton PicItem = new JButton();

					ImageIcon img = new ImageIcon(loader.getResource("card/"
							+ itemStuff.getItemID() + ".jpg"));
					Image imgB = img.getImage();

					PicItem.setIcon(new ImageIcon(imgB.getScaledInstance(200,
							300, java.awt.Image.SCALE_SMOOTH)));
					PicItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

					PicItem.setBorder(null);
					PicItem.setContentAreaFilled(false);
					PicItem.setBorderPainted(false);
					PicItem.setOpaque(false);

					PicItem.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
							0));

					final JComponent Price = new AlphaContainer(createPanel());
					OverlayLayout overlay = new OverlayLayout(Price);
					JLabel price_bg = new JLabel(new ImageIcon(
							loader.getResource("images/stuff.png")));
					Price.setLayout(overlay);

					Price.setPreferredSize(new Dimension(200, 300));
					Price.setMaximumSize(new Dimension(200, 300));
					Price.setMinimumSize(new Dimension(200, 300));

					final int ij = cargo.size() - numItem - 1;

					showPrice[ij] = new JLabel();

					showPrice[ij].setOpaque(false);
					showPrice[ij].setLayout(new BorderLayout());

					showPrice[ij].setBorder(BorderFactory.createLineBorder(
							Color.black, 1));
					/*
					 * showPrice[ij] .setText(
					 * "<html><p><br /><br /><br /><font color='black'>In Stock : "
					 * + itemStuff.getNumStock() + " Piece<br />" +
					 * itemStuff.getPrice() + " Baht / Piece</font><p></html>");
					 */
					// Label_Item[numItem].setForeground(Color.white);

					showPrice[ij].setPreferredSize(new Dimension(200, 300));
					showPrice[ij].setMaximumSize(new Dimension(200, 300));
					showPrice[ij].setMinimumSize(new Dimension(200, 300));
					// Label_Item[numItem].setForeground(Color.white);

					showPrice[ij].setHorizontalAlignment(JTextField.CENTER);
					// Label_Item[numItem].setOpaque(false);
					// Label_Item[numItem].setBackground(new Color(0, 0, 0, 0));
					Price.add(showPrice[ij], BorderLayout.LINE_START);
					PicItem.add(Price);
					Price.add(price_bg, BorderLayout.LINE_START);

					final ImageIcon souldout = new ImageIcon(
							loader.getResource("images/soldout.png"));
					if (itemStuff.isReorder()) {
						Price.setVisible(true);
						showPrice[ij].setText("");
						showPrice[ij].setIcon(souldout);
					} else {
						PicItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
						showPrice[ij].setBackground(new Color(255, 255, 255,
								100));
						showPrice[ij]
								.setText("<html><br /><br /><br /><b><font color='white'>In Stock : "
										+ itemStuff.getNumStock()
										+ " Piece<br />"
										+ itemStuff.getPrice()
										+ " Baht / Piece</font></b></html>");
						Price.setVisible(false);

						PicItem.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								// System.out.println(itemStuff.getNumStock());
								if (e.getButton() == MouseEvent.BUTTON1) {
									if (itemStuff.getNumStock() != 0) {
										if (alreadyInCartAdd(itemStuff
												.getItemID())) {

											itemStuff.removeFromStock(1);
											Cart.get(
													getIndexCart(itemStuff
															.getItemID()))
													.addNumItem();

											showPrice[ij].setText("<html><br /><br /><br /><b><font color='white'>In Stock : "
													+ itemStuff.getNumStock()
													+ " Piece<br />"
													+ itemStuff.getPrice()
													+ " Baht / Piece</font></b></html>");
										} else {
											itemStuff.removeFromStock(1);
											Cart.add(new Cart(itemStuff
													.getItemID(), 1));

											showPrice[ij].setText("<html><br /><br /><br /><b><font color='white'>In Stock : "
													+ itemStuff.getNumStock()
													+ " Piece<br />"
													+ itemStuff.getPrice()
													+ " Baht / Piece</font></b></html>");
										}
									} else {
										Price.setVisible(true);
										showPrice[ij].setText("");
										showPrice[ij].setIcon(souldout);
									}
								} else if (e.getButton() == MouseEvent.BUTTON3) {
									if (alreadyInCart(itemStuff.getItemID())) {
										itemStuff.addStock(1);
										Cart.get(
												getIndexCart(itemStuff
														.getItemID()))
												.removeNumItem();
										if (Cart.get(
												getIndexCart(itemStuff
														.getItemID()))
												.getNumItemGet() == 0) {
											Cart.remove(getIndexCart(itemStuff
													.getItemID()));
										}
										showPrice[ij].setText("<html><br /><br /><br /><b><font color='white'>In Stock : "
												+ itemStuff.getNumStock()
												+ " Piece<br />"
												+ itemStuff.getPrice()
												+ " Baht / Piece</font></b></html>");
										showPrice[ij].setIcon(null);
									}
								}
								updateCart();
							}

							public void mouseEntered(MouseEvent e) {
								Price.setVisible(true);
							}

							public void mouseExited(MouseEvent e) {
								Price.setVisible(false);
							}
						});
					}
					xStore.add(PicItem);
					if (userLogin[3].equals("isADMIN")) {
						if (cargo.size() % 3 != 0
								&& Math.ceil(cargo.size() / 3.0) == i + 1) {
							xStore.add(AddItem);
						}
					}
					numItem--;
				}
			}
			yStore.add(xStore);
		}
		if (userLogin[3].equals("isADMIN")) {
			if (cargo.size() % 3 == 0) {
				JComponent xLeft = new AlphaContainer(createPanel());
				xLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
				xLeft.add(AddItem);
				yStore.add(xLeft);
			}
		}
		mainStore.add(yStore);

		// mainStore.add(bText);
		return mainStore;
	}

	public int getIndexName(int itemID) {
		int i;
		// System.out.println(itemID);
		for (i = 0; i < cargo.size(); i++) {
			// System.out.println(cargo.get(i).getItemID() + " " + itemID + " "+
			// i);
			if (cargo.get(i).getItemID() == itemID) {
				break;
			}
		}
		return i;
	}

	public int getIndexCart(int itemID) {
		int i;
		for (i = 0; i < Cart.size(); i++) {
			if (Cart.get(i).getItemID() == itemID) {
				break;
			}
		}
		return i;
	}

	public boolean alreadyInCartAdd(int itemID) {
		boolean re = false;
		for (int i = 0; i < Cart.size(); i++) {
			if (Cart.get(i).getItemID() == itemID) {
				re = true;
				break;
			}
		}
		return re;
	}

	public boolean alreadyInCart(int itemID) {
		boolean re = false;
		for (int i = 0; i < Cart.size(); i++) {
			if (Cart.get(i).getItemID() == itemID
					&& Cart.get(i).getNumItemGet() != 0) {
				re = true;
				break;
			}
		}
		return re;
	}

	public void updateCart() {
		String txt = "<html><table width='290'>";

		double price = 0;
		for (int i = 0; i < Cart.size(); i++) {
			// System.out.println(getIndexName(Cart.get(i).getItemID()) +
			// "///");
			Stuff stuffItem = (Stuff) cargo.get((getIndexName(Cart.get(i)
					.getItemID())));
			Cart cartItem = (Cart) Cart.get(i);
			price += stuffItem.getPrice() * cartItem.getNumItemGet();
			if (cartItem.getNumItemGet() != 0) {
				txt += String
						.format("<tr><td align='left'>%s</td><td width='80'>: %s</td></tr>",
								stuffItem.getName(), cartItem.getNumItemGet()
										+ " Piece(s)");
			}
		}
		txt += "</table></html>";
		cartTxt.setText(txt);
		totalPriceTxt.setText("Total Price : " + price + " Baht(s)");
	}
	@Override
	public void run() {
		pack();
		setVisible(true);
	}
}
