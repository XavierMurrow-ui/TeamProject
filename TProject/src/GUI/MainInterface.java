package GUI;

import DBConnect.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainInterface extends JFrame {

    protected JTable table;
    protected JTree tree;
    protected JScrollPane pane;
    protected JScrollPane tPane;
    protected final JButton inTask,exTask;
    protected JPanel buttPane;

    public MainInterface(){
        super("Test");
        setLayout(new BorderLayout());
        setSize(1000,500);
        setResizable(false);
        Dimension XD = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(XD.width/2 - this.getSize().width/2, XD.height/2 - this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String[] colHeads={"Task ID","Description","Location","Price","Duration"};
        String[][]data={{"","","","",""}};
        table = new JTable(data,colHeads);

        tPane = new JScrollPane(table);
        tPane.setPreferredSize(new Dimension(800,500));
        add(tPane, BorderLayout.CENTER);

        DefaultMutableTreeNode topx = new DefaultMutableTreeNode("Staff");
        DefaultMutableTreeNode name = new DefaultMutableTreeNode("Name");
        topx.add(name);
        DBConnection test = new DBConnection();
        int i = 0;
        String count = "SELECT COUNT(Name) FROM Staff";
        String sql = "SELECT Name FROM Staff";
        while(i < test.test(count,sql).length) {
            name.add(test.test(count,sql)[i]);
            i++;
        }
        tree = new JTree(topx);

        pane = new JScrollPane(tree);
        pane.setPreferredSize(new Dimension(200,500));
        add(pane, BorderLayout.WEST);

        inTask = new JButton("Button1");
        inTask.setToolTipText("Button1");
        inTask.setSize(50,20);

        exTask = new JButton("Button2");
        exTask.setToolTipText("Button2");
        exTask.setSize(50,20);

        buttPane = new JPanel(new BorderLayout());
        add(buttPane,BorderLayout.SOUTH);
        buttPane.add(inTask, BorderLayout.NORTH);
        buttPane.add(exTask, BorderLayout.SOUTH);


    }
}

