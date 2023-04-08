import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtBname;
	private JTextField txtEdition;
	private JTextField txtPrice;
	private JTextField txtBookID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	//private JTable table_1;
	
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost/bookdb", "anish","anish");
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void table_load() {
		
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 702, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(238, 11, 169, 57);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 59, 293, 169);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 27, 88, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(20, 67, 88, 29);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(20, 107, 88, 29);
		panel.add(lblNewLabel_1_2);
		
		txtBname = new JTextField();
		txtBname.setBounds(118, 33, 137, 20);
		panel.add(txtBname);
		txtBname.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setBounds(118, 73, 137, 20);
		panel.add(txtEdition);
		txtEdition.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(118, 113, 137, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 239, 97, 36);
		frame.getContentPane().add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition,price;
				
				bname = txtBname.getText();
				edition = txtEdition.getText();
				price = txtPrice.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name, edition, price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Addedddddd!!!!!!!!");
					table_load();
					
					txtBname.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					txtBname.requestFocus();
				}  catch (SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(115, 239, 92, 36);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBname.setText("");
				txtEdition.setText("");
				txtPrice.setText("");
				txtBookID.setText("");
				txtBname.requestFocus();
			}
		});
		btnClear.setBounds(211, 239, 92, 36);
		frame.getContentPane().add(btnClear);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 286, 293, 85);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 29, 68, 29);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtBookID = new JTextField();
		txtBookID.setBounds(91, 35, 168, 20);
		panel_1.add(txtBookID);
		txtBookID.setColumns(10);
		
		txtBookID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String id = txtBookID.getText();
					pst = con.prepareStatement("select name, edition, price from book where BookID = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true)
					{
						
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtBname.setText(name);
						txtEdition.setText(edition);
						txtPrice.setText(price);
						
					}
					else
					{
						txtBname.setText("");
						txtEdition.setText("");
						txtPrice.setText("");
					}
				
				}
				catch(SQLException ex){
					
				}
			}
		});

		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(367, 286, 155, 49);
		frame.getContentPane().add(btnUpdate);
		
		btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				String bname, edition,price,bid;
				
				bname = txtBname.getText();
				edition = txtEdition.getText();
				price = txtPrice.getText();
				bid = txtBookID.getText();
				
				try {
					pst = con.prepareStatement("update book set name= ?, edition= ?, price= ? where BookID= ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated!!!");
					table_load();
					
					txtBname.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					txtBookID.setText("");
					txtBookID.requestFocus();
				} catch (SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				
				
				bid = txtBookID.getText();
				
				try {
					pst = con.prepareStatement("delete from book where BookID= ?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Deleted!!!");
					table_load();
					
					txtBname.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					txtBookID.setText("");
					txtBookID.requestFocus();
				} catch (SQLException e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(534, 286, 142, 49);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(339, 64, 337, 207);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//table.setBounds(339, 266, 337, -198);
		//frame.getContentPane().add(table);
		
		//table_1 = new JTable();
		scrollPane.setViewportView(table);
	
		
	}
}
