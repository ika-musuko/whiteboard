import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Whiteboard extends JFrame {

	private static final long serialVersionUID = 1L;
    
    private Canvas canvas;
    private ControlPanel controlPanel;
    private StatusTable statusTable;

	public Whiteboard(){
        // initialize main window
		super("whiteboard");
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); 
		
        // initialize each subwindow
        this.canvas         = new Canvas();
        this.controlPanel   = new ControlPanel();
        this.statusTable    = new StatusTable();

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
        this.setBounds(0, 0, 600, 400);
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
	}
}
