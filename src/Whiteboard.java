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

import java.util.ArrayList;

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
    			out.println(this.canvas.canvasToString(canvas.getShapeList()));
    		}
            catch (FileNotFoundException e) {
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
            try {
                Scanner scanner = new Scanner(file); 
                ArrayList<DShape> list = Canvas.canvasFromString(scanner.useDelimiter("\\Z").next());
                this.canvas.canvasChanged(list);
                scanner.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    	}
        this.canvas.deselect();
    }
}
