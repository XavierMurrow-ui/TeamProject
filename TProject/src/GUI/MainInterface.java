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
    private final JTree tree;
    protected JScrollPane pane;
    protected JScrollPane tPane;
    protected final JButton inTask,exTask;
    protected JPanel buttPane;
    private String[] colHeads={"Task ID","Description","Location","Price","Duration"};
    private String[][]data={{"","","","",""}};

    public MainInterface(String path){
        super("Test");
        setLayout(new BorderLayout());
        setSize(1000,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String[] colHeads={"Task ID","Description","Location","Price","Duration"};
        String[][]data={{"","","","",""}};
        table = new JTable(data,colHeads);

        tPane = new JScrollPane(table);
        add(tPane, BorderLayout.CENTER);

        File temp=new File(path);
        DefaultMutableTreeNode top = createTree(temp);
        DefaultMutableTreeNode topx = new DefaultMutableTreeNode("Staff");
        DefaultMutableTreeNode name = new DefaultMutableTreeNode("Name");
        topx.add(name);
        DBConnection test = new DBConnection();
        int i = 0;
        while(i < test.test().length) {
            name.add(test.test()[i]);
            i++;
        }
        tree = new JTree(topx);

        pane = new JScrollPane(tree);
        pane.setSize(10,500);
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

        //tree.addMouseListener(
              //  new MouseAdapter()
              //  {
              //      public void mouseClicked(MouseEvent me)
             //       {
             //           doMouseClicked(me);
            //        }
             //   });
    }

    DefaultMutableTreeNode createTree(@org.jetbrains.annotations.NotNull File temp)
    {
        DefaultMutableTreeNode top=new DefaultMutableTreeNode(temp.getPath());
        if(!(temp.exists() && temp.isDirectory()))
            return top;

        fillTree(top,temp.getPath());

        return top;
    }

    void fillTree(DefaultMutableTreeNode root, String filename)
    {
        File temp=new File(filename);

        if(!(temp.exists() && temp.isDirectory()))
            return;
        File[] filelist=temp.listFiles();

        for(int i=0; i<filelist.length; i++)
        {
            if(!filelist[i].isDirectory())
                continue;
            final DefaultMutableTreeNode tempDmtn=new DefaultMutableTreeNode(filelist[i].getName());
            root.add(tempDmtn);
            final String newfilename=new String(filename+"\\"+filelist[i].getName());
            Thread t=new Thread()
            {
                public void run()
                {
                    fillTree(tempDmtn,newfilename);
                }//run
            };//thread
            if(t==null)
            {System.out.println("no more thread allowed "+newfilename);return;}
            t.start();
        }//for
    }//function

    void doMouseClicked(MouseEvent me)
    {
        TreePath tp=tree.getPathForLocation(me.getX(),me.getY());
        if(tp==null) return;
        String s=tp.toString();
        s=s.replace("[","");
        s=s.replace("]","");
        s=s.replace(", ","\\");
        showFiles(s);
    }

    void showFiles(String filename)
    {
        File temp=new File(filename);
        data=new String[][]{{"","","",""}};
        remove(tPane);
        table=new JTable(data, colHeads);
        tPane=new JScrollPane(table);
        setVisible(false);
        add(tPane,BorderLayout.CENTER);
        setVisible(true);

        if(!temp.exists()) return;
        if(!temp.isDirectory()) return;

        File[] filelist=temp.listFiles();
        int fileCounter=0;
        data=new String[filelist.length][4];
        for(int i=0; i<filelist.length; i++)
        {
            if(filelist[i].isDirectory())
                continue;
            data[fileCounter][0]=new String(filelist[i].getName());
            data[fileCounter][1]=new String(filelist[i].length()+"");
            data[fileCounter][2]=new String(!filelist[i].canWrite()+"");
            data[fileCounter][3]=new String(filelist[i].isHidden()+"");
            fileCounter++;
        }//for

        String dataTemp[][]=new String[fileCounter][4];
        for(int k=0; k<fileCounter; k++)
            dataTemp[k]=data[k];
        data=dataTemp;

        remove(tPane);
        table=new JTable(data, colHeads);
        tPane=new JScrollPane(table);
        setVisible(false);
        add(tPane,BorderLayout.CENTER);
        setVisible(true);
    }//function

    void showFile(String filename){
        File temp=new File(filename);
        data=new String[][]{{"","","",""}};
        remove(tPane);
        table=new JTable(data, colHeads);
        tPane=new JScrollPane(table);
        setVisible(false);
        add(tPane,BorderLayout.CENTER);
        setVisible(true);

        if(!temp.exists()) return;

        data = new String[][]{{new String(temp.getName()),new String(temp.length()+""),new String(temp.canWrite()+""),new String(temp.isHidden()+"")}};

        remove(tPane);
        table=new JTable(data, colHeads);
        tPane=new JScrollPane(table);
        setVisible(false);
        add(tPane,BorderLayout.CENTER);
        setVisible(true);
    }

}

