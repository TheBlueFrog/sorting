package com.mike.exp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

/**
 * Created by mike on 3/24/2017.
 */
public class Experiment implements MouseListener, MouseMotionListener {

    private static final String TAG = Experiment.class.getSimpleName();
    private List<Offer> offers;
    private List<Tag> tags;

    public Experiment(List<Tag> tags, List<Offer> offers) {
        this.tags = tags;
        this.offers = offers;
    }


    public void paint(Graphics g) {
        offers.forEach(offer -> offer.paint(g));
        tags.forEach(tag -> tag.paint(g));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(Tag tag : tags) {
            if (tag.mousePressed(Drawing.pixel2WorldX((float) e.getX()), Drawing.pixel2WorldY((float) e.getY()))) {
                return;
            }
        }
//        for(Offer offer : offers) {
//            if (offer.mousePressed(Drawing.pixel2WorldX((float) e.getX()), Drawing.pixel2WorldY((float) e.getY()))) {
//                return;
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(Tag tag : tags)
            tag.clearSelection();

//        for(Offer offer : offers)
//            offer.clearSelection();
        moveOffers();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for(Tag tag : tags) {
            if (tag.mouseDragged(Drawing.pixel2WorldX((float) e.getX()), Drawing.pixel2WorldY((float) e.getY()))) {
                Main.mDrawing.repaint(0,0,700,700);
                return;
            }
        }

//        for(Offer offer : offers) {
//            if (offer.mouseDragged(Drawing.pixel2WorldX((float) e.getX()), Drawing.pixel2WorldY((float) e.getY()))) {
//                Main.mDrawing.repaint(0,0,700,700);
//                return;
//            }
//        }
    }

    private void moveOffers() {

        Anneal anneal = new Anneal(offers);
        offers = anneal.anneal();

        Main.mDrawing.repaint(0,0,700,700);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
