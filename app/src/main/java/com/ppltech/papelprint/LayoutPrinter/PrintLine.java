package com.ppltech.papelprint.LayoutPrinter;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class PrintLine {
    public static final int TEXT = 0;
    public static final int BITMAP = 1;
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    protected int type;
    protected int position;

    public PrintLine() {
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

