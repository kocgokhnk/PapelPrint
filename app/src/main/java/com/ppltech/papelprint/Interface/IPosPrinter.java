package com.ppltech.papelprint.Interface;

import android.graphics.Bitmap;
import android.os.IInterface;
import android.os.RemoteException;

import com.ppltech.papelprint.LayoutPrinter.PosPrintData;
import com.ppltech.papelprint.PosPrintStateInfo;

import java.util.List;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public interface IPosPrinter extends IInterface {


    void registerListener(IPosPrinterListener var1) throws RemoteException;

    void unregisterListener(IPosPrinterListener var1) throws RemoteException;

    void release() throws RemoteException;

    void setParameters(String var1) throws RemoteException;

    String getParameters() throws RemoteException;


    int setCursor(int var1, int var2) throws RemoteException;

    int addTextToCache(String var1, int var2, int var3) throws RemoteException;

    int addTextToCurCache(String var1) throws RemoteException;

    int addBmpToCache(String var1, int var2, int var3) throws RemoteException;

    int addBmpToCurCache(String var1) throws RemoteException;

    int cleanCache() throws RemoteException;

    int print() throws RemoteException;

    int printText(String var1) throws RemoteException;

    int printBmp(String var1) throws RemoteException;

    int setPrintCtrlFeed(int var1, int var2, int var3) throws RemoteException;

    int addRawDataToCache(byte[] var1) throws RemoteException;

    int printRawData(byte[] var1) throws RemoteException;

    int addBitmapToCache(Bitmap var1) throws RemoteException;

    int printBitmap(Bitmap var1) throws RemoteException;

    int addBatchDataToCache(List<PosPrintData> var1) throws RemoteException;

    int printBatchData(List<PosPrintData> var1) throws RemoteException;

    int getTotalPixLineCnt() throws RemoteException;

    int getBeforePixLineCnt() throws RemoteException;

    long getCurrentEndPrintTime() throws RemoteException;


    void getPrintStateInfo(PosPrintStateInfo var1) throws RemoteException;
}