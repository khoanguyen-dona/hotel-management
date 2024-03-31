package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;


public class Employee extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    Employee(){
        setBounds(300,200,1600,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
               
        JLabel employee = new JLabel ("Employee");
        employee.setBounds(0,0,100,20);
        employee.setFont(new Font("serif",Font.BOLD,20));
        add(employee);
        
        JLabel id = new JLabel ("Id");
        id.setBounds(0,50,100,20);
        add(id);
        
        JLabel name = new JLabel ("Name");
        name.setBounds(200,50,100,20);
        add(name);
        
        JLabel age = new JLabel ("Age");
        age.setBounds(400,50,100,20);
        add(age);
        
        JLabel gender = new JLabel ("Gender");
        gender.setBounds(600,50,100,20);
        add(gender);
        
        JLabel job = new JLabel ("Job");
        job.setBounds(800,50,100,20);
        add(job);
        
        JLabel salary = new JLabel ("Salary");
        salary.setBounds(1000,50,100,20);
        add(salary);
        
        JLabel phone = new JLabel ("Phone");
        phone.setBounds(1200,50,100,20);
        add(phone);
        
        JLabel email = new JLabel ("Email");
        email.setBounds(1400,50,100,20);
        add(email);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(650,500,120,30);
        add(back);
        
        table = new JTable();
        table.setBounds(0,70,1600,600);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee ");
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
        new Employee();
    }
}
