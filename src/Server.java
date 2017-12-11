import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread implements CanvasListener {

	ServerSocket serverSocket = null;
	ArrayList<ObjectOutputStream> clientList;
	Canvas canvas;
	
	public Server(Canvas canvas){
		try{
			this.canvas = canvas;
			serverSocket = new ServerSocket(39587);
			clientList = new ArrayList<ObjectOutputStream>();
		}catch (IOException e){
			System.out.println("Could not listen on port 39587");
		}
	}
	
	public Server(int port, Canvas canvas){
		try{
			this.canvas = canvas;
			serverSocket = new ServerSocket(port);
			clientList = new ArrayList<ObjectOutputStream>();
		} catch (IOException e){
			System.out.println("Could not listen on port " + port);
		}
	}
	
	public void run(){
		while(true){
			Socket client;
			try{
				client = serverSocket.accept();
				System.out.println("Connection established from " + client.getInetAddress());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				clientList.add(out);
				try {
					out.writeObject(Canvas.canvasToString(canvas.getShapeList()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e){
				System.out.println("Connection failed!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void canvasChanged(List<DShape> shapeList) {
		for(ObjectOutputStream client : clientList) {
			try {
				client.writeObject(Canvas.canvasToString(shapeList));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
