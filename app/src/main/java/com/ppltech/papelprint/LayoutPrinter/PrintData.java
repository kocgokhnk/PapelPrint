package com.ppltech.papelprint.LayoutPrinter;

import android.graphics.Bitmap;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public final class PrintData {
    public Parameters parameter;
    public String textContent;
    public String imagePath;
    public Bitmap bitmap;

    public PrintData(boolean isText, String textOrBmpPath) {
        this((Parameters)null, isText ? textOrBmpPath : null, !isText ? textOrBmpPath : null, (Bitmap)null);
    }

    public PrintData(Parameters param, boolean isText, String textOrBmpPath) {
        this(param, isText ? textOrBmpPath : null, !isText ? textOrBmpPath : null, (Bitmap)null);
    }

    public PrintData(Bitmap bitmap) {
        this((Parameters)null, (String)null, (String)null, bitmap);
    }

    public PrintData(Parameters param, Bitmap bitmap) {
        this(param, (String)null, (String)null, bitmap);
    }

    public PrintData(Parameters param, String text, String bmpPath, Bitmap bmp) {
        this.parameter = param;
        this.textContent = text;
        this.imagePath = bmpPath;
        this.bitmap = bmp;
    }
}
