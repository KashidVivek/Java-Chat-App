package Client;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import Server.serverSocket;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import Db.MysqlCon;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ClientGui extends JFrame implements ActionListener {
	static ClientGui thegui;
	
	String user,pass;
	Socket s;
	MysqlCon sqlobj;
	
	static JPanel panel0;
	JLabel label0;
	JTextField text0;
	JButton loginbutton;
	JButton regbutton;
	JButton but3;
	JLabel label2;
	JPasswordField area0;
	JButton logoutbutton;
	JTextArea msgbox;
	JTextField text1;
	DataInputStream dis;
	DataOutputStream dos;

	JList usersbox;
	Border border = BorderFactory.createLineBorder(Color.BLACK);

	ClientGui client;
	public static void main(String args[]) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}*/
		thegui = new ClientGui();
		thegui.setContentPane(panel0);
		thegui.setSize(400,400);
		thegui.pack();
		thegui.setVisible(true);
	}
	

	public ClientGui() {
		super("Chat Window");
		panel0 = new JPanel();
		panel0.setBorder(BorderFactory.createTitledBorder(""));
		GridBagLayout gbpanel0 = new GridBagLayout();
		GridBagConstraints gbcpanel0 = new GridBagConstraints();
		panel0.setLayout(gbpanel0);

		label0 = new JLabel("Username:");
		gbcpanel0.gridx = 1;
		gbcpanel0.gridy = 1;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 1;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(label0, gbcpanel0);
		panel0.add(label0);

		text0 = new JTextField();
		gbcpanel0.gridx = 2;
		gbcpanel0.gridy = 1;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 0;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(text0, gbcpanel0);
		panel0.add(text0);

		loginbutton = new JButton("Login");
		gbcpanel0.gridx = 15;
		gbcpanel0.gridy = 1;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 0;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(loginbutton, gbcpanel0);
		panel0.add(loginbutton);

		regbutton = new JButton("Register");
		gbcpanel0.gridx = 17;
		gbcpanel0.gridy = 1;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 0;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(regbutton, gbcpanel0);
		panel0.add(regbutton);

		label2 = new JLabel("Password:");
		gbcpanel0.gridx = 1;
		gbcpanel0.gridy = 3;
		gbcpanel0.gridwidth = 2;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 1;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(label2, gbcpanel0);
		panel0.add(label2);

		area0 = new JPasswordField(10);
		gbcpanel0.gridx = 2;
		gbcpanel0.gridy = 3;
		gbcpanel0.gridwidth = 9;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 1;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(area0, gbcpanel0);
		panel0.add(area0);

		logoutbutton = new JButton("Logout");
		gbcpanel0.gridx = 17;
		gbcpanel0.gridy = 3;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 0;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(logoutbutton, gbcpanel0);
		panel0.add(logoutbutton);
		
		msgbox = new JTextArea(12,10);
		gbcpanel0.gridx = 1;
		gbcpanel0.gridy = 5;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 1;
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbcpanel0.insets = new Insets(4,4,4,4);
		gbpanel0.setConstraints(msgbox, gbcpanel0);
		msgbox.setBorder(border);
		panel0.add(msgbox);

		usersbox = new JList<>();
		gbcpanel0.gridx = 17;
		gbcpanel0.gridy = 5;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 10;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 1;
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbcpanel0.insets = new Insets(4,4,4,4);
		usersbox.setBorder(border);
		//usersbox.setText("Online Users:");
		gbpanel0.setConstraints(usersbox, gbcpanel0);
		panel0.add(usersbox);
		
		//tHIS IS HOW YOU EDIT

		text1 = new JTextField();
		gbcpanel0.gridx = 1;
		gbcpanel0.gridy = 17;
		gbcpanel0.gridwidth = 16;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 0;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(text1, gbcpanel0);
		panel0.add(text1);
		
		but3 = new JButton("Send");
		gbcpanel0.gridx = 17;
		gbcpanel0.gridy = 17;
		gbcpanel0.gridwidth = 1;
		gbcpanel0.gridheight = 1;
		gbcpanel0.fill = GridBagConstraints.BOTH;
		gbcpanel0.weightx = 1;
		gbcpanel0.weighty = 0;
		gbcpanel0.insets = new Insets(2,2,2,2);
		gbcpanel0.anchor = GridBagConstraints.NORTH;
		gbpanel0.setConstraints(but3, gbcpanel0);
		panel0.add(but3);

		loginbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				
				
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		but3.addActionListener(this);
		logoutbutton.addActionListener(this);
		loginbutton.addActionListener(this);
		//exitButton.addActionListener(this);
		regbutton.addActionListener(this);
		logoutbutton.setEnabled(false);
		loginbutton.setEnabled(true);
		text1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent fe) {
				text1.selectAll();
			}
		});
//		setContentPane(panel0);
//		setSize(400,400);
//		pack();
//		setVisible(true);
		
		
		
	}

	public void clientChat(String uname) {
		try {
			
			s = new Socket("localhost", serverSocket.PORT);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			ClientThread ct = new ClientThread(dis, this);
			Thread t1 = new Thread(ct);
			t1.start();
			dos.writeUTF(uname);
		
		} catch (Exception e) {
			msgbox.append("\nClient Constructor " + e);
		}
		logoutbutton.setEnabled(true);
		loginbutton.setEnabled(false);
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

		logoutbutton.setEnabled(false);
		loginbutton.setEnabled(true);
		
	}
	public void actionPerformed(ActionEvent ev) {
		JButton temp = (JButton) ev.getSource();
		if (temp == but3) {
			if (s == null) {
				JOptionPane.showMessageDialog(panel0, "Login First");
				return;
			}
			try {
				dos.writeUTF(text1.getText());
				text1.setText("");
			} catch (Exception excp) {
				msgbox.append("\nsend button click :" + excp);
			}
		}
		if (temp == loginbutton) {
			user=text0.getText();
			pass=String.valueOf(area0.getPassword());
			try {
				sqlobj=new MysqlCon();
				boolean flag = sqlobj.checkuser(user, pass);
				if (flag)
					clientChat(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (temp == logoutbutton) {
			if (s != null)
				logoutSession();
		}
		
		if(temp == regbutton){
			user=text0.getText();
			pass=String.valueOf(area0.getPassword());
			MysqlCon sqlobj1 = new MysqlCon();
			sqlobj1.addUser(user, pass);
			JOptionPane.showMessageDialog(panel0, "Registered Succesfully");
			return;
		}
	}
}
