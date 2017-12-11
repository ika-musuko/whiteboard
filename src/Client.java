import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {

	Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		System.out.println("Connection established from " + socket.getInetAddress());
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//out.writeObject();
		
	}
	
}
