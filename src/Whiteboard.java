import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Whiteboard extends JFrame{

	private static final long serialVersionUID = 1L;

	public Whiteboard(){
		this.setTitle("Whiteboard");
		this.setBackground(Color.GRAY);
		this.setSize(900, 400);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
		Canvas canvas = new Canvas();
		whiteboard.add(canvas);
		
		//Add buttons section
		JPanel addButtons = new JPanel();
		addButtons.setBounds(0, 0, 500, 30);
		addButtons.setBackground(Color.BLACK);
		addButtons.setLayout(new FlowLayout());
		addButtons.setVisible(true);
		
		JLabel preButtonAddText = new JLabel("Add");
		JButton addRect = new JButton("Rect");
		
		JButton addOval = new JButton("Oval");
		JButton addLine = new JButton("Line");
		JButton addText = new JButton("Text");
		
		addButtons.add(preButtonAddText);
		addButtons.add(addRect);
		addButtons.add(addOval);
		addButtons.add(addLine);
		addButtons.add(addText);
		whiteboard.add(addButtons);
		
		
		
	}
	
	
}
