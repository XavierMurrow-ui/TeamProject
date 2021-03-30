package ReportGenerator;

import DBConnect.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class generateReport {
    public static DBConnection connect;

    public static void individualPerformanceReport() {
        connect = new DBConnection();
        try {
            String sql = "SELECT Staff.Name as Staff_Name, Task.Task_ID as Task_ID, Task.Location as Location, Task.Duration as Duration,\n" +
                    "(SELECT SUM(Task.Duration) FROM Task WHERE Task.StaffStaff_ID = Staff.Staff_ID)\n" +
                    "AS Total FROM Task\n" +
                    "INNER JOIN Staff ON Task.StaffStaff_ID = Staff.Staff_ID\n" +
                    "ORDER BY Staff.Name";
            ResultSet rs = connect.Connect(sql);

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


        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void individualReport() {
        Connection connection = null;
        connect = new DBConnection();
        try {
            String sql = "SELECT Date, Job_No AS Job_Number, Customer.Customer_Name AS Customer_Name FROM Job \n" +
                    "INNER JOIN Customer ON Customer.Customer_ID = Job.CustomerCustomer_No\n" +
                    "WHERE Customer.Customer_Name = 'AirVia' AND\n" +
                    "Date BETWEEN '16/03/21' AND '22/03/21' ";
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
                pw.println("<TD>" + rs.getString("Date") + "</TD>");
                pw.println("<TD>" + rs.getString("Job_Number") + "</TD>");
                pw.println("<TD>" + rs.getString("Customer_Name") + "</TD>");
                pw.println("</tr>");

            }
            pw.println("</TABLE>");
            pw.close();


        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }


}
