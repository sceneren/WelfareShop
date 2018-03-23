package com.quduo.welfareshop.ui.shop.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.shop.activity.ConfirmOrderActivity;
import com.quduo.welfareshop.ui.shop.entity.CreateOrderInfo;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailInfo;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/23 9:52
 * Description:选择商品类型
 */

public class ChooseGoodsTypeDialog extends BaseActivity {
    public static final String ARG_GOODSINFO = "goodsInfo";
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.goods_score)
    TextView goodsScore;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.goods_image)
    SelectableRoundedImageView goodsImage;
    @BindView(R.id.tagLayout)
    TagFlowLayout tagLayout;
    @BindView(R.id.layout_model)
    LinearLayout layoutModel;
    @BindView(R.id.number_less)
    ImageView numberLess;
    @BindView(R.id.number)
    TextView numberTv;
    @BindView(R.id.number_add)
    ImageView numberAdd;
    @BindView(R.id.btnConfirm)
    TextView btnConfirm;
    Unbinder unbinder;

    private GoodsDetailInfo detailInfo;

    private int number = 1;
    private String choosedModel = "";

    private List<String> goodsModelList;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow_choose_goods_type);
        unbinder = ButterKnife.bind(this);
        detailInfo = (GoodsDetailInfo) getIntent().getSerializableExtra(ARG_GOODSINFO);
        if (detailInfo == null) {
            onBackPressed();
            return;
        }
        init();
        initView();
    }

    private void init() {
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;//设置对话框置顶显示
        win.setAttributes(lp);
        setFinishOnTouchOutside(true);
    }

    private void initView() {
        inflater = LayoutInflater.from(ChooseGoodsTypeDialog.this);
        initModelLayout();
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_shop)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + detailInfo.getThumb())
                .into(goodsImage);
        goodsName.setText(detailInfo.getName());
        goodsPrice.setText(MessageFormat.format("￥{0}", detailInfo.getPrice()));
        Number num = Float.parseFloat(detailInfo.getPrice()) * 100;
        int giveNum = num.intValue() / 100;
        goodsScore.setText(MessageFormat.format("送{0}钻石+积分", giveNum));

    }

    private void initModelLayout() {
        if (null != detailInfo && null != detailInfo.getModel()) {
            goodsModelList = detailInfo.getModel();
            if (null == detailInfo.getModel() || detailInfo.getModel().size() == 0) {
                layoutModel.setVisibility(View.GONE);
                return;
            }
            layoutModel.setVisibility(View.VISIBLE);

            TagAdapter<String> modelAdapter = new TagAdapter<String>(goodsModelList) {
                @Override
                public View getView(FlowLayout parent, int position, String item) {
                    TextView tv = inflater.inflate(R.layout.layout_shop_tag, tagLayout, false).findViewById(R.id.tag);
                    tv.setText(item);
                    return tv;
                }
            };
            modelAdapter.setSelectedList(0);
            choosedModel = goodsModelList.get(0);
            tagLayout.setAdapter(modelAdapter);

            tagLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    if (selectPosSet.size() > 0) {
                        for (int position : selectPosSet) {
                            choosedModel = goodsModelList.get(position);
                        }
                    } else {
                        choosedModel = "";
                    }
                }
            });

        } else {
            layoutModel.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.close)
    public void onClickClose() {
        onBackPressed();
    }

    @OnClick(R.id.number_less)
    public void onClickNumberLess() {
        try {
            if (number > 1) {
                number -= 1;
            }
            numberTv.setText(String.valueOf(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.number_add)
    public void onClickNumAdd() {
        try {
            if (number < 100) {
                number += 1;
            }
            numberTv.setText(String.valueOf(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btnConfirm)
    public void onClickConfirm() {
        CreateOrderInfo orderInfo = new CreateOrderInfo();
        orderInfo.setGoodsId(detailInfo.getId());
        orderInfo.setGoodsName(detailInfo.getName());
        orderInfo.setThumb(detailInfo.getThumb());
        orderInfo.setGoodsPrice(detailInfo.getPrice());
        orderInfo.setChoosedNum(number);
        if (goodsModelList != null && goodsModelList.size() > 0 && StringUtils.isEmpty(choosedModel)) {
            ToastUtils.showShort("请选择您要购买的型号");
            return;
        } else {
            orderInfo.setChooseModel(choosedModel);
        }
        Intent intent = new Intent(ChooseGoodsTypeDialog.this, ConfirmOrderActivity.class);
        intent.putExtra(ConfirmOrderActivity.ARG_ORDER_INFO, orderInfo);
        startActivity(intent);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
