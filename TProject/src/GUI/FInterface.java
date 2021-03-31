package GUI;

import DBConnect.*;
import ReportGenerator.generateReport;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class FInterface extends MainInterface {

    private String[] colHeads={"Cust Name","Contact Name","Email","Address","City","PostCode","Phone Number","Discount Type"};
    private String[][]data={{"","","","","","","",""}};
    JButton job,Payment,logout;

    public FInterface(String path){
        super(path);
        remove(tPane);
        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        table.setSize(900,500);
        add(tPane,BorderLayout.CENTER);
        remove(buttPane);
        exTask.setText("Create new Customer");
        job = new JButton("Create Job");
        Payment = new JButton("Payment");
        logout = new JButton("Logout");

        exTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(tPane);
                    JPanel nTask = new JPanel();
                    nTask.setLayout(null);
                    JLabel[] labels = {new JLabel("Customer Name:"),new JLabel("Contract Name:"),new JLabel("Email:"),new JLabel("Address:"),new JLabel("PostCode:"),new JLabel("City:"),new JLabel("Telephone No:")};
                    labels[0].setBounds(5,-30,100,100);
                    labels[1].setBounds(5,0,100,100);
                    labels[2].setBounds(5,30,100,100);
                    labels[3].setBounds(5,60,100,100);
                    labels[4].setBounds(5,90,100,100);
                    labels[5].setBounds(5,120,100,100);
                    labels[6].setBounds(5,150,100,100);
                    nTask.add(labels[0]);
                    nTask.add(labels[1]);
                    nTask.add(labels[2]);
                    nTask.add(labels[3]);
                    nTask.add(labels[4]);
                    nTask.add(labels[5]);
                    nTask.add(labels[6]);

                    JTextField[] fields = {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10)};
                    fields[0].setBounds(105,11,150,20);
                    fields[1].setBounds(105,41,150,20);
                    fields[2].setBounds(105,71,150,20);
                    fields[3].setBounds(105,101,150,20);
                    fields[4].setBounds(105,131,150,20);
                    fields[5].setBounds(105,161,150,20);
                    fields[6].setBounds(105,191,150,20);
                    nTask.add(fields[0]);
                    nTask.add(fields[1]);
                    nTask.add(fields[2]);
                    nTask.add(fields[3]);
                    nTask.add(fields[4]);
                    nTask.add(fields[5]);
                    nTask.add(fields[6]);

                    JButton ok = new JButton("Submit");
                    ok.setToolTipText("Click to submit task info");
                    ok.setBounds(10,250,100,30);
                    nTask.add(ok);

                    JButton cancel = new JButton("Cancel");
                    cancel.setToolTipText("Click to cancel submission");
                    cancel.setBounds(130,250,100,30);
                    nTask.add(cancel);

                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DBConnection connection = new DBConnection();
                            String SQL = "SELECT Customer_Name FROM Customer WHERE Customer.Customer_Name == '"+fields[0].getText()+"'";
                            String CustEX = connection.CustReturn(SQL);
                            if(CustEX.equals("")) {
                                String sql = "INSERT INTO \n" +
                                        "Customer (Customer_Name,Contact_Name,Email,Address,PostCode,City,Phone)  \n" +
                                        "VALUES('"+fields[0].getText()+"','"+fields[1].getText()+"','"+ fields[2].getText()+"','"+fields[3].getText()+"','"+fields[4].getText()+"','"+ fields[5].getText()+"','"+fields[6].getText()+"');";
                                connection.Execute(sql);
                            }else{
                                JOptionPane.showMessageDialog(null,"Fuck off cunt");
                            }
                            ArrayList<String[]> Table = connection.newCustTable(fields[0].getText());
                            colHeads = new String[]{"Customer Name", "Contact Name", "Email", "Address", "Postcode", "City", "Phone"};
                            DefaultTableModel tableModel = new DefaultTableModel(colHeads, 0);
                            table = new JTable(tableModel);
                            for (String[] rows : Table) {
                                tableModel.addRow(rows);
                            }
                            remove(tPane);
                            tPane = new JScrollPane(table);
                            tPane.setPreferredSize(new Dimension(800, 500));
                            add(tPane, BorderLayout.CENTER);
                            setVisible(true);
                        }
                    });

                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            remove(tPane);
                            table = new JTable(data,colHeads);
                            tPane = new JScrollPane(table);
                            table.setSize(900,500);
                            add(tPane, BorderLayout.CENTER);
                            setVisible(true);
                        }
                    });

                    tPane = new JScrollPane(nTask);
                    table.setSize(900,500);
                    add(tPane,BorderLayout.CENTER);
                    setVisible(true);
            }
        });

        job.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);
                JLabel[] labels = {new JLabel("Priority:"),new JLabel("Deadline:"),new JLabel("Special Instructions:")};
                labels[0].setBounds(5,-30,100,100);
                labels[1].setBounds(5,0,100,100);
                labels[2].setBounds(5,30,150,100);
                nTask.add(labels[0]);
                nTask.add(labels[1]);
                nTask.add(labels[2]);

                JTextField[] fields = {new JTextField(10),new JTextField(10)};
                fields[0].setBounds(130,11,150,20);

                JDateChooser date = new JDateChooser();
                date.setBounds(130,41,150,20);
                nTask.add(date);

                fields[1].setBounds(130,71,150,20);
                nTask.add(fields[0]);
                nTask.add(fields[1]);

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
                        table.setSize(900,500);
                        add(tPane, BorderLayout.CENTER);
                        setVisible(true);
                    }
                });

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Cust = JOptionPane.showInputDialog(null,"Enter Customers name: ");
                        DBConnection connection = new DBConnection();
                        String SQL = "SELECT Customer_Name FROM Customer WHERE Customer.Customer_Name == '" +Cust+"';";
                        String custID = connection.CustReturn(SQL);
                        if(!custID.equals(null)) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String sql = "INSERT INTO Job (Priority,Status,Deadline,Special_instruction,CustomerCustomer_No)\n" +
                                    "VALUES(" + fields[0].getText() + ",'Pending','" + dateFormat.format(date.getDate()) + "','" + fields[1].getText() + "','" + custID + "');";
                            connection.Execute(sql);
                        }
                    }
                });

                tPane = new JScrollPane(nTask);
                table.setSize(900,500);
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });

        Payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);

                JButton npay = new JButton("New Payment");
                npay.setToolTipText("Click to create new payment for new customer");
                npay.setBounds(10,10,135,30);
                nTask.add(npay);

                JButton UpCard = new JButton("Update Card Information");
                UpCard.setToolTipText("Click to update customer card information");
                UpCard.setBounds(10,50,210,30);
                nTask.add(UpCard);

                JButton cancel = new JButton("Cancel");
                cancel.setToolTipText("Click to cancel submission");
                cancel.setBounds(10,90,100,30);
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

                npay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(tPane);
                        JPanel nTask = new JPanel();
                        nTask.setLayout(null);

                        JLabel[] labels = {new JLabel("Amount:"),new JLabel("Payment Type:")};
                        labels[0].setBounds(5,-30,100,100);
                        labels[1].setBounds(5,0,100,100);
                        nTask.add(labels[0]);
                        nTask.add(labels[1]);

                        JTextField[] fields = {new JTextField(10)};
                        fields[0].setBounds(130,11,150,20);
                        String[] pType = {"Cash","Card"};
                        JComboBox pay = new JComboBox(pType);
                        pay.setBounds(130,41,150,20);
                        nTask.add(fields[0]);
                        nTask.add(pay);

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
                                table.setSize(900,500);
                                add(tPane, BorderLayout.CENTER);
                                setVisible(true);
                            }
                        });
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boolean nCust = true;
                                DBConnection con = new DBConnection();
                                String CustName = JOptionPane.showInputDialog(null,"Enter Customer Making Payment: ");
                                String SQL = "SELECT Customer_ID FROM Customer WHERE Customer.Customer_Name == '" +CustName+"';";
                                int custID = Integer.parseInt(con.CustReturn(SQL));
                                String NewCust = "SELECT CardHolder_Name FROM Card_Info, Customer WHERE Card_Info.CustomerCustomer_ID == Customer.Customer_ID AND Customer.Customer_ID = "+custID+";";
                                String NCard = con.CustReturn(NewCust);
                                if(!NCard.equals("")){
                                    nCust = false;
                                    String card = "SELECT Card_Number FROM Card_Info, Customer WHERE Card_Info.CustomerCustomer_ID == Customer.Customer_ID AND Customer.Customer_ID = "+custID+";";
                                    long CardNo = Long.parseLong(con.CustReturn(card));
                                    if(custID != 0){
                                        String sql = "INSERT INTO Payment (Amount, Payment_Type, CustomerCustomer_No, Payment_Status, Card_No)" +
                                                "VALUES('"+fields[0].getText()+"','"+String.valueOf(pay.getSelectedItem())+"', "+custID+",FALSE,"+CardNo+");";
                                        con.Execute(sql);
                                    }
                                }
                                if(String.valueOf(pay.getSelectedItem()).equals("Card") && nCust){
                                    if(custID != 0){
                                        String sql = "INSERT INTO Payment (Amount, Payment_Type, CustomerCustomer_No, Payment_Status)" +
                                                "VALUES('"+fields[0].getText()+"','"+String.valueOf(pay.getSelectedItem())+"', "+custID+",FALSE);";
                                        con.Execute(sql);
                                    }
                                    CardInfo(custID,true);
                                }
                            }
                        });

                        tPane = new JScrollPane(nTask);
                        table.setSize(900,500);
                        add(tPane,BorderLayout.CENTER);
                        setVisible(true);
                    }
                });

                UpCard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DBConnection con = new DBConnection();
                        String CustName = JOptionPane.showInputDialog(null,"Enter Customer Making Payment: ");
                        String SQL = "SELECT Customer_ID FROM Customer WHERE Customer.Customer_Name == '" +CustName+"';";
                        int custID = Integer.parseInt(con.CustReturn(SQL));
                        CardInfo(custID,false);
                    }
                });

                tPane = new JScrollPane(nTask);
                tPane.setPreferredSize(new Dimension(800,500));
                add(tPane,BorderLayout.CENTER);
                setVisible(true);
            }
        });



        buttPane = new JPanel(new GridLayout(2,2));
        buttPane.add(exTask);
        buttPane.add(job);
        buttPane.add(Payment);
        buttPane.add(logout);
        add(buttPane, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void CardInfo(int custID,boolean type){
        remove(tPane);
        JPanel nTask = new JPanel();
        nTask.setLayout(null);
        JLabel[] labels = {new JLabel("Card Number:"),new JLabel("Card Type:"),new JLabel("Card Holder Name:"),new JLabel("Expiry Date:"),new JLabel("Last 4 Digits:"),new JLabel("CVV:")};
        labels[0].setBounds(5,-30,100,100);
        labels[1].setBounds(5,0,100,100);
        labels[2].setBounds(5,30,120,100);
        labels[3].setBounds(5,60,100,100);
        labels[4].setBounds(5,90,100,100);
        labels[5].setBounds(5,120,100,100);
        nTask.add(labels[0]);
        nTask.add(labels[1]);
        nTask.add(labels[2]);
        nTask.add(labels[3]);
        nTask.add(labels[4]);
        nTask.add(labels[5]);

        JTextField[] fields = {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10)};
        fields[0].setBounds(125,11,150,20);
        fields[1].setBounds(125,41,150,20);
        fields[2].setBounds(125,71,150,20);
        JDateChooser exDate = new JDateChooser();
        exDate.setBounds(125,101,150,20);
        exDate.setDateFormatString("MM/YY");
        fields[3].setBounds(125,131,150,20);
        JPasswordField CVV = new JPasswordField(10);
        CVV.setBounds(125,161,150,20);
        nTask.add(fields[0]);
        nTask.add(fields[1]);
        nTask.add(fields[2]);
        nTask.add(exDate);
        nTask.add(fields[3]);
        nTask.add(CVV);

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
                if(type == true) {
                    DBConnection con = new DBConnection();
                    String SQL = "DELETE FROM Payment WHERE CustomerCustomer_No =" + custID;
                    con.Execute(SQL);
                }
                remove(tPane);
                table = new JTable(data, colHeads);
                tPane = new JScrollPane(table);
                table.setSize(900, 500);
                add(tPane, BorderLayout.CENTER);
                setVisible(true);
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection con = new DBConnection();
                DateFormat dateFormat = new SimpleDateFormat("MM-yy");
                if(type == true) {
                    String SQL = "SELECT Payment_ID FROM Payment, Customer\n" +
                            "WHERE Payment.CustomerCustomer_No == Customer.Customer_ID \n" +
                            "AND Customer.Customer_ID ==" + custID + "\n" +
                            "ORDER BY Payment_ID DESC LIMIT 1;";
                    String temp = con.CustReturn(SQL);
                    int payID = Integer.parseInt(temp);
                    if (payID != 0) {
                        String sql = "INSERT INTO Card_Info VALUES(" + Long.parseLong(fields[0].getText()) + ",'" + fields[1].getText() + "','" + fields[2].getText() + "','" + dateFormat.format(exDate.getDate()) + "'," + Integer.parseInt(fields[3].getText()) + "," + Integer.parseInt(CVV.getText()) + "," + custID + ");";
                        con.Execute(sql);
                        String update = "UPDATE Payment SET Card_No = " + Long.parseLong(fields[0].getText()) + " WHERE Payment.Payment_ID == " + payID + ";";
                        con.Execute(update);
                    }
                }else{
                    String sql = "UPDATE Card_Info \n" +
                            "SET Card_Number = "+Long.parseLong(fields[0].getText()) +", Card_Type = '"+fields[1].getText()+"', CardHolder_Name = '"+ fields[2].getText() +"', ExpiryDate = '"+ dateFormat.format(exDate.getDate()) +"', Last4Digits = "+ Integer.parseInt(fields[3].getText()) +", CVV = "+ Integer.parseInt(CVV.getText()) +"\n" +
                            "WHERE CustomerCustomer_ID = "+ custID +";";
                    con.Execute(sql);
                }
                remove(tPane);
                table = new JTable(data, colHeads);
                tPane = new JScrollPane(table);
                table.setSize(900, 500);
                add(tPane, BorderLayout.CENTER);
                setVisible(true);
            }
        });

        tPane = new JScrollPane(nTask);
        table.setSize(900,500);
        add(tPane,BorderLayout.CENTER);
        setVisible(true);
    }
}
