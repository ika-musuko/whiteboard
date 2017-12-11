import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Whiteboard extends JFrame {

	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
		Whiteboard whiteboard2 = new Whiteboard();
	}
	
	private static final long serialVersionUID = 1L;
    
    private Canvas canvas;
    private ControlPanel controlPanel;
    private StatusTable statusTable;

    public Canvas getCanvas(){
        return this.canvas;
    }

    public ControlPanel getControlPanel() {
        return this.controlPanel;
    }

    public StatusTable getStatusTable() {
        return this.statusTable;
    }

	public Whiteboard(){		
        // initialize main window
		super("whiteboard");
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); 
		
        // initialize each subwindow
        this.controlPanel   = new ControlPanel(this);
        this.statusTable    = new StatusTable(this);
        this.canvas         = new Canvas(this);

        // place each subwindow onto the main GUI
        JPanel toolsLayout = new JPanel();
        toolsLayout.setLayout(new BoxLayout(toolsLayout, BoxLayout.Y_AXIS));
        toolsLayout.add(this.controlPanel);
        toolsLayout.add(this.statusTable);
        toolsLayout.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        
        JPanel canvasLayout = new JPanel();
        canvasLayout.setLayout(new BorderLayout());
        canvasLayout.add(this.canvas, BorderLayout.CENTER);
        
        this.add(toolsLayout);
        this.add(canvasLayout);
     
		 
        // actually show the window
        this.pack();
        this.setBounds(0, 0, 1000, 400);
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    public void fileSaver()
    {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setDialogTitle("Save");
    	chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	
    	int returnVal = chooser.showSaveDialog(null);
    	
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
    		File selectedFile = chooser.getSelectedFile();
    		
    		try (PrintWriter out = new PrintWriter(selectedFile)){
    		
    		for (int i = this.canvas.getShapeList().size()-1; i >= 0; --i)
    		{
                DShape shape = this.canvas.getShapeList().get(i);
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
    			out.println(text);
    		}
    		
    	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("Save as file: " + selectedFile.getAbsolutePath());
    		
    	}
    }
    
    public void fileLoader() throws FileNotFoundException
    {
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	int returnVal = chooser.showOpenDialog(null);
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
            this.canvas.resetArray();
    		File file = chooser.getSelectedFile();
    		Scanner scanner = new Scanner(file); 
    		
    		while(scanner.hasNext())
    		{
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

    			
    			this.canvas.addShape(shape);
    		}
    		
    		scanner.close();
    	}
        this.canvas.deselect();
    }
}
