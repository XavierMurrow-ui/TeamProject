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

                JLabel[] labels = {new JLabel("Task Type:"),new JLabel("Time Completed:"),new JLabel("Status:")};
                labels[0].setBounds(5,-30,100,100);
                labels[1].setBounds(5,150,100,100);
                labels[2].setBounds(5,190,100,100);
                nTask.add(labels[0]);
                nTask.add(labels[1]);
                nTask.add(labels[2]);

                JTextField[] fields = {new JTextField(10)};
                fields[0].setBounds(110,230,150,20);
                nTask.add(fields[0]);

                JDateChooser ComT = new JDateChooser();
                ComT.setBounds(110,190,150,20);
                nTask.add(ComT);

                JCheckBox[] tasks = {new JCheckBox("Use of large copy camera"),new JCheckBox("Black and white film processing"),new JCheckBox("Colour film processing")
                        ,new JCheckBox("Colour Transparency processing"),new JCheckBox("Use of small copy camera"),new JCheckBox("Mount Transparencies")};
                tasks[0].setBounds(110,11,200,20);
                tasks[1].setBounds(110,41,250,20);
                tasks[2].setBounds(110,71,200,20);
                tasks[3].setBounds(110,101,250,20);
                tasks[4].setBounds(110,131,200,20);
                tasks[5].setBounds(110,161,200,20);
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
                        Date date = new Date(ComT.getDate().getTime());
                        Time time = new Time(ComT.getDate().getTime());
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
                                String UpTask = "UPDATE Task SET Completion_Time = '" + date + " " + time + "', StaffStaff_ID = " + staffID + ", Status = '" + fields[1].getText() + "' WHERE Task.JobJob_No = " + jobID + " AND Task.Description = '"+tasks[i].getText()+"';";
                                con.Execute(UpTask);
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
