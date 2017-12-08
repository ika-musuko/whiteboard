import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Whiteboard extends JFrame {

	public static void main(String[] args){
		Whiteboard whiteboard = new Whiteboard();
		
		DEllipse de = new DEllipse(new Info(Color.RED, 50, 50, 100, 200));
		DRectangle dr = new DRectangle(new Info(Color.RED, 50, 50, 100, 200));
		//whiteboard.addShape(de);
		//whiteboard.addShape(dr);
		List<DShape> list = new ArrayList<DShape>();
		list.add(de);
		list.add(dr);
		//canvas = new Canvas(list);
		
		
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
        this.initStatusTable();

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
		textEditor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the text on the selected canvas shape every time the text box is changed
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
    
    public void fileLoader() throws FileNotFoundException
    {
    	//this.canvas = new Canvas();
    	this.canvas.resetArray();
    	JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	int returnVal = chooser.showOpenDialog(null);
    	if(returnVal == JFileChooser.APPROVE_OPTION)
    	{
    		File file = chooser.getSelectedFile();
    		Scanner scanner = new Scanner(file); 
    		
    		List<DShape> shapeList = new ArrayList<DShape>();
    		
    		while(scanner.hasNextLine())
    		{
    			DShape shape = null;

    			// default parameters for Info
    			String shapeType = scanner.next();
    			
    			// all parameters will be saved into info object at the end
    			//Info info; 
    			
				String colorHexVal = scanner.next();
				Color color = Color.decode(colorHexVal);
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				int width = scanner.nextInt();
				int height = scanner.nextInt();	
				
				System.out.println(shapeType + " " + color.getRed() + color.getGreen() + color.getBlue() +
					" " + x + " " + y + " " + width + " " + height);

    			
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
    				//shapeList.add(shape);
    				
    				System.out.println(text + " " + fontName);
    			}
    			else if(shapeType.equals("Line"))
    			{
    				LineInfo infoL = new LineInfo(color, x, y, width, height);
    				shape = new DLine(infoL);
    				//addShape(shape);
    				//shapeList.add(shape);
    			}
    			else
    			{
    				Info info = new Info(color, x, y, width, height);
    				
	    			if(shapeType.equals("Rectangle"))
	    			{
	    				shape = new DRectangle(info);
	    				//addShape(shape);
	    				//shapeList.add(shape);
	    			}
	    			else if(shapeType.equals("Ellipse"))
	    			{
	    				shape = new DEllipse(info);
	    				//addShape(shape);
	    				//shapeList.add(shape);
	    			}
    			}

    			
    			addShape(shape);
    			//shapeList.add(shape);
    		}
    		
			
			
    		//this.canvas = new Canvas(shapeList);
    		scanner.close();
    	}
    
    }

}
