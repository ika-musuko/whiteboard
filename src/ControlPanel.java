import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.*;

public class ControlPanel extends JPanel {
  
	private static final long serialVersionUID = 1L;
    private Whiteboard whiteboard;
    private JTextField textEditor;
    private JComboBox<String> fontBox;
    
    private JButton setColorButton;
    private JButton sendToFront   ;
    private JButton sendToBack    ;
    private JButton duplicate     ;
    private JButton removeButton  ;
    
    public boolean isClient;

    // is this a client
    
    // enables text editing fields and sets them to the parameters
    public void enableText(String s, String font){
        if(this.isClient) return;
        // enable the text editor and set it to the string
        this.textEditor.setEnabled(true);
        this.textEditor.setText(s);
        
        // enable the font box and set it to the font
        this.fontBox.setEnabled(true);
        this.fontBox.setSelectedItem(font);
    }

    // disables text editing fields
    public void disableText(){
        // disable the text editor
        this.textEditor.setText("");
        this.textEditor.setEnabled(false);
        
        // disable the font box
        this.fontBox.setEnabled(false);
    }
    
    // enables shape editing buttons
    public void enableShapeEditing(){
        if(this.isClient) return;
        this.setColorButton.setEnabled(true);
        this.sendToFront.setEnabled   (true);  
        this.sendToBack.setEnabled    (true);   
        this.duplicate.setEnabled     (true);   
        this.removeButton.setEnabled  (true); 
    }

    // disables shape editing buttons
    public void disableShapeEditing(){
        this.setColorButton.setEnabled(false);
        this.sendToFront.setEnabled   (false);  
        this.sendToBack.setEnabled    (false);   
        this.duplicate.setEnabled     (false);   
        this.removeButton.setEnabled  (false); 
    }
    
    public ControlPanel(Whiteboard whiteboard) {	 
    	// init fields
        this.whiteboard = whiteboard;
        this.textEditor = new JTextField();
        this.isClient   = false;
    	
    	//sets the layout of all the panes to be aligned vertically
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);	

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
				whiteboard.getCanvas().addShape(new DRectangle());
			}
		});
		addOval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add ellipse of random size and location*******
                whiteboard.getCanvas().addShape(new DEllipse());
			}
		});
		addLine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add line of default size and location*******
				whiteboard.getCanvas().addShape(new DLine());
			}
		});
		addText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add text of default size and location*******
				whiteboard.getCanvas().addShape(new DText());
			}
		});
		
		this.add(addButtons);
		//******************************
		
		//*****Text editing section*******
		JPanel editTextPanel = new JPanel();
		editTextPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		editTextPanel.setVisible(true);
		
		JLabel preEditText = new JLabel("Edit Text:");
		this.fontBox = fontMenu();
		
        // configure text editor
		textEditor.setColumns(30);			
		
		editTextPanel.add(preEditText);
		editTextPanel.add(textEditor);
		editTextPanel.add(fontBox);
		
		//*************listeners************
        // update the text based on the selected shape's model
		textEditor.getDocument().addDocumentListener(new DocumentListener() {
            public void updater(){
                if(whiteboard.getCanvas().getSelected().getInfo() instanceof TextInfo)
                    ((TextInfo)whiteboard.getCanvas().getSelected().getInfo()).setText(textEditor.getText()); 
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
		
		this.add(editTextPanel);
		//******************************
		
		//*****Shape color section*******
		JPanel shapeColorPanel = new JPanel();
		shapeColorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		shapeColorPanel.setVisible(true);
		
		JLabel shapeColorText = new JLabel("Set Shape Color: ");
		this.setColorButton = new JButton("Set Color");
		
		shapeColorPanel.add(shapeColorText);
		shapeColorPanel.add(this.setColorButton);
		
		//*******listeners**********
		setColorButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//bring up a dialog box which allows the user to select a color for the selected object
				colorPicker();
			}
		});
		
		this.add(shapeColorPanel);
		//******************************
		
		//*****Edit shape section*******
		JPanel editShapePanel = new JPanel();
		editShapePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		editShapePanel.setVisible(true);
		
		JLabel editShapeText = new JLabel("Edit Selected Shape:");
		this.sendToFront  = new JButton("Move Front");
		this.sendToBack   = new JButton("Move Back");
		this.duplicate    = new JButton("Duplicate");
		this.removeButton = new JButton("Remove");
		
		editShapePanel.add(editShapeText);
		editShapePanel.add(sendToFront);
		editShapePanel.add(sendToBack);
		editShapePanel.add(duplicate);
		editShapePanel.add(removeButton);
		
		//*************listeners***************
		sendToFront.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				whiteboard.getCanvas().sendToFront();
			}
		});
		sendToBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                whiteboard.getCanvas().sendToBack();
			}
		});
        duplicate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                whiteboard.getCanvas().duplicateCurrent();
			}
		});
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                whiteboard.getCanvas().removeSelected();
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
		JButton resetButton = new JButton("Reset");
		JButton saveToPngButton = new JButton("Save as PNG");
		
		fileIOPanel.add(fileIOText);
		fileIOPanel.add(saveButton);
		fileIOPanel.add(loadButton);
		fileIOPanel.add(resetButton);
		fileIOPanel.add(saveToPngButton);
		
		//**********listeners*************
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//save the locations and specifications of the shape infos into a file
				whiteboard.fileSaver();
			}
		});
		
        loadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a dialog box allowing the user to select the file they want to load, load the info from the file
				//and recreate the shapes in their specified locations
				try {
					whiteboard.fileLoader();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                whiteboard.getCanvas().resetArray();
            }
        });
        
		saveToPngButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//open a dialog box allowing the user to choose the name and location of the file they want to save
				//then save the whiteboard to a png file
				whiteboard.getCanvas().saveToPNG();
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
				JFrame frame = new JFrame();
			    String message = "Enter the port number you want to use (default is 39587):";
			    String text = JOptionPane.showInputDialog(frame, message);
			    if (text == null) {
			      //do nothing
			    	System.out.println("Cancelled");
			    } else {
			    	Server server;
			    	try{
			    		server = new Server(Integer.parseInt(text), whiteboard.getCanvas());
			    	} catch (NumberFormatException numEx) {
			    		server = new Server(whiteboard.getCanvas());
			    	}
			    	
			    	//disable networking buttons before starting server
					startServerButton.setEnabled(false);
					startClientButton.setEnabled(false);
			    
			    	server.start();
			    	whiteboard.getCanvas().addCanvasListener(server);
			    	System.out.println("Server initiated.");	
			    }
			}
		});
		
		startClientButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				//start a client to see the contents of a whiteboard that is in server mode
				JFrame frame = new JFrame();
			    String message = "Enter IP and port number you want to use (default is 127.0.0.1:39587):";
			    String text = JOptionPane.showInputDialog(frame, message);
			    if (text == null) {
			      //do nothing
			    	System.out.println("Cancelled");
			    } else {
			    	if(text.equals(""))
			    		text = "127.0.0.1:39587";
			    	String[] addressPort = text.split(":");
			    	int port = Integer.parseInt(addressPort[1]);
			    	InetAddress remoteHost = null;

			    	try {
						remoteHost = InetAddress.getByName(addressPort[0]);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    	
			    	// disable buttons in control panel before connecting to server
			    	addRect.setEnabled(false);
					addOval.setEnabled(false);
					addLine.setEnabled(false);
					addText.setEnabled(false);
					saveButton.setEnabled(false);
					loadButton.setEnabled(false);
					resetButton.setEnabled(false);
					saveToPngButton.setEnabled(false);
					startServerButton.setEnabled(false);
					startClientButton.setEnabled(false);
                    disableShapeEditing();
                    disableText();
                    
			    	isClient = true;
                    
                    whiteboard.getCanvas().disable();
                    
			    	Client client;
			    	client = new Client(remoteHost, port, whiteboard.getCanvas());
			    	client.start();
	
			    }
			}
		});
		
		this.add(networkPanel);
    }


    public void colorPicker(){
    	Color color = JColorChooser.showDialog(this, "Choose a color", Color.BLUE);
    	this.whiteboard.getCanvas().getSelected().info.setColor(color);
    	this.whiteboard.getCanvas().refresh(); 
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox<String> fontMenu()
    {
        // get all the system font names
    	GraphicsEnvironment graphE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	String [] fontList = graphE.getAvailableFontFamilyNames();
        
        // render the JCombo box
    	JComboBox<String> fontBox = new JComboBox<>(fontList);
        
        // selected items
    	fontBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
    		   public Component getListCellRendererComponent(JList<?> list,
    		         Object value, int index, boolean isSelected, boolean cellHasFocus) {
    		      return super.getListCellRendererComponent(list, (String)value, index, isSelected, cellHasFocus);
    		   }
    		});
    	
        // feed font info toe canvas
    	fontBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	
                	//prevents error from happening when shapes are "given a font"
                	if(whiteboard.getCanvas().getSelected() instanceof DText)
                	{
                         ((TextInfo) whiteboard.getCanvas().getSelected().getInfo()).setFont((String)fontBox.getSelectedItem());
                	}
                }
            }
        });

    	return fontBox;  		
    }
}
