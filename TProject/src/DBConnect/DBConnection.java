package DBConnect;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        return role;
    }

    public DefaultMutableTreeNode[] test(String Condition,String sql){
        DefaultMutableTreeNode[] name = null;
        int count = 0;
        int i = 0;
        int j = 1;
        try {
            ResultSet Count;
            con = getConnection();
            st = con.createStatement();
            Count = st.executeQuery(Condition);
            while(Count.next()){
                count = Count.getInt(1);
            }
            rs = st.executeQuery(sql);
            name = new DefaultMutableTreeNode[count];
            while (rs.next() && i < count) {
                name[i] = new DefaultMutableTreeNode(rs.getString(1));
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

    public void Execute(String sql){
        try {
            con = getConnection();
            st = con.createStatement();
            st.setQueryTimeout(30);
            int i  = st.executeUpdate(sql);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public String CustReturn(String sql){
        String custID ="";
        try {
            con = getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                custID = rs.getString(1);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err);
        }
        return custID;
    }

    public ArrayList<String[]> newCustTable(String custName) {
        ArrayList<String[]> table = new ArrayList<>();
        try {
            String sql = "SELECT Customer_Name,Contact_Name,Email,Address,PostCode,City,Phone FROM Customer WHERE Customer.Customer_Name == '"+custName+"';";
            con = getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int nCol = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] row = new String[nCol];
                for(int i = 1; i <= nCol; i++){
                    row[i-1] = rs.getString(i);
                }
                table.add(row);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return table;
    }

    // Database Connection
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:BAPERS.db";
        return DriverManager.getConnection(url);
    }

}
