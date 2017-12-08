import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.List;
import java.util.*;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final DShape NOSELECTION = new DRectangle();		//dummy shape to point to when there is no selection
    private List<DShape> shapeList;
    private DShape selected;

	public Canvas(){
        this(new ArrayList<>());
        ClickListener cl = new ClickListener();
        this.addMouseListener(cl);
	}

    public Canvas(List<DShape> shapeList){
        this.shapeList = shapeList;
        this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
        this.repaint();
        this.selected = Canvas.NOSELECTION;
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
            ds.draw(g2);
        }
        g2.dispose();
    }
    
    public DShape getSelected(){
    	return selected;
    }
    
    private class ClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
            for(DShape ds : this.shapeList){
                if(ds.contains(e.getX(), e.getY())){
			        this.selected.deselect();
                    this.selected = ds;
                    ds.select();
                    break;
                }
            }
            this.selected.deselect = Canvas.NOSELECTION;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//System.out.println("Mouse pressed!");
			//System.out.println(e.getSource());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//System.out.println("Mouse released!");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//System.out.println("Mouse entered!");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//System.out.println("Mouse exited!");
		}
    }	
}

