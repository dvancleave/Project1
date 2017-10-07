package ab;

import java.util.ArrayList;

public class AddressBook {
	private ArrayList<Contact> contacts;
	private String name = "Address Book";
	private int iteratorIndex = 0;
	
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public String getNextContactName()
	{
		Contact selectedContact = contacts.get(iteratorIndex++);
		if(iteratorIndex == contacts.size())
			iteratorIndex = 0;
		if(selectedContact != null)
			return selectedContact.getLastName() + ", " + selectedContact.getFirstName();
		return null;
	}
	public String getFirstContactName()
	{
		Contact selectedContact = contacts.get(iteratorIndex++);
		if(iteratorIndex == contacts.size())
			iteratorIndex = 0;
		if(selectedContact != null)
			return selectedContact.getLastName() + ", " + selectedContact.getFirstName();
		return null;
	}
	public Contact getContactByName(String contactName)
	{
		String[] names = contactName.split(", ");
		return null;
	}
}
