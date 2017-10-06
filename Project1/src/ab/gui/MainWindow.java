package ab.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 4031442172054995576L;
	private GridBagLayout layout;
	private GridBagConstraints c;
	
	// Panels within the main window
	private JPanel panelBookList;
	private JPanel panelContactList;
	
	public MainWindow() {
		setTitle("My Address Books");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 200));
		
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(layout);
		
		JPanel panelBookList = new JPanel();
		panelBookList.setBackground(Color.GREEN);
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		getContentPane().add(panelBookList, c);
		
		JPanel panelContactList = new JPanel();
		panelContactList.setBackground(Color.RED);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		getContentPane().add(panelContactList, c);
		
		pack();
		setVisible(true);
	}
	
}
