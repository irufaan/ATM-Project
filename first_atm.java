/**
 * @(#)first_atm.java
 *
 *
 * 
 * 
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class first_atm extends JFrame implements ActionListener{
	
	JTextField txtuser=new JTextField(25);
	JPasswordField txtpass=new JPasswordField(25);
	JLabel lbluser=new JLabel("Username: ");
	JLabel lblpass=new JLabel("Pin Number: ");
	JButton btnOk=new JButton(new ImageIcon("register.jpg"));
	JButton btnRegister=new JButton(new ImageIcon("button.jpg"));
	//JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public first_atm(){
		
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
		pane.add(txtpass);
		pane.add(lbluser);
		pane.add(lblpass);	
		pane.add(btnOk);
		pane.add(btnRegister);
		//pane.add(btnBlock);
		//-----Setting the location of the components
		lbluser.setBounds(10,20,80,20);
		lblpass.setBounds(10,40,80,20);
		txtuser.setBounds(100,20,100,20);
		txtpass.setBounds(100,40,100,20);
		btnOk.setBounds(50,70,75,20);
		btnRegister.setBounds(125,70,83,20);
		//btnBlock.setBounds(55,90,150,20);
		//-----Adding the an actionlistener to the buttons
		btnOk.addActionListener(this);
		btnRegister.addActionListener(this);
		//btnBlock.addActionListener(this);
		
		
		//btnBlock.setForeground(Color.yellow);	
		//btnBlock.setBackground(Color.black);
			

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
		lbl.setBounds(0,0,250,150);
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
			try{
				String str1=txtuser.getText();
				String str2=txtpass.getText();
				if((str1.length()==0 || str2.length()==0)){
    					JOptionPane.showMessageDialog(null,"Connot be Process","WARNING",JOptionPane.WARNING_MESSAGE);
    				}
    				else{
				st=cn.createStatement();
				String strUser="";
				String strPass="";
				
				ResultSet rs=st.executeQuery("SELECT * FROM tbl_list WHERE UserName='"+str1+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(4);
				}
				
				
				
				
				
				st.close();
		
				if(strUser.equals(str1)){
					if(strPass.equals(str2)){
						
					
    					
						JOptionPane.showMessageDialog(null,"Welcome "+txtuser.getText(),"Welcome",JOptionPane.INFORMATION_MESSAGE);
						
						third_atm panel = new third_atm();
						panel.setSize(330,300);
						panel.setVisible(true);
						panel.setResizable(false);
						panel.setLocation(400,250);
						dispose();
						
					
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the password is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txtpass.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					txtuser.requestFocus(true);
				}
    				}	
			}catch(SQLException w){
			}
		}else if(source==btnRegister){
			
				second_atm panel = new second_atm();
				panel.setSize(370,400);
				panel.setVisible(true);
				panel.setResizable(false);
				panel.setLocation(400,250);
				dispose();
		
		}
		
	/*	else if(source == btnBlock){
			
			int n=JOptionPane.showConfirmDialog(null,"If you lock this account,you cannot access this Anymore.Are you sure you want to Lock this account?","Warning",JOptionPane.YES_NO_OPTION);
			if(n==JOptionPane.YES_OPTION){
			
		try{
				
				
				st=cn.createStatement();
				String strUser="";
				String strPass="";
				
				ResultSet rs=st.executeQuery("SELECT * FROM 
				
				 WHERE UserName='"+txtuser.getText()+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(5);
				}
				
				if(strUser.equals(txtuser.getText())){
					if(strPass.equals(txtpass.getText())){
						
						ps = cn.prepareStatement("DELETE FROM tbl_Info WHERE UserName ='"+ txtuser.getText() + "'");
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"Your Account has been successfully Lock.","ATM",JOptionPane.INFORMATION_MESSAGE);
						txtuser.requestFocus(true);
					
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
			}*/
	}
	
	public static void main(String[]args){
		first_atm log=new first_atm();
		
		log.setLocation(400,250);
		log.setSize(250,150);
		log.setTitle("Log-In");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}