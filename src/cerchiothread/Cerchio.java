package cerchiothread;

import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Cerchio extends JPanel implements Runnable {

    public final static int PANEL_WIDTH = 500;
    public final static int PANEL_HEIGHT = 400;
    public final static int OVAL_SIZE = 100;

    int x = 0, y = 0, dx = 2, dy = 2;//posizione del cerchio e quanto si deve spostare all'interno del pannello
    Random ran = new Random();
    Thread animator = new Thread(this);

    public Thread getThread() {

        return animator;
    }

    public Cerchio() {

        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        x = ran.nextInt(PANEL_WIDTH - OVAL_SIZE);
        y = ran.nextInt(PANEL_HEIGHT - OVAL_SIZE);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(x, y, OVAL_SIZE, OVAL_SIZE);
    }

    private void cycle() {

        if (x + dx < 0) {
            dx = -dx;
        }
        if (x + dx > PANEL_WIDTH - OVAL_SIZE) {
            dx = -dx;
        }
        if (y + dy < 0 || y + dy > PANEL_HEIGHT - OVAL_SIZE) {
            dy = -dy;
        }
        x += dx;
        y += dy;
        repaint();
    }

    @Override
    public void run() {
        while (true) {
            cycle();
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }
}
