package com.ppltech.papelprint;

import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;

import com.ppltech.papelprint.Interface.IPosPrinter;
import com.ppltech.papelprint.Interface.IPosPrinterListener;
import com.ppltech.papelprint.LayoutPrinter.Parameters;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class PosPrinter {
    private static final String TAG = "PosPrinter";
    private IBinder mICallBack;
    private IPosPrinter mPosPrinter;
    private PosPrinter.EventListener mEventListener;
    private PosPrinter.EventHandler mEventHandler;
    private PosPrinter.PrinterListener mPrinterListener;

    public void getPrintStateInfo(PosPrintStateInfo info) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                this.mPosPrinter.getPrintStateInfo(info);
            } catch (RemoteException var3) {
                var3.printStackTrace();
            }

        }
    }
    public static PosPrinter open(int id) {
        return new PosPrinter(id);
    }
    private PosPrinter(int id) {
        try {
            this.mICallBack = new Binder();
            Looper looper;
            if ((looper = Looper.myLooper()) != null) {
                this.mEventHandler = new PosPrinter.EventHandler(this, looper);
            } else if ((looper = Looper.getMainLooper()) != null) {
                this.mEventHandler = new PosPrinter.EventHandler(this, looper);
            } else {
                this.mEventHandler = null;
            }

            this.mPrinterListener = new PosPrinter.PrinterListener();
            this.mPosPrinter.registerListener(this.mPrinterListener);
        } catch (RemoteException var3) {
            this.mPosPrinter = null;
        }

        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
           // PosUtils.checkVersion("PosPrinter");
        }
    }

    public void setParameters(Parameters params) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                this.mPosPrinter.setParameters(params.flatten());
            } catch (RemoteException var3) {
                var3.printStackTrace();
            }

        }
    }

    public Parameters getParameters() {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                Parameters p = new Parameters();
                String s = this.mPosPrinter.getParameters();
                p.unflatten(s);
                return p;
            } catch (RemoteException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }


    /** @deprecated */
    @Deprecated
    public int setCursor(int x, int y) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.setCursor(x, y);
            } catch (RemoteException var4) {
                var4.printStackTrace();
                return -1;
            }
        }
    }

    /** @deprecated */
    @Deprecated
    public int addTextToCache(String str, int x, int y) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.addTextToCache(str, x, y);
            } catch (RemoteException var5) {
                var5.printStackTrace();
                return -1;
            }
        }
    }

    public int addTextToCurCache(String str) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.addTextToCurCache(str);
            } catch (RemoteException var3) {
                var3.printStackTrace();
                return -1;
            }
        }
    }

    /** @deprecated */
    @Deprecated
    public int addBmpToCache(String path, int x, int y) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.addBmpToCache(path, x, y);
            } catch (RemoteException var5) {
                var5.printStackTrace();
                return -1;
            }
        }
    }

    public int addBmpToCurCache(String path) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.addBmpToCurCache(path);
            } catch (RemoteException var3) {
                var3.printStackTrace();
                return -1;
            }
        }
    }

    public int cleanCache() {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.cleanCache();
            } catch (RemoteException var2) {
                var2.printStackTrace();
                return -1;
            }
        }
    }

    public int print() {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.print();
            } catch (RemoteException var2) {
                var2.printStackTrace();
                return -1;
            }
        }
    }

    public int printText(String str) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.printText(str);
            } catch (RemoteException var3) {
                var3.printStackTrace();
                return -1;
            }
        }
    }

    public int printBmp(String path) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.printBmp(path);
            } catch (RemoteException var3) {
                var3.printStackTrace();
                return -1;
            }
        }
    }


    public int setPrintCtrlFeed(int potNum, int detectBlackMark, int detectBlackMarkThreshold) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.setPrintCtrlFeed(potNum, detectBlackMark, detectBlackMarkThreshold);
            } catch (RemoteException var5) {
                var5.printStackTrace();
                return -1;
            }
        }
    }

    public int addRawDataToCache(byte[] rawData) {
        if (this.mPosPrinter == null) {
            throw new NullPointerException("mPosPrinter is null!!");
        } else {
            try {
                return this.mPosPrinter.addRawDataToCache(rawData);
            } catch (RemoteException var3) {
                var3.printStackTrace();
                return -1;
            }
        }
    }


    public void setOnEventListener(PosPrinter.EventListener listener) {
        this.mEventListener = listener;
    }



    public interface EventListener {
        void onInfo(PosPrinter var1, int var2, int var3);

        void onError(PosPrinter var1, int var2, int var3);

        void onCursorChanged(PosPrinter var1, int var2, int var3, int var4, int var5);
    }


    private class EventHandler extends Handler {
        private PosPrinter mPrinter;

        public EventHandler(PosPrinter c, Looper looper) {
            super(looper);
            this.mPrinter = c;
        }


    }
    private class PrinterListener extends IPosPrinterListener.Stub {
        private PrinterListener() {
        }

        public void onInfo(int what, int extra) {
            if (PosPrinter.this.mEventHandler != null) {
                PosPrinter.this.mEventHandler.sendMessage(PosPrinter.this.mEventHandler.obtainMessage(1, what, extra));
            }

        }

        public void onError(int what, int extra) {
            if (PosPrinter.this.mEventHandler != null) {
                PosPrinter.this.mEventHandler.sendMessage(PosPrinter.this.mEventHandler.obtainMessage(2, what, extra));
            }

        }

        public void onCursorChanged(int x, int y, int lastX, int lastY) {
            if (PosPrinter.this.mEventHandler != null) {
                int offsetx = 0;
                byte[] array = new byte[16];

                PosPrinter.this.mEventHandler.sendMessage(PosPrinter.this.mEventHandler.obtainMessage(2, array));
            }

        }
    }
}
