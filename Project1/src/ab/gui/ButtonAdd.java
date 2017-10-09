package ab.gui;

import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAdd extends JButton {
	
	/*
	 * Customized green "+" button
	 */
	
	public ButtonAdd(String tooltip) {
		super();

		setToolTipText(tooltip);
		setIcon(new ImageIcon(getClass().getResource("img/+.png")));
		setRolloverIcon(new ImageIcon(getClass().getResource("img/+_rollover.png")));
		setPressedIcon(new ImageIcon(getClass().getResource("img/+_pressed.png")));
		setMargin(new Insets(4, 4, 4, 4));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
	
}
