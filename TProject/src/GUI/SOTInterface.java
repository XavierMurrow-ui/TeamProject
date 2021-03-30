package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SOTInterface extends TechInterface {
    public SOTInterface(String path,String role) {
        super(path);
        remove(buttPane);
        JButton back = new JButton("Back");
        back.setToolTipText("Click to exit Technician interface");

        buttPane = new JPanel(new GridLayout(3,1));
        buttPane.add(inTask);
        buttPane.add(exTask);
        buttPane.add(back);
        add(buttPane, BorderLayout.SOUTH);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(role.equals("Office Manager")){
                    dispose();
                    OfficeManagerInterface office = new OfficeManagerInterface("C:\\Users\\Xavie\\Documents\\AllData","Office Manager");
                }else{
                    dispose();
                    ShiftManagerInterface shift = new ShiftManagerInterface("C:\\Users\\Xavie\\Documents\\AllData\\Staff Manager","Shift Manager");
                }
            }
        });
    }
}
