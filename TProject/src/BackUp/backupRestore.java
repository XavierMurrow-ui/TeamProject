package BackUp;

import DBConnect.*;
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
    public static DBConnection connection;
    public static Date date;

    public backupRestore(Date date){
        this.date = date;
    }

    // Back-Up Data
    public static void backUp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        connection = new DBConnection();
        String fileName = dateFormat.format(date);
        connection.Execute("backup to " + fileName + ".db");
    }

    // Back-Up Data Scheduling
    public static void backUpTime() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                backUp();
            }
        }, Long.parseLong(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
    }


    // Restore From Back-Up
    public static void restoreBackup(String fileName) {
        connection.Execute("restore from " + fileName + ".db");
    }

}
