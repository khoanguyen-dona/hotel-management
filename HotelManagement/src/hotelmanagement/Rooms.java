package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;


public class Rooms extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    Rooms(){
        setBounds(300,200,1400,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel roomnumber = new JLabel ("Room Number");
        roomnumber.setBounds(0,10,100,20);
        add(roomnumber);
        
        JLabel availability = new JLabel ("Availability");
        availability.setBounds(200,10,100,20);
        add(availability);
        
        JLabel status = new JLabel ("Cleaning Status");
        status.setBounds(400,10,100,20);
        add(status);
        
        JLabel price = new JLabel ("Price");
        price.setBounds(600,10,100,20);
        add(price);
        
        JLabel bedtype = new JLabel ("Bed Type");
        bedtype.setBounds(800,10,100,20);
        add(bedtype);
        
        JLabel user_id = new JLabel ("User ID");
        user_id.setBounds(1000,10,100,20);
        add(user_id);
        
        JLabel user_name = new JLabel ("User name");
        user_name.setBounds(1200,10,100,20);
        add(user_name);
        
        table = new JTable();
        table.setBounds(0,40,1400,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room ");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(600,500,120,30);
        add(back);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == back){
            setVisible(false);
            new Reception();
            
        }
    }
    
    public static void main (String[] args){
        new Rooms();
    }
}
