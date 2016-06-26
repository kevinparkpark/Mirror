package com.kevin.mirror.allkindsglasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.mirror.R;
import com.kevin.mirror.mainpage.allkinds.AllKindsBean;

import java.util.List;

/**
 * Created by dllo on 16/6/21.
 */
public class AllKindsGlassesTopAdapter extends BaseAdapter {
    private Context context;
    private List<AllKindsBean.DataBean.ListBean.DataInfoBean.GoodsDataBean> goodsDataBeen;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;


    public AllKindsGlassesTopAdapter(Context context) {
        this.context = context;
    }

    public void setGoodsDataBeen(List<AllKindsBean.DataBean.ListBean.DataInfoBean.GoodsDataBean> goodsDataBeen) {
        this.goodsDataBeen = goodsDataBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return goodsDataBeen != null ? goodsDataBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OneHolder  oneHolder = null;
        OtherHolder otherHolder = null;
        int type = getItemViewType(position);
        if (convertView==null){
            switch (type){
                case TYPE_ONE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_secpage,parent,false);
                    oneHolder = new OneHolder(convertView);
                    convertView.setTag(oneHolder);
                    break;
                case TYPE_TWO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_thirdpage,parent,false);
                    otherHolder = new OtherHolder(convertView);
                    convertView.setTag(otherHolder);
                    break;
            }
        }else {
            switch (type){
                case TYPE_ONE:
                    oneHolder = (OneHolder) convertView.getTag();
                    break;
                case TYPE_TWO:
                    otherHolder = (OtherHolder) convertView.getTag();
                    break;

            }

        }
        switch (type){
            case TYPE_ONE:
                oneHolder.locationTv.setText(goodsDataBeen.get(position).getLocation());
                oneHolder.englishTv.setText(goodsDataBeen.get(position).getEnglish());
                oneHolder.countryTv.setText(goodsDataBeen.get(position).getCountry());
                oneHolder.introContentTv.setText(goodsDataBeen.get(position).getIntroContent());
                break;
            case TYPE_TWO:
                otherHolder.nameTvTwo.setText(goodsDataBeen.get(position).getName());
                otherHolder.introContentTvTwo.setText(goodsDataBeen.get(position).getIntroContent());
        }
        return convertView;
    }




    class OneHolder {
        //第一种 带国家的那种item
        //position 0
        TextView  locationTv, englishTv, countryTv, introContentTv;


        public OneHolder(View itemView) {

            locationTv = (TextView) itemView.findViewById(R.id.tv_CT_allKindsGlasses_secPage);
            englishTv = (TextView) itemView.findViewById(R.id.tv_english_allKindsGlasses_secPage);
            countryTv = (TextView) itemView.findViewById(R.id.tv_english_allKindsGlasses_secPage);
            introContentTv = (TextView) itemView.findViewById(R.id.tv_content_allKindsGlasses_secPage);


        }
    }

    class OtherHolder {
        //第二种 不带国家那种呗
        //position 123
        TextView introContentTvTwo, nameTvTwo;

        public OtherHolder(View view) {
            introContentTvTwo = (TextView) view.findViewById(R.id.tv_content_allKindsGlasses_thirdPage);
            nameTvTwo = (TextView) view.findViewById(R.id.tv_CT_allKindsGlasses_thirdPage);
        }

    }


}


