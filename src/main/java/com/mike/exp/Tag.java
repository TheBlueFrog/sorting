package com.mike.exp;

import java.awt.*;

import static com.mike.exp.Drawing.worldToPixelX;
import static com.mike.exp.Drawing.worldToPixelY;

/**
 * Created by mike on 4/23/2017.
 */
public class Tag {

    private static final String TAG = Tag.class.getSimpleName();

    String tag;
    float x = 0;
    float y = 0;
    boolean selected;

    public Tag(String tag, float x, float y) {
        this.tag = tag;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.setColor(selected ? Color.ORANGE : Color.BLACK);
        g.drawChars(tag.toCharArray(),
                0, tag.length(),
                (int) Math.round(worldToPixelX(this.x)),
                (int) Math.round(worldToPixelY(this.y)));

    }

    public boolean mousePressed(float worldX, float worldY) {
        if ((Math.abs(x - worldX) < 10) && (Math.abs(y - worldY) < 10)) {
            selected = true;
            Log.d(TAG, "press " + this.tag);
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
            Log.d(TAG, String.format("Move %s to %.0f, %.0f", tag, x, y));
            return true;
        }
        return false;
    }

    public void clearSelection() {
        selected = false;
    }
}
