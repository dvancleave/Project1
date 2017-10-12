package ab;

import java.util.ArrayList;
import java.util.Iterator;

public class AddressBook implements Iterator{
	private ArrayList<Contact> contacts;
	private String name = "Address Book";
	private int iteratorIndex = 0;
	
	public AddressBook()
	{
		contacts = new ArrayList<Contact>();
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public ContactCard getNextContactName()
	{
		if(iteratorIndex == contacts.size())
			return null;
		Contact selectedContact = contacts.get(iteratorIndex);
		if(selectedContact != null)
			return new ContactCard(selectedContact.getLastName() + ", " + selectedContact.getFirstName(), iteratorIndex);
		return null;
	}
	public ContactCard getFirstContactName()
	{
		if(iteratorIndex == contacts.size())
			return null;
		Contact selectedContact = contacts.get(iteratorIndex++);
		if(selectedContact != null)
			return new ContactCard(selectedContact.getLastName() + ", " + selectedContact.getFirstName(), iteratorIndex);
		return null;
	}
	public Contact getContactByName(String contactName)
	{
		String[] names = contactName.split(", ");
		return null;
	}
	public Contact getContactByIndex(int index)
	{
		if(index < contacts.size())
		{
			Contact contact = contacts.get(index);
			if(contact != null)
				return contact;
		}
		return null;
	}
	public void addContact(Contact contact)
	{
		contacts.add(contact);
		contacts.sort(SkipList.LastNameComparator);
		iteratorIndex = 0;
	}
	public ArrayList<Contact> getContacts()
	{
		return contacts;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return iteratorIndex == contacts.size() && contacts.get(iteratorIndex) != null;
	}

	@Override
	public ContactCard next() {
		// TODO Auto-generated method stub
		return getNextContactName();
	}
}
