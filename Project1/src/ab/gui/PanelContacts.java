package ab.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ab.Contact;

@SuppressWarnings("serial")
public class PanelContacts extends JPanel {
	
	private static final Color COLOR_ROW_0 = Color.WHITE; // Color for even rows
	private static final Color COLOR_ROW_1 = new Color(210, 210, 220); // Color for odd rows
	private Font font = new Font("Verdana", Font.PLAIN, 16); // Font for contact name
	private int rows = 0; // Number of contacts being shown
	
	public PanelContacts() {
		super(new GridBagLayout());
	}
	
	public int getRows() {
		return rows;
	}
	
	// JPanel for each row of the contact list
	public class PanelContactRow extends JPanel {
		
		private Contact contact;
		private JLabel label;
		
		public PanelContactRow(Contact contact) {
			this.contact = contact;
			
			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
			setBackground((rows % 2 == 0) ? COLOR_ROW_0 : COLOR_ROW_1); // Alternate the BG color for each row
		}
		public void setLabel(JLabel label) {
			this.label = label;
			setLabelText();
		}
		public void refresh() {
			setLabelText();
			revalidate();
			repaint();
		}
		private void setLabelText() {
			label.setText(" " + contact.getFirstName() + " " + contact.getLastName());
		}
		
	}
	
	public void setContacts(List<Contact> contacts) {
		// Reset everything
		removeAll();
		rows = 0;
		
		// Add filler panel to align other elements to top
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		add(new JPanel(), c);
		
		// Draw the components for each contacts
		for (Contact con : contacts) {
			addContact(con);
		}
		
		// Refresh panel
		revalidate();
		repaint();
	}
	
	public void addContact(Contact contact) {
		/*
		 * Create panel for this row of the contact list
		 */
		PanelContactRow panelRow = new PanelContactRow(contact);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		add(panelRow, c, rows++);
		
		
		/*
		 * Create & display edit & delete buttons
		 */
		JButton buttonDelete = new ButtonDeleteContact(contact);
		panelRow.add(buttonDelete);
		
		JButton buttonEdit = new ButtonEditContact(panelRow, contact);
		panelRow.add(buttonEdit);
		
		
		/*
		 * Display contact name
		 */
		JLabel label = new JLabel();
		label.setFont(font);
		
		panelRow.add(label);
		panelRow.setLabel(label);
	}
	
}
