import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class StatusTable extends JPanel implements InfoListener {
    private Whiteboard whiteboard;
    private List<DShape> shapeList;
    JTable table;
    
    // InfoListener implement
    @Override
    public void infoChanged(Info info){

    }
    
    public StatusTable(Whiteboard whiteboard){
		this.setLayout(new BorderLayout());
	    String[] column = {"X", "Y", "WIDTH", "HEIGHT"};
	    Object[][] data = {
	    		{new Integer(0), new Integer(0), new Integer(10), new Integer(10)}
	    };
	    
	    table = new JTable(data, column);
	    table.setEnabled(false);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(500, 100));

	    this.add(sp);
	    this.setVisible(true);
    }
    
    private void updateContents() {
        
    }
}
