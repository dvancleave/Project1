
package ab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ab.gui.MainWindow;

public class Main {
	
	public static List<AddressBook> addressBooks = new ArrayList<AddressBook>();
	
	public static void main(String[] args) {
		// Create placeholder list of AddressBooks
		for (int i=0; i<10; i++) {
			AddressBook ab = new AddressBook();
			ab.setName("Test address book #" + (i+1));
			for (int j=0; j<i*2; j++) ab.addContact(new Contact());
			addressBooks.add(ab);
		}
		
		// Change look & feel to that of the native OS
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// Open GUI
		new MainWindow();
	}
	
}
