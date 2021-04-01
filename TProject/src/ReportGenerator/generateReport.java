package ReportGenerator;

import DBConnect.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class generateReport {
    public static DBConnection connect;
    public static List<String[]> table;

    public generateReport(){
    }

    public static void individualPerformanceReportTable() {
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
    }

    public static void individualPerformanceReportFile(File f){
        connect = new DBConnection();
        try {
            String sql = "SELECT Staff.Name as Staff_Name, Task.Task_ID as Task_ID, Task.Location as Location, Task.Duration as Duration,\n" +
                    "(SELECT SUM(Task.Duration) FROM Task WHERE Task.StaffStaff_ID = Staff.Staff_ID)\n" +
                    "AS Total FROM Task\n" +
                    "INNER JOIN Staff ON Task.StaffStaff_ID = Staff.Staff_ID\n" +
                    "ORDER BY Staff.Name";
            ResultSet rs = connect.Connect(sql);
            // FILE NAME
            PrintWriter pw = new PrintWriter(new FileWriter(new File(f.getParent(),f.getName()+".html")));
            pw.println("<TABLE BORDER style='width: 100%; border-collapse: collapse;'><TR><th>Staff Name</th><th>Task ID</th><th>Location</th><th>Duration</th><th>Total</th></TR>");
            while (rs.next()) {
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


    public static void individualReportTable(String CustName, java.util.Date Start, java.util.Date End) {
        connect = new DBConnection();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "SELECT Deadline, Job_No AS Job_Number, Customer.Customer_Name AS Customer_Name FROM Job \n" +
                    "INNER JOIN Customer ON Customer.Customer_ID = Job.CustomerCustomer_No\n" +
                    "WHERE Customer.Customer_Name = '"+CustName+"' AND\n" +
                    "Deadline BETWEEN '"+dateFormat.format(Start)+"' AND '"+dateFormat.format(End)+"' ";

            ResultSet rs = connect.Connect(sql);
            int nCol = rs.getMetaData().getColumnCount();
            table = new ArrayList<>();

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
    }

    public static void individualReportFile(String CustName, java.util.Date Start, Date End,File f){
        connect = new DBConnection();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "SELECT Deadline, Job_No AS Job_Number, Customer.Customer_Name AS Customer_Name FROM Job \n" +
                    "INNER JOIN Customer ON Customer.Customer_ID = Job.CustomerCustomer_No\n" +
                    "WHERE Customer.Customer_Name = '"+CustName+"' AND\n" +
                    "Deadline BETWEEN '"+dateFormat.format(Start)+"' AND '"+dateFormat.format(End)+"' ";
            ResultSet rs = connect.Connect(sql);

            // FILE NAME
            PrintWriter pw = new PrintWriter(new FileWriter(new File(f.getParent(),f.getName()+".html")));
            pw.println("<TABLE BORDER style='width: 100%; border-collapse: collapse;'><TR><th>Date</th><th>Job Number</th><th>Customer Name</th></TR>");
            while (rs.next()) {

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
