
package ab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ab.gui.MainWindow;

public class Main {
	
	public static List<AddressBook> addressBooks = new ArrayList<AddressBook>();
	public static int lastLoadedAddressBook = -1;
	public static List<String> contactFiles = new ArrayList<String>();
	
	public static void main(String[] args) {
		//Load up the manifest file and then load the AddressBooks
		try
		{
			File manFile = new File("manifest");
			if(!manFile.exists())
				manFile.createNewFile();
			Scanner scan = new Scanner(manFile);
			if(manFile.length() != 0)
			{
				int nAB = scan.nextInt();
				scan.nextLine(); // Tosses the \n after the int
				for(int i = 0; i < nAB; ++i)
				{
					String[] line = scan.nextLine().split("\t");
					AddressBook ab = new AddressBook();
					ab.setName(line[0]);
					addressBooks.add(ab);
					contactFiles.add(line[1]);
					//Load up the contacts for the AddressBook
					ab.loadContacts(line[1]);
				}
				lastLoadedAddressBook = scan.nextInt();
			}
			scan.close();
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		// Create placeholder list of AddressBooks if it's empty
		if(addressBooks.size() == 0)
		{
			for (int i=0; i<10; i++) {
				AddressBook ab = new AddressBook();
				ab.setName("Test address book #" + (i+1));
				contactFiles.add(createCF(ab));
				for (int j=0; j<i*2; j++)ab.addContact(new Contact().placeholders());
				
				Contact nc1 = new Contact().placeholders();
				nc1.setFirstName("Conan");
				ab.addContact(nc1);
				
				Contact nc2 = new Contact().placeholders();
				nc2.setFirstName("Eriela");
				nc2.setLastName("Cohen");
				ab.addContact(nc2);
				
				Contact nc3 = new Contact().placeholders();
				nc3.setFirstName("Charlie");
				nc3.setLastName("Ferguson");
				ab.addContact(nc3);
				
				Contact deleted = new Contact().placeholders();
				deleted.setFirstName("Sad");
				deleted.setLastName("Face");
				ab.addContact(deleted);
				
				ab.removeContact(deleted);
				addressBooks.add(ab);
				
				if (i == 0) {
					for (int j=0; j<1000; j++) {
						Contact c = new Contact().placeholders();
						c.setFirstName("Test");
						c.setLastName("Dummy #" + (j+1));
						c.setAddressLine1("911 Emergency St.");
						c.setAddressLine2("Apt. #911");
						ab.addContact(c);
					}
				}
			}
			saveManifest();
			saveContacts();
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
	
	/*
	 * Saves the manifest according to the format specified in the sys arch docs
	 */
	public static void saveManifest()
	{
		StringBuilder sb = new StringBuilder(1<<10);
		int size = addressBooks.size();
		sb.append(size);
		sb.append("\n");
		for(int i = 0; i < size; ++i)
		{
			sb.append(addressBooks.get(i).getName() + "\t" + contactFiles.get(i)+"\n");
		}
		sb.append(lastLoadedAddressBook);
		File out = new File("Manifest");
		try
		{
			if(!out.exists())
				out.createNewFile();
			FileWriter writer = new FileWriter(out);
			writer.write(sb.toString());
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Creates a contact file for the 'ab' and adds it to the contactFiles list
	public static String createCF(AddressBook ab)
	{
		String name = ab.getName();
		File cf = new File(name);
		for(int i = 0; cf.exists(); ++i)
			cf = new File(name +  "_" + i);
		try
		{
			cf.createNewFile();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return cf.getName();
	}
	
	public static void saveContacts()
	{
		for(int i = 0; i < addressBooks.size(); ++i)
			addressBooks.get(i).saveContacts(contactFiles.get(i));
	}
}
