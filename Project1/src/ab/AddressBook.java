package ab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AddressBook {
	private String name = "Address Book";
	//For first and last name lookup
	private SkipList<Contact> contactsFirst;
	private SkipList<Contact> contactsLast;
	
	public AddressBook()
	{
		contactsFirst = new SkipList<Contact>(SkipList.ContactFirstName);
		contactsLast = new SkipList<Contact>(SkipList.ContactLastName);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public void addContact(Contact contact)
	{
		contactsFirst.add(contact);
		contactsLast.add(contact);
	}
	public void removeContact(Contact contact)
	{
		contactsFirst.remove(contact);
		contactsLast.remove(contact);
	}
	public ArrayList<Contact> getContacts()
	{
		ArrayList<Contact> c;
		return getContactsByQuery("");
	}
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
}
