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

import javax.swing.DefaultListModel;
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
	protected JPanel panelBookList;
	protected PanelContacts panelContactList;
	
	// Components for book list section
	protected JButton buttonAddBook;
	protected JLabel labelBookList;
	protected JList<String> listBooks;
	protected JScrollPane scrollPaneBooks;
	protected DefaultListModel<String> addressBookNames;
	
	// Components for contact list section
	protected JButton buttonAddContact;
	protected JLabel labelContactList;
	protected JScrollPane scrollPaneContacts;
	protected JPanel panelSearch;
	
	// Address book currently being viewed (null = none)
	private AddressBook currentAB = null;
	
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
		c.gridheight = 2;
		
		getContentPane().add(panelBookList, c);
		
		
		/*
		 * Display button for adding address books
		 */
		buttonAddBook = new ButtonAddBook(this);
		
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
		separator.setBackground(Color.GRAY);
		separator.setPreferredSize(new Dimension(1, 300));
		separator.setMaximumSize(new Dimension(1, 300));
		
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
		buttonAddContact = new ButtonAddContact(this);
		
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
		//Vector<String> addressBookNames = new Vector<String>();
		addressBookNames = new DefaultListModel<String>();
		for (AddressBook ab : Main.addressBooks) {
			addressBookNames.addElement(ab.getName());
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
		panelContactList = new PanelContacts(this);
		
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
		 * Display the search contacts panel
		 */
		panelSearch = new PanelSearch(this);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		
		getContentPane().add(panelSearch, c);
		
		
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
				currentAB = Main.addressBooks.get(index); // Address book that was selected
				panelContactList.setContacts(currentAB.getContacts()); // Add contacts to scrollable panel
				refreshContactsLabel(); // Update "Contacts" label
			}
		});
	}
	
	public AddressBook getCurrentAB() {
		return currentAB;
	}
	
	// Updates "Contacts:" label w/ correct # of contacts
	public void refreshContactsLabel() {
		ArrayList<Contact> contacts = currentAB.getContacts();
		labelContactList.setText("Contacts (" + contacts.size() + ")");
	}
	
}
