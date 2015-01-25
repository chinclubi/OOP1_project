package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Admin;
import main.MainMenu;

public class AddCard extends JFrame {
	private ArrayList<Stuff> cargo;
	private ArrayList<customerData> customerBase;
	private String currentUser;
	private JTextField cardID, cardName, cardDes, cardStock, cardPrice,
			cardBuyPrice, cardSpecies, cardAttribute, cardRace, cardHP,
			cardAttack, cardRcovery, cardCost;
	private JLabel status;

	public AddCard(String User,ArrayList<Stuff> stuff,ArrayList<customerData> customer) {
		this.currentUser = User;
		this.cargo = stuff;
		this.customerBase = customer;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2 - 250,
				screenSize.height / 2 - 250);
		setLocation(middle);
		setTitle("Welcome Administrator!");

		super.setPreferredSize(new Dimension(500,530));
		
		super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
            	Admin admin = new Admin(currentUser,customerBase);
				new WriteStore(cargo);
				admin.run();
            }
        });
		JComponent main = new AlphaContainer(createPanel());
		main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
		
		JComponent panelX1 = new AlphaContainer(createPanel());
		panelX1.setLayout(new FlowLayout());
		
		panelX1.add(new JLabel("Add New Item"));
		
		main.add(panelX1);
		
		JComponent panelX2 = new AlphaContainer(createPanel());
		panelX2.setLayout(new FlowLayout());
		
		panelX2.add(new JLabel("Card ID : "));
		cardID = new JTextField(20);
		panelX2.add(cardID);
		
		main.add(panelX2);
		
		JComponent panelX3 = new AlphaContainer(createPanel());
		panelX3.setLayout(new FlowLayout());
		
		panelX3.add(new JLabel("Card Name : "));
		cardName = new JTextField(20);
		panelX3.add(cardName);
		
		main.add(panelX3);
		
		JComponent panelX4 = new AlphaContainer(createPanel());
		panelX4.setLayout(new FlowLayout());
		
		panelX4.add(new JLabel("Description : "));
		cardDes = new JTextField(20);
		panelX4.add(cardDes);
		
		main.add(panelX4);
		
		JComponent panelX5 = new AlphaContainer(createPanel());
		panelX5.setLayout(new FlowLayout());
		
		panelX5.add(new JLabel("Stock : "));
		cardStock = new JTextField(20);
		panelX5.add(cardStock);
		
		main.add(panelX5);
		
		JComponent panelX6 = new AlphaContainer(createPanel());
		panelX6.setLayout(new FlowLayout());
		
		panelX6.add(new JLabel("Price : "));
		cardPrice= new JTextField(20);
		panelX6.add(cardPrice);
		
		main.add(panelX6);
		
		JComponent panelX7 = new AlphaContainer(createPanel());
		panelX7.setLayout(new FlowLayout());
		
		panelX7.add(new JLabel("BuyingPrice : "));
		cardBuyPrice= new JTextField(20);
		panelX7.add(cardBuyPrice);
		
		main.add(panelX7);
		
		JComponent panelX8 = new AlphaContainer(createPanel());
		panelX8.setLayout(new FlowLayout());
		
		panelX8.add(new JLabel("Species : "));
		cardSpecies= new JTextField(20);
		panelX8.add(cardSpecies);
		
		main.add(panelX8);
		
		JComponent panelX9 = new AlphaContainer(createPanel());
		panelX9.setLayout(new FlowLayout());
		
		panelX9.add(new JLabel("Attribute : "));
		cardAttribute= new JTextField(20);
		panelX9.add(cardAttribute);
		
		main.add(panelX9);
		
		JComponent panelX10 = new AlphaContainer(createPanel());
		panelX10.setLayout(new FlowLayout());
		
		panelX10.add(new JLabel("Race : "));
		cardRace= new JTextField(20);
		panelX10.add(cardRace);
		
		main.add(panelX10);
		
		JComponent panelX11 = new AlphaContainer(createPanel());
		panelX11.setLayout(new FlowLayout());
		
		panelX11.add(new JLabel("HP : "));
		cardHP= new JTextField(20);
		panelX11.add(cardHP);
		
		main.add(panelX11);
		
		JComponent panelX12 = new AlphaContainer(createPanel());
		panelX12.setLayout(new FlowLayout());
		
		panelX12.add(new JLabel("Attack : "));
		cardAttack= new JTextField(20);
		panelX12.add(cardAttack);
		
		main.add(panelX12);
		
		JComponent panelX13 = new AlphaContainer(createPanel());
		panelX13.setLayout(new FlowLayout());
		
		panelX13.add(new JLabel("Rcovery : "));
		cardRcovery= new JTextField(20);
		panelX13.add(cardRcovery);
		
		main.add(panelX13);
		
		JComponent panelX14 = new AlphaContainer(createPanel());
		panelX14.setLayout(new FlowLayout());
		
		panelX14.add(new JLabel("Rcovery : "));
		cardCost= new JTextField(20);
		panelX14.add(cardCost);
		
		main.add(panelX14);
		
		JComponent panelX15 = new AlphaContainer(createPanel());
		panelX15.setLayout(new FlowLayout());
		
		JButton saveBTN= new JButton("Save");
		JButton clearBTN= new JButton("Clear");
		panelX15.add(saveBTN);
		panelX15.add(clearBTN);
		
		main.add(panelX15);
		
		JComponent panelX16 = new AlphaContainer(createPanel());
		panelX16.setLayout(new FlowLayout());
		
		status= new JLabel();
		panelX16.add(status);
		
		main.add(panelX16);
		
		add(main);
		
		clearBTN.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardID.setText("");
				cardName.setText("");
				cardDes.setText("");
				cardStock.setText("");
				cardPrice.setText("");
				cardBuyPrice.setText("");
				cardSpecies.setText("");
				cardAttribute.setText("");
				cardRace.setText("");
				cardHP.setText("");
				cardAttack.setText("");
				cardRcovery.setText("");
				cardCost.setText("");
			}
		});
		saveBTN.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean already = false;
				for(int i=0;i<cargo.size();i++){
					if(cargo.get(i).getItemID()==Integer.parseInt(cardID.getText())){
						already = true;
						break;
					}
				}
				if(already){
					status.setText("Error same Item !!!");
					status.setForeground(Color.red);
				}else{
					cargo.add(new Stuff(Integer.parseInt(cardID.getText()),cardName.getText(),cardDes.getText(),Integer.parseInt(cardStock.getText()),Double.parseDouble(cardPrice.getText()),Double.parseDouble(cardBuyPrice.getText()),false,cardSpecies.getText(),cardAttribute.getText(),cardRace.getText(),cardHP.getText(),cardAttack.getText(),cardRcovery.getText(),cardCost.getText()));
					new WriteStore(cargo);
					status.setText("Add item successful!!!");
					status.setForeground(Color.green);
				}
			}
		});
	}

	public JPanel createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.setBackground(new Color(255, 0, 0, 0));
		panel.setOpaque(true);
		return panel;

	}
	
	public void run() {
		pack();
		setVisible(true);
	}

}
