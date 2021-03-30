package GUI;

import ReportGenerator.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class OfficeManagerInterface extends MainInterface{

    private String[] colHeads={"Job No.","Priority","Deadline","Status"};
    private String[][]data={{"","","",""}};

    public OfficeManagerInterface(String path,String role){
        super(path);
        remove(tPane);
        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        tPane.setPreferredSize(new Dimension(800,500));
        add(tPane,BorderLayout.CENTER);
        remove(buttPane);
        inTask.setText("FrontStaff Access");
        exTask.setText("TechnicianStaff Access");
        JButton nUser = new JButton("Create new User Account");
        JButton dUser = new JButton("Delete User Account");
        JButton Backup = new JButton("System Back-up");
        JButton Report = new JButton("Report Generation");

        nUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);
                JLabel[] labels = {new JLabel("Name:"), new JLabel("Department:"), new JLabel("Role:"), new JLabel("Username:")};
                labels[0].setBounds(5, -30, 100, 100);
                labels[1].setBounds(5, 0, 100, 100);
                labels[2].setBounds(5, 30, 100, 100);
                labels[3].setBounds(5, 60, 100, 100);
                nTask.add(labels[0]);
                nTask.add(labels[1]);
                nTask.add(labels[2]);
                nTask.add(labels[3]);

                JTextField[] fields = {new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
                fields[0].setBounds(85, 11, 150, 20);
                fields[1].setBounds(85, 41, 150, 20);
                fields[2].setBounds(85, 71, 150, 20);
                fields[3].setBounds(85, 101, 150, 20);
                nTask.add(fields[0]);
                nTask.add(fields[1]);
                nTask.add(fields[2]);
                nTask.add(fields[3]);

                JButton ok = new JButton("Submit");
                ok.setToolTipText("Click to submit task info");
                ok.setBounds(10,250,100,30);
                nTask.add(ok);

                JButton cancel = new JButton("Cancel");
                cancel.setToolTipText("Click to cancel submission");
                cancel.setBounds(130,250,100,30);
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

                tPane = new JScrollPane(nTask);
                tPane.setPreferredSize(new Dimension(800,500));
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        dUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null,"Enter Username:");
            }
        });

        Backup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);
                JLabel[] labels = {new JLabel("Day of Back-Up:"), new JLabel("Time of Back-Up:"), new JLabel(":")};
                labels[0].setBounds(5, -30, 100, 100);
                labels[1].setBounds(5, 0, 100, 100);
                labels[2].setBounds(153,0,100,100);
                nTask.add(labels[0]);
                nTask.add(labels[1]);
                nTask.add(labels[2]);

                /*JTextField[] fields = {new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10)};
                fields[0].setBounds(85, 11, 150, 20);
                fields[1].setBounds(85, 41, 150, 20);
                nTask.add(fields[0]);
                nTask.add(fields[1]);*/

                JDateChooser date = new JDateChooser();
                date.setBounds(100,11,150,20);
                nTask.add(date);

                JSpinField hour = new JSpinField();
                hour.setMinimum(00);
                hour.setMaximum(24);
                hour.setBounds(110,42,40,20);
                nTask.add(hour);

                JSpinField min = new JSpinField();
                min.setMinimum(00);
                min.setMaximum(59);
                min.setBounds(160,42,40,20);
                nTask.add(min);

                JButton ok = new JButton("Back-up");
                ok.setToolTipText("Click to submit back-up info");
                ok.setBounds(10,250,100,30);
                nTask.add(ok);

                JButton auto = new JButton("Auto Back-Up");
                auto.setToolTipText("Click to perform an automated back-up");
                auto.setBounds(130,250,115,30);
                nTask.add(auto);

                JButton cancel = new JButton("Cancel");
                cancel.setToolTipText("Click to cancel submission");
                cancel.setBounds(265,250,100,30);
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

                tPane = new JScrollPane(nTask);
                tPane.setPreferredSize(new Dimension(800,500));
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        Report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);

                JButton inPReport = new JButton("Individual Performance Report");
                inPReport.setToolTipText("Click to generate an individual performance report");
                inPReport.setBounds(10,10,210,30);
                nTask.add(inPReport);

                JButton InReport = new JButton("Individual Report");
                InReport.setToolTipText("Click to generate an individual report");
                InReport.setBounds(10,50,135,30);
                nTask.add(InReport);

                JButton SumReport = new JButton("Summary Report");
                SumReport.setToolTipText("Click to generate Summary Report");
                SumReport.setBounds(10,90,135,30);
                nTask.add(SumReport);

                JButton cancel = new JButton("Cancel");
                cancel.setToolTipText("Click to cancel submission");
                cancel.setBounds(10,130,100,30);
                nTask.add(cancel);

                inPReport.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        generateReport test = new generateReport();
                        test.individualPerformanceReport();
                        List<String[]> Table = test.getTable();
                        colHeads = new String[]{"Staff Name", "Task ID","Location", "Duration","Price"};
                        DefaultTableModel tableModel = new DefaultTableModel(colHeads,0);
                        remove(tPane);
                        table = new JTable(tableModel);
                        for(String[] rows : Table){
                            tableModel.addRow(rows);
                        }
                        tPane = new JScrollPane(table);
                        tPane.setPreferredSize(new Dimension(800,500));
                        add(tPane,BorderLayout.CENTER);
                        setVisible(true);
                    }
                });

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

                tPane = new JScrollPane(nTask);
                tPane.setPreferredSize(new Dimension(800,500));
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        buttPane = new JPanel(new GridLayout(3,2));
        buttPane.add(inTask);
        buttPane.add(exTask);
        buttPane.add(nUser);
        buttPane.add(dUser);
        buttPane.add(Backup);
        buttPane.add(Report);
        add(buttPane, BorderLayout.SOUTH);
        setVisible(true);

        exTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SOTInterface tech = new SOTInterface("C:\\Users\\Xavie\\Documents\\AllData",role);
                tech.setVisible(true);
            }
        });
    }
}