package GUI;

import BUS.*;
import DTO.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class MenuAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtuserid;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JTable tbuser;
	private JTextField txtsearch;
	private JComboBox cbauth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MenuAdmin() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 385, 270);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 326, 385, 270);
		contentPane.add(scrollPane);
		
		tbuser = new JTable();		
		tbuser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMenuAdminDataBinding();
			}
		});
		scrollPane.setViewportView(tbuser);
	
		
		JLabel lblNewLabel = new JLabel("Manager Users");
		lblNewLabel.setBounds(73, 11, 227, 53);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		
		JLabel lblNewLabel_1 = new JLabel("UserID :");
		lblNewLabel_1.setBounds(47, 85, 49, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblUsername = new JLabel("UserName :");
		lblUsername.setBounds(47, 116, 67, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(47, 147, 67, 14);
		panel.add(lblPassword);
		
		txtpassword = new JTextField();
		txtpassword.setBounds(138, 144, 162, 20);
		panel.add(txtpassword);
		txtpassword.setColumns(10);
		
		txtusername = new JTextField();
		txtusername.setBounds(138, 113, 162, 20);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		txtuserid = new JTextField();
		txtuserid.setBounds(138, 82, 162, 20);
		panel.add(txtuserid);
		txtuserid.setColumns(10);
		
		JButton btadd = new JButton("Add");
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            String userName = txtusername.getText();
			            String password = txtpassword.getText();
			            String auth = "Admin";
			            if (adminbus.insertUser(userName,password,auth)) {
			            	JOptionPane.showMessageDialog(null, "Success");
								//OptionPane.showMessageDialog(frame, "Add account complete!");
			                    getUsers();
			                }
			                else JOptionPane.showMessageDialog(null, "Failed");

			        } catch (SQLException ex) { 
			        	System.out.println(ex.getMessage());
			        }
			}
		});
		btadd.setBounds(29, 224, 89, 23);
		panel.add(btadd);
		
		JButton btdelete = new JButton("Delete");
		btdelete.setBounds(142, 224, 89, 23);
		panel.add(btdelete);
		
		JButton btedit = new JButton("Edit");
		btedit.setBounds(256, 224, 89, 23);
		panel.add(btedit);
		
		String auth[] = {"Admin","User","Manager"};
		JComboBox cbauth = new JComboBox(auth);
		cbauth.setBounds(138, 175, 67, 22);
		panel.add(cbauth);
		
		
		JLabel lblAuthirities = new JLabel("Authirities :");
		lblAuthirities.setBounds(47, 179, 67, 14);
		panel.add(lblAuthirities);
		
		txtsearch = new JTextField();
		txtsearch.setBounds(125, 292, 250, 23);
		contentPane.add(txtsearch);
		txtsearch.setColumns(10);
		
		
		
		JButton btshow = new JButton("Show All");
		btshow.setBounds(10, 292, 89, 23);
		contentPane.add(btshow);
		getUsers();
	}
	MenuAdminBUS adminbus = new MenuAdminBUS();

	
	public void getUsers() throws SQLException {
		tbuser.removeAll();
		
		//DefaultTableModel tbModel = new DefaultTableModel();
		
		//table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("MajorID");
		//repaint();
	    
		String cols[] = {"UserID", "UserName","Password","Auth"};
		DefaultTableModel tbModel = new DefaultTableModel();
		tbModel.setColumnIdentifiers(cols);
		ArrayList<DTO.MenuAdmin> adminList = adminbus.getUsers();
		for (DTO.MenuAdmin admin : adminList) {
			Vector row = new Vector();
			row.add(Integer.toString(admin.getUserid()));
			row.add(admin.getUsername());
			row.add(admin.getPasswords());
			row.add(admin.getAuth());
			tbModel.addRow(row);
		}
		tbuser.setModel(tbModel);
	}
	
	public void setMenuAdminDataBinding() {
		int sltRow = tbuser.getSelectedRow();
		txtuserid.setText((String)tbuser.getValueAt(sltRow, 0));
		txtusername.setText((String)tbuser.getValueAt(sltRow, 1));
		txtpassword.setText((String)tbuser.getValueAt(sltRow, 2));
		String auth = (String)tbuser.getValueAt(sltRow, 3);
		cbauth.setSelectedItem(auth);
    }
	
	 private void btnAddAccountActionPerformed(java.awt.event.ActionEvent evt) {
	        try {
	            String userName = txtusername.getText();
	            String password = txtpassword.getText();
	            String auth = null;
	            if (adminbus.insertUser(userName,password,auth)) {
	                    JOptionPane.showMessageDialog(this, "Add account complete!");
	                    getUsers();
	                }
	                else JOptionPane.showMessageDialog(this, "Fail to add account!"); 

	        } catch (SQLException ex) { }
}
	 
	 
}
