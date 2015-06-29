package broconut.ciangallagher.net;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Cian on 28/06/2015.
 */
class TraySettings {

    private final static String BUTTON = "Button 1";
    private final static String TEXT1 = "This is some sample text";

    public TraySettings () {
        // set the look and feel of the window
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception w) {w.printStackTrace();}

        JFrame frame = new JFrame();

        JPanel pMain;
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        pMain = new JPanel(new CardLayout());
        pMain.add(p1, BUTTON);
        pMain.add(p2, TEXT1);

        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        // dispose of window only on close
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}
