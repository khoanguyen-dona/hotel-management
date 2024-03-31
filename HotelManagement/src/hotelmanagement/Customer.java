package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;


public class Customer extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    Customer(){
        setBounds(300,200,1600,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
               
        JLabel employee = new JLabel ("Customer");
        employee.setBounds(10,0,100,20);
        employee.setFont(new Font("serif",Font.BOLD,20));
        add(employee);
        
        JLabel id_type = new JLabel ("Id_type");
        id_type.setBounds(0,50,100,20);
        add(id_type);
        
        JLabel number = new JLabel ("Number");
        number.setBounds(200,50,100,20);
        add(number);
        
        JLabel name = new JLabel ("Name");
        name.setBounds(400,50,100,20);
        add(name);
        
        JLabel gender = new JLabel ("Gender");
        gender.setBounds(600,50,100,20);
        add(gender);
        
        JLabel country = new JLabel ("Country");
        country.setBounds(800,50,100,20);
        add(country);
        
        JLabel room = new JLabel ("Room");
        room.setBounds(1000,50,100,20);
        add(room);
        
        JLabel checkin_time = new JLabel ("Checkin time");
        checkin_time.setBounds(1200,50,100,20);
        add(checkin_time);
        
        JLabel checkout_time = new JLabel ("Checkout time");
        checkout_time.setBounds(1400,50,100,20);
        add(checkout_time);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(650,500,120,30);
        add(back);
        
        table = new JTable();
        table.setBounds(0,90,1600,600);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
  
   
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == back){
            setVisible(false);
            new Reception();
            
        }
    }
    
    public static void main (String[] args){
        new Customer();
    }
}
