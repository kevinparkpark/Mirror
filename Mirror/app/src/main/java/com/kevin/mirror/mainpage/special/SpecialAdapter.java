package com.kevin.mirror.mainpage.special;

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
 * Created by kevin on 16/6/22.
 */
public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyHolder> {
    private List<SpecialBean.DataBean.ListBean> listBeen;
    private Context context;
    private NetTool netTool = new NetTool();
    private FragmentToDetailsOnClickListener clickListener;

    public void setClickListener(FragmentToDetailsOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public SpecialAdapter(Context context) {
        this.context = context;
    }

    public void setListBeen(List<SpecialBean.DataBean.ListBean> listBeen) {
        this.listBeen = listBeen;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_allkinds_2, parent, false);
        MyHolder holder = new MyHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.textView.setText(listBeen.get(position).getStory_title());
        netTool.getImage(listBeen.get(position).getStory_img(), new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                holder.imageView.setImageBitmap(bitmap);
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        if (clickListener != null) {
            holder.autoLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onFragmentToDetailsClickListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listBeen == null ? 0 : listBeen.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ProgressBar progressBar;
        AutoLinearLayout autoLinearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_allkinds_brand2);
            imageView = (ImageView) itemView.findViewById(R.id.iv_allkinds_img2);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar_item_allkinds_2);
            autoLinearLayout = (AutoLinearLayout) itemView.findViewById(R.id.linearlayout_item_allkinds_2);
        }
    }
}
