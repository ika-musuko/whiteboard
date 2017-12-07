import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
  
	private static final long serialVersionUID = 1L;

public ControlPanel() {
		//Add buttons section
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		JLabel preButtonAddText = new JLabel("Add Shapes:");
		JButton addRect = new JButton("Rect");
		
		JButton addOval = new JButton("Oval");
		JButton addLine = new JButton("Line");
		addLine.setEnabled(false);
		JButton addText = new JButton("Text");
		addText.setEnabled(false);
		
		this.add(preButtonAddText);
		this.add(addRect);
		this.add(addOval);
		this.add(addLine);
		this.add(addText);
   }
}
