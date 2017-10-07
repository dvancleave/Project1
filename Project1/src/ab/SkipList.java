package ab;

import java.util.ArrayList;
import java.util.Comparator;

public class SkipList<E> {
	ArrayList<E> data;
	/*
	public static Comparator<Contact> FirstNameComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact arg0, Contact arg1) {
			return arg0.getFirstName().compareTo(arg1.getFirstName());
		}
	};
	public static Comparator<Contact> LastNameComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact arg0, Contact arg1) {
			return arg0.getLastName().compareTo(arg1.getLastName());
		}
	};
	public static Comparator<AddressBook> AddressBookNameComparator = new Comparator<AddressBook>()
	{
		@Override
		public int compare(AddressBook arg0, AddressBook arg1)
		{
			return arg0.getName().compareTo(arg1.getName());
		}
	};//*/
	public static ToStringLambda<Contact> ContactFirstName = new ToStringLambda<Contact>()
	{
		@Override
		public String toString(Contact arg0)
		{
			return arg0.getFirstName();
		}
	};
	public static ToStringLambda<Contact> ContactLastName = new ToStringLambda<Contact>()
	{
		@Override
		public String toString(Contact arg0)
		{
			return arg0.getLastName();
		}
	};
	public static ToStringLambda<AddressBook> AddressBookName = new ToStringLambda<AddressBook>()
	{
		@Override
		public String toString(AddressBook arg0)
		{
			return arg0.getName();
		}
	};
	private ToStringLambda<E> lamb;
	private Comparator<E> comp = new Comparator<E>()
	{
		@Override
		public int compare(E arg0, E arg1)
		{
			String s0 = lamb.toString(arg0);
			String s1 = lamb.toString(arg1);
			return s0.compareTo(s1);
		}
	};
	
	public void addContact(E newContact)
	{
		data.add(newContact);
		data.sort(comp);
	}
	
	public SkipList(Comparator comparator)
	{
		comp = comparator;
	}
	
	public SkipList(ToStringLambda lambda)
	{
		lamb = lambda;
	}
	
	public void setComparator(Comparator<E> comparator)
	{
		comp = comparator;
	}	
	public void setToStringLambda(ToStringLambda<E> lambda)
	{
		lamb = lambda;
	}
}
