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

public class Canvas extends JPanel implements CanvasListener{

	private static final long serialVersionUID = 1L;
    
    //dummy shape to point to when there is no selection
	private static final DShape NOSELECTION = new DShape() {
        @Override
        protected void draw(Graphics g){
            // dummy shapes don't draw anything!
        }
        
        @Override
        public DShape copy(){
            return null;
        }
    };	
	
    private List<DShape> shapeList;
    private DShape selected;
    private DShape dragged;
    
    private boolean resizing;
    private Point anchorKnob;
    public static final Point NOANCHOR = new Point(-10000, -10000);
    
    private List<CanvasListener> canvasListeners;

    private Whiteboard whiteboard;

    // CanvasListener implement
    // potential way for canvas objects to listen to each other???
    @Override
    public void canvasChanged(List<DShape> shapeList) {
        this.shapeList = shapeList;
        this.refresh();
    }
    
    private class ClickListener implements MouseListener, MouseMotionListener {
		// tries to select a DShape or returns NOSELECTION if there is no selection
        private DShape trySelect(MouseEvent e){
            for(DShape ds : shapeList){
                if(ds.contains(e.getX(), e.getY())){
                    return ds;             
                }
            }
            return Canvas.NOSELECTION;
        }
        
        // mouseClicked selects a Shape
        @Override
		public void mouseClicked(MouseEvent e) {
            select(this.trySelect(e));
            refresh();
		}

        @Override
        public void mouseDragged(MouseEvent e) { 
            // if something is already being dragged...
            if (dragged != Canvas.NOSELECTION) {
                if (anchorKnob != Canvas.NOANCHOR)
                    dragged.getInfo().resize(anchorKnob, e.getX(), e.getY());                
                else
                    dragged.getInfo().move(e.getX(), e.getY());
                return;
            }
            
            // try to make a new selection and move it (no resizing) if a selection is made
            for(DShape ds : shapeList){
                if(ds.contains(e.getX(), e.getY())){
                    select(ds);
                    dragged = ds;
                    anchorKnob = ds.getAnchor(e.getX(), e.getY());
                    break;
                }
            }
            refresh();
        }

        @Override
        public void mouseMoved(MouseEvent e){

        }

		@Override
		public void mousePressed(MouseEvent e) {
			//System.out.println("Mouse pressed!");
			//System.out.println(e.getSource());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			dragged = Canvas.NOSELECTION;
            anchorKnob = Canvas.NOANCHOR;
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

	public Canvas(Whiteboard whiteboard){
        this(whiteboard, new ArrayList<>());
	}

    public Canvas(Whiteboard whiteboard, List<DShape> shapeList){
        // init the fields
        this.whiteboard = whiteboard;
        this.shapeList = shapeList;
        this.select(Canvas.NOSELECTION); 
        this.dragged = Canvas.NOSELECTION;
        this.anchorKnob = Canvas.NOANCHOR;
        
        // init the main canvas mouse click listener
        ClickListener cl = new ClickListener();
        this.addMouseListener(cl);
        this.addMouseMotionListener(cl);
        
        // init the canvas listeners
        this.canvasListeners = new ArrayList<>();
        this.addCanvasListener(this.whiteboard.getStatusTable());
        
        // display the canvas
        this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
        this.refresh();
    }
    
    // canvas listener methods and shapelist editing
    public void addCanvasListener(CanvasListener cl){
        this.canvasListeners.add(cl);
    }
    
    private void notifyCanvasListeners() {
        for(CanvasListener cl : this.canvasListeners){
            cl.canvasChanged(this.shapeList);
        }
    }

    public void refresh() {
        this.notifyCanvasListeners();
        this.revalidate();
        this.repaint();
    }

    // verifies that a shape is actually selected
    public void verifyShapeSelected() {
        this.verifyText();
        if(this.selected == null || this.selected == Canvas.NOSELECTION)
            this.whiteboard.getControlPanel().disableShapeEditing();
        else
            this.whiteboard.getControlPanel().enableShapeEditing();
    }
    
    // verifies if the currently selected DShape's info is a TextInfo or not, enables/disables the appropriate boxes, and synchronizes their fields
    public void verifyText() {
       if(this.selected != null && this.selected.getInfo() instanceof TextInfo) {
           TextInfo ti = (TextInfo)this.selected.getInfo();
           this.whiteboard.getControlPanel().enableText(ti.getText(), ti.getFont().getFontName());
       }
       else
            this.whiteboard.getControlPanel().disableText();
    }

    // adds a shape to the canvas
    public void addShape(DShape ds) {
        ds.getInfo().addListener(new InfoListener() {
            public void infoChanged(Info info){
                refresh();
            }
        });
        
        this.shapeList.add(0, ds);
        this.select(ds);
        this.refresh();
    }
    
    public void duplicateCurrent() {
        this.addShape(this.selected.copy());
    }

    private void select(DShape ds) {
        this.selected = ds;
        this.verifyShapeSelected();
    }
    
    public void deselect(){
        this.select(Canvas.NOSELECTION);
    }

    public void resetArray(){
    	shapeList = new ArrayList<DShape>();
        this.refresh();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        for (int i = this.shapeList.size()-1; i >= 0; --i) {
            this.shapeList.get(i).draw(g2);
            if(this.shapeList.get(i) == this.selected)
                this.shapeList.get(i).drawKnobs(g2);
            g2.setClip(null);
        }
        g2.dispose();
    }
    
    // swap two DShapes in the shapelist via index
    private void shapeSwap(int i, int j) {
        DShape temp = this.shapeList.get(i);
        this.shapeList.set(i, this.shapeList.get(j));
        this.shapeList.set(j, temp);
    }
    
    private int selectedIndex(){
        if(this.selected == Canvas.NOSELECTION) return -1;
        return this.shapeList.indexOf(this.selected);
    }
    
    public void sendToBack(){
        int si = this.selectedIndex();
        if(si == -1 || si >= this.shapeList.size()-1) return;
        this.shapeSwap(si, si+1);
        this.refresh();
    }
    
    public void sendToFront(){
        int si = this.selectedIndex();
        if(si <= 0) return;
        this.shapeSwap(si, si-1);
        this.refresh();
    }
    
    public void removeSelected(){
        if(this.selected != Canvas.NOSELECTION) {
            this.shapeList.remove(this.selected);
            this.select(Canvas.NOSELECTION);
            this.refresh();
        }        
    }
    
    public List<DShape> getShapeList(){
    	return shapeList;
    }
    
    public DShape getSelected(){
    	return selected;
    }
 
    public void saveToPNG(){
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
	    		ImageIO.write(image, "png", new File(chooser.getSelectedFile().getAbsolutePath() + ".png"));
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	System.out.println("Saved as png file: " + chooser.getSelectedFile().getAbsolutePath() + ".png");
    	}
    } 
}

