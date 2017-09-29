/**
 * @(#)transfer_trans.java
 *
 *
 * @author 
 * 
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class transfer_trans extends JFrame implements ActionListener{
	
	JPasswordField txtuser=new JPasswordField(25);
	JTextField txtpass=new JTextField(25);
	JTextField txtwid=new JTextField(25);
	
	JPasswordField txtuser2=new JPasswordField(25);
	JTextField txtpass2=new JTextField(25);
	JTextField txtwid2=new JTextField(25);
	
	JLabel lbluser=new JLabel("Pin Number: ");
	JLabel lblpass=new JLabel("Transfer: ");
	JLabel lblwid=new JLabel("Balanced: ");
	
	JLabel lbluser2=new JLabel("Pin Number: ");
	JLabel lblpass2=new JLabel("Balanced: ");
	JLabel lblwid2=new JLabel("Balanced: ");
	
	JButton btnOk=new JButton("Back to Menu");
	JButton btnRegister=new JButton("Transfer");
	JButton btnRegister2=new JButton("Balanced");
	
//	JLabel lbltransferTo = new JLabel("Transfer to");
	JLabel lblyour = new JLabel("Your Account");
	JLabel lblReciever = new JLabel("Reciever Account");
	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	
	public transfer_trans(){
		super("Transfer Fund Transaction");
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
 		pane.add(txtpass);
 		pane.add(txtwid);
		pane.add(lbluser);
 		pane.add(lblpass);
 		pane.add(lblwid);	
 		pane.add(txtuser2);
 		pane.add(txtpass2);
 		pane.add(txtwid2);
		pane.add(lbluser2);
 		pane.add(lblpass2);
 		pane.add(lblwid2);	
		pane.add(btnOk);
		pane.add(btnRegister);
		pane.add(btnRegister2);
	//	pane.add(lbltransferTo);
		pane.add(lblyour);
		pane.add(lblReciever);
		//-----Setting the location of the components
		lbluser.setBounds(10,40,80,20);
 		lblpass.setBounds(10,60,80,20);
 		lblwid.setBounds(600,80,80,20);
		txtuser.setBounds(100,40,100,20);
 		txtpass.setBounds(600,80,100,20);
 		txtwid.setBounds(100,60,100,20);
 		
 		lbluser2.setBounds(270,40,80,20);
 		lblpass2.setBounds(270,60,80,20);
 		lblwid2.setBounds(670,80,80,20);
		txtuser2.setBounds(350,40,100,20);
 		txtpass2.setBounds(650,80,100,20);
 		txtwid2.setBounds(350,60,100,20);
 		
 	//	lbltransferTo.setBounds(200,10,140,20);
 		lblyour.setBounds(100,10,140,20);
 		lblReciever.setBounds(350,10,140,20);
 		
		btnOk.setBounds(175,140,140,20);
		btnRegister.setBounds(100,110,100,20);
		btnRegister2.setBounds(350,110,100,20);
		//-----Adding the an actionlistener to the buttons
		btnOk.addActionListener(this);
		btnRegister.addActionListener(this);
		btnRegister2.addActionListener(this);
			txtpass.setEditable(false);
			txtpass2.setEditable(false);
			txtwid2.setEditable(false);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		pane.setBorder(BorderFactory.createTitledBorder(
           BorderFactory.createEtchedBorder(), "Transfer Transaction"));
		
		btnRegister.setToolTipText("Click First then Enter Your Money to Transfer");
		btnRegister2.setToolTipText("Click To display your balanced");
		btnOk.setToolTipText("Click To Back to Menu");
		
		
		btnOk.setForeground(Color.yellow);
		btnRegister.setForeground(Color.yellow);
		btnOk.setBackground(Color.black);
		btnRegister.setBackground(Color.black);
		btnRegister2.setBackground(Color.black);
		btnRegister2.setForeground(Color.yellow);
		
		
		lblyour.setForeground(Color.red);
		lblReciever.setForeground(Color.red);
		
		JLabel image = new JLabel(new ImageIcon("newback.jpg"));
		pane.add(image);
		image.setBounds(0,0,500,250);
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
	panel.setLocation(400,250);
	dispose();

		}else if(source==btnRegister){
		//	btnRegister.setText("Transfer");
			try{
				try{
				st= cn.createStatement();	
					ResultSet rs=st.executeQuery("SELECT * FROM tbl_list WHERE Password ='"+txtuser.getText()+"'");
					
						while(rs.next()){
						txtpass.setText(rs.getString(9));
							int a = Integer.parseInt(txtpass.getText());
							int b = Integer.parseInt(txtwid.getText());
							
							if(a<b){
								JOptionPane.showMessageDialog(null,"Insufficient fund","ATM",JOptionPane.INFORMATION_MESSAGE);
								txtwid.setText("");
								}else{
									if(b<10){
										JOptionPane.showMessageDialog(null,"The Minimum cash you can Transfer is 10","ATM",JOptionPane.INFORMATION_MESSAGE);
								
									}else{
							int sum = a-b;
							txtpass.setText((sum+""));
							//txtwid.setText("");
						JOptionPane.showMessageDialog(null,"You Transfer "+b,"ATM",JOptionPane.INFORMATION_MESSAGE);
				ps = cn.prepareStatement("UPDATE tbl_list SET Password = '" + txtuser.getText() + "',ct = '" + sum +  "'WHERE Password = '" + txtuser.getText() + "'");
		
				ps.executeUpdate();
				txtuser.requestFocus(true);
							}
							}
						}
					st.close();
				
				}catch(NumberFormatException nfe){
								JOptionPane.showMessageDialog(null,"Enter now the amount to Transfer","ATM",JOptionPane.INFORMATION_MESSAGE);
							
							}
				
				}catch(SQLException s){
					System.out.println("No record found!\n\n\n");
					System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
									}
				
				catch(Exception x){
					System.out.println("Error" + x.toString()+" " + x.getMessage());
								}
			
				
			
		}else if(source == btnRegister2){
						try{
				try{
				st= cn.createStatement();	
					ResultSet rs2=st.executeQuery("SELECT * FROM tbl_list WHERE Password ='"+txtuser2.getText()+"'");
					
						while(rs2.next()){
						txtpass2.setText(rs2.getString(9));
							int c = Integer.parseInt(txtpass2.getText());
							int d = Integer.parseInt(txtwid.getText());
							int sum = c+d;
							txtwid2.setText((sum+""));
							txtpass.setText("");
							txtwid.setText("");
				//		JOptionPane.showMessageDialog(null,"You Transfer "+d,"ATM",JOptionPane.INFORMATION_MESSAGE);
				ps = cn.prepareStatement("UPDATE tbl_list SET Password = '" + txtuser2.getText() + "',ct = '" + sum +  "'WHERE Password = '" + txtuser2.getText() + "'");
				
				ps.executeUpdate();
				txtuser2.requestFocus(true);
							}
					st.close();
				
				}catch(NumberFormatException nfe){
								JOptionPane.showMessageDialog(null,"Enter now the amount to Transfer","ATM",JOptionPane.INFORMATION_MESSAGE);
							
							}
				
				}catch(SQLException s){
					System.out.println("No record found!\n\n\n");
					System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
									}
				
				catch(Exception x){
					System.out.println("Error" + x.toString()+" " + x.getMessage());
								}
			
			
		}
	}
	
	public static void main(String[]args){
		transfer_trans log=new transfer_trans();
		
		log.setLocation(400,250);
		log.setSize(500,250);
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}