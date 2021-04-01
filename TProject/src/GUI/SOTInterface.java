package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SOTInterface extends TechInterface {
    public SOTInterface(String username, String role) {
        super(username);

        remove(buttPane);
        JButton back = new JButton("Back");
        back.setToolTipText("Click to exit Technician interface");

        buttPane = new JPanel(new GridLayout(3,1));
        buttPane.add(inTask);
        buttPane.add(Logout);
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
