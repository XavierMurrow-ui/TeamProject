import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.sql.*;
import java.util.Calendar;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.table.*;

public class loginGUI extends JFrame{

    private final JLabel title;
    private final JTextField login;
    private final JPasswordField password;
    private final JButton Login, Exit;
    private boolean enter = false;
    private String username;
    public String getUsername() {
        return username;
    }
    public loginGUI(){
        super("Login");
        setLayout(new FlowLayout());
        setResizable(false);
        setSize(250,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Login");
        add(title);

        login = new JTextField(20);
        login.setToolTipText("Enter username");
        add(login);

        password = new JPasswordField(20);
        password.setToolTipText("Enter password");
        password.setEchoChar('*');
        add(password);

        Login = new JButton("Login");
        Login.setToolTipText("Click to login");
        add(Login);

        Exit = new JButton("Exit");
        Exit.setToolTipText("Exit BAPERS");
        add(Exit);

        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    enter = true;
                }else{
                    enter = false;
                }
            }
        });
        Enter Action = new Enter();
        login.addActionListener(Action);
        password.addActionListener(Action);
        Login.addActionListener(Action);
        Exit.addActionListener(Action);


    }

    private class Enter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            String Message="Please press ok ";
            String Password = "";
            String role = "";
            if(e.getSource()==Login || enter){
                username = login.getText();
                Password = password.getText();
                try{
                    Statement st;
                    ResultSet rs;
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bapers","root","");
                    st = con.createStatement();
                    rs = st.executeQuery("SELECT Role " +
                            "FROM Staff " +
                            "WHERE Staff.Username = '"+username+"'" +
                            "AND Staff.Password = '"+Password+"'");
                    while(rs.next()){role = rs.getString(1);}
                }
                catch(SQLException err){
                    JOptionPane.showMessageDialog(null,err);
                }
                if(role.equals("Technician")) {
                    JOptionPane.showMessageDialog(null,Message+username);
                    TechInterface test = new TechInterface("C:\\Users\\Xavie\\Documents\\AllData\\Technician");
                    test.setVisible(true);
                }else if(username.equals("Front")){
                    JOptionPane.showMessageDialog(null,Message+username);
                    FInterface Front = new FInterface("C:\\Users\\Xavie\\Documents\\AllData\\FrontStaff");
                    Front.setVisible(true);
                }else if(username.equals("Office")){
                    JOptionPane.showMessageDialog(null, Message+username);
                    OfficeManagerInterface Office = new OfficeManagerInterface("C:\\Users\\Xavie\\Documents\\AllData","Office");
                }else if(username.equals("Shift")){
                    JOptionPane.showMessageDialog(null, Message+username);
                    ShiftManagerInterface shift = new ShiftManagerInterface("C:\\Users\\Xavie\\Documents\\AllData\\Staff Manager","Shift");
                }else{
                    JOptionPane.showMessageDialog(null,"Fuck off");
                    System.exit(0);
                }
                dispose();
            }else if(e.getSource() == Exit){
                System.exit(0);
            }
        }
    }
}
