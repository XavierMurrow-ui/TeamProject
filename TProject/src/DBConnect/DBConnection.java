package DBConnect;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.*;

public class DBConnection {
    private String Username;
    private String Password;
    private Connection con = null;
    Statement st;
    ResultSet rs;

    public DBConnection(){}

    //LoginConnection Constructor//
    public DBConnection(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    //Returns the role for login//
    public String RoleReturn(){
        String role ="";
        try {
            con = getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT Role " +
                    "FROM Staff " +
                    "WHERE Staff.Username = '" + Username + "'" +
                    "AND Staff.Password = '" + Password + "'");
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err);
        }
        System.out.println(role);
        return role;
    }

    public DefaultMutableTreeNode[] test(){
        DefaultMutableTreeNode[] name = null;
        int count = 0;
        int i = 0;
        int j = 1;
        try {
            ResultSet Count;
            con = getConnection();
            st = con.createStatement();
            Count = st.executeQuery("SELECT COUNT(Name) FROM Staff");
            while(Count.next()){
                count = Count.getInt(1);
            }
            rs = st.executeQuery("SELECT Name FROM Staff");
            name = new DefaultMutableTreeNode[count];
            while (rs.next() && i < count) {
                name[i] = new DefaultMutableTreeNode(rs.getString("Name"));
                i++;
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err);
        }
        return name;
    }

    public ResultSet Connect(String sql) {
        try {
            con = getConnection();
            st = con.createStatement();
            st.setQueryTimeout(30);
            rs = st.executeQuery(sql);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err);
        }
        return rs;
    }

    // Database Connection
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:BAPERS.db";
        return DriverManager.getConnection(url);
    }

}
