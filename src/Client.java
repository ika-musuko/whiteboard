import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread {

	Socket socket;
	
	public Client() {
		
	}

	public Client(Socket client) {
		this.socket = client;
	}

	public Client(int port){
		socket = new Socket();
	}
	
}
