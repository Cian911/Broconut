package broconut.ciangallagher.net;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Cian on 19/06/2015.
 */
class DrawSquare extends JPanel implements MouseListener, MouseMotionListener {

    // Components
    public JDialog frame;
    public Rectangle rectangle;
    public BufferedImage bufferedImage;
    public BufferedImage captured;
    public Point start, end, currentPoint;
    public Process process = new Process();

    // Variables
    public String capturedImage;
    public int width, height;

    public DrawSquare(JDialog frame) {

        this.frame = frame;

        // Read in crosshair image to replace mouse icon
        Toolkit tool = Toolkit.getDefaultToolkit();
        Image newImage = getToolkit().getImage("components/cursor.png");
        Cursor cursor = tool.createCustomCursor(newImage, new Point (this.frame.getX(), this.frame.getY()), "img");

        this.frame.setCursor(cursor);
        this.frame.addMouseListener(this);
        this.frame.addMouseMotionListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        boolean raised = false;

        if (rectangle != null) {
            final BasicStroke stroke = new BasicStroke(1.0f, BasicStroke.JOIN_ROUND, BasicStroke.CAP_SQUARE, 1.0f);
            frame.setOpacity(0.15f);
            g2d.setStroke(stroke);
            g2d.setColor(new Color(72,119,205));
            g2d.fill3DRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, raised);
            g2d.draw(rectangle);
        }

        g2d.dispose();
    }

    @Override
    public void mouseDragged (MouseEvent e) {
        this.currentPoint = e.getPoint();

        // get absolute value of cords
        // so we can resize window in all directions
        int x = Math.min(start.x, currentPoint.x);
        int y = Math.min(start.y, currentPoint.y);
        width = Math.abs(start.x - currentPoint.x);
        height = Math.abs(start.y - currentPoint.y);

        // draw our rectangle
        rectangle.setBounds(x, y, width, height);
        frame.validate();
        frame.repaint();
    }

    @Override
    public void mousePressed (MouseEvent e) {
        // Get the X and Y point from the mouse pressed
        this.start = e.getPoint();
        this.currentPoint = e.getPoint();
        rectangle = new Rectangle(this.start);
        // Repaint the screen
        frame.validate();
        frame.repaint();
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        this.currentPoint = e.getPoint();
        frame.dispose();
        frame.validate();
        frame.repaint();

        try {
            process.SaveImage(this.rectangle);
        } catch (Exception a) {a.printStackTrace();}
    }

    @Override
    public void mouseClicked (MouseEvent e) {}

    @Override
    public void mouseEntered (MouseEvent e) {}

    @Override
    public void mouseExited (MouseEvent e) {}

    @Override
    public void mouseMoved (MouseEvent e) {}

}
