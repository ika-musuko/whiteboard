import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.Class;

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
	    this.table = new JTable(new DefaultTableModel(null, new Object[]{"type", "x", "y", "width", "height", "color"}));
        ((DefaultTableModel)this.table.getModel()).setColumnCount(6);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(60);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(15);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(15);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(25);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(25);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(70);
	    this.table.setEnabled(false);
	    this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane sp = new JScrollPane(this.table);
	    sp.setPreferredSize(new Dimension(500, 100));

	    this.add(sp);
	    this.setVisible(true);
    }
    
    private void updateContents(List<DShape> shapeList) {
        ((DefaultTableModel)this.table.getModel()).setRowCount(shapeList.size());   
        for (int i = 0; i < shapeList.size(); ++i) {
            DShape ds = shapeList.get(i);
            this.table.getModel().setValueAt(ds.getClass().getSimpleName(), i, 0);
            this.table.getModel().setValueAt(ds.getInfo().getX(), i, 1);
            this.table.getModel().setValueAt(ds.getInfo().getY(), i, 2);
            this.table.getModel().setValueAt(ds.getInfo().getWidth(), i, 3);
            this.table.getModel().setValueAt(ds.getInfo().getHeight(), i, 4);
            Color color = ds.getInfo().getColor();
            this.table.getModel().setValueAt("R: "+color.getRed()+", G: "+color.getGreen()+", B: "+color.getBlue(), i, 5);
        }
    }
}
