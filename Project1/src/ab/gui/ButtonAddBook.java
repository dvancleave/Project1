package ab.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ab.AddressBook;
import ab.Main;

@SuppressWarnings("serial")
public class ButtonAddBook extends ButtonAdd {
	public ButtonAddBook(MainWindow window) {
		super();
		setToolTipText("Add address book");

		// When button is pressed, add a new AddressBook to our list
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create book
				String name = JOptionPane.showInputDialog(window.getRootPane().getParent(),
						"Title your new address book:", "New Address Book", JOptionPane.PLAIN_MESSAGE);
				
				if (name == null || name.isEmpty()) {
					JOptionPane.showMessageDialog(window.getRootPane().getParent(),
							"No address book was created.", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					// If "OK" button was pressed, delete contact
					AddressBook ab = new AddressBook(name);
					Main.addressBooks.add(ab);
					window.addressBookNames.addElement(ab.getName());

					// Scroll down to newly created book if necessary
					int index = window.listBooks.getModel().getSize() - 1;
					if (index >= 0) {
						window.listBooks.ensureIndexIsVisible(index);
					}
					
					// Select newly created address book
					window.listBooks.setSelectedIndex(index);
				}
			}
		});
	}

}
