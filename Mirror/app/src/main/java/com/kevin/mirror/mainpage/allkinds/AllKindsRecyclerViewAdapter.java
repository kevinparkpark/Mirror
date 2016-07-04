package com.kevin.mirror.mainpage.allkinds;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.kevin.mirror.R;
import com.kevin.mirror.mainpage.maininterface.FragmentToDetailsOnClickListener;
import com.kevin.mirror.netutils.netinterface.ImageNetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.utils.ReSize;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by kevin on 16/6/21.
 */
public class AllKindsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<AllKindsBean.DataBean.ListBean> allKindsBeen;
    private List<AllKind2Bean.DataBean.ListBean> allKinds2Been;
    private Context context;
    private FragmentToDetailsOnClickListener clickListener;
    private ReSize reSize=new ReSize();
    private NetTool netTool = new NetTool();

    public void setAllKinds2Been(List<AllKind2Bean.DataBean.ListBean> allKinds2Been) {
        this.allKinds2Been = allKinds2Been;
        notifyDataSetChanged();
    }

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
        switch (viewType) {
            case 1:
                final MyHolder holder1 = (MyHolder) holder;
                holder1.setIsRecyclable(false);
                holder1.tvName.setText(allKindsBeen.get(position).getData_info().getGoods_name());
                holder1.tvPrice.setText("￥" + allKindsBeen.get(position).getData_info().getGoods_price());
                holder1.tvArea.setText(allKindsBeen.get(position).getData_info().getProduct_area());
                holder1.tvBrand.setText(allKindsBeen.get(position).getData_info().getBrand());

//                Glide.with(context).load(allKindsBeen.get(position).getData_info()
//                        .getGoods_img()).into(holder1.ivImg);

                netTool.getImage(allKindsBeen.get(position).getData_info().getGoods_img(), new ImageNetListener() {
                    @Override
                    public void onSuccessed(Bitmap bitmap) {
                        reSize.bitmapResize(bitmap,holder1.ivImg);
//                        holder1.ivImg.setImageBitmap(bitmap);
                        holder1.progressBar.setVisibility(View.GONE);
//                        bitmap.recycle();
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
                holder2.setIsRecyclable(false);
                holder2.textView.setText(allKinds2Been.get(position).getData_info().getStory_title());
                String url= allKinds2Been.get(position).getData_info().getStory_img();
                netTool.getImage(url, new ImageNetListener() {
                    @Override
                    public void onSuccessed(Bitmap bitmap) {
                        reSize.bitmapResize(bitmap,holder2.imageView);
//                        holder2.imageView.setImageBitmap(bitmap);
                        holder2.progressBar.setVisibility(View.GONE);
                        bitmap.recycle();
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
