package hotelmanagement;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class UpdateCustomer extends JFrame implements ActionListener{
    
    Choice ccustomer;
    JTextField tfidtype,tfroom,tfname,tfcheckin,tfgender,tfcountry;
    JButton check,update,back;
    JComboBox combogender;
    UpdateCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setBounds(300,200,500,500);
        setLayout(null);
               
        JLabel updateStatus = new JLabel("Update Customer");
        updateStatus.setFont(new Font("serif",Font.PLAIN,20));
        updateStatus.setBounds(90,20,200,30);
        updateStatus.setForeground(Color.BLACK);
        add(updateStatus);
        
        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30,80,100,20);
        add(lblid);
        
        ccustomer = new Choice();
        ccustomer.setBounds(200,80,200,25);
        add(ccustomer);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblroom = new JLabel("Room number");
        lblroom.setBounds(30,120,100,20);
        add(lblroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200,120,200,25);
        add(tfroom);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,160,100,20);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,160,200,25);
        add(tfname);
        
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30,200,100,20);
        add(lblcheckin);
        
        tfcheckin = new JTextField();
        tfcheckin.setBounds(200,200,200,25);
        add(tfcheckin);
        
        JLabel lblidtype = new JLabel("ID Type");
        lblidtype.setBounds(30,240,100,20);
        add(lblidtype);
        
        tfidtype = new JTextField();
        tfidtype.setBounds(200,240,200,25);
        add(tfidtype);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(30,280,100,20);
        add(lblgender);
        
//        tfgender = new JTextField();
//        tfgender.setBounds(200,280,200,25);
//        add(tfgender);
        
        String options[] = {"Male","Female"};
        combogender = new JComboBox(options);
        combogender.setBounds(200,280,150,25);
        combogender.setBackground(Color.WHITE);
        add(combogender);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(30,320,100,20);
        add(lblcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200,320,200,25);
        add(tfcountry);
        
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30,380,100,30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(160,380,100,30);
        update.addActionListener(this);

        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.setBounds(300,380,100,30);
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
    }
    
    public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == check){
            String id = ccustomer.getSelectedItem();
            String query = "select * from customer where number = '"+id+"' ";
            try{
                Conn c = new Conn();
                ResultSet rs  = c.s.executeQuery(query);
                while(rs.next()){
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkin_time"));
                    tfidtype.setText(rs.getString("id_type"));
                    combogender.setSelectedItem(rs.getString("gender"));
                    tfcountry.setText(rs.getString("country"));
                }
            }catch(Exception e){
                e.printStackTrace(); 
            }
        }else if(ae.getSource() == update){
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = tfcheckin.getText();
            String idtype = tfidtype.getText();
            String country = tfcountry.getText();
            String gender =(String) combogender.getSelectedItem();
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set id_type = '"+idtype+"',number = '"+number+"',name = '"+name+"',room = '"+room+"',checkin_time = '"+checkin+"'"
                        + ",country = '"+country+"',gender='"+gender+"' where number = '"+number+"'  ");
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
        new UpdateCustomer();
    }
}
