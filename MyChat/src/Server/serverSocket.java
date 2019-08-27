package Server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Client.ClientThread;
import Client.MyThread;
import Db.MysqlCon;

public class serverSocket {

	ArrayList al = new ArrayList();
	ArrayList users = new ArrayList();
	ServerSocket ss;
	Socket s;
	MysqlCon sqlobj;

	public final static int PORT = 9999;
	public final static String UPDATE_USERS = "updateuserslist:";
	public final static String LOGOUT_MESSAGE = "@@logoutme@@:";

	public serverSocket() throws IOException {
		ServerSocket ss = new ServerSocket(PORT);

		System.out.println("Waiting for Client");
		while (true) {
			try {
				s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				sqlobj = new MysqlCon();
				String usernameClient = dis.readUTF();
				String passwordClient = dis.readUTF();
				boolean flag = sqlobj.checkuser(usernameClient, passwordClient);
				if (flag) {
					System.out.println(usernameClient + passwordClient);
					Runnable r = new MyThread(s, al, users);
					Thread t = new Thread(r);
					t.start();
				} else
					System.out.println("ERROR");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public static void main(String[] args) throws IOException {
		serverSocket t = new serverSocket();
	}
}
