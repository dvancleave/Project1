package ab;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class AddressBook {
	private String name;
	//For first and last name lookup
	private SkipList<Contact> contactsFirst;
	private SkipList<Contact> contactsLast;
	
	public AddressBook(String name)
	{
		this.name = name;
		contactsFirst = new SkipList<Contact>(SkipList.ContactFirstName);
		contactsLast = new SkipList<Contact>(SkipList.ContactLastName);
	}
	public AddressBook()
	{
		this("Address Book");
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	/*
	 * Adds the provided contact
	 */
	public void addContact(Contact contact)
	{
		contact.setContainer(this);
		contactsFirst.add(contact);
		contactsLast.add(contact);
	}
	/*
	 * Removes the selected contact
	 */
	public void removeContact(Contact contact)
	{
		contactsFirst.remove(contact);
		contactsLast.remove(contact);
	}
	/*
	 * Returns the whole list of contacts
	 */
	public ArrayList<Contact> getContacts()
	{
		ArrayList<Contact> c;
		return getContactsByQuery("");
	}
	/*
	 * Returns the complete list of contacts that match the given query
	 */
	public ArrayList<Contact> getContactsByQuery(String query)
	{
		ArrayList<Contact> ret;
		if(query.length() == 0)
			ret = contactsFirst.getElementsByQuery("");
		else
		{
			ret = contactsFirst.getElementsByQuery(query);
			ret.addAll(contactsLast.getElementsByQuery(query));
			ret = new ArrayList<Contact>(new HashSet<Contact>(ret)); //Lazily filters the duplicates
			ret.sort(SkipList.LastNameComparator);
		}
		return ret;
	}
	
	/*
	 * Saves the contacts from the first name sorted ADT
	 * to the specified file. This shouldn't be an
	 * arbitrary file, but its corresponding file from
	 * contactFiles in Main
	 */
	public void saveContacts(String fileName)
	{
		File file = new File(fileName);
		try
		{
			FileWriter writer = new FileWriter(file);
			ArrayList<Contact> contacts = contactsFirst.getElementsByQuery("");
			for(Contact c : contacts)
				writer.write(c.formatString());
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns true if the load worked, false otherwise
	 */
	public boolean loadContacts(String fileName)
	{
		File file = new File(fileName);
		try
		{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine())
			{
				String csString = scan.nextLine();
				String[] tokens = new String[9];
				int index1 = 0;
				int index2;
				int i = 0;
				while(index1 != csString.length())
				{
					index2 = csString.indexOf('\t', index1);
					tokens[i++] = csString.substring(index1, index2);
					index1 = index2 + 1;
				}
				Contact c = new Contact();
				c.setData(tokens);
				addContact(c);
			}
			scan.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
