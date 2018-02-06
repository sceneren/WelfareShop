package com.quduo.welfareshop.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.blankj.utilcode.util.LogUtils;
import com.quduo.welfareshop.greendao.gen.DaoMaster;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //----------------------------使用sql实现升级逻辑
        if (oldVersion == newVersion) {
            LogUtils.e("数据库是最新版本,无需升级");
            return;
        }
        LogUtils.e("数据库从版本" + oldVersion + "升级到版本" + newVersion);
        switch (oldVersion) {
            case 1:

            case 2:
            default:
                break;
        }
    }
}