
package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashBoard extends JFrame implements ActionListener {
    
    DashBoard(){
        setBounds(0,0,2000,1000);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/hotel5.jpg"));
        Image i2=i1.getImage().getScaledInstance(2000,1000,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,2000,1000);
        add(image);
        
        JLabel text = new JLabel("PHARAON HOTEL WELCOME");
        text.setBounds(600,80,1000,50);
        text.setFont(new Font("serif",Font.BOLD,46));
        text.setForeground(Color.WHITE);
        image.add(text);
        
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0,0,2000,30);
        image.add(mb);
        
        JMenu hotel = new JMenu ("HOTEL MANAGEMENT");
        hotel.setForeground(Color.RED);
        mb.add(hotel);
        
        JMenuItem reception = new JMenuItem("RECEPTION");
        reception.addActionListener(this);
        hotel.add(reception);
        
        JMenu admin = new JMenu ("ADMIN");
        admin.setForeground(Color.BLUE);
        mb.add(admin);
        
        JMenuItem addEmployee = new JMenuItem("ADD EMPLOYEE");
        addEmployee.addActionListener(this);
        admin.add(addEmployee);
        
        JMenuItem addRoom = new JMenuItem("ADD ROOM");
        addRoom.addActionListener(this);
        admin.add(addRoom);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("ADD EMPLOYEE")){
            new AddEmployee();
        }else if (ae.getActionCommand().equals("ADD ROOM")) {
            new AddRoom();
        }else if (ae.getActionCommand().equals("RECEPTION")){
            new Reception();
        }
    }
    
    public static void main(String[] args){
        new DashBoard();
    }
}
