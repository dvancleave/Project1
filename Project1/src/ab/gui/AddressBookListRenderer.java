package ab.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

public class AddressBookListRenderer extends DefaultListCellRenderer {
	
	private static final long serialVersionUID = -1179844853200052110L;
	Font font = new Font("Verdana", Font.PLAIN, 16);
    ImageIcon icon;
    
    public AddressBookListRenderer() {
    	try {
			icon = new ImageIcon(getClass().getResource("img/address_book.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Override
    public String getToolTipText() {
    	return "hi";
    }
    
	@Override
    @SuppressWarnings("rawtypes")
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setIcon(icon);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
    
}
