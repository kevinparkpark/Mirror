package com.kevin.mirror.allkindsglasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.mirror.R;

/**
 * Created by dllo on 16/6/21.
 */
public class AllKindsGlassesOneAdapter extends BaseAdapter {
    private Context context;
    private AllKindsGlassesBean allKindsGlassesBean;


    public AllKindsGlassesOneAdapter(Context context) {
        this.context = context;
    }

    public void setAllKindsGlassesBean(AllKindsGlassesBean allKindsGlassesBean) {
        this.allKindsGlassesBean = allKindsGlassesBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return allKindsGlassesBean != null ? allKindsGlassesBean.getData().getList().size() : 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        OneHolder oneHolder = null;
        TwoHolder twoHolder = null;
        ThreeHolder threeHolder = null;


        if (convertView == null&&allKindsGlassesBean.getData().getList().get(position).getData_info()!=null) {

            convertView=LayoutInflater.from(context).
                    inflate(R.layout.item_allkindsglasses_firstpage,parent,false);
            oneHolder = new OneHolder(convertView);
            convertView.setTag(oneHolder);

        }else if (allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getEnglish()!=null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_secpage,parent,false);
            twoHolder = new TwoHolder(convertView);
            convertView.setTag(twoHolder);

        }else if (allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getEnglish()==null){

            convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_thirdpage,parent,false);
            threeHolder = new ThreeHolder(convertView);
            convertView.setTag(threeHolder);
        }else {
            oneHolder = (OneHolder) convertView.getTag();
            twoHolder = (TwoHolder) convertView.getTag();
            threeHolder = (ThreeHolder) convertView.getTag();
        }
        oneHolder.goodsNameTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_name());
        oneHolder.brandTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getBrand());
        oneHolder.infoDesTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getInfo_des());
        oneHolder.goodsPriceTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_price());
        twoHolder.brand2Tv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getBrand());
        twoHolder.locationTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getLocation());
        twoHolder.englishTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getEnglish());
        twoHolder.countryTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getCountry());
        twoHolder.introContentTv.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getIntroContent());
        threeHolder.nameTvTwo.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getName());
        threeHolder.introContentTvTwo.setText(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getIntroContent());

        return convertView;
    }


    //第一种 首页的item
    class OneHolder {
        TextView goodsNameTv, brandTv, infoDesTv, goodsPriceTv;


        public OneHolder(View itemView) {

            goodsNameTv = (TextView) itemView.findViewById(R.id.tv_allKindsGlasses_firstPage);
            brandTv = (TextView) itemView.findViewById(R.id.tv_allKindsGlasses_brand_firstPage);
            infoDesTv = (TextView) itemView.findViewById(R.id.tv_content_allKindsGlasses_firstPager);
            goodsPriceTv = (TextView) itemView.findViewById(R.id.tv_price_allKindsGlasses_firstPager);


        }
    }

    //第二页
    class TwoHolder {
        TextView brand2Tv, locationTv, englishTv, countryTv, introContentTv;

        public TwoHolder(View itemView) {
            brand2Tv = (TextView) itemView.findViewById(R.id.tv_allKindsGlasses_secPage);
            locationTv = (TextView) itemView.findViewById(R.id.tv_CT_allKindsGlasses_secPage);
            englishTv = (TextView) itemView.findViewById(R.id.tv_english_allKindsGlasses_secPage);
            countryTv = (TextView) itemView.findViewById(R.id.tv_english_allKindsGlasses_secPage);
            introContentTv = (TextView) itemView.findViewById(R.id.tv_content_allKindsGlasses_secPage);

        }
    }

    //第三页
    class ThreeHolder {
        TextView introContentTvTwo, nameTvTwo;

        public ThreeHolder(View itemView) {

            introContentTvTwo = (TextView) itemView.findViewById(R.id.tv_content_allKindsGlasses_thirdPage);
            nameTvTwo = (TextView) itemView.findViewById(R.id.tv_CT_allKindsGlasses_thirdPage);
        }
    }





}


