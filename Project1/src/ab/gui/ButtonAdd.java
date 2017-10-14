package ab.gui;

import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class ButtonAdd extends JButton {
	
	/*
	 * Customized green "+" button
	 */

	private static final ImageIcon icon = new ImageIcon(ButtonEditContact.class.getResource("img/+.png"));
	private static final ImageIcon iconPressed = new ImageIcon(ButtonEditContact.class.getResource("img/+_pressed.png"));
	private static final ImageIcon iconRollover = new ImageIcon(ButtonEditContact.class.getResource("img/+_rollover.png"));
	
	public ButtonAdd() {
		super();

		setIcon(icon);
		setPressedIcon(iconPressed);
		setRolloverIcon(iconRollover);
		setMargin(new Insets(4, 4, 4, 4));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
	
}
