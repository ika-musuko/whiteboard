import java.io.IOException;
import java.net.*;

public class Server extends Thread {

	ServerSocket serverSocket = null;
	
	public Server(){
		try{
			serverSocket = new ServerSocket(39587);
		}catch (IOException e){
			System.out.println("Could not listen on port 39587");
		}
	}
	
	public Server(int port){
		try{
			serverSocket = new ServerSocket(port);
		} catch (IOException e){
			System.out.println("Could not listen on port " + port);
		}
	}
	
	public void run(){
		while(true){
			Client clientThread;
			Socket client;
			try{
				client = serverSocket.accept();
				clientThread = new Client(client);
				//clientThread.start();
				System.out.println("Connection established from " + client.getInetAddress());
			} catch (IOException e){
				System.out.println("Connection failed!");
				e.printStackTrace();
			}
		}
	}
	
}
