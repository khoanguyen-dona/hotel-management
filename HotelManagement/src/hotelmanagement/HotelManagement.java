
package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelManagement extends JFrame implements ActionListener {
    
    HotelManagement (){
//        setSize(1600,800);
//        setLocation(100,100);
        setBounds(100,100,1600,800);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/hotel5.jpg"));
        Image i2=i1.getImage().getScaledInstance(1600,800,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1600,800);
        add(image);
        
        JLabel text= new JLabel("QUẢN LÝ KHÁCH SẠN");
        text.setBounds(10,680,1000,90);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif",Font.PLAIN,50));
        image.add(text);
        
        JButton next= new JButton("Next");
        next.setBounds(1400,700,150,50);
        next.setBackground(Color.red);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        next.setFont(new Font("serif",Font.BOLD,24));
        add(next);
        
        setVisible(true);
  
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Login();
    }
    public static void main(String[] args) {
        new HotelManagement();
    }
    
}
