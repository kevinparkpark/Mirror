package com.kevin.mirror.allkindsglasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.ImageNetListener;
import com.kevin.mirror.netutils.NetTool;

/**
 * Created by dllo on 16/6/22.
 */
public class AllKindsGlassesTwoAdapter extends BaseAdapter {
    private Context context;
    private AllKindsGlassesBean allKindsGlassesBean;
    private NetTool netTool=new NetTool();

    public AllKindsGlassesTwoAdapter(Context context) {
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
         TwoHolder twoHolder= null;
         ThreeHolder threeHolder= null;
         FourHolder fourHolder = null;
         FiveHolder fiveHolder = null;
        if (convertView == null&&allKindsGlassesBean.getData().getList().get(position).getData_info()!=null) {

            convertView= LayoutInflater.from(context).
                    inflate(R.layout.item_allkindsglasses_firstpage,parent,false);
            oneHolder = new OneHolder(convertView);
            convertView.setTag(oneHolder);

        }else if (allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getEnglish()!=null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_secpage_two,parent,false);
            twoHolder = new TwoHolder(convertView);
            convertView.setTag(twoHolder);

        }else if (allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_data().get(position).getEnglish()==null){

            convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_thirdpage_two,parent,false);
            threeHolder = new ThreeHolder(convertView);
            convertView.setTag(threeHolder);
        }else if (allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(position).getType()=="2"){
            convertView =LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_fifthpage,parent,false);
            fourHolder = new FourHolder(convertView);
            convertView.setTag(fourHolder);
        }else if (allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(position).getType()=="3"){
            convertView =LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_sixthpage,parent,false);
            fiveHolder = new FiveHolder(convertView);
            convertView.setTag(fiveHolder);

        }else {
            oneHolder = (OneHolder) convertView.getTag();
            twoHolder = (TwoHolder) convertView.getTag();
            threeHolder = (ThreeHolder) convertView.getTag();
            fourHolder = (FourHolder) convertView.getTag();
            fiveHolder = (FiveHolder) convertView.getTag();
        }
        final OneHolder finalOneHolder = oneHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getGoods_img(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalOneHolder.imgOne.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


        final TwoHolder finalTwoHolder = twoHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(0).getImg(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalTwoHolder.imgTwo.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        final ThreeHolder finalThreeHolder = threeHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(1).getImg(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalThreeHolder.imgThree.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        final ThreeHolder finalThreeHolder1 = threeHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(2).getImg(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalThreeHolder1.imgThree.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        final ThreeHolder finalThreeHolder2 = threeHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(3).getImg(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalThreeHolder2.imgThree.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        final FourHolder finalFourHolder = fourHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(4).getImg(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalFourHolder.imgFour.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        final FiveHolder finalFiveHolder = fiveHolder;
        netTool.getImage(allKindsGlassesBean.getData().getList().get(position).getData_info().getDesign_des().get(5).getImg(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                finalFiveHolder.imgFive.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });


        return convertView;
    }



    class OneHolder{
        ImageView imgOne;
        public OneHolder(View itemView){
            imgOne = (ImageView) itemView.findViewById(R.id.relativeLayout_allKindsGlasses_firstPage);

        }
    }
    class TwoHolder{
        ImageView imgTwo;
        public TwoHolder(View itemView){
            imgTwo = (ImageView) itemView.findViewById(R.id.iv_allKindsGlasses_secPage);
        }
    }
    class ThreeHolder{
        ImageView imgThree;
        public ThreeHolder(View itemView){

            imgThree = (ImageView) itemView.findViewById(R.id.iv_allKindsGlasses_thirdPage);
        }
    }

    class FourHolder{
        ImageView imgFour;
        public FourHolder(View itemView){
            imgFour = (ImageView) itemView.findViewById(R.id.iv_allKindsGlasses_fifthPage);
        }
    }
    class FiveHolder{
        ImageView imgFive;
        public FiveHolder(View itemView){
            imgFive = (ImageView) itemView.findViewById(R.id.iv_allKindsGlasses_sixthPage);
        }
    }

}
