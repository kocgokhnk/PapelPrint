package com.ppltech.papelprint.Interface;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public interface IPosPrinterListener extends IInterface {
    void onInfo(int var1, int var2) throws RemoteException;

    void onError(int var1, int var2) throws RemoteException;

    void onCursorChanged(int var1, int var2, int var3, int var4) throws RemoteException;

    public abstract static class Stub extends Binder implements IPosPrinterListener {
        private static final String DESCRIPTOR = "com.pos.sdk.printer.IPosPrinterListener";
        static final int TRANSACTION_onInfo = 1;
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onCursorChanged = 3;

        public Stub() {
            this.attachInterface(this, "com.pos.sdk.printer.IPosPrinterListener");
        }

        public static IPosPrinterListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.pos.sdk.printer.IPosPrinterListener");
                return (IPosPrinterListener)(iin != null && iin instanceof IPosPrinterListener ? (IPosPrinterListener)iin : new IPosPrinterListener.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = "com.pos.sdk.printer.IPosPrinterListener";
            int _arg0;
            int _arg1;
            switch(code) {
                case 1:
                    data.enforceInterface(descriptor);
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    this.onInfo(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(descriptor);
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    this.onError(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(descriptor);
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    this.onCursorChanged(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(descriptor);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        public static boolean setDefaultImpl(IPosPrinterListener impl) {
            if (IPosPrinterListener.Stub.Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl != null) {
                IPosPrinterListener.Stub.Proxy.sDefaultImpl = impl;
                return true;
            } else {
                return false;
            }
        }

        public static IPosPrinterListener getDefaultImpl() {
            return IPosPrinterListener.Stub.Proxy.sDefaultImpl;
        }

        private static class Proxy implements IPosPrinterListener {
            private IBinder mRemote;
            public static IPosPrinterListener sDefaultImpl;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.pos.sdk.printer.IPosPrinterListener";
            }

            public void onInfo(int what, int extra) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.pos.sdk.printer.IPosPrinterListener");
                    _data.writeInt(what);
                    _data.writeInt(extra);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && IPosPrinterListener.Stub.getDefaultImpl() != null) {
                        IPosPrinterListener.Stub.getDefaultImpl().onInfo(what, extra);
                        return;
                    }

                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onError(int what, int extra) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.pos.sdk.printer.IPosPrinterListener");
                    _data.writeInt(what);
                    _data.writeInt(extra);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || IPosPrinterListener.Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        return;
                    }

                    IPosPrinterListener.Stub.getDefaultImpl().onError(what, extra);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void onCursorChanged(int x, int y, int lastX, int lastY) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.pos.sdk.printer.IPosPrinterListener");
                    _data.writeInt(x);
                    _data.writeInt(y);
                    _data.writeInt(lastX);
                    _data.writeInt(lastY);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && IPosPrinterListener.Stub.getDefaultImpl() != null) {
                        IPosPrinterListener.Stub.getDefaultImpl().onCursorChanged(x, y, lastX, lastY);
                        return;
                    }

                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }

    public static class Default implements IPosPrinterListener {
        public Default() {
        }

        public void onInfo(int what, int extra) throws RemoteException {
        }

        public void onError(int what, int extra) throws RemoteException {
        }

        public void onCursorChanged(int x, int y, int lastX, int lastY) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }
}

