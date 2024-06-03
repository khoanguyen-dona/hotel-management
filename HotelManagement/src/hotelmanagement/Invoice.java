package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;


public class Invoice extends JFrame implements ActionListener{

    JTable table;
    JButton back;
    Invoice(){
        setBounds(300,200,1400,600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel invoice = new JLabel ("Invoice");
        invoice.setFont(new Font("serif",Font.BOLD,20));
        invoice.setBounds(600,10,100,20);
        add(invoice);

        JLabel id = new JLabel ("Id");
        id.setBounds(5,70,100,20);
        add(id);

        JLabel user_name = new JLabel ("Username");
        user_name.setBounds(140,70,100,20);
        add(user_name);

        JLabel user_id = new JLabel ("User Id");
        user_id.setBounds(280,70,100,20);
        add(user_id);

        JLabel room_number = new JLabel ("Room Number");
        room_number.setBounds(420,70,100,20);
        add(room_number);

        JLabel room_type = new JLabel ("Room type");
        room_type.setBounds(560,70,100,20);
        add(room_type);

        JLabel checkin_time = new JLabel ("Checkin time");
        checkin_time.setBounds(700,70,100,20);
        add(checkin_time);

        JLabel checkout_time = new JLabel ("Checkout time");
        checkout_time.setBounds(840,70,100,20);
        add(checkout_time);

        JLabel time_stay = new JLabel ("Time stay");
        time_stay.setBounds(980,70,100,20);
        add(time_stay);

        JLabel room_price = new JLabel ("Room price");
        room_price.setBounds(1120,70,100,20);
        add(room_price);

        JLabel total_price = new JLabel ("Total price");
        total_price.setBounds(1260,70,100,20);
        add(total_price);

        table = new JTable();
        table.setBounds(0,100,1400,400);
        add(table);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from invoice ");
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
        new Invoice();
    }
}
