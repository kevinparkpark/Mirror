package com.kevin.mirror.mainpage.allkinds;

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
import com.kevin.mirror.R;
import com.kevin.mirror.mainpage.FragmentToDetailsOnClickListener;
import com.kevin.mirror.netutils.ImageNetListener;
import com.kevin.mirror.netutils.NetTool;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by kevin on 16/6/21.
 */
public class AllKindsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<AllKindsBean.DataBean.ListBean> allKindsBeen;
    private Context context;
    private FragmentToDetailsOnClickListener clickListener;

    public void setClickListener(FragmentToDetailsOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public AllKindsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setAllKindsBeen(List<AllKindsBean.DataBean.ListBean> allKindsBeen) {
        this.allKindsBeen = allKindsBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.valueOf(allKindsBeen.get(position).getType());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case 1:
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_allkinds_1, parent, false);
                holder = new MyHolder(itemView);
                break;
            case 2:
                View item = LayoutInflater.from(context).inflate(R.layout.item_allkinds_2, parent, false);
                holder = new SecHolder(item);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        NetTool netTool = new NetTool();
        switch (viewType) {
            case 1:
                final MyHolder holder1 = (MyHolder) holder;
                holder1.tvName.setText(allKindsBeen.get(position).getData_info().getGoods_name());
                holder1.tvPrice.setText("￥" + allKindsBeen.get(position).getData_info().getGoods_price());
                holder1.tvArea.setText(allKindsBeen.get(position).getData_info().getProduct_area());
                holder1.tvBrand.setText(allKindsBeen.get(position).getData_info().getBrand());

                netTool.getImage(allKindsBeen.get(position).getData_info().getGoods_img(), new ImageNetListener() {
                    @Override
                    public void onSuccessed(Bitmap bitmap) {
                        holder1.ivImg.setImageBitmap(bitmap);
                        holder1.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailed(VolleyError error) {

                    }
                });
                if (clickListener!=null) {
                    holder1.autoLinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickListener.onFragmentToDetailsClickListener(position);
                        }
                    });
                }
                break;
            case 2:
                final SecHolder holder2 = (SecHolder) holder;
                holder2.textView.setText("mirror人物｜小眾氣質完美詮釋");
                String url = "http://image.mirroreye.cn/28416d5fc2a7e83582324c809e55d1c09.jpg";
                netTool.getImage(url, new ImageNetListener() {
                    @Override
                    public void onSuccessed(Bitmap bitmap) {
                        holder2.imageView.setImageBitmap(bitmap);
                        holder2.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailed(VolleyError error) {

                    }
                });
                if (clickListener!=null){
                    holder2.autoLinearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickListener.onFragmentToDetailsClickListener(position);
                        }
                    });
                }
                break;

        }
    }

    @Override
    public int getItemCount() {
        return allKindsBeen == null ? 0 : allKindsBeen.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;
        TextView tvName, tvPrice, tvArea, tvBrand;
        ProgressBar progressBar;
        AutoLinearLayout autoLinearLayout;

        public MyHolder(View itemView) {
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

    class SecHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ProgressBar progressBar;
        AutoLinearLayout autoLinearLayout;

        public SecHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_allkinds_brand2);
            imageView = (ImageView) itemView.findViewById(R.id.iv_allkinds_img2);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progressbar_item_allkinds_2);
            autoLinearLayout= (AutoLinearLayout) itemView.findViewById(R.id.linearlayout_item_allkinds_2);
        }
    }
}
