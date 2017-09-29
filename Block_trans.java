/**
 * @(#)Block_trans.java
 *
 *
 * @author 
 * 
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Block_trans extends JFrame implements ActionListener{
	
	JTextField txtuser=new JTextField(25);
	JPasswordField txtpass=new JPasswordField(25);
	JLabel lbluser=new JLabel("User Name: ");
	JLabel lblpass=new JLabel("Pin Number");
	JButton btnOk=new JButton("Back to Menu");
	JButton btnRegister=new JButton("Block");
	

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public Block_trans(){
		
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
		pane.add(txtpass);
		pane.add(lbluser);
		pane.add(lblpass);	
		pane.add(btnOk);
		pane.add(btnRegister);
		//-----Setting the location of the components
		lbluser.setBounds(10,20,80,20);
		lblpass.setBounds(10,40,80,20);
		txtuser.setBounds(100,20,100,20);
		txtpass.setBounds(100,40,100,20);
		btnOk.setBounds(80,120,140,20);
		btnRegister.setBounds(100,70,100,20);
		//-----Adding the an actionlistener to the buttons
		btnOk.addActionListener(this);
		btnRegister.addActionListener(this);
		
	
			btnOk.setToolTipText("Click To Back to Menu");
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
		lbl.setBounds(0,0,250,200);
		pane.add(lbl);

		
		//connection
				
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn = DriverManager.getConnection("jdbc:odbc:project");
		}catch(ClassNotFoundException e)  {
 			System.err.println("Failed to load driver");
 			e.printStackTrace();
 	
 		}catch(SQLException e){
 			System.err.println("Unable to connect");
 			e.printStackTrace();
 			
 		}
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnOk){
	third_atm panel = new third_atm();
	panel.setSize(330,300);
	panel.setVisible(true);
	panel.setResizable(false);
	panel.setLocation(200,200);
	dispose();				
						
						
			}else if(source==btnRegister){
			try{
				
				
				st=cn.createStatement();
				String strUser="";
				String strPass="";
				
				ResultSet rs=st.executeQuery("SELECT * FROM tbl_list WHERE UserName='"+txtuser.getText()+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(5);
				}
				
				if(strUser.equals(txtuser.getText())){
					if(strPass.equals(txtpass.getText())){
						
						ps = cn.prepareStatement("DELETE FROM tbl_list WHERE UserName ='"+ txtuser.getText() + "'");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"Your Account has been successfully Block.","ATM",JOptionPane.INFORMATION_MESSAGE);
						txtuser.requestFocus(true);
					//	clear();
					//	st.close();
					txtuser.setText("");
					txtpass.setText("");
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the Pin Number is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txtpass.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					txtuser.requestFocus(true);
				}
					}
					catch(SQLException s){
					System.out.print("SQL statement is not executed!");
				}
						catch(Exception j){
						j.printStackTrace();
				}
		}
	}
	
	public static void main(String[]args){
		Block_trans log=new Block_trans();
		
		log.setLocation(400,250);
		log.setSize(250,200);
		log.setTitle("Blocking Account");
		log.setResizable(false);
		log.setVisible(true);
		
	
}
}