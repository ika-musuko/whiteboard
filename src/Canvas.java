import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.List;
import java.util.*;

public class Canvas extends JPanel implements InfoListener {

	private static final long serialVersionUID = 1L;
	private static final DShape NOSELECTION = new DRectangle(new Info(-4, -4, 0, 0));		//dummy shape to point to when there is no selection
    private List<DShape> shapeList;
    private List<InfoListener> selectedListeners;
    private DShape selected;

    // InfoListener implement
    public void infoChanged(Info info){
        this.refresh();
    }

	public Canvas(){
        this(new ArrayList<>());
        ClickListener cl = new ClickListener();
        this.addMouseListener(cl);
	}

    public Canvas(List<DShape> shapeList){
        this.shapeList = shapeList;
        this.selectedListeners = new ArrayList<>();
        this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
        this.refresh();
        this.selected = Canvas.NOSELECTION;
    }

    public void addShape(DShape ds) {
        ds.getInfo().addListener(new InfoListener() {
            public void infoChanged(Info info){
                refresh();
            }
        });

        this.shapeList.add(ds);
        this.selected = ds;
        this.refresh();
    }

    public void refresh() {
        revalidate();
        repaint();
    }

    public void resetArray()
    {
    	shapeList = new ArrayList<DShape>();
        this.refresh();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        for (DShape ds : this.shapeList) {
            ds.draw(g2);
            if(ds == this.selected)
                ds.drawKnobs(g2);
        }
        g2.dispose();
    }
    
    public DShape getSelected(){
    	return selected;
    }

    public void updateText(String text) {
        if(this.selected instanceof DText){
            ((TextInfo)this.selected.getInfo()).setText(text);
            System.out.println(((TextInfo)this.selected.getInfo()).getText());
        }
        this.refresh();
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
            refresh();
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

