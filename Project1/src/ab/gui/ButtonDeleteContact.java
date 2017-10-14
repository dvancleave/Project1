package ab.gui;

import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ab.Contact;

@SuppressWarnings("serial")
public class ButtonDeleteContact extends JButton {
	
	/*
	 * Customized red "x" button for deleting contacts
	 */
	
	public ButtonDeleteContact(Contact contact) {
		super();

		setToolTipText("Delete contact");
		setIcon(new ImageIcon(getClass().getResource("img/x.png")));
		setRolloverIcon(new ImageIcon(getClass().getResource("img/x_rollover.png")));
		setPressedIcon(new ImageIcon(getClass().getResource("img/x_pressed.png")));
		setMargin(new Insets(2, 2, 2, 2));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
	
}
