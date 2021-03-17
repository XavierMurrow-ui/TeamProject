import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FInterface extends MainInterface {

    private String[] colHeads={"Cust Name","Contact Name","Email","Address","City","PostCode","Phone Number","Discount Type"};
    private String[][]data={{"","","","","","","",""}};

    public FInterface(String path){
        super(path);
        remove(tPane);
        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        add(tPane,BorderLayout.CENTER);
        remove(buttPane);
        inTask.setText("Search for Customer");
        exTask.setText("Create new Customer");
        JButton job = new JButton("Create Job");

        inTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null,"Enter Customers name: ");
            }
        });

        exTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(tPane);
                    JPanel nTask = new JPanel();
                    nTask.setLayout(null);
                    JLabel[] labels = {new JLabel("Customer Name:"),new JLabel("Contract  Name:"),new JLabel("Email:"),new JLabel("Address:"),new JLabel("PostCode:"),new JLabel("City:"),new JLabel("Telephone No:")};
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

                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            remove(tPane);
                            table = new JTable(data,colHeads);
                            tPane = new JScrollPane(table);
                            add(tPane, BorderLayout.CENTER);
                            setVisible(true);
                        }
                    });

                    tPane = new JScrollPane(nTask);
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
                JLabel[] labels = {new JLabel("Customer Name:"),new JLabel("Contract  Name:"),new JLabel("Email:"),new JLabel("Address:"),new JLabel("PostCode:"),new JLabel("City:"),new JLabel("Telephone No:")};
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

                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(tPane);
                        table = new JTable(data,colHeads);
                        tPane = new JScrollPane(table);
                        add(tPane, BorderLayout.CENTER);
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
        buttPane.add(job);
        add(buttPane, BorderLayout.SOUTH);
        setVisible(true);
    }
}
