package Client;

import Server.serverSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	
	public Socket s;
	public DataInputStream dis;
	public DataOutputStream dos;
	
	public ClientSocket() throws UnknownHostException, IOException{
		Socket s = new Socket("localhost",9999);
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		dout.writeUTF("hiiii");
		dout.flush();
		s.close();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientSocket d = new ClientSocket();
	}
}
