import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends Thread {

	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	InetAddress remoteHost;
	int port;
	Canvas canvas;

	public Client(InetAddress remoteHost, int port, Canvas canvas) {
		this.remoteHost = remoteHost;
		this.port = port;
		this.canvas = canvas;
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			socket = new Socket(remoteHost, port);
			System.out.println("Connection established to " + socket.getInetAddress());
			in = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			System.out.println("Failed to connect! Try again.");
		}
		while(true) {
			String shapeList;
			try {		
				shapeList = (String) in.readObject();
				ArrayList<DShape> list = Canvas.canvasFromString(shapeList);
				canvas.canvasChanged(list);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
}
