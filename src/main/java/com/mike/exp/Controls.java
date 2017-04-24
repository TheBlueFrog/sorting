package com.mike.exp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// derived from Oracle's Java tutorials

public class Controls extends JPanel
implements ActionListener {
	    protected JButton b1, b2, b3;
	    private JFrame mFrame;
	    
	    public Controls() {

	    	//Create and set up the controls	 
	        b1 = new JButton("Disable middle button");
	        b1.setVerticalTextPosition(AbstractButton.CENTER);
	        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
	        b1.setMnemonic(KeyEvent.VK_D);
	        b1.setActionCommand("disable");
	 
	        b2 = new JButton("Middle button");
	        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
	        b2.setHorizontalTextPosition(AbstractButton.CENTER);
	        b2.setMnemonic(KeyEvent.VK_M);
	 
	        b3 = new JButton("Enable middle button");
	        //Use the default text position of CENTER, TRAILING (RIGHT).
	        b3.setMnemonic(KeyEvent.VK_E);
	        b3.setActionCommand("enable");
	        b3.setEnabled(false);
	 
	        //Listen for actions on the buttons
	        b1.addActionListener(this);
	        b2.addActionListener(this);
	        b3.addActionListener(this);
	 
	        b1.setToolTipText("Click this button to disable the middle button.");
	        b2.setToolTipText("This middle button repaints");
	        b3.setToolTipText("Click this button to enable the middle button.");
	 
	        //Add Components to this container, using the default FlowLayout.
	        add(b1);
	        add(b2);
	        add(b3);

	        mFrame = new JFrame("Controls");
	        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        setOpaque(true); //content panes must be opaque
	        mFrame.setContentPane(this);
	 
	        //Display the window.
	        mFrame.pack();
	        mFrame.setVisible(true);	        
	    }
	 
	    public void actionPerformed(ActionEvent e) {
	        if ("disable".equals(e.getActionCommand())) {
	            b1.setEnabled(false);
	            b2.setEnabled(false);
	            b3.setEnabled(true);
	        } else if ("enable".equals(e.getActionCommand())) {
	            b1.setEnabled(true);
	            b2.setEnabled(true);
	            b3.setEnabled(false);
	        }

	        // always repaint
	        Main.mDrawing.mFrame.repaint();
	    }
	 
}
