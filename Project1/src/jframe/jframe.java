
package jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class jframe {
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Address Book");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(500, 200));
		
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		
		frame.pack();

		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	
}
