import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Whiteboard extends JFrame {

	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
        //whiteboard.addShape(new DEllipse(50, 50, 100, 90));
        //whiteboard.addShape(new DRectangle(300, 60, 70, 90));
	}
	
	private static final long serialVersionUID = 1L;
    
    private Canvas canvas;
    private JPanel controlPanel;
    private JPanel statusTable;

	public Whiteboard(){
		
        // initialize main window
		super("whiteboard");
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); 
		
        // initialize each subwindow
        this.canvas         = new Canvas();
        this.initControlPanel();
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
        this.setBounds(0, 0, 1000, 400);
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    public void addShape(DShape ds) {
        this.canvas.addShape(ds);
    }
    
    private void initControlPanel(){
    	
    	this.controlPanel = new JPanel();
    	
    	//sets the layout of all the panes to be aligned vertically
		controlPanel.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		controlPanel.setVisible(true);
		

		//*****Add buttons section*******
		JPanel addButtons = new JPanel();
		addButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		addButtons.setVisible(true);
		
		JLabel preButtonAddText = new JLabel("Add Shapes:");
		JButton addRect = new JButton("Rect");
		JButton addOval = new JButton("Oval");
		JButton addLine = new JButton("Line");
		addLine.setEnabled(false);
		JButton addText = new JButton("Text");
		addText.setEnabled(false);											//10 10 20 20 
		
		addButtons.add(preButtonAddText);
		addButtons.add(addRect);
		addButtons.add(addOval);
		addButtons.add(addLine);
		addButtons.add(addText);
		
		//***********listeners*************
		addRect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//*******Add rectangle of random size and location******
				addShape(new DRectangle());
			}
		});
		addOval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add ellipse of random size and location*******
			}
		});
		addLine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add line of default size and location*******
			}
		});
		addText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add text of default size and location*******
			}
		});
		
		this.add(addButtons);
		//******************************
		
		//*****Text editing section*******
		JPanel editTextPanel = new JPanel();
		editTextPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		editTextPanel.setVisible(true);
		
		JLabel preEditText = new JLabel("Edit Text:");
		JTextField textEditor = new JTextField();
		textEditor.setColumns(30);				//***********TO ADD: font dropdown selection
		
		editTextPanel.add(preEditText);
		editTextPanel.add(textEditor);
		
		//*************listeners************
		textEditor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the text on the selected canvas shape every time the text box is changed
			}
		});
		
		this.add(editTextPanel);
		//******************************
		
		//*****Shape color section*******
		JPanel shapeColorPanel = new JPanel();
		shapeColorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		shapeColorPanel.setVisible(true);
		
		JLabel shapeColorText = new JLabel("Set Shape Color: ");
		JButton setColorButton = new JButton("Set Color");
		
		shapeColorPanel.add(shapeColorText);
		shapeColorPanel.add(setColorButton);
		
		//*******listeners**********
		setColorButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//bring up a dialog box which allows the user to select a color for the selected object
			}
		});
		
		this.add(shapeColorPanel);
		//******************************
		
		//*****Edit shape section*******
		JPanel editShapePanel = new JPanel();
		editShapePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		editShapePanel.setVisible(true);
		
		JLabel editShapeText = new JLabel("Edit Shapes:");
		JButton sendToFront = new JButton("Send to Front");
		JButton sendToBack = new JButton("Send to Back");
		JButton removeButton = new JButton("Remove this Shape");
		
		editShapePanel.add(editShapeText);
		editShapePanel.add(sendToFront);
		editShapePanel.add(sendToBack);
		editShapePanel.add(removeButton);
		
		//*************listeners***************
		sendToFront.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the text on the selected canvas shape every time the text box is changed
			}
		});
		sendToBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the text on the selected canvas shape every time the text box is changed
			}
		});
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the text on the selected canvas shape every time the text box is changed
			}
		});
		
		this.add(editShapePanel);
		//******************************
		
		//*****FileIO section*******
		JPanel fileIOPanel = new JPanel();
		fileIOPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		fileIOPanel.setVisible(true);
		
		JLabel fileIOText = new JLabel("Load/Save Content:");
		JButton saveButton = new JButton("Save");
		JButton loadButton = new JButton("Load");
		JButton saveToPngButton = new JButton("Save as PNG");
		
		fileIOPanel.add(fileIOText);
		fileIOPanel.add(saveButton);
		fileIOPanel.add(loadButton);
		fileIOPanel.add(saveToPngButton);
		
		//**********listeners*************
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//save the locations and specifications of the shape infos into a file, xml? txt? something else?
			}
		});
		loadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a dialog box allowing the user to select the file they want to load, load the info from the file
				//and recreate the shapes in their specified locations
			}
		});
		saveToPngButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a dialog box allowing the user to choose the name and location of the file they want to save
				//then save the whiteboard to a png file
			}
		});
		
		this.add(fileIOPanel);
		//******************************
		
		//*****Networking section*******
		JPanel networkPanel = new JPanel();
		networkPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		networkPanel.setVisible(true);
		
		JLabel networkingLabel = new JLabel("Networking:");
		JButton startServerButton = new JButton("Start Server");
		JButton startClientButton = new JButton("Start Client");
		
		networkPanel.add(networkingLabel);
		networkPanel.add(startServerButton);
		networkPanel.add(startClientButton);
		
		//**********listeners*************
		startServerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//start a server to display the contents of the whiteboard to a client over the network
			}
		});
		startClientButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//start a client to see the contents of a whiteboard that is in server mode
			}
		});
		
		this.add(networkPanel);
		//******************************
    }
	
	
}
