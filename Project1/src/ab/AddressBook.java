package ab;

import java.util.ArrayList;
import java.util.HashSet;

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
	public void addContact(Contact contact)
	{
		contact.setContainer(this);
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
