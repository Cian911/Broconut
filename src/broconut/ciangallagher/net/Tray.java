package broconut.ciangallagher.net;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Cian on 19/06/2015.
 */
class Tray implements NativeKeyListener {

    public TrayIcon trayIcon = null;
    public Capture capture = new Capture();

    public Tray () {
        // set the look and feel of the window
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception w) {w.printStackTrace();}

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

            /* create menu */
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem  = new MenuItem("Capture");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);

            MenuItem settings = new MenuItem("Settings");
            settings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showSettings();
                }
            });
            popup.add(settings);

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

            // turn off the fucking logging..
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
            }

            GlobalScreen.addNativeKeyListener(this);

            try {

                tray.add(trayIcon);

            } catch (AWTException e) {e.printStackTrace();}
        } else {
            // disable tray option
            // TODO: Load error dialog
            System.err.println("System Tray is not supported!");
            return;
        }
    }

    public void showSettings () {
        TraySettings settings = new TraySettings();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException a) {a.printStackTrace();}
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT_R) {
            capture.SelectArea();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {}

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {}
}
