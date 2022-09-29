package com.ppltech.papelprint.LayoutPrinter;

import android.content.Context;

import com.ppltech.papelprint.PosParameters;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class Parameters extends PosParameters {
    public static final String KEY_FONT_NAME = "font-name";
    public static final String KEY_FONT_SIZE = "font-size";
    public static final String KEY_FONT_EFFECT = "font-effect";
    public static final String KEY_FONT_FLAGS = "font-flags";
    public static final String KEY_PRINT_GRAY = "print-gray";
    public static final String KEY_PRINT_ALIGN = "print-align";
    public static final String KEY_PRINT_HEATPOINT = "print-heatpoint";
    public static final String KEY_PRINT_STEP = "print-step";
    public static final String KEY_LINE_SPACE = "line-space";
    public static final String KEY_CHAR_SPACE = "char-space";
    public static final String KEY_DETECT_BLACKMARK = "detect-blackmark";
    public static final String KEY_DETECT_BLACKMARK_STEP = "detect-blackmark-step";
    public static final String KEY_PRINT_STEP_BY_KEYSTOKE = "print-step-by-keystoke";
    public static final String KEY_PRINT_APPEND_TEXT = "print-append-text";
    public static final String KEY_FONT_SCALE = "font-scale";
    public static final int ALIGH_LEFT = 0;
    public static final int ALIGH_CENTER = 1;
    public static final int ALIGH_RIGHT = 2;
    public static final int EFFECT_NONE = 0;
    public static final int EFFECT_BOLD = 1;
    public static final int EFFECT_LEAN = 2;
    public static final int EFFECT_BOLD_LEAN = 3;
    private Context context;
    public Parameters() {
    }

    public void setFontName(String fontName) {
        this.set("font-name", fontName);
    }

    public String getFontName() {
        return this.get("font-name");
    }

    public void setFontSize(int size) {
        this.set("font-size", size);
    }

    public int getFontSize() {
        return this.getInt("font-size");
    }

    public void setFontEffet(int effect) {
        this.set("font-effect", effect);
    }

    public int getFontEffet() {
        return this.getInt("font-effect");
    }

    public void setFontFlags(int flags) {
        this.set("font-flags", flags);
    }

    public int getFontFlags() {
        return this.getInt("font-flags");
    }

    public void setPrintGray(int gray) {
        this.set("print-gray", gray);
    }

    public int getPrintGray() {
        return this.getInt("print-gray");
    }

    public void setPrintAlign(int align) {
        this.set("print-align", align);
    }

    public int getPrintAlign() {
        return this.getInt("print-align");
    }

    public void setPrintHeatPoint(int heatpoint) {
        this.set("print-heatpoint", heatpoint);
    }

    public int getPrintHeatPoint() {
        return this.getInt("print-heatpoint");
    }

    public void setPrintStep(int step) {
        this.set("print-step", step);
    }

    public int getPrintStep() {
        return this.getInt("print-step");
    }

    public void setLineSpace(int space) {
        this.set("line-space", space);
    }

    public int getLineSpace() {
        return this.getInt("line-space");
    }

    public void setCharSpace(int space) {
        this.set("char-space", space);
    }

    public int getCharSpace() {
        return this.getInt("char-space");
    }

    /** @deprecated */
    @Deprecated
    public void setDetectBlackMark(int blackMark, int step) {
        this.set("detect-blackmark", blackMark);
        this.set("detect-blackmark-step", step);
    }

    /** @deprecated */
    @Deprecated
    public int getDetectBlackMark() {
        return this.getInt("detect-blackmark");
    }

    /** @deprecated */
    @Deprecated
    public int getDetectBlackMarkStep() {
        return this.getInt("detect-blackmark-step");
    }

    public void setPrintStepByKeyStroke(int value) {
        this.set("print-step-by-keystoke", value);
    }

    public int getPrintStepByKeyStroke() {
        return this.getInt("print-step-by-keystoke");
    }

    public void setPrintAppendText(boolean value) {
        this.set("print-append-text", value);
    }

    public boolean getPrintAppendText() {
        return this.getBoolean("print-append-text");
    }

    public void setFontScale(float value) {
        this.set("font-scale", value);
    }

    public float getFontScale() {
        return this.getFloat("font-scale");
    }
}

