package com.kevin.mirror.mainpage;

import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by kevin on 16/6/21.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener {
    private ImageView iv0,iv1,iv2,iv3,iv4;
    private TextView tvAllKinds,tvGlass,tvSunglass,tvSpecial,tvShopping,tvOUt;
    private int position;
    private AutoLinearLayout linearLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initView(View view) {
        iv0= (ImageView) view.findViewById(R.id.iv_menu_0);
        iv1= (ImageView) view.findViewById(R.id.iv_menu_1);
        iv2= (ImageView) view.findViewById(R.id.iv_menu_2);
        iv3= (ImageView) view.findViewById(R.id.iv_menu_3);
        iv4= (ImageView) view.findViewById(R.id.iv_menu_4);
        linearLayout= (AutoLinearLayout) view.findViewById(R.id.autolinearlayout_menu);

        tvAllKinds= (TextView) view.findViewById(R.id.tv_menu_allkinds);
        tvGlass= (TextView) view.findViewById(R.id.tv_menu_glasses);
        tvSunglass= (TextView) view.findViewById(R.id.tv_menu_sunglasses);
        tvShopping= (TextView) view.findViewById(R.id.tv_menu_shopping);
        tvOUt= (TextView) view.findViewById(R.id.tv_menu_out);
        tvSpecial= (TextView) view.findViewById(R.id.tv_menu_special);

        tvAllKinds.setOnClickListener(this);
        tvGlass.setOnClickListener(this);
        tvSunglass.setOnClickListener(this);
        tvSpecial.setOnClickListener(this);
        tvShopping.setOnClickListener(this);
        tvOUt.setOnClickListener(this);

                setAmination();

    }

    @Override
    protected void initData() {
        position=getArguments().getInt("position");
        setVisibility(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_menu_allkinds:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(0);
                iv0.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_menu_glasses:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(1);
                iv1.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_menu_sunglasses:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(2);
                iv2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_menu_special:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(3);
                iv3.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_menu_shopping:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(4);
                iv4.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_menu2main:

                break;
            case R.id.tv_menu_out:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(0);
                break;
        }
    }

    public void setVisibility(int position){
        switch (position) {
            case 0:
                iv0.setVisibility(View.VISIBLE);
                break;
            case 1:
                iv1.setVisibility(View.VISIBLE);
                break;
            case 2:
                iv2.setVisibility(View.VISIBLE);
                break;
            case 3:
                iv3.setVisibility(View.VISIBLE);
                break;
            case 4:
                iv4.setVisibility(View.VISIBLE);
                break;
        }
    }
    public interface Menu2MainOnClickListener{
        void onMenu2MainClickListener(int position);
    }
    private void setAmination() {
        AnimationSet localAnimationSet = new AnimationSet(true);
        ScaleAnimation localScaleAnimation = new ScaleAnimation(
                1.10000002384185791016F, 1F, 1.10000002384185791016F, 1F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setDuration(250L);
        localAnimationSet.addAnimation(localScaleAnimation);
        linearLayout.startAnimation(localAnimationSet);
    }
}
