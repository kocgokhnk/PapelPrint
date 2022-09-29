package com.ppltech.papelprint;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class PosPrintStateInfo implements Parcelable {
    public int mState;
    public int mCurX;
    public int mCurY;
    public int mLastX;
    public int mLastY;
    public static final Creator<PosPrintStateInfo> CREATOR = new Creator<PosPrintStateInfo>() {
        public PosPrintStateInfo createFromParcel(Parcel source) {
            int state = source.readInt();
            int x = source.readInt();
            int y = source.readInt();
            int lastX = source.readInt();
            int lastY = source.readInt();
            return new PosPrintStateInfo(state, x, y, lastX, lastY);
        }

        public PosPrintStateInfo[] newArray(int size) {
            return new PosPrintStateInfo[size];
        }
    };

    public PosPrintStateInfo() {
        this.mState = 0;
        this.mCurX = 0;
        this.mCurY = 0;
        this.mLastX = 0;
        this.mLastY = 0;
    }

    public PosPrintStateInfo(int state, int x, int y, int lastX, int lastY) {
        this.mState = state;
        this.mCurX = x;
        this.mCurY = y;
        this.mLastX = lastX;
        this.mLastY = lastY;
    }

    public PosPrintStateInfo(PosPrintStateInfo o) {
        this.mState = o.mState;
        this.mCurX = o.mCurX;
        this.mCurY = o.mCurY;
        this.mLastX = o.mLastX;
        this.mLastY = o.mLastY;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mState);
        dest.writeInt(this.mCurX);
        dest.writeInt(this.mCurY);
        dest.writeInt(this.mLastX);
        dest.writeInt(this.mLastY);
    }

    public void readFromParcel(Parcel source) {
        this.mState = source.readInt();
        this.mCurX = source.readInt();
        this.mCurY = source.readInt();
        this.mLastX = source.readInt();
        this.mLastY = source.readInt();
    }

    public void copyFrom(PosPrintStateInfo o) {
        this.mState = o.mState;
        this.mCurX = o.mCurX;
        this.mCurY = o.mCurY;
        this.mLastX = o.mLastX;
        this.mLastY = o.mLastY;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("PosPrintStateInfo:");
        builder.append("mState= " + this.mState + ", ");
        builder.append("mCurX= " + this.mCurX + ", ");
        builder.append("mCurY= " + this.mCurY + ", ");
        builder.append("mLastX= " + this.mLastX + ", ");
        builder.append("mLastY= " + this.mLastY + ", ");
        return builder.toString();
    }
}
