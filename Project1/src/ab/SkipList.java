package ab;

import java.util.ArrayList;
import java.util.Comparator;

public class SkipList<E> {
	// The generic implementation of ArrayList<E>[][];
	ArrayList<ArrayList<ArrayList<E> > > data;
	//*
	public static Comparator<Contact> FirstNameComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact arg0, Contact arg1) {
			return arg0.getFirstName().toLowerCase().compareTo(arg1.getFirstName().toLowerCase());
		}
	};
	public static Comparator<Contact> LastNameComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact arg0, Contact arg1) {
			return arg0.getLastName().toLowerCase().compareTo(arg1.getLastName().toLowerCase());
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
			return arg0.getFirstName().toLowerCase().concat(arg0.getLastName().toLowerCase());
		}
	};
	public static ToStringLambda<Contact> ContactLastName = new ToStringLambda<Contact>()
	{
		@Override
		public String toString(Contact arg0)
		{
			return arg0.getLastName().toLowerCase().concat(arg0.getFirstName().toLowerCase());
		}
	};
	public static ToStringLambda<AddressBook> AddressBookName = new ToStringLambda<AddressBook>()
	{
		@Override
		public String toString(AddressBook arg0)
		{
			return arg0.getName().toLowerCase();
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
		String str = lamb.toString(newContact).toLowerCase();
		char c1 = str.charAt(0);
		char c2 = str.charAt(1);
		data.get(c1 - 'a').get(c2 - 'a').add(newContact);
	}
	
	@SuppressWarnings("unused")
	public SkipList(ToStringLambda<E> lambda)
	{
		lamb = lambda;
		data = new ArrayList<ArrayList<ArrayList<E> > >();
		for(int i = 0; i < 26; ++i)
		{
			ArrayList<ArrayList<E> > foo = new ArrayList<ArrayList<E> >();
			for(int j = 0; j < 26; ++j)
				foo.add(new ArrayList<E>());
			data.add(foo);
		}
		int i = 12;
	}
	
	public void setComparator(Comparator<E> comparator)
	{
		comp = comparator;
	}	
	public void setToStringLambda(ToStringLambda<E> lambda)
	{
		lamb = lambda;
	}
	public ArrayList<E> getElementsByQuery(String query)
	{
		query = query.toLowerCase();
		ArrayList<E> ret = new ArrayList<E>();
		int index1, index2;
		switch(query.length())
		{
		case 0:
			for(int i = 0; i < 26; ++i)
				for(int j = 0; j < 26; ++j)
					ret.addAll(data.get(i).get(j));
			break;
		case 1:
			index1 = query.charAt(0) - 'a';
			for(int j = 0; j < 26; ++j)
				ret.addAll(data.get(index1).get(j));
			break;
		case 2:
			index1 = query.charAt(0) - 'a';
			index2 = query.charAt(1) - 'a';
			ret.addAll(data.get(index1).get(index2));
			break;
		default:
			index1 = query.charAt(0) - 'a';
			index2 = query.charAt(1) - 'a';
			ArrayList<E> ref = data.get(index1).get(index2);
			for(E e : ref)
			{
				if(lamb.toString(e).compareTo(query) > 0)
					break;
				ret.add(e);
			}
		}
		ret.sort(comp);
		return ret;
	}
	public void add(E e)
	{
		String eString = lamb.toString(e);
		int index1 = 0, index2 = 0, index3 = 0;
		if(eString.length() >= 1)
		{
			index1 = eString.charAt(0) - 'a';
			if(eString.length() >= 2)
				index2 = eString.charAt(1) - 'a';
		}
		ArrayList<E> ref = data.get(index1).get(index2);
		for(; index3 < ref.size(); ++index3)
			if(comp.compare(e, ref.get(index3)) < 0)
				break;
		ref.add(index3, e);
	}
	public void remove(E e)
	{
		String eString = lamb.toString(e);
		int index1 = 0, index2 = 0, index3 = 0;
		if(eString.length() >= 1)
		{
			index1 = eString.charAt(0) - 'a';
			if(eString.length() >= 2)
				index2 = eString.charAt(1) - 'a';
		}
		ArrayList<E> ref = data.get(index1).get(index2);
		ref.remove(e);
	}
	public void sort()
	{
		for(ArrayList<ArrayList<E> > array : data)
			for(ArrayList<E> a : array)
				a.sort(comp);
	}

}
