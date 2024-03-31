package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;


public class SearchRoom extends JFrame implements ActionListener{
    
    JTable table;
    JButton back,submit;
    JComboBox bedType;
    JCheckBox available;
    SearchRoom(){
        setBounds(300,200,1400,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
               
        JLabel employee = new JLabel ("Search Room");
        employee.setBounds(10,0,200,20);
        employee.setFont(new Font("serif",Font.BOLD,20));
        add(employee);
        
        JLabel roomnumber = new JLabel ("Room Number");
        roomnumber.setBounds(0,100,100,20);
        add(roomnumber);
        
        JLabel availability = new JLabel ("Availability");
        availability.setBounds(200,100,100,20);
        add(availability);
        
        JLabel cleaning_status = new JLabel ("Cleaning status");
        cleaning_status.setBounds(400,100,100,20);
        add(cleaning_status);
        
        JLabel price = new JLabel ("Price");
        price.setBounds(600,100,100,20);
        add(price);
        
        JLabel bed_type = new JLabel ("Bed Type");
        bed_type.setBounds(800,100,100,20);
        add(bed_type);
        
        JLabel user_id = new JLabel ("User ID");
        user_id.setBounds(1000,100,100,20);
        add(user_id);
        
        JLabel user_name = new JLabel ("User Name");
        user_name.setBounds(1200,100,100,20);
        add(user_name);
        
        available = new JCheckBox ("Only display Available");
        available.setBounds(650,50,150,25);
        available.setBackground(Color.WHITE);
        add(available);
        
        bedType = new JComboBox (new String[]{"Single Bed","Double Bed"} );
        bedType.setBounds(150,50,150,25);
        bedType.setBackground(Color.WHITE);
        add(bedType);
        
        
          
        table = new JTable();
        table.setBounds(0,120,1400,300);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        submit = new JButton("Search");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(550,500,120,30);
        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(750,500,120,30);
        add(back);
   
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == submit ){
            try{
                String query1 = "select * from room where bed_type = '"+bedType.getSelectedItem()+"'  ";
                String query2 = "select * from room where bed_type = '"+bedType.getSelectedItem()+"' and availability = 'Available'  ";
                
                Conn conn = new Conn();
                ResultSet rs;
                
                if(available.isSelected()){
                    rs = conn.s.executeQuery(query2);
                }else {
                    rs = conn.s.executeQuery(query1);
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main (String[] args){
        new SearchRoom();
    }
}
