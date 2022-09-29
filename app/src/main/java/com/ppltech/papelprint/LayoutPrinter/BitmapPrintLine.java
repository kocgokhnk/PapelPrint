package com.ppltech.papelprint.LayoutPrinter;

import android.graphics.Bitmap;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class BitmapPrintLine extends PrintLine {
    private Bitmap bitmap;

    public BitmapPrintLine() {
        this((Bitmap)null, 1);
    }

    public BitmapPrintLine(Bitmap bitmap) {
        this(bitmap, 1);
    }

    public BitmapPrintLine(Bitmap bitmap, int position) {
        this.type = 1;
        this.bitmap = bitmap;
        this.position = position;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
