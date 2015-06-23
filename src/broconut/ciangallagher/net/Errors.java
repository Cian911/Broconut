package broconut.ciangallagher.net;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cian on 23/06/2015.
 */
class Errors extends JDialog {

    private List<String> error = new ArrayList<String>();
    private JFrame owner;
    private String title = "Error!";

    public Errors (JFrame owner) {
        this.owner = owner;
    }

    public Errors () {}

    public void addErrors (String errorMessage) {
        this.error.add(errorMessage);
    }

    public boolean checkErrors () {
        if (this.error.size() > 0) {
            return true;
        }
        return false;
    }

    public void displayErrorDialog () {
        Point p = new Point(owner.getWidth()/2, owner.getHeight()/2);
        setLocation(p.x, p.y);

        // Create message
        JPanel messagePane = new JPanel();

        for (String me : error) {
            messagePane.add(new JLabel(me));
        }

        getContentPane().add(messagePane);
    }

}
