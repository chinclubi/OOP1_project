package main;

import object.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Restock extends JFrame implements Runnable {

	private ArrayList<Stuff> stuff;
	private JLabel[] showPrice;
	private JTextField[] input1, input2, input3;
	private JLabel status;
	private JComponent scrollPane;
	private Stuff itemStuff;

	public Restock() {
		super("Restock");
		setResizable(false);
		ReadStore itemB;
		try {
			itemB = new ReadStore();
			this.stuff = itemB.getStuff();
		} catch (IOException e1) {
			System.out.println("Error to Read store Data!!!");
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2 - 527,
				screenSize.height / 2 - 325);
		setLocation(middle);

		showPrice = new JLabel[stuff.size()];
		input1 = new JTextField[stuff.size()];
		input2 = new JTextField[stuff.size()];
		input3 = new JTextField[stuff.size()];
		initComponents();
	}

	public JPanel createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.setBackground(new Color(255, 0, 0, 0));
		panel.setOpaque(true);
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

	private void initComponents() {
		ClassLoader loader = this.getClass().getClassLoader();

		int numItem = 0;
		if (stuff.size() != 0) {
			numItem = stuff.size() - 1;
		}
		for (int i = 0; i < stuff.size(); i++) {
			//System.out.println(stuff.get(i).toString());
		}
		
		
		JComponent button = new AlphaContainer(createPanel());
		// button.setLayout(overlay);
		button.setBorder(BorderFactory.createEmptyBorder(0,
				0, 0, 0));
		button.setPreferredSize(new Dimension(200, 300));
		button.setMaximumSize(new Dimension(200, 300));
		button.setMinimumSize(new Dimension(200, 300));
		
		JButton buttonX = new JButton();
		buttonX.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		buttonX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		buttonX.setContentAreaFilled(false);
		buttonX.setOpaque(false);
		buttonX.setBorder(BorderFactory.createLineBorder(
				Color.black, 1));
		
		JComponent panelB = new AlphaContainer(createPanel());
		panelB.setLayout(new BoxLayout(panelB, BoxLayout.Y_AXIS));
		
		JComponent panelX = new AlphaContainer(createPanel());
		panelX.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		status = new JLabel();
		
		JButton SaveBTN = new JButton("Save");
		JButton ResetBTN = new JButton("Reset");
		
		panelX.add(SaveBTN);
		panelX.add(ResetBTN);
		panelX.add(status);
		
		panelB.add(panelX);
		
		button.add(panelB, BorderLayout.CENTER);
		
		buttonX.add(button);
		
		
		JComponent yStore = new AlphaContainer(createPanel());
		yStore.setLayout(new BoxLayout(yStore, BoxLayout.Y_AXIS));
		for (int i = 0; i < Math.ceil(stuff.size() / 5.0); i++) {

			JComponent xStore = new AlphaContainer(createPanel());
			xStore.setLayout(new FlowLayout(FlowLayout.CENTER));

			for (int j = 0; j < 5; j++) {
				if (numItem >= 0) {

					itemStuff = (Stuff) stuff.get(stuff.size() - numItem - 1);
					JButton PicItem = new JButton();

					ImageIcon img = new ImageIcon(loader.getResource("card/"
							+ itemStuff.getItemID() + "i.png"));
					Image imgB = img.getImage();

					PicItem.setIcon(new ImageIcon(imgB.getScaledInstance(100,
							100, java.awt.Image.SCALE_SMOOTH)));
					PicItem.setVerticalAlignment(javax.swing.SwingConstants.TOP);
					PicItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

					PicItem.setContentAreaFilled(false);
					PicItem.setOpaque(false);

					PicItem.setBorder(BorderFactory.createLineBorder(
							Color.black, 1));

					JComponent Price = new AlphaContainer(createPanel());
					OverlayLayout overlay = new OverlayLayout(Price);

					// Price.setLayout(overlay);

					Price.setPreferredSize(new Dimension(200, 300));
					Price.setMaximumSize(new Dimension(200, 300));
					Price.setMinimumSize(new Dimension(200, 300));

					int num = stuff.size() - numItem - 1;

					showPrice[num] = new JLabel();

					showPrice[num].setOpaque(false);
					showPrice[num].setLayout(new BorderLayout());

					showPrice[num].setBorder(BorderFactory.createEmptyBorder(0,
							0, 0, 0));

					showPrice[num].setPreferredSize(new Dimension(200, 100));
					showPrice[num].setMaximumSize(new Dimension(200, 100));
					showPrice[num].setMinimumSize(new Dimension(200, 100));

					showPrice[num].setHorizontalAlignment(JTextField.CENTER);
					showPrice[num]
							.setText("<html><br /><br /><br /><b><font color='Black'>"
									+ itemStuff.getName()
									+ "<br />In Stock : "
									+ itemStuff.getNumStock()
									+ " Piece<br />"
									+ itemStuff.getPrice()
									+ " Baht / Piece</font></b></html>");

					// Price.add(showPrice[ij], BorderLayout.PAGE_START);

					JComponent panel1 = new AlphaContainer(createPanel());
					panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

					JLabel txt1 = new JLabel("SalePrice : ");
					txt1.setPreferredSize(new Dimension(90, 25));

					input1[num] = new JTextField();
					input1[num].setPreferredSize(new Dimension(90, 25));
					input1[num].setText(itemStuff.getPrice()+"");

					JLabel txt2 = new JLabel("BuyingPrice : ");
					txt2.setPreferredSize(new Dimension(90, 25));

					input2[num] = new JTextField();
					input2[num].setPreferredSize(new Dimension(90, 25));
					input2[num].setText(itemStuff.getBuyingPrice()+"");
					
					JLabel txt3 = new JLabel("Refill with : ");
					txt3.setPreferredSize(new Dimension(90, 25));

					input3[num] = new JTextField();
					input3[num].setPreferredSize(new Dimension(90, 25));
					input3[num].setText("0");

					panel1.add(txt1);
					panel1.add(input1[num]);

					JComponent panel2 = new AlphaContainer(createPanel());
					panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

					panel2.add(txt2);
					panel2.add(input2[num]);

					JComponent panel3 = new AlphaContainer(createPanel());
					panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

					panel3.add(txt3);
					panel3.add(input3[num]);

					JComponent panel = new AlphaContainer(createPanel());
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JComponent panelTXT = new AlphaContainer(createPanel());
					panelTXT.setLayout(new FlowLayout(FlowLayout.LEFT));
					panelTXT.add(showPrice[num]);

					panel.add(panelTXT);
					panel.add(panel1);
					panel.add(panel2);
					panel.add(panel3);

					Price.add(panel, BorderLayout.SOUTH);
					PicItem.add(Price);

					Price.setVisible(true);

					xStore.add(PicItem);
					if (stuff.size() % 5 != 0	&& Math.ceil(stuff.size() / 5.0) == i + 1) {
						xStore.add(buttonX);
					}

					numItem--;
				}
			}
			yStore.add(xStore);
			
		}
		if (stuff.size() % 5 == 0) {
			JComponent xLeft = new AlphaContainer(createPanel());
			xLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
			xLeft.add(buttonX);
			yStore.add(xLeft);
		}

		scrollPane = new AlphaContainer(createScroll(yStore));
		scrollPane.setPreferredSize(new Dimension(1064, 570));
		add(scrollPane);
		
		buttonX.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				status.setText("");
			}
		});
		
		SaveBTN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for(int i=0;i<stuff.size();i++){
					if(Integer.parseInt(input3[i].getText())!=0){
						stuff.get(i).refillStock(Integer.parseInt(input3[i].getText()), Double.parseDouble(input2[i].getText()), Double.parseDouble(input1[i].getText()));
					}
				}
				update();
				new WriteStore(stuff);
				status.setText("<html><u>Update stock successful!!!</u></html>");
				status.setForeground(Color.GREEN);
			}
		});
		ResetBTN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				update();				
			}
		});
	}

	public void update() {
		for (int i = 0; i < stuff.size(); i++) {
			Stuff item = stuff.get(i);
			input1[i].setText(item.getPrice() + "");
			input2[i].setText(item.getBuyingPrice() + "");
			input3[i].setText("0");
			showPrice[i]
					.setText("<html><br /><br /><br /><b><font color='Black'>"
							+ item.getName() + "<br />In Stock : "
							+ item.getNumStock() + " Piece<br />"
							+ item.getPrice()
							+ " Baht / Piece</font></b></html>");
		}
	}

	@Override
	public void run() {
		pack();
		setVisible(true);

	}

}
