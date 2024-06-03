package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
//import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = date.format(myFormatObj);

        lbl_checkout_time = new JLabel(""+formattedDate);
        lbl_checkout_time.setBounds(150,380,200,30);
        add(lbl_checkout_time);
               
        JLabel lbltimestay = new JLabel("Time stay");
        lbltimestay.setBounds(30,430,150,30);
        add(lbltimestay);       
        time_stay = new JLabel();
        time_stay.setBounds(150,430,200,30);
        add(time_stay);
              
        JLabel lbltotalprice = new JLabel("Total price (VNĐ)");
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
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String room_number = croom.getSelectedItem();
            try {
                Conn c = new Conn();
                ResultSet rs1 = c.s.executeQuery("select * from room where room_number = '" + room_number + "' ");
                while (rs1.next()) {
                    String user_id = rs1.getString("user_id");
                    customer_id.setText(rs1.getString("user_id"));
                    customer_name.setText(rs1.getString("user_name"));
                    room_price.setText(rs1.getString("price"));
                    bed_type.setText(rs1.getString("bed_type"));
                    ResultSet rs2 = c.s.executeQuery("select * from customer where number = '" + user_id + "' ");
                    while (rs2.next()) {
                        checkin_time.setText(rs2.getString("checkin_time"));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String s_time_stay = null;
            try {

                Date d1 = sdf.parse(checkin_time.getText());
                Date d2 = sdf.parse(lbl_checkout_time.getText());

                long difference_In_Time
                        = d2.getTime() - d1.getTime();

                long difference_In_Minutes
                        = TimeUnit
                        .MILLISECONDS
                        .toMinutes(difference_In_Time)
                        % 60;

                long difference_In_Hours
                        = TimeUnit
                        .MILLISECONDS
                        .toHours(difference_In_Time)
                        % 24;

                long difference_In_Days
                        = TimeUnit
                        .MILLISECONDS
                        .toDays(difference_In_Time)
                        % 365;


                System.out.print(
                        "Difference"
                                + " between two dates is: ");

                // Print result
                System.out.println(
                        difference_In_Days
                                + " days, "
                                + difference_In_Hours
                                + " hours, "
                                + difference_In_Minutes
                                + " minutes, ");

                String my_time_stay = difference_In_Days+" days, "+difference_In_Hours+" hours, "+difference_In_Minutes+" minutes.";
                time_stay.setText(my_time_stay);
                int my_room_price = Integer.parseInt(room_price.getText());

                if(difference_In_Days >=1 ) {
                    int my_total_price = (int) ((1 + difference_In_Days) * my_room_price);
                    total_price.setText(String.valueOf(my_total_price));
                }
                if(difference_In_Days == 0 ) {
                    if (difference_In_Hours < 2 ) {
                        int my_total_price = (int) (0.4 * my_room_price);
                        total_price.setText(String.valueOf(my_total_price));
                    }
                    else{
                        int my_total_price = (int) ((1 + difference_In_Days) * my_room_price);
                        total_price.setText(String.valueOf(my_total_price));
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == checkout) {
            String room_chosen = croom.getSelectedItem();
            String checkout_time = lbl_checkout_time.getText();
            String customer_number = customer_id.getText();
            String q_room_price=room_price.getText();
            String q_total_price=total_price.getText();
            String q_time_stay=time_stay.getText();
            try {
                System.out.println("check in time la "+checkin_time.getText());
                System.out.println("check out time la "+checkout_time);
                System.out.println("room chosen  la "+room_chosen);
                System.out.println("customer_number  la "+customer_number);
                System.out.println("Room price la "+q_room_price);
                System.out.println("total price la :"+q_total_price);
                Conn conn = new Conn();
                String query1 = "delete from customer where number = '"+customer_number+"' ";
                String query2 = "update room set availability ='Available',cleaning_status='Dirty',user_id='"+null+"',user_name='"+null+"' where room_number = '"+room_chosen+"' ";
                String query3 = "update invoice set checkout_time = '"+checkout_time+"',room_price='"+q_room_price+"',total_price='"+q_total_price+"',time_stay='"+q_time_stay+"'" +
                        " where user_id='"+customer_number+"' ";

                 conn.s.executeUpdate(query1);
                 conn.s.executeUpdate(query2);
                 conn.s.executeUpdate(query3);




                JOptionPane.showMessageDialog(null, "Checkout done");
                setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (ae.getSource() == back){
                setVisible(false);
                new Reception();
        }
    }


    
    public static void main(String[] args){
        new Checkout();
    }
}
