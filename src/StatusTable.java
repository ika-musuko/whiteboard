import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class StatusTable extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public StatusTable()
    {
		
		this.setLayout(new BorderLayout());
	    String[] column = {"X", "Y", "WIDTH", "HEIGHT"};
	    Object[][] data = {
	    		{new Integer(0), new Integer(0), new Integer(10), new Integer(10)}
	    	};
	    
	    JTable table = new JTable(data, column);
	    table.setEnabled(false);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(500, 100));

	    this.add(sp);
	    this.setVisible(true);
    
    }
}
