package ab.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ab.Contact;

@SuppressWarnings("serial")
public class PanelSearch extends JPanel {
	
	private JTextField fieldSearch;
	
	public PanelSearch(MainWindow window) {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 2;
		c.gridy = 0;
		
		JLabel label = new JLabel("Search contacts:");
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(4, 4, 4, 0);
		c.gridx = 0;
		c.weightx = 0;
		add(label, c);
		
		fieldSearch = new JTextField();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(4, 4, 4, 4);
		c.gridx = 1;
		c.weightx = 1;
		add(fieldSearch, c);
		
		fieldSearch.getDocument().addDocumentListener(new DocumentListener() {
			private void update() {
				String query = fieldSearch.getText();
				
				if (query.isEmpty()) {
					// If query blank, show all contacts
					window.panelContactList.showAllRows();
				} else {
					// If not, filter by query
					List<Contact> results = window.getCurrentAB().getContactsByQuery(query);
					window.panelContactList.filter(results); // Only show search results (hide other contacts)
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}
		});
	}
	
}
