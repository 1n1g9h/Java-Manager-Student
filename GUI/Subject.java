package GUI;

import BUS.*;
import DTO.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.MajorBUS;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Subject extends JFrame {

	private JPanel contentPane;
	private JTextField txtsubjectid;
	private JTextField txtsubjectname;
	private JTextField textField;
	private JTable tbsubject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subject frame = new Subject();
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
	public Subject() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 382, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 372, 269);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 90, 22);
		panel.add(menuBar);
		
		JButton btnMenuadmin = new JButton("MenuAdmin");
		menuBar.add(btnMenuadmin);
		
		JLabel lblManage = new JLabel("Manager Subject");
		lblManage.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblManage.setBounds(104, 35, 182, 22);
		panel.add(lblManage);
		
		JLabel lblNewLabel = new JLabel("SubjectID :");
		lblNewLabel.setBounds(32, 79, 69, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SubjectName :");
		lblNewLabel_1.setBounds(32, 125, 89, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Credit :");
		lblNewLabel_2.setBounds(32, 169, 49, 14);
		panel.add(lblNewLabel_2);
		
		JComboBox cbsubject = new JComboBox();
		cbsubject.setBounds(125, 165, 49, 22);
		panel.add(cbsubject);
		cbsubject.addItem("1");
		cbsubject.addItem("2");
		cbsubject.addItem("3");
		
		txtsubjectid = new JTextField();
		txtsubjectid.setBounds(125, 76, 182, 20);
		panel.add(txtsubjectid);
		txtsubjectid.setColumns(10);
		
		txtsubjectname = new JTextField();
		txtsubjectname.setBounds(125, 122, 182, 20);
		panel.add(txtsubjectname);
		txtsubjectname.setColumns(10);
		
		JButton btadd = new JButton("Add");
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
					
					String subjectid = txtsubjectid.getText();
		            String subjectname = txtsubjectname.getText();	
		            String credit = "1";		            
		            if (sjBUS.insertSubject(subjectid,subjectname,credit)) {
		            	JOptionPane.showMessageDialog(null, " Add Subject Success");
							//OptionPane.showMessageDialog(frame, "Add account complete!");
		                    getSubject();
		                }
		                else JOptionPane.showMessageDialog(null, "Add Subject Failed");
					}

		         catch (SQLException ex) { 
		        	//JOptionPane.showMessageDialog(null, "Please Check Again SubjectID or SubjectName");
		        	 System.out.println(ex.getMessage());
				}
			}
		});
		btadd.setBounds(32, 204, 89, 23);
		panel.add(btadd);
		
		JButton btedit = new JButton("Edit");
		btedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

		            String id = txtsubjectid.getText();

		            String subjectid = txtsubjectid.getText();
		            String subjectname = txtsubjectname.getText();
		            String credit = "2";
		            //String status = (String)cbStatusTable.getSelectedItem();

		            if (sjBUS.updateSubject(subjectid,subjectname,credit,id)) {

		            	JOptionPane.showMessageDialog(null, " Edit Subject Success");
						//OptionPane.showMessageDialog(frame, "Add account complete!");
	                    getSubject();
	                }
	                else JOptionPane.showMessageDialog(null, "Edit Subject Failed"); 

		        } catch (SQLException ex) {
		        	 System.out.println(ex.getMessage());

		        }
			}
		});
		btedit.setBounds(135, 204, 89, 23);
		panel.add(btedit);
		
		JButton btdelete = new JButton("Delete");
		btdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 String id = txtsubjectid.getText();
		            if (sjBUS.removeSubject(id)) {

		            	JOptionPane.showMessageDialog(null, " Delete Subject Success");

		                getSubject();

		            }

		            //else JOptionPane.showMessageDialog(null, " Delete Subject Failed");

		        } catch (SQLException ex) { 
		        	System.out.println(ex.getMessage());
		        }
			}
		});
		btdelete.setBounds(239, 204, 89, 23);
		panel.add(btdelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 310, 372, 252);
		contentPane.add(scrollPane);
		
		tbsubject = new JTable();
		tbsubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSubjectDataBinding();
			}
		});
		scrollPane.setViewportView(tbsubject);
		
		JButton btshow = new JButton("Show All");
		btshow.setBounds(0, 280, 89, 23);
		contentPane.add(btshow);
		
		textField = new JTextField();
		textField.setBounds(99, 280, 259, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		getSubject();
	}
	SubjectBUS sjBUS = new SubjectBUS();
	
	public void getSubject() throws SQLException {
		tbsubject.removeAll();
		
		//DefaultTableModel tbModel = new DefaultTableModel();
		
		//table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("MajorID");
		//repaint();
	    
		String cols[] = {"SubjectID", "SubjectName","Credit"};
		DefaultTableModel tbModel = new DefaultTableModel();
		tbModel.setColumnIdentifiers(cols);
		ArrayList<DTO.Subject> subjectList = sjBUS.getSubject();
		for (DTO.Subject sj : subjectList) {
			Vector row = new Vector();
			row.add(sj.getSubjectid());
			row.add(sj.getSubjectname());
			row.add(sj.getCredit());
			tbModel.addRow(row);
		}
		
		tbsubject.setModel(tbModel);
	}
	public void setSubjectDataBinding() {
		int sltRow = tbsubject.getSelectedRow();
		String data = new String();
		txtsubjectid.setText((String)tbsubject.getValueAt(sltRow, 0));
		txtsubjectname.setText((String)tbsubject.getValueAt(sltRow, 1));
	}
}
