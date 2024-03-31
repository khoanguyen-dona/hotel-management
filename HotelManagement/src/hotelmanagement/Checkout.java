package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;


public class Checkout extends JFrame implements ActionListener {
    Choice croom;
    JLabel checkin_time,customer_id,customer_name,room_price,time_stay,total_price,lbl_checkout_time,bed_type;
    
    JButton checkout,back,check;
    Checkout() {
        setBounds(300,200,400,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblCheckout = new JLabel("Checkout");
        lblCheckout.setBounds(30,20,100,30);
        lblCheckout.setForeground(Color.BLACK);
        lblCheckout.setFont(new Font("serif",Font.PLAIN,20));
        add(lblCheckout);
       
        
        JLabel lblroomnumber = new JLabel("Room number");
        lblroomnumber.setBounds(30,80,100,30);
        add(lblroomnumber);         
        croom = new Choice();
        croom.setBounds(150,85,150,30);
        add(croom);
              
        JLabel lblroomtype = new JLabel("Room type");
        lblroomtype.setBounds(30,130,100,30);
        add(lblroomtype);       
        bed_type = new JLabel();
        bed_type.setBounds(150,130,230,30);
        add(bed_type);
                     
        JLabel lblroomprice = new JLabel("Unit price (VNĐ)");
        lblroomprice.setBounds(30,180,100,30);
        add(lblroomprice);      
        room_price = new JLabel();
        room_price.setBounds(150,180,230,30);
        add(room_price);
               
        JLabel lblcustomerid = new JLabel("Customer Id");
        lblcustomerid.setBounds(30,230,100,30);
        add(lblcustomerid);       
        customer_id = new JLabel();
        customer_id.setBounds(150,230,230,30);
        add(customer_id);
             
        JLabel lblcustomername = new JLabel("Customer Name");
        lblcustomername.setBounds(30,280,100,30);
        add(lblcustomername);     
        customer_name = new JLabel();
        customer_name.setBounds(150,280,230,30);
        add(customer_name);
              
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30,330,100,30);
        add(lblcheckin);       
        checkin_time = new JLabel();
        checkin_time.setBounds(150,330,230,30);
        add(checkin_time);
               
        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30,380,150,30);
        add(lblcheckout);       
        Date date = new Date();
        lbl_checkout_time = new JLabel(""+date);
        lbl_checkout_time.setBounds(150,380,200,30);
        add(lbl_checkout_time);
               
        JLabel lbltimestay = new JLabel("Time stay");
        lbltimestay.setBounds(30,430,150,30);
        add(lbltimestay);       
        time_stay = new JLabel();
        time_stay.setBounds(150,430,200,30);
        add(time_stay);
              
        JLabel lbltotalprice = new JLabel("Total price");
        lbltotalprice.setBounds(30,480,150,30);
        add(lbltotalprice);     
        total_price = new JLabel();
        total_price.setBounds(150,480,200,30);
        add(total_price);
              
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30,530,90,30);
        check.addActionListener(this);
        add(check);
        
        checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBounds(135,530,90,30);
        checkout.addActionListener(this);
        add(checkout);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.setBounds(240,530,90,30);
        back.addActionListener(this);
        add(back);
        
         try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            while(rs.next()){
                
                croom.add(rs.getString("room_number"));
                
            }
            
            
                      
        }catch(Exception e){
            e.printStackTrace();
        }
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == check){
            String room_number = croom.getSelectedItem();
            try{
           
                Conn c = new Conn();
                ResultSet rs1 = c.s.executeQuery("select * from room where room_number = '"+room_number+"' ");
                while(rs1.next()){
                    String user_id = rs1.getString("user_id");
                    customer_id.setText(rs1.getString("user_id"));                   
                    customer_name.setText(rs1.getString("user_name"));
                    room_price.setText(rs1.getString("price"));
                    bed_type.setText(rs1.getString("bed_type"));
                    ResultSet rs2 = c.s.executeQuery("select * from customer where number = '"+user_id+"' ");
                    while(rs2.next()){
                        checkin_time.setText(rs2.getString("checkin_time"));
                    }
                }
                
                
            }catch(Exception e){
                e.printStackTrace();
            }                            
        }else if(ae.getSource() == checkout){
            String room_chosen = croom.getSelectedItem();
            String checkout_time=lbl_checkout_time.getText();
            String customer_number = customer_id.getText();
            String query1 = "delete from customer where number = '"+customer_number+"'  " ;
            String query2 = "update room set availability ='Available',cleaning_status='Dirty',user_id='"+null+"',user_name='"+null+"' where room_number = '"+room_chosen+"' " ;
            String query3 = "update invoice set checkout_time = '"+checkout_time+"' ";
            
            try{
                Conn c = new Conn();
                c.s.executeQuery(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                
                JOptionPane.showMessageDialog(null,"Checkout done");
                setVisible(false);
                new Reception();                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main(String[] args){
        new Checkout();
    }
}
