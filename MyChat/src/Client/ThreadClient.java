package Client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import Server.serverSocket;

public class ThreadClient implements Runnable {
	DataInputStream dis;
	gui client;

	ThreadClient(DataInputStream dis, gui client) {
		this.dis = dis;
		this.client = client;
	}

	////////////////////////
	public void run() {
		String s2 = "";
		do {
			try {
				s2 = dis.readUTF();
				if (s2.startsWith(serverSocket.UPDATE_USERS))
					updateUsersList(s2);
				else if (s2.equals(serverSocket.LOGOUT_MESSAGE))
					break;
				else
					client.msgbox.append("\n" + s2);
				int lineOffset = client.msgbox.getLineStartOffset(client.msgbox.getLineCount() - 1);
				client.msgbox.setCaretPosition(lineOffset);
			} catch (Exception e) {
				client.msgbox.append("\nClientThread run : " + e);
			}
		} while (true);
	}

	//////////////////////////
	public void updateUsersList(String ul) {
		Vector ulist = new Vector();

		ul = ul.replace("[", "");
		ul = ul.replace("]", "");
		ul = ul.replace(serverSocket.UPDATE_USERS, "");
		StringTokenizer st = new StringTokenizer(ul, ",");

		while (st.hasMoreTokens()) {
			String temp = st.nextToken();
			ulist.add(temp);
		}
		client.usersbox.setListData(ulist);
	}
	
}
/////////////////////////////////////////

