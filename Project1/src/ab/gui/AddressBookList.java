package ab.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JList;
import javax.swing.ListModel;

@SuppressWarnings("rawtypes")
public class AddressBookList<E> extends JList<E> {
	
	private static final long serialVersionUID = 7820003430416292753L;
	
	public AddressBookList() {
        super();
        
        // Attach a mouse motion adapter to let us know the mouse is over an item and to show the tip.
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            	AddressBookList theList = (AddressBookList) e.getSource();
                ListModel model = theList.getModel();
                int index = theList.locationToIndex(e.getPoint());
                if (index > -1) {
                    theList.setToolTipText(null);
                    String text = (String) model.getElementAt(index);
                    theList.setToolTipText(text);
                }
            }
        });
    }
	public AddressBookList(E[] listData) {
		this();
		setListData(listData);
	}

	// Expose the getToolTipText event of our JList
	public String getToolTipText(MouseEvent e) {
		return super.getToolTipText();
	}

}
