package ab.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 4031442172054995576L;
	private GridBagLayout layout;
	private GridBagConstraints c;
	
	// Panels within the main window
	private JPanel panelBookList;
	private JPanel panelContactList;
	
	// Components for panelBookList
	private AddressBookList<String> listBooks;
	private JScrollPane scrollPaneBooks;
	
	public MainWindow() {
		setTitle("My Address Books");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(layout);
		
		
		/*
		 * Display panel showing list of address books
		 */
		
		panelBookList = new JPanel(new BorderLayout());
		panelBookList.setBackground(Color.LIGHT_GRAY);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		
		getContentPane().add(panelBookList, c);
		
		
		/*
		 * Display button for adding address books
		 */
		
		JButton buttonAddBook = new JButton(new ImageIcon(getClass().getResource("img/+.png")));
		buttonAddBook.setMargin(new Insets(4, 4, 4, 4));
		buttonAddBook.setContentAreaFilled(false);
		buttonAddBook.setRolloverIcon(new ImageIcon(getClass().getResource("img/+_rollover.png")));
		buttonAddBook.setPressedIcon(new ImageIcon(getClass().getResource("img/+_pressed.png")));
		buttonAddBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonAddBook.setFocusable(false);
		
		c.insets = new Insets(6, 6, 6, 0);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		getContentPane().add(buttonAddBook, c);
		
		
		/*
		 * Display "Address Books" title
		 */
		
		JLabel labelBookList = new JLabel("Address Books");
		labelBookList.setFont(new Font("verdana", Font.BOLD, 20));
		
		c.insets = new Insets(6, 6, 6, 6);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		getContentPane().add(labelBookList, c);
		
		
		/*
		 * Display separator between book list & contact list
		 */
		JPanel separator = new JPanel();
		separator.setBackground(Color.BLACK);
		separator.setPreferredSize(new Dimension(2, 300));
		separator.setMaximumSize(new Dimension(2, 300));
		
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		
		getContentPane().add(separator, c);
		
		
		/*
		 * Display panel showing list of contacts in selected address book
		 */
		panelContactList = new JPanel(new BorderLayout());
		panelContactList.setPreferredSize(new Dimension(300, 0));
		
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		
		getContentPane().add(panelContactList, c);
		
		/*
		 * Display the components inside the panels
		 */
		listBooks = new AddressBookList<String>(new String[] {
			"Insert Book Title Here",
			"Test",
			"Insert Book Title Here",
			"Test",
			"Insert Book Title Here",
			"Test"
		});
		listBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBooks.setCellRenderer(new AddressBookListRenderer());
		listBooks.setFixedCellWidth(300);
		listBooks.setVisibleRowCount(4);
		scrollPaneBooks = new JScrollPane(listBooks, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneBooks.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		panelBookList.add(scrollPaneBooks, BorderLayout.CENTER);
		
		pack();
		setMinimumSize(new Dimension(680, 350));
		setFocusable(true);
		setLocationRelativeTo(null); // center on screen
		setVisible(true);
	}
	
}
