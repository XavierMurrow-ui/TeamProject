import java.sql.*;


public class main {

    // Database Connection
    public static Connection getConnection() throws SQLException
    {
        String url = "jdbc:sqlite:sample.db";
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // create a database connection
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            //statement.executeUpdate("INSERT or REPLACE INTO Customer VALUES('ACC0001', 'City Uni', 'Prof Test', 'test.l@city.ac.uk', 'someaddress', 'EC1V 2HB', 'London', '020220408000')");

            //backupRestore.backUp();
            //backupRestore.restoreBackup("2021-03-10");

            ResultSet rs = statement.executeQuery("SELECT * FROM Customer");
            while (rs.next()) {
                // read the result set
                System.out.println("Customer Number = " + rs.getString("Customer_ID"));
                System.out.println("Name = " + rs.getString("Customer_Name"));
                System.out.println("Contact Details = " + rs.getString("Contact_Name"));
                System.out.println("Email = " + rs.getString("Email"));
                System.out.println("Address = " + rs.getString("Address_Street"));
                System.out.println("Postcode = " + rs.getString("PostCode") + ", " +  rs.getString("City"));
                System.out.println("Phone = " + rs.getString("Phone"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
