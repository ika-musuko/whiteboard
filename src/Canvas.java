import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.util.List;
import java.util.*;

public class Canvas extends JPanel implements InfoListener {

	private static final long serialVersionUID = 1L;
	private static final DShape NOSELECTION = new DRectangle(new Info(-4, -4, 0, 0));		//dummy shape to point to when there is no selection
    private List<DShape> shapeList;
    private List<InfoListener> selectedListeners;
    private Whiteboard whiteboard;
    private DShape selected;

    // InfoListener implement
    public void infoChanged(Info info){
        this.refresh();
    }

	public Canvas(Whiteboard whiteboard){
        this(whiteboard, new ArrayList<>());
	}

    public Canvas(Whiteboard whiteboard, List<DShape> shapeList){
        // init the fields
        this.whiteboard = whiteboard;
        this.shapeList = shapeList;
        this.selectedListeners = new ArrayList<>();
        this.selected = Canvas.NOSELECTION; 
        
        // init the main canvas mouse click listener
        ClickListener cl = new ClickListener();
        this.addMouseListener(cl);
        
        // display the canvas
        this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
        this.refresh();
    }

    public void verifyText() {
       if(this.selected.getInfo() instanceof TextInfo)
            this.whiteboard.getControlPanel().enableText( ((TextInfo)this.selected.getInfo()).getText() );
       else
            this.whiteboard.getControlPanel().disableText();
    }

    public void addShape(DShape ds) {
        ds.getInfo().addListener(new InfoListener() {
            public void infoChanged(Info info){
                refresh();
            }
        });
        
        this.shapeList.add(ds);
        this.select(ds);
        this.refresh();
    }

    private void select(DShape ds) {
        this.selected = ds;
        this.verifyText();
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
    
    public List<DShape> getShapeList()
    {
    	return shapeList;
    }
    
    public DShape getSelected(){
    	return selected;
    }
 
    public void saveToPNG()
    {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	int returnVal = chooser.showSaveDialog(null);
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{	
	    	BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	    	Graphics2D g2 = image.createGraphics();
	    	printAll(g2);
	    	g2.dispose();
	    	try
	    	{
	    		ImageIO.write(image, "png", new File(chooser.getSelectedFile().getAbsolutePath()));
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	System.out.println("Saved as png file: " + chooser.getSelectedFile().getAbsolutePath());
    	}
    	
    }

    private class ClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
            selected = Canvas.NOSELECTION;
            System.out.println("mouse: "+e.getX()+" "+e.getY());
            for(DShape ds : shapeList){
                //System.out.println(ds+": bounds rect"+ds.getInfo().getBounds()+" CONTAINS? "+ds.contains(e.getX(), e.getY()));
                if(ds.contains(e.getX(), e.getY())){
                    select(ds);
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

