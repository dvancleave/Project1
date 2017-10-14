package ab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AddressBook {
	private String name = "Address Book";
	//For first and last name lookup
	private SkipList<Contact> contactsFirst;
	private SkipList<Contact> contactsLast;
	//If we repeat the query, we shouldn't need to redo the computation (which is expensive)
	private String previousQuery = null;
	private ArrayList<Contact> prevList;
	
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
		if(previousQuery != null && query.compareTo(previousQuery) == 0)
			return prevList;
		previousQuery = query;
		if(query.length() == 0)
			prevList = contactsFirst.getElementsByQuery("");
		else
		{
			prevList = contactsFirst.getElementsByQuery(query);
			prevList.addAll(contactsLast.getElementsByQuery(query));
			prevList = new ArrayList<Contact>(new HashSet<Contact>(prevList)); //Lazily filters the duplicates
			prevList.sort(SkipList.LastNameComparator);
		}
		return prevList;
	}
}
