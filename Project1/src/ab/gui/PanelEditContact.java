package ab.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import ab.Contact;
import ab.AddressBook;

@SuppressWarnings("serial")
public class PanelEditContact extends JPanel {
	
	// Contact object (used for loading/saving field values)
	private Contact contact;
	
	// String arguments used for JLabel text later on
	private JTextField fieldFirstName = new JTextField("First name:");
	private JTextField fieldLastName = new JTextField("Last name:");
	private JTextField fieldAddress1 = new JTextField("Address (Line 1):");
	private JTextField fieldAddress2 = new JTextField("Address (Line 2):");
	private JTextField fieldCity = new JTextField("City:");
	private JTextField fieldState = new JTextField("State:");
	private JTextField fieldZIP = new JTextField("ZIP code:");
	private JTextField fieldPhone = new JTextField("Phone number:");
	private JTextField fieldEmail = new JTextField("Email:");
	
	// List of all above fields
	private JTextField[] fields = new JTextField[] {
			fieldFirstName, fieldLastName, fieldAddress1, fieldAddress2,
			fieldCity, fieldState, fieldZIP, fieldPhone, fieldEmail
	};
	
	public PanelEditContact(Contact contact) {
		this.contact = contact;
		
		// Set up layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		c.ipadx = 2;
		c.ipady = 2;
		c.gridy = 0;
		
		// Add each field to the dialog box
		for (JTextField f : fields) {
			// Add label
			c.gridx = 0;
			c.anchor = GridBagConstraints.EAST;
			add(new JLabel(f.getText()), c);
			
			// Set size of text field
			f.setColumns(32);
			f.setMargin(new Insets(2, 4, 2, 4));
			
			// Add text field
			c.gridx = 1;
			c.anchor = GridBagConstraints.WEST;
			add(f, c);
			
			// Move to nextrow
			c.gridy++;
		}
		
		// Load values from Contact object into text fields
		fieldFirstName.setText(contact.getFirstName());
		fieldLastName.setText(contact.getLastName());
		fieldAddress1.setText(contact.getAddressLine1());
		fieldAddress2.setText(contact.getAddressLine2());
		fieldCity.setText(contact.getCity());
		fieldState.setText(contact.getState());
		fieldZIP.setText(contact.getZIP());
		fieldPhone.setText(contact.getPhoneNumber());
		fieldEmail.setText(contact.getEmail());	
	}
	
	// Saves the updated contact info into Contact object
	public void saveContact() {
		AddressBook container = contact.getContainer();
		if(container != null)
			container.removeContact(contact);
		contact.setFirstName(fieldFirstName.getText());
		contact.setLastName(fieldLastName.getText());
		contact.setAddressLine1(fieldAddress1.getText());
		contact.setAddressLine2(fieldAddress2.getText());
		contact.setCity(fieldCity.getText());
		contact.setState(fieldState.getText());
		contact.setZIP(fieldZIP.getText());
		contact.setPhoneNumber(fieldPhone.getText());
		contact.setEmail(fieldEmail.getText());	
		if(container != null) {
			container.addContact(contact);
		}
	}
	
	
	public boolean checkValid() {
		//Checks all fields for correct formatting
		
		if (fieldFirstName.getText().trim().length() == 0 & fieldLastName.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Contact must have a valid First or Last Name", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if(fieldFirstName.getText().matches("^.*[^a-zA-Z ].*$") | fieldFirstName.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid First Name", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if(fieldLastName.getText().matches("^.*[^a-zA-Z ].*$") | fieldLastName.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid Last Name", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if(fieldAddress1.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid Address (Line 1)", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if(fieldAddress2.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid Address (Line 2)", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if(fieldCity.getText().matches("^.*[^a-zA-Z ].*$") | fieldCity.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid City", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if(fieldState.getText().matches("^.*[^a-zA-Z ].*$") | fieldState.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid State", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if (fieldZIP.getText().length() != 0)
		{
			String new_str = fieldZIP.getText();
			new_str = new StringBuilder(new_str).toString().replaceAll("[^0-9]","");
			fieldZIP.setText(new_str);
			
			if(new_str.length() != 5 & new_str.length() != 9)
			{
				JOptionPane.showMessageDialog(null, "Please Enter a valid Zip Code", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(fieldZIP.getText().length() == 9) {
				String new_strs = fieldZIP.getText();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-4, "-").toString();
				this.fieldZIP.setText(new_strs);
			}
		}	
		
		
		if(fieldPhone.getText().length() != 0)
		{
			String new_str = fieldPhone.getText();
			new_str = new StringBuilder(new_str).toString().replaceAll("[^0-9]","");
			fieldPhone.setText(new_str);
			
			if(new_str.length() != 7 & new_str.length() != 10 & new_str.length() != 11)
			{
				JOptionPane.showMessageDialog(null, "Please Enter a valid Phone Number", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(fieldPhone.getText().length() == 7)
			{
				String new_strs = fieldPhone.getText();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-4, "-").toString();
				fieldPhone.setText(new_strs);
			}
			if(fieldPhone.getText().length() == 10)
			{
				String new_strs = fieldPhone.getText();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-10, "(").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-7, ")").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-7, " ").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-4, "-").toString();
				fieldPhone.setText(new_strs);
			}
			if(fieldPhone.getText().length() == 11)
			{
				String new_strs = fieldPhone.getText();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-11, "+").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-10, " ").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-10, "(").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-7, ")").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-7, " ").toString();
				new_strs = new StringBuilder(new_strs).insert(new_strs.length()-4, "-").toString();
				fieldPhone.setText(new_strs);
			}
		}
		
		
		if(fieldEmail.getText().length() > 64)
		{
			JOptionPane.showMessageDialog(null, "Please Enter a valid Email Address", "Error",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
	
		return true;

	}
	
}
