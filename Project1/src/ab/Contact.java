package ab;

import java.util.ArrayList;;

public class Contact {
	/*
	private String firstName = "John";
	private String lastName = "Doe";
	private String addressLine = "1234 University St";
	private String city = "Eugene";
	private String state = "OR";
	private String ZIP = "97401";
	private String phoneNumber = "(541)123-4567";
	private String email = "foo@bar.com";
	//*/
	
	private int firstName = 0;
	private int lastName = 1;
	private int addressLine = 2;
	private int city = 3;
	private int state = 4;
	private int ZIP = 5;
	private int phoneNumber = 6;
	private int email = 7;
	
	private ArrayList<String> data; //Stores field values
	private ArrayList<String> newFields; //Stores field names
	
	/*
	 * Instantiates the class and adds a default identity to the contact
	 * and adds the correct fields to the data ArrayList
	 */
	Contact()
	{
		// Add the values to the various fields
		data = new ArrayList<String>();
		newFields = new ArrayList<String>();
		data.add(firstName, "John");
		data.add(lastName, "Doe");
		data.add(addressLine, "1234 University St.");
		data.add(city, "Eugene");
		data.add(state, "OR");
		data.add(ZIP, "97401");
		data.add(phoneNumber, "97401");
		data.add(email, "foo@bar.com");
	}
	
	/*
	 * Searches for the specified field. If the field is a new field, not a predefined one,
	 * and it can be found in the array list, it will be removed and the function will
	 * return true. Otherwise, the function returns false;
	 * 
	 * args:
	 * 		String field: The name of the field to delete
	 * returns:
	 * 		Boolean: whether or not the field could be deleted
	 */
	public boolean deleteField(String field)
	{
		int position = newFields.lastIndexOf(field);
		if(position == -1)
			return false;
		newFields.remove(position);
		//email is the last normal (undeletable) entry, so the variable entries start 1 after it
		data.remove(position + email + 1);
		return true;
	}
	
	public void addField(String field)
	{
		newFields.add(field);
		data.add("");
	}
	
	public void addField(String field, String value)
	{
		newFields.add(field);
		data.add(value);
	}
	
	public boolean setField(String field, String value) 
	{
		int position = newFields.lastIndexOf(field);
		if(position == -1)
			return false;
		//email is the last normal (undeletable) entry, so the variable entries start 1 after it
		data.set(position + email + 1, value);
		return true;
	}
	
	public String getField(String field)
	{
		int position = newFields.lastIndexOf(field);
		if(position == -1)
			return null;
		//email is the last normal (undeletable) entry, so the variable entries start 1 after it
		return data.get(position + email + 1);
	}
	
	/*
	 * Getter for firstName. Returns the value pointed at by firstName; 
	 */	
	public String getFirstName() {
		return data.get(firstName);
	}
	public void setFirstName(String nfirstName) {
		data.set(firstName, nfirstName);
	}
	/*
	 * Getter for lastName. Returns the value pointed at by lastName; 
	 */	
	public String getLastName() {
		return data.get(lastName);
	}
	public void setLastName(String nlastName) {
		data.set(lastName, nlastName);
	}
	/*
	 * Getter for addressLine. Returns the value pointed at by addressLine; 
	 */	
	public String getAddressLine() {
		return data.get(addressLine);
	}
	public void setAddressLine(String naddressLine) {
		data.set(addressLine, naddressLine);
	}
	/*
	 * Getter for city. Returns the value pointed at by city; 
	 */	
	public String getCity() {
		return data.get(city);
	}
	public void setCity(String ncity) {
		data.set(city, ncity);
	}
	/*
	 * Getter for state. Returns the value pointed at by state; 
	 */	
	public String getState() {
		return data.get(state);
	}
	public void setState(String nstate) {
		data.set(state, nstate);
	}
	/*
	 * Getter for ZIP. Returns the value pointed at by ZIP; 
	 */	
	public String getZIP() {
		return data.get(ZIP);
	}
	public void setZIP(String nZIP) {
		data.set(ZIP, nZIP);
	}
	/*
	 * Getter for phoneNumber. Returns the value pointed at by phoneNumber; 
	 */	
	public String getPhoneNumber() {
		return data.get(phoneNumber);
	}
	public void setPhoneNumber(String nphoneNumber) {
		data.set(phoneNumber, nphoneNumber);
	}
	/*
	 * Getter for Email. Returns the value pointed at by Email; 
	 */	
	public String getEmail() {
		return data.get(email);
	}
	public void setEmail(String nemail) {
		data.set(email, nemail);
	}
}
