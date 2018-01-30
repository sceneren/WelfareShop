package com.quduo.welfareshop.ui.read.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.Config;
import com.quduo.welfareshop.db.BookMarks;
import com.quduo.welfareshop.util.PageFactory;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 书签
 * Created by Administrator on 2016/1/3.
 */
public class MarkAdapter extends BaseAdapter {
    private Context mContext;
    private List<BookMarks> list;
    private Typeface typeface;
    private PageFactory pageFactory;

    private OnClickDeleteMarkListener onClickDeleteMarkListener;

    public MarkAdapter(Context context, List<BookMarks> list) {
        mContext = context;
        this.list = list;
        pageFactory = PageFactory.getInstance();
        Config config = Config.getInstance();
        typeface = config.getTypeface();
    }

    public void setOnClickDeleteMarkListener(OnClickDeleteMarkListener onClickDeleteMarkListener) {
        this.onClickDeleteMarkListener = onClickDeleteMarkListener;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        final MarkViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_bookmark, null);
            viewHolder = new MarkViewHolder(convertView);
            viewHolder.textMark.setTypeface(typeface);
            viewHolder.progress1.setTypeface(typeface);
            viewHolder.markTime.setTypeface(typeface);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MarkViewHolder) convertView.getTag();
        }
        viewHolder.textMark.setText(list.get(position).getText());
        long begin = list.get(position).getBegin();
        float fPercent = (float) (begin * 1.0 / pageFactory.getBookLen());
        DecimalFormat df = new DecimalFormat("#0.0");
        String strPercent = df.format(fPercent * 100) + "%";
        viewHolder.progress1.setText(strPercent);
        viewHolder.markTime.setText(list.get(position).getTime().substring(0, 16));
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickDeleteMarkListener != null) {
                    onClickDeleteMarkListener.onClickDeleteMark(viewHolder, position);
                }
            }
        });
        viewHolder.contentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickDeleteMarkListener != null) {
                    onClickDeleteMarkListener.onClickItemLayout(position);
                }
            }
        });

        return convertView;
    }

    public void closeItem(int position) {
    }


    public interface OnClickDeleteMarkListener {
        void onClickDeleteMark(MarkViewHolder holder, int position);

        void onClickItemLayout(int position);
    }

    public static class MarkViewHolder {
        @BindView(R.id.content_item)
        public LinearLayout contentItem;
        @BindView(R.id.text_mark)
        public TextView textMark;
        @BindView(R.id.progress1)
        public TextView progress1;
        @BindView(R.id.mark_time)
        public TextView markTime;
        @BindView(R.id.delete)
        public TextView delete;
        @BindView(R.id.swipe)
        public SwipeMenuLayout swipe;

        MarkViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
