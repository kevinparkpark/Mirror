package com.kevin.mirror.specialtoshare;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.mirror.R;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * Created by dllo on 16/6/22.
 */
public class SpecialDetailsAdapter extends PagerAdapter {
    private Context context;
    private SpecialDetailsBean specialDetailsBean;
    private AutoRelativeLayout oneRl, twoRl, threeRl, fourRl, fiveRl;
    private TextView oneVerticalTitle, oneSmallTitle, oneSubTitle, oneColorTitle, oneTitle,
            twoSmallTitle, twoTitle, twoSubTitle,
            threeSubTitle, threeSmallTitle, threeTitle,
            fourTitle, fourSubTitle, fiveTitle, fiveSmallTitle;
    private String category;

    public SpecialDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setSpecialDetailsBean(SpecialDetailsBean specialDetailsBean) {
        this.specialDetailsBean = specialDetailsBean;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_one, container, false);
        oneRl = (AutoRelativeLayout) view.findViewById(R.id.oneRl);
        twoRl = (AutoRelativeLayout) view.findViewById(R.id.twoRl);
        threeRl = (AutoRelativeLayout) view.findViewById(R.id.threeRl);
        fourRl = (AutoRelativeLayout) view.findViewById(R.id.fourRl);
        fiveRl = (AutoRelativeLayout) view.findViewById(R.id.fiveRl);
        oneVerticalTitle = (TextView) view.findViewById(R.id.oneVerticalTitle);
        oneSmallTitle = (TextView) view.findViewById(R.id.oneSmallTitle);
        oneSubTitle = (TextView) view.findViewById(R.id.oneSubTitle);
        oneColorTitle = (TextView) view.findViewById(R.id.oneColorTitle);
        oneTitle = (TextView) view.findViewById(R.id.oneTitle);
        twoSmallTitle = (TextView) view.findViewById(R.id.twoSmallTitle);
        twoTitle = (TextView) view.findViewById(R.id.twoTitle);
        twoSubTitle = (TextView) view.findViewById(R.id.twoSubTitle);
        threeSubTitle = (TextView) view.findViewById(R.id.threeSubTitle);
        threeSmallTitle = (TextView) view.findViewById(R.id.threeSmallTitle);
        threeTitle = (TextView) view.findViewById(R.id.threeTitle);
        fourTitle = (TextView) view.findViewById(R.id.fourTitle);
        fourSubTitle = (TextView) view.findViewById(R.id.fourSubTitle);
        fiveTitle = (TextView) view.findViewById(R.id.fiveTitle);
        fiveSmallTitle = (TextView) view.findViewById(R.id.fiveSmallTitle);
        category = specialDetailsBean.getData().getStory_data().getText_array().get(position).getCategory();
        switch (category) {
            case "styleOne":
                oneRl.setVisibility(View.VISIBLE);
                oneVerticalTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getVerticalTitle());
                oneSmallTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                oneTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getTitle());
                oneSubTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                oneColorTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getColorTitle());
                break;
            case "styleTwo":
                twoRl.setVisibility(View.VISIBLE);
                twoSmallTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                twoTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getTitle());
                twoSubTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                break;
            case "styleThree":
                threeRl.setVisibility(View.VISIBLE);
                threeSmallTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                threeTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getTitle());
                threeSubTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                break;
            case "styleFour":
                fourRl.setVisibility(View.VISIBLE);
                fourTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getTitle());
                fourSubTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                break;
            case "styleFive":
                fiveRl.setVisibility(View.VISIBLE);
                fiveTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getTitle());
                fiveSmallTitle.setText(specialDetailsBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                break;
            default:
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return specialDetailsBean != null && specialDetailsBean.getData().getStory_data().getImg_array().size() > 0 ?
                specialDetailsBean.getData().getStory_data().getImg_array().size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
