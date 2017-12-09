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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.*;

public class ControlPanel extends JPanel {
  
	private static final long serialVersionUID = 1L;
    private Whiteboard whiteboard;
    private JTextField textEditor;

    public void enableText(String s){
        this.textEditor.setEnabled(true);
        this.textEditor.setText(s);
    }

    public void disableText(){
        this.textEditor.setText("");
        this.textEditor.setEnabled(false);
    }

    public ControlPanel(Whiteboard whiteboard) {	 
    	// init fields
        this.whiteboard = whiteboard;
        this.textEditor = new JTextField();
    	
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
				whiteboard.addShape(new DRectangle());
			}
		});
		addOval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add ellipse of random size and location*******
                whiteboard.addShape(new DEllipse());
			}
		});
		addLine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add line of default size and location*******
				whiteboard.addShape(new DLine());
			}
		});
		addText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//********add text of default size and location*******
				whiteboard.addShape(new DText());
			}
		});
		
		this.add(addButtons);
		//******************************
		
		//*****Text editing section*******
		JPanel editTextPanel = new JPanel();
		editTextPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		editTextPanel.setVisible(true);
		
		JLabel preEditText = new JLabel("Edit Text:");
        
        // configure text editor
		textEditor.setColumns(30);				//***********TO ADD: font dropdown selection
		
		editTextPanel.add(preEditText);
		editTextPanel.add(textEditor);
		
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
			    String message = "Enter IP and port number you want to use (default is 39587):";
			    String text = JOptionPane.showInputDialog(frame, message);
			    if (text == null) {
			      //do nothing
			    	System.out.println("Cancelled");
			    } else {
			    	Server server;
			    	try{
			    		server = new Server(Integer.parseInt(text));
			    	} catch (NumberFormatException numEx) {
			    		server = new Server();
			    	}
			    	server.start();
			    	System.out.println("Server initiated.");	
			    }

				
				
				
			}
		});
		startClientButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//start a client to see the contents of a whiteboard that is in server mode
			}
		});
		
		this.add(networkPanel);
    }


    public void colorPicker(){
    	Color color = JColorChooser.showDialog(this, "Choose a color", Color.BLUE);
    	this.whiteboard.getCanvas().getSelected().info.setColor(color);
    	this.whiteboard.getCanvas().refresh(); //change this to method ?
    }
}
