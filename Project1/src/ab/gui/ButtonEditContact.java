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
public class ButtonEditContact extends JButton {
	
	/*
	 * Customized edit contact button
	 */
	
	public ButtonEditContact(PanelContactRow parent, Contact contact) {
		super();
		
		setToolTipText("Edit contact");
		setIcon(new ImageIcon(getClass().getResource("img/edit.png")));
		setRolloverIcon(new ImageIcon(getClass().getResource("img/edit_rollover.png")));
		setPressedIcon(new ImageIcon(getClass().getResource("img/edit_pressed.png")));
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
		
		// When button is pressed...
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create & open dialog for editing contacts
				PanelEditContact panelEdit = new PanelEditContact(contact);
				String title = "Editing '" + contact.getFirstName() + " " + contact.getLastName() + "'";
				int choice = JOptionPane.showConfirmDialog(parent.getRootPane().getParent(), panelEdit, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				// If "OK" button was pressed, save contact data
				if (choice == JOptionPane.OK_OPTION) {
					panelEdit.saveContact();
					parent.refresh();
				}
			}
		});
	}
	
}
