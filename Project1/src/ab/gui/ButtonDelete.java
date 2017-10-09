package ab.gui;

import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonDelete extends JButton {
	
	/*
	 * Customized red "x" button
	 */
	
	public ButtonDelete(String tooltip) {
		super();

		setToolTipText(tooltip);
		setIcon(new ImageIcon(getClass().getResource("img/x.png")));
		setRolloverIcon(new ImageIcon(getClass().getResource("img/x_rollover.png")));
		setPressedIcon(new ImageIcon(getClass().getResource("img/x_pressed.png")));
		setMargin(new Insets(2, 2, 2, 2));
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
	
}
