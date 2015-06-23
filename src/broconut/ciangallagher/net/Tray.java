package broconut.ciangallagher.net;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cian on 19/06/2015.
 */
class Tray {

    public TrayIcon trayIcon = null;
    public Capture capture = new Capture();

    public Tray () {
        if (SystemTray.isSupported()) {
            // get the tray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load image
            Image image = Toolkit.getDefaultToolkit().getImage("components/tray.gif");
            // create listener for action on icon
            ActionListener listener = new ActionListener() {
               public void actionPerformed (ActionEvent e) {
                   // execute action to be performed
                   capture.SelectArea();
               }
            };

            // create menu
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem  = new MenuItem("Capture");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);

            // create close button
            MenuItem close = new MenuItem("Close");
            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popup.add(close);

            // add more items...
            trayIcon = new TrayIcon(image, "Broconut", popup);
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(listener);
            // add tray image..

            try {

                tray.add(trayIcon);

            } catch (AWTException e) {e.printStackTrace();}
        } else {
            // disable tray option
            // TODO: Load error dialog
            System.out.println("System Tray is not supported!");
            return;
        }
    }
}
