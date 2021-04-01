package GUI;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import DBConnect.*;

public class TechInterface extends MainInterface {

    private String[] colHeads={"Task ID","Description","Location","Price","Duration"};
    private String[][]data={{"","","","",""}};
    JButton Logout;

    public TechInterface(String Username){

        remove(buttPane);
        inTask.setText("Update Task");
        inTask.setToolTipText("Click to Update a Task");
        Logout = new JButton("Logout");
        Logout.setToolTipText("Logout");

        remove(tPane);
        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        tPane.setPreferredSize(new Dimension(800,500));
        add(tPane, BorderLayout.CENTER);

        inTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CustName = JOptionPane.showInputDialog(null,"Enter Customer Name this Tasks' Job is for: ");
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);

                JLabel[] labels = {new JLabel("Task Type:"),new JLabel("Time Started:"),new JLabel("Time Completed:"),new JLabel("Status:")};
                labels[0].setBounds(5,0,100,20);
                labels[1].setBounds(5,190,100,20);
                labels[2].setBounds(5,230,100,20);
                labels[3].setBounds(5,270,100,20);
                nTask.add(labels[0]);
                nTask.add(labels[1]);
                nTask.add(labels[2]);
                nTask.add(labels[3]);

                JTextField[] fields = {new JTextField(10)};
                fields[0].setBounds(110,270,150,20);
                nTask.add(fields[0]);

                JDateChooser StaT = new JDateChooser();
                StaT.setBounds(110,190,150,20);
                nTask.add(StaT);

                JDateChooser ComT = new JDateChooser();
                ComT.setBounds(110,230,150,20);
                nTask.add(ComT);

                JCheckBox[] tasks = {new JCheckBox("Use of large copy camera"),new JCheckBox("Black and white film processing"),new JCheckBox("Colour film processing")
                        ,new JCheckBox("Colour Transparency processing"),new JCheckBox("Use of small copy camera"),new JCheckBox("Mount Transparencies")};
                tasks[0].setBounds(110,0,200,20);
                tasks[1].setBounds(110,30,250,20);
                tasks[2].setBounds(110,60,200,20);
                tasks[3].setBounds(110,90,250,20);
                tasks[4].setBounds(110,120,200,20);
                tasks[5].setBounds(110,150,200,20);
                nTask.add(tasks[0]);
                nTask.add(tasks[1]);
                nTask.add(tasks[2]);
                nTask.add(tasks[3]);
                nTask.add(tasks[4]);
                nTask.add(tasks[5]);

                JButton ok = new JButton("Submit");
                ok.setToolTipText("Click to submit task info");
                ok.setBounds(10,330,100,30);
                nTask.add(ok);

                JButton cancel = new JButton("Cancel");
                cancel.setToolTipText("Click to cancel submission");
                cancel.setBounds(130,330,100,30);
                nTask.add(cancel);

                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(tPane);
                        table = new JTable(data,colHeads);
                        tPane = new JScrollPane(table);
                        tPane.setPreferredSize(new Dimension(800,500));
                        add(tPane,BorderLayout.CENTER);
                        setVisible(true);
                    }
                });

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date Cdate = new Date(ComT.getDate().getTime());
                        Time Ctime = new Time(ComT.getDate().getTime());
                        Date Sdate = new Date(StaT.getDate().getTime());
                        Time Stime = new Time(StaT.getDate().getTime());
                        DBConnection con = new DBConnection();
                        String SQL = "SELECT Customer_Name FROM Customer WHERE Customer.Customer_Name == '" +CustName+"';";
                        int custID = Integer.parseInt(con.CustReturn(SQL));
                        String Job = "SELECT Job_No From Job, Customer \n" +
                                "WHERE Job.CustomerCustomer_No == Customer.Customer_ID\n" +
                                "AND Customer.Customer_ID = "+custID+"\n" +
                                "AND Job.Status == 'Pending'";
                        int jobID = Integer.parseInt(con.CustReturn(Job));
                        String Staff = "SELECT Staff_ID FROM Staff WHERE Staff.UserName == '"+Username+"';";
                        int staffID = Integer.parseInt(con.CustReturn(Staff));
                        for(int i = 0; i < tasks.length; i++) {
                            if(tasks[i].isSelected()) {
                                String UpTask = "INSERT INTO Task (Description,Location,Price,Duration,JobJob_No,StaffStaff_ID,Status)\n" +
                                        "VALUES(10,'Mount Transparencies','Finishing Room',55.50,45,,'Pending');";
                                con.Execute(UpTask);
                                String UpMainTask = "UPDATE Task ";
                            }
                        }

                        for(int i = 0; i < tasks.length; i++){
                            String Task = "";
                            if(tasks[i].isSelected()) {
                                switch (tasks[i].getText()) {
                                    case "Use of large copy camera":
                                        Task = "INSERT INTO Task (Description,Location,Price,Start_Time,Completion_Time,Duration,JobJob_No,StaffStaff_No,Status)\n " +
                                                "VALUES('" + tasks[i] + "','Copy Room',19.00,'"+Sdate+" "+Stime+"','"+Cdate+" "+Ctime+"',120," + jobID + ","+staffID+",'Completed')";
                                        break;
                                    case "Black and white film processing":
                                        Task = "INSERT INTO Task (Description,Location,Price,Start_Time,Completion_Time,Duration,JobJob_No,StaffStaff_No,Status)\n " +
                                                "VALUES('" + tasks[i] + "','Development Area',49.50,'"+Sdate+" "+Stime+"','"+Cdate+" "+Ctime+"',60," + jobID + ","+staffID+",'Completed')";
                                        break;
                                    case "Colour film processing":
                                        Task = "INSERT INTO Task (Description,Location,Price,Start_Time,Completion_Time,Duration,JobJob_No,StaffStaff_No,Status)\n " +
                                                "VALUES('" + tasks[i] + "','Development Area',80.00,'"+Sdate+" "+Stime+"','"+Cdate+" "+Ctime+"',90," + jobID + ","+staffID+",'Completed')";
                                        break;

                                    case "Colour Transparency processing":
                                        Task = "INSERT INTO Task (Description,Location,Price,Start_Time,Completion_Time,Duration,JobJob_No,StaffStaff_No,Status)\n " +
                                                "VALUES('" + tasks[i] + "','Development Area',110.30,'"+Sdate+" "+Stime+"','"+Cdate+" "+Ctime+"',180," + jobID + ","+staffID+",'Completed')";
                                        break;

                                    case "Use of small copy camera":
                                        Task = "INSERT INTO Task (Description,Location,Price,Start_Time,Completion_Time,Duration,JobJob_No,StaffStaff_No,Status)\n " +
                                                "VALUES('" + tasks[i] + "','Copy Room',8.30,'"+Sdate+" "+Stime+"','"+Cdate+" "+Ctime+"',75," + jobID + ","+staffID+",'Completed')";
                                        break;

                                    case "Mount Transparencies":
                                        Task = "INSERT INTO Task (Description,Location,Price,Start_Time,Completion_Time,Duration,JobJob_No,StaffStaff_No,Status)\n " +
                                                "VALUES('" + tasks[i] + "','Finishing Room',55.50,'"+Sdate+" "+Stime+"','"+Cdate+" "+Ctime+"',45," + jobID + ","+staffID+",'Completed')";
                                        break;
                                }
                                con.Execute(Task);
                                String temp = "SELECT Task_ID FROM Task,Job WHERE Task.JobJob_No == Job.Job_No AND Job.Job_No = "+jobID+" AND Task.Quantity > 1 AND Task.Description = '"+tasks[i].getText()+"';";
                                String Task_ID = con.CustReturn(temp);
                                if(!Task_ID.equals("")) {
                                    String Update = "UPDATE Task SET Quantity = Quantity - 1 WHERE Task.Task_ID = " + Integer.parseInt(Task_ID) + "AND Quantity > 0;";
                                    con.Execute(Update);
                                }else{
                                    temp = "SELECT Task_ID FROM Task,Job WHERE Task.JobJob_No == Job.Job_No AND Job.Job_No = "+jobID+" AND Task.Quantity == 0 AND Task.Description = '"+tasks[i].getText()+"';";
                                    int tID = Integer.parseInt(con.CustReturn(temp));
                                    String Update = "UPDATE Task SET Status = 'Completed', Completion_Time = '"+Cdate+" "+Ctime+"' WHERE Task.Task_ID = "+tID+" AND Task.Description = '"+tasks[i].getText()+"';";
                                    con.Execute(Update);
                                }
                            }
                        }
                    }
                });

                tPane = new JScrollPane(nTask);
                tPane.setPreferredSize(new Dimension(800,500));
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttPane = new JPanel(new GridLayout(1,2));
        buttPane.add(inTask);
        buttPane.add(Logout);
        add(buttPane, BorderLayout.SOUTH);
        setVisible(true);
    }
}
