package hotelmanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener{
    
    JButton newCustomer,rooms,allEmployee,customerInfo,searchRoom,updateCustomer,updateRoom,checkout,addRoom,logout,addEmployee,viewInvoice;
    Reception () {
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(350,200,800,570);
        
        JLabel lblcustomer = new JLabel("Customer");
        lblcustomer.setFont(new Font("serif",Font.PLAIN,20));
        lblcustomer.setForeground(Color.white);
        lblcustomer.setBounds(90,30,200,30);
        add(lblcustomer);
        
        newCustomer = new JButton("Check in");
        newCustomer.setBounds(30,70,200,30);
        newCustomer.setBackground(Color.BLACK);
        newCustomer.setForeground(Color.WHITE);
        newCustomer.addActionListener(this);
        add(newCustomer);
        
        customerInfo = new JButton("Customer");
        customerInfo.setBounds(30,110,200,30);
        customerInfo.setBackground(Color.BLACK);
        customerInfo.setForeground(Color.WHITE);
        customerInfo.addActionListener(this);
        add(customerInfo);
        
        updateCustomer = new JButton("Update Customer");
        updateCustomer.setBounds(30,150,200,30);
        updateCustomer.setBackground(Color.BLACK);
        updateCustomer.setForeground(Color.WHITE);
        updateCustomer.addActionListener(this);
        add(updateCustomer);
        
        checkout = new JButton("Checkout");
        checkout.setBounds(30,190,200,30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);
        
        JLabel lblroom = new JLabel("Room");
        lblroom.setFont(new Font("serif",Font.PLAIN,20));
        lblroom.setForeground(Color.white);
        lblroom.setBounds(380,30,200,30);
        add(lblroom);
        
        rooms = new JButton("Rooms");
        rooms.setBounds(300,70,200,30);
        rooms.setBackground(Color.BLACK);
        rooms.setForeground(Color.WHITE);
        rooms.addActionListener(this);
        add(rooms);
        
        addRoom = new JButton("Add Room");
        addRoom.setBounds(300,110,200,30);
        addRoom.setBackground(Color.BLACK);
        addRoom.setForeground(Color.WHITE);
        addRoom.addActionListener(this);
        add(addRoom);
        
        updateRoom = new JButton("Update Room ");
        updateRoom.setBounds(300,150,200,30);
        updateRoom.setBackground(Color.BLACK);
        updateRoom.setForeground(Color.WHITE);
        updateRoom.addActionListener(this);
        add(updateRoom);
            
        searchRoom = new JButton("Search Room");
        searchRoom.setBounds(300,190,200,30);
        searchRoom.setBackground(Color.BLACK);
        searchRoom.setForeground(Color.WHITE);
        searchRoom.addActionListener(this);
        add(searchRoom);
      
        
        JLabel lblemployee = new JLabel("Employee");
        lblemployee.setFont(new Font("serif",Font.PLAIN,20));
        lblemployee.setForeground(Color.white);
        lblemployee.setBounds(630,30,200,30);
        add(lblemployee);
        
        allEmployee = new JButton("Employees");
        allEmployee.setBounds(570,70,200,30);
        allEmployee.setBackground(Color.BLACK);
        allEmployee.setForeground(Color.WHITE);
        allEmployee.addActionListener(this);
        add(allEmployee);
        
        
        addEmployee = new JButton("Add Employee");
        addEmployee.setBounds(570,110,200,30);
        addEmployee.setBackground(Color.BLACK);
        addEmployee.setForeground(Color.WHITE);
        addEmployee.addActionListener(this);
        add(addEmployee);

        JLabel lbl_invoice = new JLabel("Invoice");
        lbl_invoice.setFont(new Font("serif",Font.PLAIN,20));
        lbl_invoice.setForeground(Color.white);
        lbl_invoice.setBounds(380,300,200,30);
        add(lbl_invoice);

        viewInvoice = new JButton("Invoice");
        viewInvoice.setBounds(300,350,200,30);
        viewInvoice.setBackground(Color.BLACK);
        viewInvoice.setForeground(Color.WHITE);
        viewInvoice.addActionListener(this);
        add(viewInvoice);
      
 
        
        logout = new JButton("Logout");
        logout.setBounds(300,470,200,30);
        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/hotel5.jpg"));
        Image i2=i1.getImage().getScaledInstance(800,570,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,800,570);
        add(image);
        
        


        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== newCustomer ){
            setVisible(false);
            new AddCustomer();
        }else if (ae.getSource() == rooms){
            setVisible(false);
            new Rooms();
        }else if (ae.getSource() == allEmployee){
            setVisible(false);
            new Employee();
        }else if (ae.getSource() == customerInfo){
            setVisible(false);
            new Customer();
        }else if (ae.getSource() == searchRoom ){
            setVisible(false);
            new SearchRoom();
        }else if (ae.getSource() == updateCustomer){
            setVisible(false);
            new UpdateCustomer();
        }else if (ae.getSource() == updateRoom){
            setVisible(false);
            new UpdateRoom();
        }else if (ae.getSource() == checkout){
            setVisible(false);
            new Checkout();
        }else if(ae.getSource() == addRoom){
            setVisible(false);
            new AddRoom();
        }else if (ae.getSource() == logout){
            setVisible(false);
            System.exit(0);
        }else if (ae.getSource() == addEmployee){
            setVisible(false);
            new AddEmployee();
        }else if (ae.getSource() == viewInvoice) {
            setVisible(false);
            new Invoice();
        }
    }
    
    public static void main(String[] args){
        new Reception();
    }
}
