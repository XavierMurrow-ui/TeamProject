import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class backupRestore {

    // Database Connection
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:sample.db";
        return DriverManager.getConnection(url);
    }

    // Back-Up Data
    static void backUp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Connection connection;
        try {
            // create a database connection
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String fileName = dateFormat.format(date);
            connection.createStatement().executeUpdate("backup to " + fileName + ".db");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Back-Up Data Scheduling
    static void backUpTime() {
        Timer t = new Timer();
        try {
            t.schedule(new TimerTask() {
                public void run() {
                    backUp();
                }
            }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-03-09 15:47:20"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    // Restore From Back-Up
    static void restoreBackup(String fileName) {
        Connection connection;
        try {
            // create a database connection
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            connection.createStatement().executeUpdate("restore from " + fileName + ".db");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
