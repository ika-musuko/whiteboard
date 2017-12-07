import java.awt.Color;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public Canvas(){
		this.setBounds(500, 0, 400, 400);  //creates the whiteboard at 500 pixels over with 400x400 dimensions
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}

}
