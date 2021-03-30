package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechInterface extends MainInterface {

    private String[] colHeads={"Task ID","Description","Location","Price","Duration"};
    private String[][]data={{"","","","",""}};

    public TechInterface(String path){
        super(path);

        inTask.setText("Create new Task");
        inTask.setToolTipText("Click to create a new Task");
        exTask.setText("Search for existing Task");
        exTask.setToolTipText("Click to search for existing Task");

        remove(tPane);
        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        tPane.setPreferredSize(new Dimension(800,500));
        add(tPane, BorderLayout.CENTER);

        setVisible(true);

        inTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(tPane);
                JPanel nTask = new JPanel();
                nTask.setLayout(null);

                JLabel[] labels = {new JLabel("Task ID:"),new JLabel("Description:"),new JLabel("Location:"),new JLabel("Price:"),new JLabel("Duration:")};
                labels[0].setBounds(5,-30,100,100);
                labels[1].setBounds(5,0,100,100);
                labels[2].setBounds(5,110,100,100);
                labels[3].setBounds(5,140,100,100);
                labels[4].setBounds(5,170,100,100);
                nTask.add(labels[0]);
                nTask.add(labels[1]);
                nTask.add(labels[2]);
                nTask.add(labels[3]);
                nTask.add(labels[4]);

                JTextField[] fields = {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10)};
                fields[0].setBounds(85,11,150,20);
                fields[1].setBounds(85,152,150,20);
                fields[2].setBounds(85,182,150,20);
                fields[3].setBounds(85,212,150,20);
                nTask.add(fields[0]);
                nTask.add(fields[1]);
                nTask.add(fields[2]);
                nTask.add(fields[3]);

                JTextArea desc = new JTextArea();
                desc.setBounds(85,47,200,100);
                nTask.add(desc);

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

        exTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null,"Enter Task ID: ");
            }
        });
    }
}
