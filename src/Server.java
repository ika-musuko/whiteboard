import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread implements CanvasListener {

	ServerSocket serverSocket = null;
	ArrayList<Client> clientList;
	
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
				clientList.add(clientThread);
				clientThread.start();
			} catch (IOException e){
				System.out.println("Connection failed!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void canvasChanged(List<DShape> shapeList) {
		// TODO Auto-generated method stub
		
	}
	
}
