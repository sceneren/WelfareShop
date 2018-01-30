package com.quduo.welfareshop.util;


import com.quduo.welfareshop.db.BookList;
import com.quduo.welfareshop.ui.read.listener.OnSaveData2DBListener;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;

import java.util.List;

/**
 * Author:scene
 * Time:2018/1/30 17:46
 * Description:小说工具类
 */
public class ReaderUtil {
    /**
     * Author:scene
     * Time:2018/1/30 18:04
     * Description:保存书籍到数据库
     */
    public static void addBook2DB(final BookList book, final OnSaveData2DBListener listener) {
        DataSupport.where("bookpath = ?", book.getBookpath()).findAsync(BookList.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                if (t.size() > 0) {
                    listener.onSaveRepeat();
                } else {
                    book.saveAsync().listen(new SaveCallback() {
                        @Override
                        public void onFinish(boolean success) {
                            if (success) {
                                listener.onSaveSuccess();
                            } else {
                                listener.onSaveFail();
                            }
                        }
                    });
                }
            }
        });

    }

}
