package com.kevin.mirror.mainpage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.mirror.DB.DBUtils;
import com.kevin.mirror.DB.DbBean;
import com.kevin.mirror.MainActivity;
import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by kevin on 16/6/21.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener {
    private ImageView iv0,iv1,iv2,iv3,iv4;
    private TextView tvAllKinds,tvGlass,tvSunglass,tvSpecial,tvShopping,tvOUt,tvBack;
    private int position;
    private AutoLinearLayout linearLayout;
    private DBUtils dbUtils=new DBUtils();

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
        tvBack= (TextView) view.findViewById(R.id.tv_menu2main);

        tvAllKinds.setOnClickListener(this);
        tvGlass.setOnClickListener(this);
        tvSunglass.setOnClickListener(this);
        tvSpecial.setOnClickListener(this);
        tvShopping.setOnClickListener(this);
        tvOUt.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

        //设置透明度
        tvAllKinds.setAlpha(0.25f);
        tvGlass.setAlpha(0.25f);
        tvSunglass.setAlpha(0.25f);
        tvSpecial.setAlpha(0.25f);
        tvShopping.setAlpha(0.25f);
        tvOUt.setAlpha(0.25f);
        tvBack.setAlpha(0.25f);

                setAmination();

    }
    //接收visibility参数
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
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(0);
                break;
            case R.id.tv_menu_out:
                //设置sharedperferense 退出设置0;
                SharedPreferences sp=context.getSharedPreferences("token", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token","0");
                editor.commit();

                dbUtils.deleteAll(DbBean.class);
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(0);
                break;
            case R.id.autolinearlayout_menu:
                ((Menu2MainOnClickListener)getActivity()).onMenu2MainClickListener(position);
                break;
        }
    }

    public void setVisibility(int position){
        switch (position) {
            case 0:
                iv0.setVisibility(View.VISIBLE);
                tvAllKinds.setAlpha(1f);
                break;
            case 1:
                iv1.setVisibility(View.VISIBLE);
                tvGlass.setAlpha(1f);
                break;
            case 2:
                iv2.setVisibility(View.VISIBLE);
                tvSunglass.setAlpha(1f);
                break;
            case 3:
                iv3.setVisibility(View.VISIBLE);
                tvSpecial.setAlpha(1f);
                break;
            case 4:
                iv4.setVisibility(View.VISIBLE);
                tvShopping.setAlpha(1f);
                break;
        }
    }
    public interface Menu2MainOnClickListener{
        void onMenu2MainClickListener(int position);
    }
    //进入menufragment动画
    private void setAmination() {
        AnimationSet localAnimationSet = new AnimationSet(true);
        ScaleAnimation localScaleAnimation = new ScaleAnimation(
                1.10000002384185791016F, 1F, 1.10000002384185791016F, 1F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setDuration(250L);
        localAnimationSet.addAnimation(localScaleAnimation);
        linearLayout.startAnimation(localAnimationSet);
    }
}
