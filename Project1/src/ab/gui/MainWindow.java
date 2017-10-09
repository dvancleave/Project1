package ab.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ab.AddressBook;
import ab.Contact;
import ab.Main;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private GridBagLayout layout;
	private GridBagConstraints c;
	private Font fontHeader = new Font("verdana", Font.BOLD, 20);
	
	// Panels within the main window
	private JPanel panelBookList;
	private PanelContacts panelContactList;
	
	// Components for book list section
	private JButton buttonAddBook;
	private JLabel labelBookList;
	private JList<String> listBooks;
	private JScrollPane scrollPaneBooks;
	
	// Components for contact list section
	private JButton buttonAddContact;
	private JLabel labelContactList;
	private JScrollPane scrollPaneContacts;
	
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
		buttonAddBook = new ButtonAdd("Add address book");
		
		c.insets = new Insets(6, 6, 6, 0);
		c.fill = GridBagConstraints.NONE;
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
		labelBookList = new JLabel("Address Books");
		labelBookList.setFont(fontHeader);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(6, 6, 6, 6);
		c.fill = GridBagConstraints.VERTICAL;
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
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		
		getContentPane().add(separator, c);
		
		
		/*
		 * Display button for adding address books
		 */
		buttonAddContact = new ButtonAdd("Add contact");
		
		c.insets = new Insets(6, 6, 6, 0);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		getContentPane().add(buttonAddContact, c);
		
		
		/*
		 * Display "Contacts" title
		 */
		labelContactList = new JLabel("Contacts");
		labelContactList.setFont(fontHeader);
		
		c.insets = new Insets(6, 6, 6, 6);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		
		getContentPane().add(labelContactList, c);
		
		
		/*
		 * Display the components inside book panel
		 */
		Vector<String> addressBookNames = new Vector<String>();
		for (AddressBook ab : Main.addressBooks) {
			addressBookNames.add(ab.getName());
		}
		listBooks = new AddressBookList<String>(addressBookNames);
		listBooks.setFixedCellWidth(300);
		
		scrollPaneBooks = new JScrollPane(listBooks,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPaneBooks.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		panelBookList.add(scrollPaneBooks, BorderLayout.CENTER);
		
		
		/*
		 * Display the contacts panel
		 */
		panelContactList = new PanelContacts();
		
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		
		scrollPaneContacts = new JScrollPane(panelContactList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneContacts.setPreferredSize(new Dimension(300, 0));
		getContentPane().add(scrollPaneContacts, c);
		
		
		/*
		 * Display main JFrame
		 */
		pack();
		setMinimumSize(new Dimension(680, 350));
		setFocusable(true);
		setLocationRelativeTo(null); // center on screen
		setVisible(true);
		
		
		/*
		 * Listener for address book selection
		 */
		listBooks.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = listBooks.getSelectedIndex(); // Index of address book selected in address book list
				AddressBook ab = Main.addressBooks.get(index); // Address book that was selected
				ArrayList<Contact> contacts = ab.getContacts(); // List of contacts in this address book
				
				labelContactList.setText("Contacts (" + contacts.size() + ")"); // Update header w/ # of contacts
				
				panelContactList.setContacts(contacts); // Add contacts to scrollable panel
			}
		});
	}
	
}
