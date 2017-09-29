/**
 * @(#)third_atm.java
 *
 *
 * @author 
 *
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

 public class third_atm extends JFrame implements ActionListener{
	
	
	public static void main(String[]args){
	third_atm panel = new third_atm();
	panel.setSize(330,300);
	panel.setVisible(true);
	panel.setResizable(false);
	panel.setLocation(400,250);
	}
	
	
	

	


	
	JButton btnBals = new JButton("Bal.Inquiry");
	JButton btnDep = new JButton("Deposit");
	JButton btnWid = new JButton("Withdraw");
	JButton btntrans = new JButton("Transfer");
	JButton btnLogOut = new JButton(new ImageIcon("logout.jpg"));
	
	
	Connection cn;
	Statement st;
	PreparedStatement ps;
	




	
	public third_atm() {
		super("ATM");
	
		JPanel pane = new JPanel();
		pane.setLayout(null);
		
	 	
	
	  	
			
		btnBals.setBounds(15,60,100,25);
		pane.add(btnBals);
		btnBals.addActionListener(this);
		btnBals.setForeground(Color.yellow);
		btnBals.setBackground(Color.black);
		
		btntrans.setBounds(15,120,100,25);
		pane.add(btntrans);
		btntrans.addActionListener(this);	
		btntrans.setForeground(Color.yellow);
		btntrans.setBackground(Color.black);
		
		btnDep.setBounds(210,60,100,25);
		pane.add(btnDep);
		btnDep.addActionListener(this);
		btnDep.setForeground(Color.yellow);
		btnDep.setBackground(Color.black);

		btnWid.setBounds(210,120,100,25);
		pane.add(btnWid);
		btnWid.addActionListener(this);	
		btnWid.setForeground(Color.yellow);	
		btnWid.setBackground(Color.black);
		
		btnLogOut.setBounds(125,190,80,25);
		pane.add(btnLogOut);
		btnLogOut.addActionListener(this);
		
		
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
		lbl.setBounds(0,0,330,300);
		pane.add(lbl);
		
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select Transaction"));
		
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
				
				if(source == btnBals){			
				
					Bal_trans log=new Bal_trans();
		
					log.setLocation(400,250);
					log.setSize(250,200);
					log.setTitle("Balance Inquiry");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				}
				if(source == btnDep){
				
					dep_trans log=new dep_trans();
	
					log.setLocation(400,250);
					log.setSize(250,200);
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				}
				if(source == btnWid){
					WDraw_trans log=new WDraw_trans();
		
					log.setLocation(400,250);
					log.setSize(250,200);
					log.setResizable(false);
					log.setVisible(true);

					dispose();
				
				}	
			
				if(source == btntrans){
					transfer_trans log=new transfer_trans();
			
					log.setLocation(400,250);
					log.setSize(500,250);
					log.setResizable(false);
					log.setVisible(true);
					dispose();		
				}
				
		
				if(source == btnLogOut){
					
					int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
					if(n==JOptionPane.YES_OPTION){
					//		JOptionPane.showMessageDialog(null,"Good Bye","ATM",JOptionPane.INFORMATION_MESSAGE);
					//		System.exit(0);
						first_atm log=new first_atm();
		
						log.setLocation(400,250);
						log.setSize(250,150);
						log.setTitle("Log-In");
						log.setResizable(false);
						log.setVisible(true);
						dispose();
						}
				}	
       }
 
 }