import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class StatusTable extends JPanel implements CanvasListener {
    
    private Whiteboard whiteboard;
    private DefaultTableModel shapeTable;
    private JTable table;
    
    // CanvasListener implement
    @Override
    public void canvasChanged(List<DShape> shapeList){
        this.updateContents(shapeList);
    }
    
    public StatusTable(Whiteboard whiteboard){
		this.setLayout(new BorderLayout());
	    this.table = new JTable(new DefaultTableModel(null, new Object[]{"x", "y", "width", "height"}));
        ((DefaultTableModel)this.table.getModel()).setColumnCount(4);
	    this.table.setEnabled(false);
	    this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane sp = new JScrollPane(this.table);
	    sp.setPreferredSize(new Dimension(500, 100));

	    this.add(sp);
	    this.setVisible(true);
    }
    
    private void updateContents(List<DShape> shapeList) {
        System.out.println("UPDATING TABLE");
        ((DefaultTableModel)this.table.getModel()).setRowCount(shapeList.size());   
        for (int i = 0; i < shapeList.size(); ++i) {
            DShape ds = shapeList.get(i);
            this.table.getModel().setValueAt(ds.getInfo().getX(), i, 0);
            this.table.getModel().setValueAt(ds.getInfo().getY(), i, 1);
            this.table.getModel().setValueAt(ds.getInfo().getWidth(), i, 2);
            this.table.getModel().setValueAt(ds.getInfo().getHeight(), i, 3);
        }
    }
}
