package broconut.ciangallagher.net;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cian on 19/06/2015.
 */
class Capture extends JFrame {

    // Components
    public JDialog frame;
    public Dimension dimension;

    public Capture() {

        dimension = Toolkit.getDefaultToolkit().getScreenSize();

    }

    public void SelectArea () {

        EventQueue.invokeLater(new Runnable () {
            public void run () {

                frame = new JDialog();
                frame.setUndecorated(true);
                frame.getContentPane().setBackground(Color.GRAY);
                frame.setOpacity(0.01f);
                frame.setLocation(0,0);
                frame.setSize(dimension);
                frame.setVisible(true);
                frame.getContentPane().add(new DrawSquare(frame));

            }
        });

    }

}
