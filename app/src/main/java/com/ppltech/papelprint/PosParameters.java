package com.ppltech.papelprint;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Gökhan Koç on 28,Eylül,2022
 */
public class PosParameters {
    private static final String TAG = "PosParameters";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    protected final LinkedHashMap<String, String> mMap = new LinkedHashMap(64);

    public PosParameters() {
    }

    /** @deprecated */
    public void dump() {
        //PosLog.e("PosParameters", "dump: size=" + this.mMap.size());
        Iterator var1 = this.mMap.keySet().iterator();

        while(var1.hasNext()) {
            String k = (String)var1.next();
            //PosLog.e("PosParameters", "dump: " + k + "=" + (String)this.mMap.get(k));
        }

    }

    public String flatten() {
        StringBuilder flattened = new StringBuilder(128);
        Iterator var2 = this.mMap.keySet().iterator();

        while(var2.hasNext()) {
            String k = (String)var2.next();
            flattened.append(k);
            flattened.append("=");
            flattened.append((String)this.mMap.get(k));
            flattened.append(";");
        }

        if (flattened.length() > 0) {
            flattened.deleteCharAt(flattened.length() - 1);
        }

        return flattened.toString();
    }

    public void unflatten(String flattened) {
        this.mMap.clear();
        TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(';');
        splitter.setString(flattened);
        Iterator var3 = splitter.iterator();

        while(var3.hasNext()) {
            String kv = (String)var3.next();
            int pos = kv.indexOf(61);
            if (pos != -1) {
                String k = kv.substring(0, pos);
                String v = kv.substring(pos + 1);
                this.mMap.put(k, v);
            }
        }

    }

    public void remove(String key) {
        this.mMap.remove(key);
    }

    public void set(String key, String value) {
        if (key.indexOf(61) == -1 && key.indexOf(59) == -1 && key.indexOf(0) == -1) {
            if (value.indexOf(61) == -1 && value.indexOf(59) == -1 && value.indexOf(0) == -1) {
                this.put(key, value);
            } else {
                //PosLog.e("PosParameters", "Value \"" + value + "\" contains invalid character (= or ; or \\0)");
            }
        } else {
            //PosLog.e("PosParameters", "Key \"" + key + "\" contains invalid character (= or ; or \\0)");
        }
    }

    public void set(String key, int value) {
        this.put(key, Integer.toString(value));
    }

    public void set(String key, boolean value) {
        this.put(key, Boolean.toString(value));
    }

    public void set(String key, float value) {
        this.put(key, Float.toString(value));
    }

    private void put(String key, String value) {
        this.mMap.remove(key);
        this.mMap.put(key, value);
    }

    public String get(String key) {
        return (String)this.mMap.get(key);
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt((String)this.mMap.get(key));
        } catch (NumberFormatException var3) {
            return -1;
        }
    }

    public boolean getBoolean(String key) {
        try {
            return Boolean.parseBoolean((String)this.mMap.get(key));
        } catch (NumberFormatException var3) {
            return false;
        }
    }

    public float getFloat(String key) {
        try {
            return Float.parseFloat((String)this.mMap.get(key));
        } catch (NumberFormatException var3) {
            return -1.0F;
        }
    }
}

