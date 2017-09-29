/**
 * @(#)second_atm.java
 *
 *
 * @author 
 * 
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

 public class second_atm extends JFrame implements ActionListener{
	
	
	public static void main(String[]args){
	second_atm panel = new second_atm();
	panel.setSize(370,400);
	panel.setVisible(true);
	panel.setResizable(false);
	panel.setLocation(400,250);
	}
	
	
	Font f1 = new Font("", Font.BOLD, 10);
	JLabel lblUser = new JLabel("User Name ",JLabel.RIGHT);	
	JLabel lblFName = new JLabel("First Name ",JLabel.RIGHT);
	JLabel lblVPass = new JLabel("Verify Pin Number ",JLabel.RIGHT);
	JLabel lblLName = new JLabel("Last Name ",JLabel.RIGHT);
	JLabel lblPass = new JLabel("Pin Number ",JLabel.RIGHT);
	JLabel lblBday = new JLabel("B-Day ",JLabel.RIGHT);
	JLabel lblCash = new JLabel("Cash Tender ",JLabel.RIGHT);
	JLabel lblday = new JLabel("Day");
	JLabel lblMonth = new JLabel("Month");
	JLabel lblyer = new JLabel("Year");
	
	JTextField txtUser = new JTextField(20);
	JTextField txtFName= new JTextField(20);
	JTextField txtLName = new JTextField(20);
	JPasswordField txtPass= new JPasswordField(20);
	JPasswordField txtVPass = new JPasswordField(20);
	JTextField txtday = new JTextField(20);
	JTextField txtmonth = new JTextField(20);
	JTextField txtyear = new JTextField(20);
	JTextField txtCash = new JTextField(20);
	
	JButton btnCret = new JButton(new ImageIcon("reg.jpg"));
	
	
	
	Connection cn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	PreparedStatement ps1;


	
	public void clear(){
		
		txtUser.setText("");
		txtFName.setText("");
		txtPass.setText("");
		txtLName.setText("");
		txtVPass.setText("");
	}
	
	public second_atm() {
		super("Project");
	
		JPanel pane = new JPanel();
		pane.setLayout(null);
		
		lblyer.setFont(f1);
		lblday.setFont(f1);
		lblMonth.setFont(f1);
		lblUser.setBounds(5,50,120,25);
		pane.add(lblUser);
		txtUser.setBounds(125,50,150,25);
		pane.add(txtUser);
		
		lblFName.setBounds(5,85,120,25);
		pane.add(lblFName);
		txtFName.setBounds(125,85,150,25);
		pane.add(txtFName);
		
		lblLName.setBounds(5,120,120,25); 
		pane.add(lblLName);
		txtLName.setBounds(125,120,150,25); 
		pane.add(txtLName);
		
		lblPass.setBounds(5,155,120,25);
		pane.add(lblPass);
		txtVPass.setBounds(125,155,150,25);
		pane.add(txtVPass);
		
		lblVPass.setBounds(5,190,120,25);
		pane.add(lblVPass);
		txtPass.setBounds(125,190,150,25); 
		pane.add(txtPass);	
		
		lblBday.setBounds(5,225,120,25);
		pane.add(lblBday);	
		txtday.setBounds(125,225,50,25);
		pane.add(txtday);	
		txtmonth.setBounds(175,225,50,25);
		pane.add(txtmonth);
		txtyear.setBounds(225,225,50,25);
		pane.add(txtyear);
		lblday.setBounds(135,210,70,20);
		pane.add(lblday);
		lblMonth.setBounds(185,210,70,20);
		pane.add(lblMonth);
		lblyer.setBounds(235,210,70,20);
		pane.add(lblyer);
	
			
		lblCash.setBounds(5,275,120,25);
		pane.add(lblCash);
		txtCash.setBounds(125,275,100,25);
		pane.add(txtCash);
			
		btnCret.setBounds(129,310,120,35);
		pane.add(btnCret);
		btnCret.addActionListener(this);
	
	
		JLabel lbl = new JLabel(new ImageIcon("background.gif"));
		
		lbl.setBounds(0,0,370,400);
		pane.add(lbl);
		
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBorder(BorderFactory.createTitledBorder(
           BorderFactory.createEtchedBorder(), "Registration Form"));
		
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn = DriverManager.getConnection("jdbc:odbc:project");
		}catch(ClassNotFoundException e)  {
 			System.err.println("Failed to load driver");
 			e.printStackTrace();
 		}
 		catch(SQLException e){
 			System.err.println("Unable to connect");
 			e.printStackTrace();
 		}
	}

			public void actionPerformed(ActionEvent e){

				Object source = e.getSource();
				if(source == btnCret){
	String suser = 	txtUser.getText();
	String sname =	txtFName.getText();
	String spass = 	txtPass.getText();
	String slname =	txtLName.getText();
	String svpass = txtVPass.getText();

			/*	int cash = Integer.parseInt(txtCash.getText());
			//	int nem = Integer.parseInt(txtFName.getText());
					if(txtPass.getText().equals(txtVPass.getText())){
						if(cash >= 100 && cash <= 50000){
			//		 		if(txtUser.length() == 0)
			//			if(nem>=0 || nem<=0 ){
			//				if(txtLName.equals("")){
					
			//		int	d=Integer.parseInt(txtday.getText());
			//	try{				if(d!=0 && d>0){*/
		
	if((suser.length()==0 || sname.length()==0 || spass.length()==0 || slname.length()==0 || svpass.length()==0 )){
    					JOptionPane.showMessageDialog(null,"Some Fields are empty","WARNING",JOptionPane.WARNING_MESSAGE);
    				}
    else{
    		if(spass.equals(svpass)){
    			boolean xx = false;
    			try{
	
				st= cn.createStatement();	
                rs=st.executeQuery("SELECT * FROM tbl_list WHERE Password = '" + spass + "'");
					
						while(rs.next()){
							String spass2 =rs.getString(4);
							xx = true;
							JOptionPane.showMessageDialog(null,"Pincode already used","Information",JOptionPane.INFORMATION_MESSAGE);
						}	
					st.close();
					
					if(xx==false){
								try{
	
				st= cn.createStatement();
				/*String pass="";
				
					ResultSet rs=st.executeQuery("SELECT * FROM tbl_Info WHERE VPassword = '" + txtPass.getText()+"'");
			 		while(rs.next()){
			 	
			 		pass=rs.getString(8);		
			 				}
			 		
		
			
					if(pass.equals(txtPass.getText())){
						JOptionPane.showMessageDialog(null,"Pin Number Already exist.","ATM",JOptionPane.INFORMATION_MESSAGE);
					
					}else{*/				
						
				ps=cn.prepareStatement("INSERT INTO tbl_list " + " (UserName,FirstName,LastName,Password,VPassword,bday,bmonth,byear,ct) " + " VALUES(?,?,?,?,?,?,?,?,?)");		
				ps.setString(1,txtUser.getText());	
				ps.setString(2,txtFName.getText());
				ps.setString(3,txtLName.getText());
				ps.setString(4,txtPass.getText());
				ps.setString(5,txtVPass.getText());
				ps.setString(6,txtday.getText());
				ps.setString(7,txtmonth.getText());
				ps.setString(8,txtyear.getText());
				ps.setString(9,txtCash.getText());
				
				
				
					
					
				
				
				ps.executeUpdate();
				
			
				JOptionPane.showMessageDialog(null,"Your New Account has been successfully Created.","ATM",JOptionPane.INFORMATION_MESSAGE);
				txtUser.requestFocus(true);
				st.close();
				clear();
				
				first_atm log=new first_atm();
					log.setLocation(400,250);
					log.setSize(250,150);
					log.setTitle("Log-In");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				}
				//}

				catch(SQLException sqlEx){
				JOptionPane.showMessageDialog(null,"General error","ATM",JOptionPane.INFORMATION_MESSAGE);
				}
						
					}
				}
				
				catch(SQLException s){
					System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
									}
				catch(Exception x){
					System.out.println("Error" + x.toString()+" " + x.getMessage());
				
						}
    							

				
				
				}
				else{
					JOptionPane.showMessageDialog(null,"Verify your password.","ATM",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
				
				}	
					
						//		}else{
						//			JOptionPane.showMessageDialog(null,"Invalid Date.","ATM",JOptionPane.INFORMATION_MESSAGE);
						//		}
						//			    	}catch(NumberFormatException b){
	    				//			JOptionPane.showMessageDialog(null,"Invalid Date.","ATM",JOptionPane.INFORMATION_MESSAGE);

    					//		}
			///				}else{	JOptionPane.showMessageDialog(null,"Invalid Name.","ATM",JOptionPane.INFORMATION_MESSAGE);
			//				}
			// 			}else if(txtFName.equals("")){
			// 				JOptionPane.showMessageDialog(null,"Invalid Name.","ATM",JOptionPane.INFORMATION_MESSAGE);
			// 			}
						//else{
							//JOptionPane.showMessageDialog(null,"Maximum cash 50,000 & Minimum 100.","ATM",JOptionPane.INFORMATION_MESSAGE);

						}
						}
 }
 
					//else{
							//JOptionPane.showMessageDialog(null,"Verify your password.","ATM",JOptionPane.INFORMATION_MESSAGE);
	//}	

								
		
		
					
					
  
 
 