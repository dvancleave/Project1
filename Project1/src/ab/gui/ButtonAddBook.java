package ab.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;

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
				AddressBook ab = new AddressBook();
				Main.addressBooks.add(ab);
				window.addressBookNames.addElement(ab.getName());
				//window.listBooks.add
				//((DefaultListModel<String>)window.listBooks.getModel()).addElement(ab);
				//DefaultListModel<String> model = (DefaultListModel<String>) window.listBooks.getModel();
			}
		});
	}
	
}
