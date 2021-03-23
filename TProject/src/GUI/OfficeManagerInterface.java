package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;

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
        add(tPane,BorderLayout.CENTER);
        remove(buttPane);
        inTask.setText("FrontStaff Access");
        exTask.setText("TechnicianStaff Access");
        JButton front = new JButton("Create new User Account");
        JButton tech = new JButton("Delete User Account");
        JButton Backup = new JButton("System Back-up");

        front.addActionListener(new ActionListener() {
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
                        add(tPane,BorderLayout.CENTER);
                        setVisible(true);
                    }
                });

                tPane = new JScrollPane(nTask);
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        tech.addActionListener(new ActionListener() {
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
                        add(tPane,BorderLayout.CENTER);
                        setVisible(true);
                    }
                });

                tPane = new JScrollPane(nTask);
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        buttPane = new JPanel(new GridLayout(3,2));
        buttPane.add(inTask);
        buttPane.add(exTask);
        buttPane.add(front);
        buttPane.add(tech);
        buttPane.add(Backup);
        add(buttPane, BorderLayout.SOUTH);
        setVisible(true);

        exTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SOTInterface tech = new SOTInterface("C:\\Users\\Xavie\\Documents\\AllData","Office");
                tech.setVisible(true);
            }
        });
    }
}
