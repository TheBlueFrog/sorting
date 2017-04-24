package com.mike.exp;

import javax.swing.*;
import java.awt.*;

//derived from Oracle's Java tutorials

public class Drawing extends JPanel {

    private final Experiment experiment;

    JFrame mFrame;

	public Drawing (Experiment experiment) {

        super();

        //Create and set up the drawing area
        mFrame = new JFrame("Drawing");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        setOpaque(true); //content panes must be opaque
        mFrame.setContentPane(this);

        //Display the window.
        mFrame.setSize(new Dimension(700, 700));
//	        frame.pack();
        mFrame.setVisible(true);

        this.experiment = experiment;
        addMouseListener(experiment);
        addMouseMotionListener(experiment);

    }

    public void paintComponent(Graphics g) {
	    super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        Dimension d = getSize();
//        double uni = d.width / 200.0;
//        int gridHeight = d.height / 200.0;

        experiment.paint(g);
    }


    // world 0, 0 = center of screen
    //       width = 200
    //      height = 200

    static public double worldToPixelX(double worldX) {
	    return ((worldX + 100.0) / 200.0) * 700.0;
    };
    static public double worldToPixelY(double worldY) {
        return 700.0 - (((worldY + 100.0) / 200.0) * 700.0);
    };

    public static float pixel2WorldX(float x) {
        return (float) (((x - 350.0) / 700.0) * 200.0);
    }
    public static float pixel2WorldY(float y) {
        return (float) ((((700.0 - y) - 350.0) / 700.0) * 200.0);
    }

}
