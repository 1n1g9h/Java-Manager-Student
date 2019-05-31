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

public class Major extends JFrame {

	private JPanel contentPane;
	private JTextField txtmajorname;
	private JTextField txtmajorid;
	private JTable tablemajor;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Major frame = new Major();
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
	public Major() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 232, 426, 284);
		contentPane.add(scrollPane);
		
		tablemajor = new JTable();
		tablemajor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMajorDataBinding();
			}
		});
		scrollPane.setViewportView(tablemajor);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 426, 232);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtmajorname = new JTextField();
		txtmajorname.setBounds(174, 138, 179, 20);
		panel.add(txtmajorname);
		txtmajorname.setColumns(10);
		
		txtmajorid = new JTextField();
		txtmajorid.setBounds(174, 82, 179, 20);
		panel.add(txtmajorid);
		txtmajorid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("MajorID :");
		lblNewLabel.setBounds(65, 75, 82, 31);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("MajorName :");
		lblNewLabel_2.setBounds(65, 136, 96, 20);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Manager Major");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_3.setBounds(118, 23, 189, 41);
		panel.add(lblNewLabel_3);
		
		JButton btadd = new JButton("Add");
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String majorid = txtmajorid.getText();
		            String majorname = txtmajorname.getText();		            
		            if (mjBUS.insertMajor(majorid,majorname)) {
		            	JOptionPane.showMessageDialog(null, " Add Major Success");
							//OptionPane.showMessageDialog(frame, "Add account complete!");
		                    getMajor();
		                }
		                else JOptionPane.showMessageDialog(null, "Add Major Failed");
					}

		         catch (SQLException ex) { 
		        	JOptionPane.showMessageDialog(null, "Please Check Again MajorID or MajorName");
		        
				}
			}
			
		});
		btadd.setBounds(65, 189, 70, 23);
		panel.add(btadd);
		
		JButton btedit = new JButton("Edit");
		btedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        try {		        
		            String majorid = txtmajorid.getText();
		            String majorname = txtmajorname.getText();
		            
		            
		            if (mjBUS.updateMajor(majorid,majorname)) {

		            	JOptionPane.showMessageDialog(null, " Update Major Success");
						//OptionPane.showMessageDialog(frame, "Add account complete!");
	                    getMajor();
	                }
	                else JOptionPane.showMessageDialog(null, "Update Major Failed");
				

		        } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Major can't update because it default");}

		    
			}
		});
		btedit.setBounds(174, 189, 70, 23);
		panel.add(btedit);
		
		JButton btcancel = new JButton("Exit");
		btcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure Exit","Login System",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btcancel.setBounds(283, 189, 82, 23);
		panel.add(btcancel);
		
		getMajor();
	}
	
	MajorBUS mjBUS = new MajorBUS();
	
	public void getMajor() throws SQLException {
		tablemajor.removeAll();
		
		//DefaultTableModel tbModel = new DefaultTableModel();
		
		//table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("MajorID");
		//repaint();
	    
		String cols[] = {"Major ID", "Major Name"};
		DefaultTableModel tbModel = new DefaultTableModel();
		tbModel.setColumnIdentifiers(cols);
		ArrayList<DTO.Major> mjList = mjBUS.getMajor();
		for (DTO.Major mj : mjList) {
			Vector row = new Vector();
			row.add(mj.getMajorid());
			row.add(mj.getMajorname());
			tbModel.addRow(row);
		}
		
		tablemajor.setModel(tbModel);
	}
	
	public void setMajorDataBinding() {
		int sltRow = tablemajor.getSelectedRow();
		String data = new String();
		txtmajorid.setText((String)tablemajor.getValueAt(sltRow, 0));
		txtmajorname.setText((String)tablemajor.getValueAt(sltRow, 1));
	}
}
