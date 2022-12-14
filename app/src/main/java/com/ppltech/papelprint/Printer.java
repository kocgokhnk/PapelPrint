package com.ppltech.papelprint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.ppltech.papelprint.Interface.PosPrintInerface;
import com.ppltech.papelprint.LayoutPrinter.BitmapPrintLine;
import com.ppltech.papelprint.LayoutPrinter.PrintLine;
import com.ppltech.papelprint.LayoutPrinter.TextPrintLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class Printer {
    private static final String TAG = "Printer";

    public static void printerTest(Context context, PosPrintInerface callPrinter) {
        final POIPrinterManager printerManager = new POIPrinterManager(context,callPrinter);

        printerManager.setPrintGray(2000);
        printerManager.setLineSpace(5);
        //printerManager.cleanCache();
        String str1 = "This is an example of a receipt";
        PrintLine p1 = new TextPrintLine(str1, PrintLine.CENTER);
        printerManager.addPrintLine(p1);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.shopping_mall);
        printerManager.addPrintLine(new BitmapPrintLine(bitmap, PrintLine.CENTER));
        printerManager.setPrintFont("/system/fonts/ComingSoon.ttf");
        String str2 = "Floor ** , Building **, No.*** LONG DONG Avenue, Pudong New District, Shanghai, China";
        PrintLine p2 = new TextPrintLine(str2, PrintLine.LEFT, 20);
        printerManager.addPrintLine(p2);
        printerManager.setPrintFont("/system/fonts/DroidSansMono.ttf");
        List<TextPrintLine> list1 = printList("24 June 2025", "     Assistant 6", "815002", 18, false);
        printerManager.addPrintLine(list1);
        List<TextPrintLine> list2 = printList("Item", "Quantity", "Price", 24, true);
        printerManager.addPrintLine(list2);
        List<TextPrintLine> list3 = printList("Tomato", "1", "$2.08", 24, false);
        printerManager.addPrintLine(list3);
        List<TextPrintLine> list4 = printList("Orange", "1", "$1.06", 24, false);
        printerManager.addPrintLine(list4);
        PrintLine p3 = new TextPrintLine("Total  $3.14", PrintLine.RIGHT);
        printerManager.addPrintLine(p3);
        printerManager.addPrintLine(new TextPrintLine(""));
        // drawable/barcode.jpg  100*100
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.barcode);
        printerManager.addPrintLine(new BitmapPrintLine(bitmap, PrintLine.CENTER));
        printerManager.addPrintLine(new TextPrintLine(""));
        String str3 = "Did you know you could have earned Rewards points on this purchase?";
        PrintLine p4 = new TextPrintLine(str3, PrintLine.CENTER);
        printerManager.addPrintLine(p4);
        PrintLine p5 = new TextPrintLine("Simply sign up today for a Membership Card!", PrintLine.CENTER);
        printerManager.addPrintLine(p5);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.barcode_12345);
        printerManager.addPrintLine(new BitmapPrintLine(bitmap, PrintLine.CENTER));
        printerManager.addPrintLine(new TextPrintLine(" ", 0, 100));
        POIPrinterManager.IPrinterListener listener = new POIPrinterManager.IPrinterListener() {
            @Override
            public void onStart() {}

            @Override
            public void onFinish() {
                //printerManager.cleanCache();
            }

            @Override
            public void onError(int code, String msg) {
                Log.e("POIPrinterManager", "code: " + code + "msg: " + msg);
            }
        };

        printerManager.beginPrint(listener);

    }

    private static List<TextPrintLine> printList(String leftStr, String centerStr, String rightStr, int size, boolean bold){
        TextPrintLine textPrintLine1 = new TextPrintLine(leftStr, PrintLine.LEFT, size, bold);
        TextPrintLine textPrintLine2 = new TextPrintLine(centerStr,PrintLine.CENTER, size, bold);
        TextPrintLine textPrintLine3 = new TextPrintLine(rightStr, PrintLine.RIGHT, size, bold);
        List<TextPrintLine> list = new ArrayList<>();
        list.add(textPrintLine1);
        list.add(textPrintLine2);
        list.add(textPrintLine3);
        return list;
    }
}

