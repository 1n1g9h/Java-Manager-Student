package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

public class Log extends JFrame {

	private JPanel FormLogin;
	private JTextField txtusername;
	protected JFrame frmLoginSystem;
	private final JTextField authuser = new JTextField();
	private JTextField authadmin;
	private JTextField authmanager;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
					Log frame = new Log();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Log() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		FormLogin = new JPanel();
		FormLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(FormLogin);
		FormLogin.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblLogin.setBounds(182, 29, 95, 40);
		FormLogin.add(lblLogin);
		
		JLabel lbusername = new JLabel("Username: ");
		lbusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbusername.setBounds(62, 93, 83, 27);
		FormLogin.add(lbusername);
		
		JLabel lbpassword = new JLabel("Password:");
		lbpassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbpassword.setBounds(62, 145, 83, 14);
		FormLogin.add(lbpassword);
		
		txtusername = new JTextField();
		txtusername.setBounds(169, 94, 150, 23);
		FormLogin.add(txtusername);
		txtusername.setColumns(10);
		
		JButton btsigin = new JButton("Sign-in");
		btsigin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btsigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtpassword.getText();
				String userName = txtusername.getText();
				String auth = authadmin.getText();
				String auth1 = authuser.getText();
				String auth2 = authmanager.getText();
				
				//String auth2 = authManager.getText();
;				UsersBUS usBUS = new UsersBUS();
				try {
				
					if(usBUS.getLogin(userName, password,auth)) {
						//dispose();//stop program
						//frame.dispose();//close windows
					    //frame.setVisibale(false);
						setVisible(false);
						dispose();
						MenuAdmin info = new MenuAdmin();
						info.main(null);			
					}
					
					else if(usBUS.getLogin(userName, password,auth1)) 
						{
							//dispose();//stop program
							//frame.dispose();//close windows
						    //frame.setVisibale(false);
							setVisible(false);
							dispose();
							Major major = new Major();
							major.main(null);	
						}
	
					
					else if(usBUS.getLogin(userName, password,auth2)) 
						{
							//dispose();//stop program
							//frame.dispose();//close windows
						    //frame.setVisibale(false);
							setVisible(false);
							dispose();
							Manager manager = new Manager();
							manager.main(null);	
						}
					
	
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		btsigin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btsigin.setBounds(105, 196, 89, 23);
		FormLogin.add(btsigin);
		
		JButton btexit = new JButton("Exit");
		btexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Are you sure Exit","Login System",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}		
		});
		btexit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btexit.setBounds(230, 196, 89, 23);
		FormLogin.add(btexit);
		authuser.setText("user");
		authuser.setBounds(343, 63, 38, -45);
		FormLogin.add(authuser);
		authuser.setColumns(10);
		
		authadmin = new JTextField();
		authadmin.setText("admin");
		authadmin.setBounds(385, 17, -36, 6);
		FormLogin.add(authadmin);
		authadmin.setColumns(10);
		
		authmanager = new JTextField();
		authmanager.setText("manager");
		authmanager.setBounds(330, 69, -23, -37);
		FormLogin.add(authmanager);
		authmanager.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(170, 141, 149, 23);
		FormLogin.add(txtpassword);
		
		
		
	}
}
