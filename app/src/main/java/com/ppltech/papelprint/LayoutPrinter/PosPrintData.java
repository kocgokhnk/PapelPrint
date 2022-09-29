package com.ppltech.papelprint.LayoutPrinter;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class PosPrintData implements Parcelable {
    public String mParameters;
    public String mText;
    public String mImagePath;
    public Bitmap mBitmap;
    public static final Creator<PosPrintData> CREATOR = new Creator<PosPrintData>() {
        public PosPrintData createFromParcel(Parcel source) {
            String param = source.readString();
            String text = source.readString();
            String path = source.readString();
            Bitmap bmp;
            if (0 != source.readInt()) {
                bmp = (Bitmap)Bitmap.CREATOR.createFromParcel(source);
            } else {
                bmp = null;
            }

            return new PosPrintData(param, text, path, bmp);
        }

        public PosPrintData[] newArray(int size) {
            return new PosPrintData[size];
        }
    };

    public PosPrintData() {
        this.mParameters = null;
        this.mText = null;
        this.mImagePath = null;
        this.mBitmap = null;
    }

    public PosPrintData(String paramters, String text, String path, Bitmap bitmap) {
        this.mParameters = paramters;
        this.mText = text;
        this.mImagePath = path;
        this.mBitmap = bitmap;
    }

    public PosPrintData(PosPrintData o) {
        this.mParameters = o.mParameters;
        this.mText = o.mText;
        this.mImagePath = o.mImagePath;
        this.mBitmap = o.mBitmap;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mParameters);
        dest.writeString(this.mText);
        dest.writeString(this.mImagePath);
        if (this.mBitmap != null) {
            dest.writeInt(1);
            this.mBitmap.writeToParcel(dest, 0);
        } else {
            dest.writeInt(0);
        }

    }

    public void readFromParcel(Parcel source) {
        this.mParameters = source.readString();
        this.mText = source.readString();
        this.mImagePath = source.readString();
        if (0 != source.readInt()) {
            this.mBitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(source);
        } else {
            this.mBitmap = null;
        }

    }

    public void copyFrom(PosPrintData o) {
        if (o.mParameters != null) {
            this.mParameters = new String(o.mParameters);
        } else {
            this.mParameters = null;
        }

        if (o.mText != null) {
            this.mText = new String(o.mText);
        } else {
            this.mText = null;
        }

        if (o.mImagePath != null) {
            this.mImagePath = new String(o.mImagePath);
        } else {
            this.mImagePath = null;
        }

        if (o.mBitmap != null) {
            this.mBitmap = Bitmap.createBitmap(o.mBitmap);
        } else {
            this.mBitmap = null;
        }

    }

    public String toString() {
        StringBuilder builder = new StringBuilder("PosPrintData:");
        builder.append("mParameters= " + this.mParameters + ", ");
        builder.append("mText= " + this.mText + ", ");
        builder.append("mImagePath= " + this.mImagePath + ", ");
        builder.append("mBitmap= " + (this.mBitmap != null ? "true" : "false"));
        return builder.toString();
    }
}
