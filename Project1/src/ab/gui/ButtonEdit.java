package ab.gui;

import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonEdit extends JButton {
	
	/*
	 * Customized edit button
	 */
	
	public ButtonEdit(String tooltip) {
		super();

		setToolTipText(tooltip);
		setIcon(new ImageIcon(getClass().getResource("img/edit.png")));
		setRolloverIcon(new ImageIcon(getClass().getResource("img/edit_rollover.png")));
		setPressedIcon(new ImageIcon(getClass().getResource("img/edit_pressed.png")));
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
	
}
