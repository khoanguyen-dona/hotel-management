
package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login  extends JFrame implements ActionListener {
    
    JTextField username;
    JPasswordField password;
    JButton login,cancel;
    
    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel user = new JLabel("Username");
        user.setBounds(40,20,100,30);
        add(user);
        
        username = new JTextField();
        username.setBounds(150,20,150,30);
        add(username);
      
        JLabel pass = new JLabel("Password");
        pass.setBounds(40,70,100,30);
        add(pass);
        
        password = new JPasswordField();
        password.setBounds(150,70,150,30);
        add(password);
        
        login = new JButton("Login");
        login.setBounds(40,150,120,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(180,150,120,30);
        cancel.setBackground(Color.gray);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);
        
        setBounds(500,200,600,300);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if ( ae.getSource()== login){
            String user = username.getText();
            String pass = password.getText();
            
            try {
                Conn c = new Conn();
                String query = "select * from admin where username = '" + user + "' and password = '" + pass + "'   ";
                ResultSet rs = c.s.executeQuery(query);
                
                if ( rs.next()){
                    setVisible(false);
                    new Reception();
                }else {
                    JOptionPane.showMessageDialog(null,"Sai mật khẩu hoặc tên user!");
                    setVisible(true);
                }
            }catch(Exception e ){
                e.printStackTrace();
            }
        }else if (ae.getSource() == cancel){
            setVisible(false);
            new HotelManagement();
        }
    }
    
    
    public static void main(String[] args){
        new Login();
    }
}
