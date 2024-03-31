package hotelmanagement;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class UpdateRoom extends JFrame implements ActionListener{
    
    Choice croom;
    
    JButton check,update,back;
    JComboBox comboAvail,comboStatus;
    JTextField user_id,user_name;
    
    UpdateRoom() {
        getContentPane().setBackground(Color.WHITE);
        setBounds(300,200,500,500);
        setLayout(null);
               
        JLabel updateStatus = new JLabel("Update Room");
        updateStatus.setFont(new Font("serif",Font.PLAIN,20));
        updateStatus.setBounds(90,20,200,30);
        updateStatus.setForeground(Color.BLACK);
        add(updateStatus);
        
        JLabel lblroomnumber = new JLabel("Room number");
        lblroomnumber.setBounds(30,80,100,20);
        add(lblroomnumber);
        
        croom = new Choice();
        croom.setBounds(200,80,150,25);
        add(croom);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            while(rs.next()){
                croom.add(rs.getString("room_number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
   
        
        JLabel lblAvail = new JLabel("Availability");
        lblAvail.setBounds(30,120,100,20);
        add(lblAvail);
        
        String optionsAvail[] = {"Available","Occupied"};
        comboAvail = new JComboBox(optionsAvail);
        comboAvail.setBounds(200,120,150,25);
        comboAvail.setBackground(Color.WHITE);
        add(comboAvail);
        
        JLabel lblStatus = new JLabel("Cleaning status");
        lblStatus.setBounds(30,160,100,20);
        add(lblStatus);
        
        String optionsStatus[] = {"Cleaned","Dirty"};
        comboStatus = new JComboBox(optionsStatus);
        comboStatus.setBounds(200,160,150,25);
        comboStatus.setBackground(Color.WHITE);
        add(comboStatus);
        
        JLabel lblUserID = new JLabel("User ID");
        lblUserID.setBounds(30,200,100,20);
        add(lblUserID);
        
        user_id = new JTextField();
        user_id.setBounds(200,200,150,25);
        add(user_id);
        
        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setBounds(30,240,100,20);
        add(lblUserName);
               
        user_name = new JTextField();
        user_name.setBounds(200,240,150,25);
        add(user_name);
        
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30,380,100,30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150,380,100,30);
        update.addActionListener(this);

        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.setBounds(280,380,100,30);
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
    }
    
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == check){
            String room_number=croom.getSelectedItem();
            try{               
            Conn c = new Conn();
              
            ResultSet rs2 = c.s.executeQuery("select * from room where room_number = '"+room_number+"' ");
            while(rs2.next()){
                comboAvail.setSelectedItem(rs2.getString("availability"));
                comboStatus.setSelectedItem(rs2.getString("cleaning_status"));
                user_id.setText(rs2.getString("user_id"));
                user_name.setText(rs2.getString("user_name"));
            }
            }catch(Exception e){
                e.printStackTrace(); 
            }
        }else if(ae.getSource() == update){

            String room_number = croom.getSelectedItem();
            String available =(String) comboAvail.getSelectedItem();
            String status =(String) comboStatus.getSelectedItem();
            String userId = user_id.getText();
            String userName = user_name.getText();
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update room set availability = '"+available+"',cleaning_status = '"+status+"',user_id='"+userId+"',user_name='"+userName+"' "
                        +" where room_number = '"+room_number+"'  ");
                c.s.executeUpdate("update customer set number = '"+userId+"',name='"+userName+"' where room='"+room_number+"'   ");
                JOptionPane.showMessageDialog(null,"Data updated successfully");
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main(String[] args){
        new UpdateRoom();
    }
}
