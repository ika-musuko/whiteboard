import javax.swing.*;
import java.awt.*;

public class StatusTable extends JPanel{
    private Whiteboard whiteboard;
    public StatusTable(Whiteboard whiteboard){
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