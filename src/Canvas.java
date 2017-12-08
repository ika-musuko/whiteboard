import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.List;
import java.util.*;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final DShape NOSELECTION = new DRectangle(new Info(-4, -4, 0, 0));		//dummy shape to point to when there is no selection
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
        System.out.println("printing: " + ds);
        this.repaint();
    }

    public void resetArray()
    {
    	shapeList = new ArrayList<DShape>();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    	System.out.println("a");
        Graphics2D g2 = (Graphics2D) g.create();
    	System.out.println(shapeList.size());
        for (DShape ds : this.shapeList) {
        	System.out.println(shapeList.size());
        	System.out.println(ds);
            ds.draw(g2);
            if(ds == this.selected)
                ds.drawKnobs(g2);
        }
        g2.dispose();
    }
    
    public DShape getSelected(){
    	return selected;
    }
    
    private class ClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
            selected = Canvas.NOSELECTION;
            System.out.println("mouse: "+e.getX()+" "+e.getY());
            for(DShape ds : shapeList){
                //System.out.println(ds+": bounds rect"+ds.getInfo().getBounds()+" CONTAINS? "+ds.contains(e.getX(), e.getY()));
                if(ds.contains(e.getX(), e.getY())){
                    selected = ds;
                    break;
                }
            }
            System.out.println("SELECTED SHAPE: "+selected);
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

