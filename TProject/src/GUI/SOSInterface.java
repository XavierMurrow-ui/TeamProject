package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SOSInterface extends FInterface{

    public SOSInterface(String username, String role){
        remove(buttPane);
        JButton back = new JButton("Back");
        back.setToolTipText("Click to exit Technician interface");

        buttPane = new JPanel(new GridLayout(3,2));
        buttPane.add(exTask);
        buttPane.add(job);
        buttPane.add(Payment);
        buttPane.add(logout);
        buttPane.add(back);
        add(buttPane, BorderLayout.SOUTH);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(role.equals("Office Manager")){
                    dispose();
                    OfficeManagerInterface office = new OfficeManagerInterface(username,role);
                }else{
                    dispose();
                    ShiftManagerInterface shift = new ShiftManagerInterface(username,role);
                }
            }
        });
    }
}
