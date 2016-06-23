package com.kevin.mirror.mainpage;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseFragment;

/**
 * Created by kevin on 16/6/21.
 */
public class ShoppingFragment extends BaseFragment{
    private RelativeLayout relativeLayout;
    private TextView tvTitle,tvDetails;
    @Override
    public int setLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView(View view) {
        relativeLayout= (RelativeLayout) view.findViewById(R.id.relativeLayout_shopping);
        tvTitle= (TextView) view.findViewById(R.id.tv_shopping_title);
        tvDetails= (TextView) view.findViewById(R.id.tv_shopping_details);

    }

    @Override
    protected void initData() {
        tvTitle.setText("我的购物车");
        tvDetails.setText("显示排序为 最新推荐");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener)getActivity()).onMenuClickListener(4);
            }
        });
    }
}
