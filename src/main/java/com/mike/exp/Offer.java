package com.mike.exp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.mike.exp.Drawing.worldToPixelX;
import static com.mike.exp.Drawing.worldToPixelY;

/**
 * Created by mike on 4/23/2017.
 */
public class Offer {
    private static final String TAG = Offer.class.getSimpleName();

    private String name;
    public List<Tag> tags;
    public float x = 0;
    public float y = 0;
    private boolean selected = false;

    public Offer(String name, List<Tag> tags) {
        this.name = name;
        this.tags = tags;
    }

    public Offer(Offer offer) {
        this.name = offer.name;
        this.tags = new ArrayList<>(offer.tags);
        this.x = offer.x;
        this.y = offer.y;
    }

    public void paint(Graphics g) {
        g.setColor(selected ? Color.RED : Color.BLUE);
        g.drawChars(name.toCharArray(),
                0, name.length(),
                (int) Math.round(worldToPixelX(this.x)),
                (int) Math.round(worldToPixelY(this.y)));

        tags.forEach(tag -> tag.paint(g));
    }

    public boolean mousePressed(float worldX, float worldY) {
        if ((Math.abs(x - worldX) < 10) && (Math.abs(y - worldY) < 10)) {
            selected = true;
            Log.d(TAG, "press " + this.name);
            return true;
        }

        return false;
    }

//    public boolean mouseReleased(float worldX, float worldY) {
//        if ((Math.abs(x - worldX) < 10) && (Math.abs(y - worldY) < 10)) {
//            selected = false;
//            Log.d(TAG, "release " + this.name);
//            return true;
//        }
//
//        return false;
//    }

    public boolean mouseDragged(float worldX, float worldY) {
        if (selected) {
            this.x = worldX;
            this.y = worldY;
            Log.d(TAG, String.format("Move %s to %.0f, %.0f", name, x, y));
            return true;
        }
        return false;
    }

    public void clearSelection() {
        selected = false;
    }

    public void move() {
        // locate at the minimum energy point given the pull of it's offer's tags
//        new time_simeq();

    }
}
