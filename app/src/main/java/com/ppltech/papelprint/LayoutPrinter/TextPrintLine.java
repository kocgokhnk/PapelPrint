package com.ppltech.papelprint.LayoutPrinter;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class TextPrintLine extends PrintLine {
    public static final int FONT_SMALL = 16;
    public static final int FONT_NORMAL = 24;
    public static final int FONT_LARGE = 36;
    private String content;
    private int size;
    private boolean bold;
    private boolean italic;
    private boolean invert;

    public TextPrintLine() {
        this((String)null, 0, 24, false, false, false);
    }

    public TextPrintLine(String content) {
        this(content, 0, 24, false, false, false);
    }

    public TextPrintLine(String content, int position) {
        this(content, position, 24, false, false, false);
    }

    public TextPrintLine(String content, int position, int size) {
        this(content, position, size, false, false, false);
    }

    public TextPrintLine(String content, int position, int size, boolean bold) {
        this(content, position, size, bold, false, false);
    }

    public TextPrintLine(String content, int position, int size, boolean bold, boolean italic) {
        this(content, position, size, bold, italic, false);
    }

    public TextPrintLine(String content, int position, int size, boolean bold, boolean italic, boolean invert) {
        this.type = 0;
        this.content = content;
        this.position = position;
        this.size = size;
        this.bold = bold;
        this.italic = italic;
        this.invert = invert;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isBold() {
        return this.bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return this.italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isInvert() {
        return this.invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    public String toString() {
        return "type:" + this.type + ",size:" + this.size + ",bold:" + this.bold + ",italic:" + this.italic + ",invert:" + this.invert + ",content:" + this.content;
    }
}
