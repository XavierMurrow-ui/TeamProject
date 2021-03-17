import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SOTInterface extends TechInterface {
    public SOTInterface(String path) {
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
                loginGUI user = new loginGUI();
                if(user.getUsername().equals("Office")){
                    dispose();
                    OfficeManagerInterface office = new OfficeManagerInterface("C:\\Users\\Xavie\\Documents\\AllData");
                }else{
                    dispose();
                    ShiftManagerInterface shift = new ShiftManagerInterface("C:\\Users\\Xavie\\Documents\\AllData\\Staff Manager");
                }
            }
        });
    }
}
