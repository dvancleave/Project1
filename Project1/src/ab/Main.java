
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
			for (int j=0; j<i*2; j++)ab.addContact(new Contact());
			
			Contact nc1 = new Contact();
			nc1.setFirstName("Conan");
			ab.addContact(nc1);
			
			Contact nc2 = new Contact();
			nc2.setFirstName("Eriela");
			nc2.setLastName("Cohen");
			ab.addContact(nc2);
			
			Contact nc3 = new Contact();
			nc3.setFirstName("Charlie");
			nc3.setLastName("Ferguson");
			ab.addContact(nc3);
			
			Contact deleted = new Contact();
			deleted.setFirstName("Sad");
			deleted.setLastName("Face");
			ab.addContact(deleted);
			
			ab.removeContact(deleted);
			addressBooks.add(ab);
			
			if (i == 0) {
				for (int j=0; j<1000; j++) {
					Contact c = new Contact();
					c.setFirstName("Test");
					c.setLastName("Dummy #" + (j+1));
					c.setAddressLine1("911 Emergency St.");
					c.setAddressLine2("Apt. #911");
					ab.addContact(c);
				}
			}
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
