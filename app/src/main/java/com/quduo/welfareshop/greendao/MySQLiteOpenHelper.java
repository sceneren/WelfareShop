package com.quduo.welfareshop.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.blankj.utilcode.util.LogUtils;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.quduo.welfareshop.greendao.gen.ChatMessageInfoDao;
import com.quduo.welfareshop.greendao.gen.DaoMaster;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;

import org.greenrobot.greendao.database.Database;

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
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },ChatMessageInfoDao.class);
    }
}