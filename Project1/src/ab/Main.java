
package ab;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ab.gui.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		// Change look & feel to that of the native OS
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// Open GUI
		new MainWindow();
	}
	
}
