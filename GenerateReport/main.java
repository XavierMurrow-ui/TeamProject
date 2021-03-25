import java.sql.*;


public class main {

    // Database Connection
    public static Connection getConnection() throws SQLException {
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

            //backupRestore.backUp();
            //backupRestore.restoreBackup("2021-03-10");

            generateReport.individualPerformanceReport();
            generateReport.individualReport();


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
