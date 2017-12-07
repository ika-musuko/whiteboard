import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

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
		this.setLayout(new BorderLayout()); 
		
        // initialize each subwindow
        this.canvas         = new Canvas();
        this.controlPanel   = new ControlPanel();
        this.statusTable    = new StatusTable();

        // place each subwindow onto the main GUI
		this.add(this.canvas, BorderLayout.EAST);
        this.add(this.controlPanel, BorderLayout.NORTH);
        this.add(this.statusTable, BorderLayout.SOUTH);
		 
        // actually show the window
        this.pack();
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
	}
}
