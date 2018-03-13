package com.quduo.welfareshop.ui.shop.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:scene
 * Time:2018/3/13 13:08
 * Description:选择商品型号的弹窗
 */

public class ChooseGoodsTypePopupwindow extends PopupWindow {
    private ImageView goodsImage;
    private TextView goodsName;
    private TextView goodsPrice;
    private TextView goodsScore;
    private ImageView close;
    private View closeView;
    private TagFlowLayout tagLayout;
    private ImageView numberLess;
    private ImageView numberAdd;
    private TextView number;
    private TextView btnConfirm;

    private Context context;
    private View contentView;
    private LayoutInflater inflater;

    private TagAdapter<String> tagAdapter;
    private List<String> list;

    private OnClickChooseTypeButtonListener onClickChooseTypeButtonListener;

    public ChooseGoodsTypePopupwindow(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void setOnClickChooseTypeButtonListener(OnClickChooseTypeButtonListener onClickChooseTypeButtonListener) {
        this.onClickChooseTypeButtonListener = onClickChooseTypeButtonListener;
    }

    private void init() {
        inflater = LayoutInflater.from(context);
        contentView = inflater.inflate(R.layout.popupwindow_choose_goods_type, null);
        setContentView(contentView);
        initConfig();
        initView();
        initListener();
    }

    private void initView() {
        goodsImage = contentView.findViewById(R.id.goods_image);
        goodsName = contentView.findViewById(R.id.goods_name);
        goodsPrice = contentView.findViewById(R.id.goods_price);
        goodsScore = contentView.findViewById(R.id.goods_score);
        close = contentView.findViewById(R.id.close);
        tagLayout = contentView.findViewById(R.id.tagLayout);
        numberLess = contentView.findViewById(R.id.number_less);
        numberAdd = contentView.findViewById(R.id.number_add);
        number = contentView.findViewById(R.id.number);
        btnConfirm = contentView.findViewById(R.id.btnConfirm);
        closeView = contentView.findViewById(R.id.close_view);
        list = new ArrayList<>();
        list.add("小号");
        list.add("大号");
        tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String item) {
                TextView tv = inflater.inflate(R.layout.layout_shop_tag, tagLayout, false).findViewById(R.id.tag);
                tv.setText(item);
                return tv;
            }
        };
        tagAdapter.setSelectedList(0);
        tagLayout.setAdapter(tagAdapter);

    }

    private void initListener() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickChooseTypeButtonListener != null) {
                    onClickChooseTypeButtonListener.onClickConfirm();
                }
            }
        });
    }

    private void initConfig() {
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setAnimationStyle(R.style.pop_animation);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
    }


    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public interface OnClickChooseTypeButtonListener {
        void onClickConfirm();
    }

}
