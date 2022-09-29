package com.ppltech.papelprint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.ppltech.papelprint.Interface.PosPrintInerface;
import com.ppltech.papelprint.LayoutPrinter.BitmapPrintLine;
import com.ppltech.papelprint.LayoutPrinter.Parameters;
import com.ppltech.papelprint.LayoutPrinter.PrintLine;
import com.ppltech.papelprint.LayoutPrinter.PrinterLayout;
import com.ppltech.papelprint.LayoutPrinter.TextPrintLine;
import com.ppltech.papelprint.PosPrinter.EventListener;

import java.util.List;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class POIPrinterManager {
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_PRINTING = 1;
    public static final int STATUS_OVERHEAT = 2;
    public static final int STATUS_NO_PAPER = 3;
    public static final int STATUS_NO_PRINTER = 4;
    public static final int ERROR_INIT = -1;
    public static final int ERROR_PRINT = -2;
    public static final int ERROR_OVERHEAT = -3;
    public static final int ERROR_NO_PAPER = -4;
    public static final int ERROR_OTHER = -999;
    private static final int PRINTER_LENGTH_SCALE = 2;
    private static final int PRINTER_LENGTH_SCALE_HEIGHT = 8;
    private Context context;
    private int openId;
    private volatile int status;
    private PosPrinter printer;
    private PrinterLayout printerLayout;
    private POIPrinterManager.IPrinterListener listener;
    private POIPrinterManager.ViewToBitmapListener viewToBitmapListener;

    public PosPrintInerface callPrinter;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            POIPrinterManager.this.printer = null;
            POIPrinterManager.this.status = 4;

                POIPrinterManager.this.printer = PosPrinter.open(POIPrinterManager.this.openId);
                POIPrinterManager.this.status = 0;


        }
    };
    private EventListener eventListener = new EventListener() {
        public void onCursorChanged(PosPrinter posPrinter, int x, int y, int lastX, int lastY) {
        }

        @Override
        public void onInfo(PosPrinter var1, int var2, int var3) {
            PosPrintStateInfo statInfo = new PosPrintStateInfo();
            printer.getPrintStateInfo(statInfo);
            switch(statInfo.mState) {
                case 1:
                    if (POIPrinterManager.this.listener != null) {
                        POIPrinterManager.this.listener.onFinish();
                    }
                    break;
                case 3:
                    if (POIPrinterManager.this.listener != null) {
                        POIPrinterManager.this.listener.onStart();
                    }
            }
        }

        public void onError(PosPrinter printer, int what, int extra) {
            switch(what) {
                case 1:
                    if (POIPrinterManager.this.listener != null) {
                        POIPrinterManager.this.listener.onError(-3, "Printer overheat.");
                    }
                    break;
                case 2:
                    if (POIPrinterManager.this.listener != null) {
                        POIPrinterManager.this.listener.onError(-4, "Printer no paper.");
                    }
                    break;
                default:
                    if (POIPrinterManager.this.listener != null) {
                        POIPrinterManager.this.listener.onError(-999, "Printer other error.");
                    }
            }

        }

    };

    public POIPrinterManager(Context context,PosPrintInerface callPrinter) {
        this.context = context;
        this.openId = 0;
        this.status = 4;
        this.viewToBitmapListener = new POIPrinterManager.ViewToBitmapListener();
        this.callPrinter = callPrinter;
    }

    public void setPrintGray(int gray) {
        if (this.printer != null) {
            Parameters printParams = this.printer.getParameters();
            printParams.setPrintGray(gray);
            this.printer.setParameters(printParams);
        }
    }

    public void setPrintFont(String path) {
        if (this.printerLayout == null) {
            this.printerLayout = new PrinterLayout(this.context);
        }

        this.printerLayout.setFontPath(path);
    }

    public void setLineSpace(int line) {
        if (this.printerLayout == null) {
            this.printerLayout = new PrinterLayout(this.context);
        }

        this.printerLayout.setLineSpace(line);
    }

    public void addPrintLine(PrintLine line) {
        if (this.printerLayout == null) {
            this.printerLayout = new PrinterLayout(this.context);
        }

        switch(line.getType()) {
            case 0:
                this.printerLayout.addText((TextPrintLine)line);
                break;
            case 1:
                this.printerLayout.addBitmap((BitmapPrintLine)line);
        }

    }

    public void addPrintLine(List<TextPrintLine> line) {
        if (this.printerLayout == null) {
            this.printerLayout = new PrinterLayout(this.context);
        }

        this.printerLayout.addText(line);
    }
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        int threshold = 127;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int totalPixels = width * height;
        int[] pixels = new int[totalPixels];
        byte[] data = new byte[totalPixels / 8];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        for(int i = 0; i < totalPixels; ++i) {
            int color = pixels[i];
            int red = (color & 16711680) >> 16;
            int green = (color & '\uff00') >> 8;
            int blue = color & 255;
            int gray = (red + green + blue) / 3;
            if (gray <= threshold) {
                data[i / 8] = (byte)(data[i / 8] | 1 << 7 - i % 8);
            } else {
                data[i / 8] = (byte)(data[i / 8] & ~(1 << 7 - i % 8));
            }
        }

        return data;
    }

    public void beginPrint(POIPrinterManager.IPrinterListener printerListener) {
        this.listener = printerListener;
        if (this.listener != null &&  this.printerLayout != null) {
            this.printerLayout.viewToBitmap(this.viewToBitmapListener);
        } else {
            if (this.listener != null) {
                this.listener.onError(-1, "Printer init failed.");
            }

        }
    }


    public interface IPrinterListener {
        void onStart();

        void onFinish();

        void onError(int var1, String var2);
    }

    class ViewToBitmapListener implements PrinterLayout.ViewToBitmapListener {
        ViewToBitmapListener() {
        }

        public void success(Bitmap bitmap) {
            if (bitmap != null ) {
                //byte[] data = POIPrinterManager.this.bitmapToByteArray(bitmap);



                callPrinter.callPrinter(bitmap);

            } else {
                this.failure();
            }
        }

        public void failure() {
            POIPrinterManager.this.listener.onError(-2, "Printer bitmap failed.");
        }
    }

}