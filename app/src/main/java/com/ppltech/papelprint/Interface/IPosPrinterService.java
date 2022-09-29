package com.ppltech.papelprint.Interface;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public interface IPosPrinterService extends IInterface {
    int getNumberOfPrinters() throws RemoteException;



    IPosPrinter createPrinter(int var1, IBinder var2) throws RemoteException;

    void removePrinter(IPosPrinter var1, IBinder var2) throws RemoteException;
}
