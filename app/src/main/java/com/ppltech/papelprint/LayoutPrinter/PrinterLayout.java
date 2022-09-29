package com.ppltech.papelprint.LayoutPrinter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class PrinterLayout extends LinearLayout {
    private static final int BITMAP_WIDTH = 384;
    private static final int BITMAP_HEIGHT = 0;
    private static final int BITMAP_MAX_WIDTH = 384;
    private static final int BITMAP_MAX_HEIGHT = 100000;
    private int lineSpace;
    private int bottomSpace;
    private int bitmapMaxWidth;
    private int bitmapMaxHeight;
    private Context context;
    private LayoutParams layoutParams;
    private String fontPath;
    private static Hashtable<String, Typeface> fontCache = new Hashtable();

    public PrinterLayout(Context context) {
        this(context, (AttributeSet)null);
    }

    public PrinterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PrinterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.bitmapMaxWidth = 384;
        this.bitmapMaxHeight = 100000;
        this.setOrientation(1);
        this.layoutParams = new LayoutParams(-1, -2);
    }

    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom + this.bottomSpace);
    }

    public Typeface getFont() {
        if (!fontCache.isEmpty() && this.fontPath != null) {
            Typeface typeface = (Typeface)fontCache.get(this.fontPath);
            return typeface != null ? typeface : Typeface.DEFAULT;
        } else {
            return Typeface.DEFAULT;
        }
    }

    public void setFontPath(String path) {
        this.fontPath = path;
        Typeface typeface = (Typeface)fontCache.get(path);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromFile(path);
            } catch (Exception var4) {
                return;
            }

            fontCache.put(path, typeface);
        }

    }

    public void setFontAsset(String path) {
        Typeface typeface = (Typeface)fontCache.get(path);
        if (typeface == null) {
            try {
                AssetManager manager = this.context.getAssets();
                typeface = Typeface.createFromAsset(manager, "fonts/" + path);
            } catch (Exception var4) {
                return;
            }

            this.fontPath = path;
            fontCache.put(path, typeface);
        }

    }

    public int getLineSpace() {
        return this.lineSpace;
    }

    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
    }

    public int getBottomSpace() {
        return this.bottomSpace;
    }

    public void setBottomSpace(int bottomSpace) {
        this.bottomSpace = bottomSpace;
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom() + bottomSpace);
    }

    public int getBitmapMaxWidth() {
        return this.bitmapMaxWidth;
    }

    public void setBitmapMaxWidth(int bitmapMaxWidth) {
        this.bitmapMaxWidth = bitmapMaxWidth;
    }

    public int getBitmapMaxHeight() {
        return this.bitmapMaxHeight;
    }

    public void setBitmapMaxHeight(int bitmapMaxHeight) {
        this.bitmapMaxHeight = bitmapMaxHeight;
    }

    public void addView(View view) {
        this.addView(view, this.layoutParams);
    }

    public void addTextView(TextView textView) {
        this.addView(textView, this.layoutParams);
    }

    public void addImageView(ImageView imageView) {
        this.addView(imageView, this.layoutParams);
    }

    public void addText(TextPrintLine printLine) {
        this.addView(this.getTextPrintLine(printLine), this.layoutParams);
    }

    public void addText(List<TextPrintLine> printLine) {
        RelativeLayout lineLinearLayout = new RelativeLayout(this.context);
        Iterator var3 = printLine.iterator();

        while(var3.hasNext()) {
            TextPrintLine line = (TextPrintLine)var3.next();
            lineLinearLayout.addView(this.getTextPrintLine(line), this.layoutParams);
        }

        this.addView(lineLinearLayout, this.layoutParams);
    }

    public void addBitmap(BitmapPrintLine printLine) {
        this.addView(this.getBitmapPrintLine(printLine), this.layoutParams);
    }

    private View getTextPrintLine(TextPrintLine printLine) {
        TextView view = new TextView(this.context);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom() + this.lineSpace);
        switch(printLine.getPosition()) {
            case 0:
                view.setGravity(8388611);
                break;
            case 2:
                view.setGravity(8388613);
                break;
            default:
                view.setGravity(17);
        }

        if (printLine.isInvert()) {
            view.setTextColor(-1);
            view.setBackgroundColor(-16777216);
        } else {
            view.setTextColor(-16777216);
            view.setBackgroundColor(0);
        }

        view.setTextSize(0, (float)printLine.getSize());
        view.setLineSpacing((float)this.lineSpace, 1.0F);
        view.getPaint().setAntiAlias(false);
        view.setPaintFlags(256);
        Typeface typeface = this.getFont();
        if (printLine.isBold() && printLine.isItalic()) {
            view.setTypeface(typeface, 3);
        } else if (printLine.isBold()) {
            view.setTypeface(typeface, 1);
        } else if (printLine.isItalic()) {
            view.setTypeface(typeface, 2);
        } else {
            view.setTypeface(typeface, 0);
        }

        if (printLine.getContent() != null) {
            view.setText(printLine.getContent());
        }

        return view;
    }

    private View getBitmapPrintLine(BitmapPrintLine printLine) {
        ImageView view = new ImageView(this.context);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom() + this.lineSpace);
        switch(printLine.getPosition()) {
            case 0:
                view.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case 2:
                view.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            default:
                view.setScaleType(ImageView.ScaleType.CENTER);
        }

        if (printLine.getBitmap() != null) {
            int width = printLine.getBitmap().getWidth();
            int height = printLine.getBitmap().getHeight();
            if (width <= this.bitmapMaxWidth && height <= this.bitmapMaxHeight) {
                view.setImageBitmap(printLine.getBitmap());
            } else {
                Bitmap scaleBitmap = this.scaleBitmap(printLine.getBitmap());
                view.setImageBitmap(scaleBitmap);
            }
        }

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0F);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        view.setColorFilter(colorFilter);
        return view;
    }

    private Bitmap scaleBitmap(Bitmap origin) {
        int width = origin.getWidth();
        int height = origin.getHeight();
        float scaleWidth = 384.0F / (float)width;
        float scaleHeight = 0.0F / (float)height;
        float scale = Math.max(scaleWidth, scaleHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
    }

    public void viewToBitmap(PrinterLayout.ViewToBitmapListener listener) {
        int measuredWidth = MeasureSpec.makeMeasureSpec(384, MeasureSpec.AT_MOST);
        int measuredHeight = MeasureSpec.makeMeasureSpec(100000, MeasureSpec.AT_MOST);
        this.measure(measuredWidth, measuredHeight);
        this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        int width = this.getWidth();
        int height = this.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(-1);
        this.invalidate();
        this.draw(canvas);
        listener.success(bitmap);
    }

    public interface ViewToBitmapListener {
        void success(Bitmap var1);

        void failure();
    }
}

