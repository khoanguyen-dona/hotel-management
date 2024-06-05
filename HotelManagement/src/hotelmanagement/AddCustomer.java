package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCustomer extends JFrame implements ActionListener{
    
    JComboBox comboid;
    JTextField tfnumber,tfname,tfcountry,tfroom_type;
    JRadioButton rmale,rfemale;
    Choice croom;
    JLabel checkintime;
    JButton add,back;
    
    AddCustomer(){
        setBounds(350,200,500,500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel ("Check in");
        text.setBounds(100,20,300,30);
        text.setFont(new Font("serif",Font.PLAIN,20));
        add(text);
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(35,80,100,20);
        lblid.setFont(new Font("serif",Font.PLAIN,20));
        add(lblid);
        
        String options[] = {"CMND","CAVET XE","PASSPORT"};
        comboid = new JComboBox(options);
        comboid.setBounds(200,80,150,25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(35,120,100,25);
        lblnumber.setFont(new Font("serif",Font.PLAIN,20));
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,160,100,25);
        lblname.setFont(new Font("serif",Font.PLAIN,20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(35,200,100,25);
        lblgender.setFont(new Font("serif",Font.PLAIN,20));
        add(lblgender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200,200,60,25);
        add(rmale);
              
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270,200,60,25);
        add(rfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rmale);
        bg.add(rfemale);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(35,240,100,25);
        lblcountry.setFont(new Font("serif",Font.PLAIN,20));
        add(lblcountry);
                
        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);

        tfroom_type = new JTextField();
        tfroom_type.setVisible(false);
        tfroom_type.setBounds(200,240,150,25);
        add(tfroom_type);
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(35,280,150,25);
        lblroom.setFont(new Font("serif",Font.PLAIN,20));
        add(lblroom);
              
        croom = new Choice();
        croom.setBounds(200,280,150,25);
        try{
            Conn conn =new Conn();
            String query = "select * from room where availability = 'Available'  ";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("room_number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        add(croom);
        
        JLabel lbltime = new JLabel("Checkin Time");
        lbltime.setBounds(35,320,150,25);
        lbltime.setFont(new Font("serif",Font.PLAIN,20));
        add(lbltime);
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = date.format(myFormatObj);


        checkintime = new JLabel("" + formattedDate);
        checkintime.setBounds(200,320,250,25);
        checkintime.setFont(new Font("serif",Font.PLAIN,20));
        add(checkintime);
        
        add = new JButton("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(50,410,120,30);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBackground(Color.gray);
        back.setForeground(Color.WHITE);
        back.setBounds(200,410,120,30);
        back.addActionListener(this);
        add(back);
        
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/hotel5.jpg"));
//        Image i2=i1.getImage().getScaledInstance(800,500,Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(0,0,800,500);
//        add(image);
        
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add){
            String id_type = (String) comboid.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;

            if(rmale.isSelected()){
                gender = "Male";
            }else if (rfemale.isSelected()){
                gender = "Female";
            }
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String checkin_time = checkintime.getText();

            try{
                Conn conn = new Conn();
                String query0 = "select bed_type from room where room_number = '"+room+"' ";
                ResultSet rs1 = conn.s.executeQuery(query0);

                while(rs1.next()){

                    tfroom_type.setText(rs1.getString("bed_type"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            String bed_type = tfroom_type.getText();

            if  (id_type.equals("")){
                JOptionPane.showMessageDialog(null,"id_type should not be empty");
            return;
            }
            try{
                Conn conn = new Conn();
                String query = "insert into customer values('"+id_type+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+checkin_time+"','"+null+"')";
                String query2 = "update room set availability = 'Occupied',user_id = '"+number+"',user_name='"+name+"' where room_number = '"+room+"'  ";
                String query3 = "insert into invoice(user_name,user_id,room_number,room_type,checkin_time)"
                        + "values('"+name+"','"+number+"','"+room+"','"+bed_type+"','"+checkin_time+"') ";
                
                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, " Thêm khách hàng thành công");
                
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if (ae.getSource() == back){
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main(String[] args){
        new AddCustomer();
    }
}
