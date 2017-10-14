package ab.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ab.AddressBook;
import ab.Contact;

@SuppressWarnings("serial")
public class ButtonAddContact extends ButtonAdd {
	
	public ButtonAddContact(MainWindow window) {
		super();
		setToolTipText("Add contact");
		
		// When button is pressed...
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create & open dialog for editing contacts
				Contact contact = new Contact();
				PanelEditContact panelAdd = new PanelEditContact(contact);
				int choice = JOptionPane.showConfirmDialog(window.getRootPane().getParent(), panelAdd, "Add Contact",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				// If "OK" button was pressed, save contact data
				if (choice == JOptionPane.OK_OPTION) {
					AddressBook book = window.getCurrentAB();
					
					book.addContact(contact); // Add the Contact to the AddressBook
					panelAdd.saveContact(); // Update info in Contact object according to dialog fields
					window.panelContactList.setContacts(book.getContacts()); // Refresh contacts list
					window.refreshContactsLabel(); // Update "Contacts" label
				}
			}
		});
	}
	
}
