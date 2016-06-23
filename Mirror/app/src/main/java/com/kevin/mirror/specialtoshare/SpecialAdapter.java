package com.kevin.mirror.specialtoshare;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.VolleySingleton;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/22.
 */
public class SpecialAdapter extends PagerAdapter {
    private Context context;
    private SpecialBean specialBean;
    private AutoRelativeLayout oneRl, twoRl, threeRl, fourRl, fiveRl;
    private TextView oneVerticalTitle, oneSmallTitle, oneSubTitle, oneColorTitle, oneTitle,
            twoSmallTitle, twoTitle, twoSubTitle,
            threeSubTitle, threeSmallTitle, threeTitle,
            fourTitle, fourSubTitle, fiveTitle, fiveSmallTitle;
    private String category;

    public SpecialAdapter(Context context, SpecialBean specialBean) {
        this.context = context;
        this.specialBean = specialBean;
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
        category = specialBean.getData().getStory_data().getText_array().get(position).getCategory();

        switch (category) {
            case "styleOne":
                oneRl.setVisibility(View.VISIBLE);
                oneVerticalTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getVerticalTitle());
                oneVerticalTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getVerticalTitleColor()));
                oneSmallTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                oneTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getTitle());
                oneTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getTitleColor()));
                oneSubTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                oneColorTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getColorTitle());
                oneColorTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getColorTitleColor()));
                break;
            case "styleTwo":
                twoRl.setVisibility(View.VISIBLE);
                twoSmallTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                twoTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getTitle());
                twoTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getTitleColor()));
                twoSubTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                break;
            case "styleThree":
                threeRl.setVisibility(View.VISIBLE);
                threeSmallTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                threeTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getTitle());
                threeTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getTitleColor()));
                threeSubTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                break;
            case "styleFour":
                fourRl.setVisibility(View.VISIBLE);
                fourTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getTitle());
                fourTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getTitleColor()));
                fourSubTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSubTitle());
                break;
            case "styleFive":
                fiveRl.setVisibility(View.VISIBLE);
                fiveTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getTitle());
                fiveTitle.setTextColor(Integer.parseInt(specialBean.getData().getStory_data().getText_array().get(position).getTitleColor()));
                fiveSmallTitle.setText(specialBean.getData().getStory_data().getText_array().get(position).getSmallTitle());
                break;
            default:
        }
        return view;
    }

    @Override
    public int getCount() {
        return specialBean != null && specialBean.getData().getStory_data().getImg_array().size() > 0 ?
                specialBean.getData().getStory_data().getImg_array().size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
