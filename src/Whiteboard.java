import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.*;

public class Whiteboard extends JFrame {

	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
		DEllipse de = new DEllipse(new Info(Color.RED, 50, 50, 100, 200));
		DRectangle dr = new DRectangle(new Info(Color.RED, 50, 50, 100, 200));
		List<DShape> list = new ArrayList<DShape>();
		list.add(de);
		list.add(dr);
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
        this.canvas         = new Canvas(this);
        this.controlPanel   = new ControlPanel(this);
        this.statusTable    = new StatusTable(this);

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
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setVisible(true);
		

		//*****Add buttons section*******
		JPanel addButtons = new JPanel();
		addButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		addButtons.setVisible(true);
		
		JLabel preButtonAddText = new JLabel("Add Shapes:");
		JButton addRect = new JButton("Rect");
		JButton addOval = new JButton("Oval");
		JButton addLine = new JButton("Line");
		JButton addText = new JButton("Text");
		
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
                addShape(new DEllipse());
			}
		});
		addLine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add line of default size and location*******
				addShape(new DLine());
			}
		});
		addText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add text of default size and location*******
				addShape(new DText());
			}
		});
		
		controlPanel.add(addButtons);
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
        // update the text based on the selected shape's model
		textEditor.getDocument().addDocumentListener(new DocumentListener() {
            public void updater(){
                if(canvas.getSelected().getInfo() instanceof TextInfo)
                    ((TextInfo)canvas.getSelected()).getInfo().setText(text.getText());
            
            }
            public void insertUpdate(DocumentEvent de){
                updater();
            }

            public void removeUpdate(DocumentEvent de){
                updater();
            }
            public void changedUpdate(DocumentEvent de){
            
            }
        });
		
		controlPanel.add(editTextPanel);
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
				colorPicker();
			}
		});
		
		controlPanel.add(shapeColorPanel);
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
		
		controlPanel.add(editShapePanel);
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
				fileSaver();
			}
		});
		loadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a dialog box allowing the user to select the file they want to load, load the info from the file
				//and recreate the shapes in their specified locations
				try {
					fileLoader();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveToPngButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a dialog box allowing the user to choose the name and location of the file they want to save
				//then save the whiteboard to a png file
				canvas.saveToPNG();
			}
		});
		
		controlPanel.add(fileIOPanel);
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
		
		controlPanel.add(networkPanel);
		//******************************
    }
	
    public void initStatusTable(){
    	
    	statusTable = new JPanel();
    	
		statusTable.setLayout(new BorderLayout());
	    String[] column = {"X", "Y", "WIDTH", "HEIGHT"};
	    Object[][] data = {
	    		{new Integer(0), new Integer(0), new Integer(10), new Integer(10)}
	    	};
	    
	    JTable table = new JTable(data, column);
	    table.setEnabled(false);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(500, 100));

	    statusTable.add(sp);
	    //Add a comment to this line
	    statusTable.setVisible(true);
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
    		
    		for (DShape shape : this.canvas.getShapeList()) 
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
    				text += ((TextInfo) shape.getInfo()).getFontName();		
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
    	this.canvas.resetArray();
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	int returnVal = chooser.showOpenDialog(null);
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
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
    				
    				String fontName = scanner.next();
    				
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

    			
    			addShape(shape);
    		}
    		
    		scanner.close();
    	} 
    }
    
    public void colorPicker()
    {
    	Color color = JColorChooser.showDialog(this, "Choose a color", Color.BLUE);
    	this.canvas.getSelected().info.setColor(color);
    	this.canvas.repaint(); //change this to method ?
    }

}
