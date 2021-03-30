package ReportGenerator;

import DBConnect.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class generateReport {
    public static DBConnection connect;
    public static List<String[]> table;

    public generateReport(){}

    public static void individualPerformanceReport() {
        connect = new DBConnection();
        try {
            String sql = "SELECT Staff.Name as Staff_Name, Task.Task_ID as Task_ID, Task.Location as Location, Task.Duration as Duration,\n" +
                    "(SELECT SUM(Task.Duration) FROM Task WHERE Task.StaffStaff_ID = Staff.Staff_ID)\n" +
                    "AS Total FROM Task\n" +
                    "INNER JOIN Staff ON Task.StaffStaff_ID = Staff.Staff_ID\n" +
                    "ORDER BY Staff.Name";
            ResultSet rs = connect.Connect(sql);
            int nCol = rs.getMetaData().getColumnCount();
            table = new ArrayList<>();
            // FILE NAME
            PrintWriter pw = new PrintWriter(new FileWriter("IndividualPerformanceReport.html"));
            pw.println("<TABLE BORDER style='width: 100%; border-collapse: collapse;'><TR><th>Staff Name</th><th>Task ID</th><th>Location</th><th>Duration</th><th>Total</th></TR>");
            while (rs.next()) {
                /*
                System.out.println("Staff Name: " + rs.getString("Staff_Name"));
                System.out.println("Task ID: " + rs.getString("Task_ID"));
                System.out.println("Location: " + rs.getString("Location"));
                System.out.println("Duration: " + rs.getString("Duration"));
                System.out.println("TOTAL: " + rs.getString("Total"));
                */
                String[] row = new String[nCol];
                for(int i = 0; i <= nCol; i++){
                    row[i-1] = rs.getString(i);
                }

                pw.println("<tr>");
                pw.println("<TD>" + rs.getString("Staff_Name") + "</TD>");
                pw.println("<TD>" + rs.getString("Task_ID") + "</TD>");
                pw.println("<TD>" + rs.getString("Location") + "</TD>");
                pw.println("<TD>" + rs.getString("Duration") + "</TD>");
                pw.println("<TD>" + rs.getString("Total") + "</TD>");
                pw.println("</tr>");

            }
            pw.println("</TABLE>");
            pw.close();


        } catch (SQLException | IOException err) {
            err.printStackTrace();
        }
    }


    public static void individualReport(String CustName, Date Start, Date End) {
        connect = new DBConnection();
        try {
            String sql = "SELECT Deadline, Job_No AS Job_Number, Customer.Customer_Name AS Customer_Name FROM Job \n" +
                    "INNER JOIN Customer ON Customer.Customer_ID = Job.CustomerCustomer_No\n" +
                    "WHERE Customer.Customer_Name = '"+CustName+"' AND\n" +
                    "Deadline BETWEEN '"+Start+"' AND '"+End+"' ";
            ResultSet rs = connect.Connect(sql);

            // FILE NAME
            PrintWriter pw = new PrintWriter(new FileWriter("IndividualReport.html"));
            pw.println("<TABLE BORDER style='width: 100%; border-collapse: collapse;'><TR><th>Date</th><th>Job Number</th><th>Customer Name</th></TR>");
            while (rs.next()) {

                /*
                System.out.println("Date: " + rs.getString("Date"));
                System.out.println("Job Number: " + rs.getString("Job_Number"));
                System.out.println("Customer Name: " + rs.getString("Customer_Name"));
                */

                pw.println("<tr>");
                pw.println("<TD>" + rs.getString("Deadline") + "</TD>");
                pw.println("<TD>" + rs.getString("Job_Number") + "</TD>");
                pw.println("<TD>" + rs.getString("Customer_Name") + "</TD>");
                pw.println("</tr>");

            }
            pw.println("</TABLE>");
            pw.close();


        } catch (SQLException | IOException err) {
            err.printStackTrace();
        }
    }

    public static List<String[]> getTable() {
        return table;
    }
}
