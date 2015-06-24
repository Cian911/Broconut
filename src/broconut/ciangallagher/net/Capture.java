package broconut.ciangallagher.net;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cian on 19/06/2015.
 */
class Capture extends JFrame {

    // Components
    private JDialog frame;
    private int width, height;

    public Capture() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for(GraphicsDevice curGs : gs)
        {
            DisplayMode dm = curGs.getDisplayMode();
            width += dm.getWidth();
            height += dm.getHeight();
        }
    }

    public void SelectArea () {
        EventQueue.invokeLater(new Runnable () {
            public void run () {
                frame = new JDialog();
                frame.setUndecorated(true);
                frame.getContentPane().setBackground(Color.GRAY);
                frame.setOpacity(0.01f);
                frame.setLocation(0,0);
                frame.setSize(width, height);
                frame.setVisible(true);
                frame.getContentPane().add(new DrawSquare(frame));
            }
        });
    }

    public JDialog getOwner () {
        return this.frame;
    }

}
