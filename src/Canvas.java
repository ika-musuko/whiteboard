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
    
    // dummy shape to point to when there is no selection
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
    
    private ClickListener cl;
    
    private boolean resizing;
    private Point anchorKnob;
    public static final Point NOANCHOR = new Point(-10000, -10000); // dummy anchor point
    
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
        private int xOffset;
        private int yOffset;
        private boolean startIsShape;
        
        private DShape trySelect(MouseEvent e){
            for(DShape ds : shapeList){
                if(ds.getInfo().contains(e.getX(), e.getY())){
                    return ds;             
                }
            }
            return Canvas.NOSELECTION;
        }
        
        private DShape trySelectWithKnobs(MouseEvent e){
            for(DShape ds : shapeList){
                if(ds.getInfo().containsWithKnobs(e.getX(), e.getY())){
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
            if (!this.startIsShape) return;
        
            // if something is already being dragged...
            if (dragged != Canvas.NOSELECTION) {
                if (selected == dragged && anchorKnob != Canvas.NOANCHOR)
                    dragged.getInfo().resize(anchorKnob, e.getX(), e.getY());                
                else{
                    dragged.getInfo().move(e.getX()-this.xOffset, e.getY()-this.yOffset);
                    select(Canvas.NOSELECTION);
                }
                return;
            }
            
            // try to make a new selection and move it (no resizing) if a selection is made
            for(DShape ds : shapeList){
                if(ds.getInfo().containsWithKnobs(e.getX(), e.getY())){
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
            if (selected == Canvas.NOSELECTION) 
                select(this.trySelect(e));
            else
                select(this.trySelectWithKnobs(e));
            if (selected != Canvas.NOSELECTION) {
                this.xOffset = e.getX() - selected.getInfo().getX();
                this.yOffset = e.getY() - selected.getInfo().getY();
                this.startIsShape = true;
                refresh();
                return;
            }
            this.startIsShape = false;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
            select(dragged);
			dragged = Canvas.NOSELECTION;
            anchorKnob = Canvas.NOANCHOR;
            refresh();
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
        this.cl = new ClickListener();
        this.addMouseListener(this.cl);
        this.addMouseMotionListener(this.cl);
        
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
            if(this.shapeList.get(i) == this.selected || this.shapeList.get(i) == this.dragged)
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
    
    // disable for client mode
    public void disable() {
        this.removeMouseMotionListener(this.cl);
        this.removeMouseListener(this.cl);
        this.deselect();
        this.refresh();
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
    
    public static String canvasToString(List<DShape> shapeList) {
    	StringBuilder out = new StringBuilder();
		for (DShape shape : shapeList)
		{
			String text = "";
			
			if(shape instanceof DRectangle)
			{
				text += "Rectangle ";
			}
			else if(shape instanceof DEllipse)
			{
				text += "Ellipse ";
			}
			else if(shape instanceof DLine)
			{
				text += "Line ";
			}
			else if(shape instanceof DText)
			{
				text += "Text ";
			}
			
			text += ("#" + Integer.toHexString(shape.getInfo().getColor().getRGB()).substring(2).toUpperCase() + " ");
			text += shape.getInfo().getX() + " " + shape.getInfo().getY() + " " + shape.getInfo().getWidth() + " " + shape.getInfo().getHeight();
			
			if(shape instanceof DText)
			{
				text += (" " + '"' + ((TextInfo) shape.getInfo()).getText() + '"' + " ");
				text += ('"' + ((TextInfo) shape.getInfo()).getFontName() + '"' + " ");	
			}
			out.append(text);
			out.append('\n');
		}
		return out.toString();
    }
    
    public static ArrayList<DShape> canvasFromString(String input) {
	
		Scanner scanner = new Scanner(input); 
		ArrayList<DShape> list = new ArrayList<DShape>();
		
		while(scanner.hasNext()){
			DShape shape = null;
			
			// default parameters for Info
			String shapeType = scanner.next();
			
			String colorHexVal = scanner.next();
			Color color = Color.decode(colorHexVal);
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int width = scanner.nextInt();
			int height = scanner.nextInt();	
			
			if(shapeType.equals("Text"))
			{
				String text = "";
				char c = ' ';
				while(c != '"')
				{
					text += scanner.next();
					text += " ";
					c = text.charAt(text.length() - 2);
				}
				
				text = text.substring(1, text.length() - 2);  // get rid of beginning and end quotes
				
				String fontName = "";
				c = ' ';
				while(c != '"')
				{
					fontName += scanner.next();
					fontName += " ";
					c = fontName.charAt(fontName.length() - 2);
				}
				fontName = fontName.substring(1, fontName.length() - 2);  // get rid of beginning and end quotes
				
				TextInfo infoT = new TextInfo(text, fontName, color, x, y, width, height);
				shape = new DText(infoT);
			}
			else if(shapeType.equals("Line"))
			{
				LineInfo infoL = new LineInfo(color, x, y, width, height);
				shape = new DLine(infoL);
			}
			else
			{
				Info info = new Info(color, x, y, width, height);
				
    			if(shapeType.equals("Rectangle"))
    			{
    				shape = new DRectangle(info);
    			}
    			else if(shapeType.equals("Ellipse"))
    			{
    				shape = new DEllipse(info);
    			}
			}

			
			list.add(shape);
		}
		scanner.close();
		return list;
    }
    
    
}

