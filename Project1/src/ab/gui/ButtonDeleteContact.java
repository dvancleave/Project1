package ab.gui;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ab.Contact;
import ab.gui.PanelContacts.PanelContactRow;

@SuppressWarnings("serial")
public class ButtonDeleteContact extends JButton {
	
	/*
	 * Customized red "x" button for deleting contacts
	 */

	private static final ImageIcon icon = new ImageIcon(ButtonDeleteContact.class.getResource("img/x.png"));
	private static final ImageIcon iconPressed = new ImageIcon(ButtonDeleteContact.class.getResource("img/x_pressed.png"));
	private static final ImageIcon iconRollover = new ImageIcon(ButtonDeleteContact.class.getResource("img/x_rollover.png"));
	
	public ButtonDeleteContact(MainWindow window, PanelContactRow row, Contact contact) {
		super();

		setToolTipText("Delete contact");
		setIcon(icon);
		setPressedIcon(iconPressed);
		setRolloverIcon(iconRollover);
		setMargin(new Insets(2, 2, 2, 2));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Confirm that the user really wants to delete the contact
				String msg = "Are you sure you want to delete '" + contact.getFirstName() + " " + contact.getLastName() + "' from your contacts?";
				int choice = JOptionPane.showConfirmDialog(window.getRootPane().getParent(),
						msg, "Delete Contact", JOptionPane.OK_CANCEL_OPTION);
				
				// If "OK" button was pressed, delete contact
				if (choice == JOptionPane.OK_OPTION) {
					window.getCurrentAB().removeContact(contact); // Remove contact from actual address book
					window.panelContactList.removeContact(contact); // Update contact list in GUI
					window.refreshContactsLabel(); // Update "Contacts" label
				}
			}
		});
	}
	
}
