package com.mike.exp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Drawing mDrawing;
	private static Controls mControls;
    private static Experiment experiment;

    public static void main(String[] args) {

        List<Offer> offers = new ArrayList<>();

        List<Tag> tags = new ArrayList<>();
        Tag produce = new Tag("Produce", -75.0f, 75.0f);       tags.add(produce);
        Tag nonGMO = new Tag("Non-GMO", -75.0f, 65.0f);        tags.add(nonGMO);
        Tag organic = new Tag("Organic", -75.0f, 55.0f);       tags.add(organic);
        Tag meat = new Tag("Meat", -75.0f, 45.0f);             tags.add(meat);
        Tag grassFed = new Tag("Grass-Fed", -75.0f, 35.0f);    tags.add(grassFed);
        Tag beef = new Tag("Beef", -75.0f, 25.0f);             tags.add(beef);
        Tag pork = new Tag("Pork", -75.0f, 15.0f);             tags.add(pork);

        {
            List<Tag> tags1 = new ArrayList<>();
            tags1.add(produce);
            tags1.add(nonGMO);
            tags1.add(organic);

            offers.add(new Offer("Carrot", tags1));
        }
        {
            List<Tag> tags1 = new ArrayList<>();
            tags1.add(produce);
            tags1.add(organic);

            offers.add(new Offer("Celery", tags1));
        }
        {
            List<Tag> tags1 = new ArrayList<>();
            tags1.add(meat);
            tags1.add(grassFed);
            tags1.add(organic);
            tags1.add(beef);

            offers.add(new Offer("Steak", tags1));
        }
        {
            List<Tag> tags1 = new ArrayList<>();
            tags1.add(meat);
            tags1.add(pork);
            tags1.add(organic);

            offers.add(new Offer("Back Ribs", tags1));
        }

        experiment = new Experiment(tags, offers);

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mControls = new Controls();
                mDrawing = new Drawing(experiment);
            }
        });

    }

    public static void paint(Graphics g) {
        experiment.paint(g);
    }
}
