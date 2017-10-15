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
			String s1 = arg0.getFirstName().toLowerCase();
			String s2 = arg1.getFirstName().toLowerCase();
			return s1.compareTo(s2);
		}
	};
	public static Comparator<Contact> LastNameComparator = new Comparator<Contact>()
	{
		@Override
		public int compare(Contact arg0, Contact arg1) {
			String s1 = arg0.getFirstName().toLowerCase();
			String s2 = arg1.getFirstName().toLowerCase();
			return s1.compareTo(s2);
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
			return arg0.getFirstName().toLowerCase() + " " + arg0.getLastName().toLowerCase();
		}
	};
	public static ToStringLambda<Contact> ContactLastName = new ToStringLambda<Contact>()
	{
		@Override
		public String toString(Contact arg0)
		{
			return arg0.getLastName().toLowerCase() + " " + arg0.getFirstName().toLowerCase();
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
	
	/*
	 * Returns true if e matches all of s, even if e's string has a greater length than s
	 */
	private int matchES(E e, String s)
	{
		String eS = lamb.toString(e);
		if(eS.length() == s.length())
			return eS.compareTo(s);
		if(eS.length() < s.length())
			return -1;
		return eS.substring(0, s.length()).compareTo(s);
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
				int result = matchES(e, query);
				if(result < 0) // Toss this out, but the next one can still match
					continue;
				else if(result > 0) // None of the rest can match since they're after the query
					break;
				ret.add(e);
			}
			/*
			index1 = query.charAt(0) - 'a';
			index2 = query.charAt(1) - 'a';
			ArrayList<E> ref = data.get(index1).get(index2);
			for(E e : ref)
			{
				String eString = lamb.toString(e);
				eString = eString.substring(0, query.length());
				if(eString.compareTo(query) != 0)
					break;
				ret.add(e);
			}
			//*/
		}
		ret.sort(comp);
		return ret;
	}
	public void add(E e)
	{
		// We want the first sequence of strings before any whitespace for use in finding the index.
		// The rest of the string is normally used for comparison, but it will be tossed;
		String eString = lamb.toString(e);
		if(eString.charAt(0) == ' ') //First radix (first name, last name etc) is empty
			eString = "";
		else
			eString = eString.split(" ")[0];
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
		// We want the first sequence of strings before any whitespace for use in finding the index.
		// The rest of the string is normally used for comparison, but it will be tossed;
		String eString = lamb.toString(e);
		if(eString.charAt(0) == ' ') //First radix (first name, last name etc) is empty
			eString = "";
		else
			eString = eString.split(" ")[0];
		int index1 = 0, index2 = 0;
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
