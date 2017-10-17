package ab.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

@SuppressWarnings({ "rawtypes", "serial" })
public class AddressBookList<E> extends JList<E> {
	
	public AddressBookList() {
        super();
        
        // Set up properties specific to this JList
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setCellRenderer(new AddressBookListRenderer());
		setVisibleRowCount(4);
        
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
	public AddressBookList(Vector<E> listData) {
		this();
		setListData(listData);
	}
	public AddressBookList(DefaultListModel<E> model)
	{
		this();
		setModel(model);
	}

	// Expose the getToolTipText event of our JList
	public String getToolTipText(MouseEvent e) {
		return super.getToolTipText();
	}

}
