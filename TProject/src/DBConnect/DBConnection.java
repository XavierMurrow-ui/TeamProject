package DBConnect;

import javax.swing.*;
import java.sql.*;

public class DBConnection {
    private String Username;
    private String Password;
    //LoginConnection//
    public DBConnection(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public String RoleReturn(){
        String role ="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement st;
            ResultSet rs;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bapers", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT Role " +
                    "FROM Staff " +
                    "WHERE Staff.Username = '" + Username + "'" +
                    "AND Staff.Password = '" + Password + "'");
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException | ClassNotFoundException err) {
            JOptionPane.showMessageDialog(null, err);
        }
        return role;
    }
}
