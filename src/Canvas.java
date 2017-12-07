import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.List;
import java.util.*;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

    private List<DShape> shapeList;

	public Canvas(){
        this(new ArrayList<>());
	}

    public Canvas(List<DShape> shapeList){
        this.shapeList = shapeList;
        this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
        this.repaint();
    }

    public void addShape(DShape ds) {
        this.shapeList.add(ds);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        for (DShape ds : this.shapeList) {
            g2.fill(ds.getShape());
        }
        g2.dispose();
    }
}
