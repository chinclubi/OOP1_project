package object;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MessageBox extends JFrame implements Runnable {
	
	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;
	
	public MessageBox(String message){
		//Create Message Windows
		ClassLoader loader = this.getClass().getClassLoader();
		URL bgPic = loader.getResource("images/boxMessage.png");
		setContentPane(new JLabel(new ImageIcon(bgPic)));
		setResizable(false);
		
		setLayout(new GridBagLayout());
		setUndecorated(true);
		setAlwaysOnTop(true);
		//setLayout(new FlowLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBackground(new Color(0, 0, 0, 200));
		//setPreferredSize(new Dimension(20,20));
		Point middle = new Point(0,0);
		setLocation(middle);
		//End Create Message Windows
		
		JComponent windows = new AlphaContainer(createPanel());
		windows.setLayout(new BoxLayout(windows,BoxLayout.Y_AXIS));
		//windows.setLayout(new GridBagLayout());
		windows.setBackground(new Color(255, 0, 255, 20));
		//windows.setSize(500, 200);
		//windows.setPreferredSize(new Dimension(2000,2000));
		//windows.setMaximumSize(new Dimension(200,200));
		windows.setMinimumSize(new Dimension(200,200));
		
		//windows.setBorder(BorderFactory.createEmptyBorder(100,50,50,50));
		//windows.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JComponent windows2 = new AlphaContainer(createPanel());
		windows2.setLayout(new BoxLayout(windows2,BoxLayout.Y_AXIS));
		windows2.setBackground(new Color(255, 0, 255, 0));
		
		JComponent line_1 = new AlphaContainer(createPanel());
		line_1.setLayout(new FlowLayout());
		//line_1.setBackground(Color.red);
		
		JLabel icon_successful = new JLabel(new ImageIcon(loader.getResource("images/messageBox_03.png")));
		line_1.add(icon_successful);
		JLabel text = new JLabel(message);
		text.setForeground(Color.white);
		text.setVerticalAlignment(SwingConstants.CENTER);
		line_1.add(text);
		
		JComponent line_2 = new AlphaContainer(createPanel());
		line_2.setLayout(new FlowLayout());
		//line_2.setBackground(Color.red);
		
		JButton okBTN = new JButton(new ImageIcon(loader.getResource("images/messageBox_07.png")));
		okBTN.setRolloverIcon(new ImageIcon(loader.getResource("images/messageBox_over_07.png")));
		okBTN.setBorder(null);
		okBTN.setBackground(new Color(0,0,0,0));
		okBTN.setContentAreaFilled(false);
		okBTN.setOpaque(false);
		okBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		line_2.add(okBTN);
		
		windows.add(line_1);
		windows.add(new JLabel());
		windows.add(line_2);
		//windows.add(windows2);
		add(windows);
		
		okBTN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
	}
	public static JPanel createPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		// panel.setBorder(new TitledBorder(text));
		panel.setBackground(new Color(255, 0, 0, 0));
		panel.setOpaque(true);
		//panel.setPreferredSize(new Dimension(200,200));
		return panel;

	}

	@Override
	public void run() {
		pack();
		setVisible(true);
	}
}
