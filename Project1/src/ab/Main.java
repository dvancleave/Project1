
package ab;

import ab.gui.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});
	}
	
}
