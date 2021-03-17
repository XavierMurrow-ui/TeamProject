import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiftManagerInterface extends MainInterface {

    private String[] colHeads={"Job No.","Priority","Deadline","Status"};
    private String[][]data={{"","","",""}};

    public ShiftManagerInterface(String path) {
        super(path);
        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        add(tPane, BorderLayout.CENTER);
        inTask.setText("FrontStaff Access");
        exTask.setText("Technician Access");
        setVisible(true);

        exTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SOTInterface tech = new SOTInterface("C:\\Users\\Xavie\\Documents\\AllData\\Staff Manager");
                tech.setVisible(true);
            }
        });
    }
}
