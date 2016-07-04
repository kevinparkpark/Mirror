package com.kevin.mirror.mainpage.shooping;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.kevin.mirror.DB.DbBean;
import com.kevin.mirror.R;
import com.kevin.mirror.mainpage.maininterface.FragmentToDetailsOnClickListener;
import com.kevin.mirror.netutils.netinterface.ImageNetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.utils.ReSize;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by kevin on 16/6/25.
 */
public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.MyHoder>{
    private List<DbBean> listBeen;
    private Context context;
    private NetTool netTool=new NetTool();
    private FragmentToDetailsOnClickListener clickListener;

    public void setClickListener(FragmentToDetailsOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ShoppingAdapter(Context context) {
        this.context = context;
    }

    public void setListBeen(List<DbBean> listBeen) {
        this.listBeen = listBeen;
        notifyDataSetChanged();
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.item_allkinds_1,parent,false);
        MyHoder hoder=new MyHoder(itemView);
        return hoder;
    }

    @Override
    public void onBindViewHolder(final MyHoder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.tvName.setText(listBeen.get(position).getGoodsName());
        holder.tvPrice.setText("￥"+listBeen.get(position).getGoodsPrice());
        holder.tvArea.setText(listBeen.get(position).getProductArea());
        holder.tvBrand.setText(listBeen.get(position).getBrand());
        netTool.getImage(listBeen.get(position).getGoodsUrl(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {

                ReSize reSize=new ReSize();
                reSize.bitmapResize(bitmap,holder.ivImg);
//                holder.ivImg.setImageBitmap(bitmap);
                holder.progressBar.setVisibility(View.GONE);
                bitmap.recycle();
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        holder.autoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null){
                    clickListener.onFragmentToDetailsClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeen==null?0:listBeen.size();
    }

    class MyHoder extends RecyclerView.ViewHolder{
        ImageView ivImg;
        TextView tvName, tvPrice, tvArea, tvBrand;
        ProgressBar progressBar;
        AutoLinearLayout autoLinearLayout;
        public MyHoder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_allkinds_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_allkinds_goods_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_allkinds_goods_price);
            tvArea = (TextView) itemView.findViewById(R.id.tv_allkinds_product_area);
            tvBrand = (TextView) itemView.findViewById(R.id.tv_allkinds_brand);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar_item_allkinds_1);
            autoLinearLayout= (AutoLinearLayout) itemView.findViewById(R.id.linearlayout_item_allkinds_1);
        }
    }
}
