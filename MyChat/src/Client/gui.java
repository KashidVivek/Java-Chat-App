package Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Client.ClientThread;
import Db.MysqlCon;
import Server.serverSocket;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class gui extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField password;
	public JTextArea msgbox;
	@SuppressWarnings("rawtypes")public JList usersbox;
	private JTextField text1;
	private JButton btnRegister;
	private JButton btnLogin;
	private JButton btnlogout;
	private JButton btnSend;
	public Socket s;
	public DataInputStream dis;
	public DataOutputStream dos;
	public String user,pass;
	public MysqlCon sqlobj;
	public JScrollPane scroll ;
	public static String serverAdd;

	public gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel lblUsername = new JLabel("Username:");
		
		usernameText = new JTextField();
		usernameText.setColumns(10);
		usernameText.setBorder(border);
		
		JLabel lblPassword = new JLabel("Password:");
		
		password = new JPasswordField();
		password.setColumns(10);
		password.setBorder(border);
		
		 btnLogin = new JButton("Login");
		// btnLogin.setBorder(border);
		
		btnlogout = new JButton("Logout");
		//btnlogout.setBorder(border);
		
		btnRegister = new JButton("Register");
		//btnRegister.setBorder(border);
		
		msgbox = new JTextArea();
		msgbox.setColumns(10);
		msgbox.setEditable(false);
		scroll = new JScrollPane(msgbox);
		
		usersbox = new JList();
		usersbox.setBorder(border);
		msgbox.setBorder(border);
		
		
		JLabel lblOnline = new JLabel("Online:");
		
		text1 = new JTextField();
		text1.setColumns(10);
		text1.setBorder(border);
		
		
		 btnSend = new JButton("Send");
		 //btnSend.setBorder(border);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(usernameText, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(18)
									.addComponent(password)))
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnlogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(text1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
								.addComponent(scroll))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(usersbox, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addComponent(lblOnline, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(24))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin)
						.addComponent(btnRegister))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnlogout)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(lblOnline)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(usersbox)
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(text1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		contentPane.setLayout(gl_contentPane);
	
	btnLogin.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
	
			
			
		}
	});
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	btnSend.addActionListener(this);
	btnlogout.addActionListener(this);
	btnLogin.addActionListener(this);
	btnRegister.addActionListener(this);
	btnlogout.setEnabled(false);
	btnLogin.setEnabled(true);
	text1.addFocusListener(new FocusAdapter() {
		public void focusGained(FocusEvent fe) {
			text1.selectAll();
		}
	});
	//setContentPane(panel0);
	setSize(400,400);
	pack();
	setVisible(true);
		
}

public void clientChat(String uname) {
	try {	
		s = new Socket(serverAdd, serverSocket.PORT);
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		ThreadClient ct = new ThreadClient (dis, this);
		Thread t1 = new Thread(ct);
		t1.start();
		dos.writeUTF(uname);
	
	} catch (Exception e) {
		msgbox.append("\nClient Constructor " + e);
	}
	btnlogout.setEnabled(true);
	btnLogin.setEnabled(false);
}
public void logoutSession() {
	if (s == null)
		return;
	try {
		dos.writeUTF(serverSocket.LOGOUT_MESSAGE);
		Thread.sleep(500);
		s = null;
	} catch (Exception e) {
		msgbox.append("\n inside logoutSession Method" + e);
	}

	btnlogout.setEnabled(false);
	btnLogin.setEnabled(true);
	
}
public void actionPerformed(ActionEvent ev) {
	JButton temp = (JButton) ev.getSource();
	if (temp == btnSend) {
		if (s == null) {
			JOptionPane.showMessageDialog(null,"Login First");
			return;
		}
		try {
			dos.writeUTF(text1.getText());
			text1.setText("");
		} catch (Exception excp) {
			msgbox.append("\nsend button click :" + excp);
		}
	}
	if (temp == btnLogin) {
//		user=usernameText.getText();
//		pass=String.valueOf(password.getPassword());
		/*try {
			sqlobj=new MysqlCon();
			boolean flag = sqlobj.checkuser(user, pass);
			if (flag)
				clientChat(user);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try {	
			user=usernameText.getText();
			pass=String.valueOf(password.getPassword());
			s = new Socket(serverAdd, serverSocket.PORT);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(user);
			dos.writeUTF(pass);
			ThreadClient ct = new ThreadClient (dis, this);
			Thread t1 = new Thread(ct);
			t1.start();
			dos.writeUTF(user);
		
		} catch (Exception e) {
			msgbox.append("\nClient Constructor " + e);
		}
		
		
		
	}
	if (temp == btnlogout) {
		if (s != null)
			logoutSession();
	}
	
	if(temp == btnRegister){
		user=usernameText.getText();
		pass=String.valueOf(password.getPassword());
		MysqlCon sqlobj1 = new MysqlCon();
		sqlobj1.addUser(user, pass);
		JOptionPane.showMessageDialog(null, "Registered Succesfully");
		return;
	}
	
	}
public static void main(String[] args) {
	serverAdd = JOptionPane.showInputDialog(null,"Enter Server Address");
	gui My = new gui();
}

}

