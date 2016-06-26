package com.kevin.mirror.allkindsglasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.kevin.mirror.R;
import com.kevin.mirror.mainpage.allkinds.AllKindsBean;
import com.kevin.mirror.netutils.ImageNetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.VolleySingleton;
import com.kevin.mirror.utils.ReSize;

import java.util.List;

/**
 * Created by dllo on 16/6/22.
 */
public class AllKindsGlassesDownAdapter extends BaseAdapter {
    private Context context;
    private List<AllKindsBean.DataBean.ListBean.DataInfoBean.DesignDesBean>designDesBeen;
    private NetTool netTool;
    private ReSize reSize=new ReSize();


    public AllKindsGlassesDownAdapter(Context context) {
        this.context = context;

    }

    public void setDesignDesBeen(List<AllKindsBean.DataBean.ListBean.DataInfoBean.DesignDesBean> designDesBeen) {
        this.designDesBeen = designDesBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return designDesBeen ==null?0:designDesBeen.size();
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
       MyViewHolder viewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_allkindsglasses_down,parent,false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        netTool = new NetTool();
        final MyViewHolder finalViewHolder = viewHolder;
        if (designDesBeen.get(position).getImg()!=null) {
            netTool.getImage(designDesBeen.get(position).getImg(), new ImageNetListener() {
                @Override
                public void onSuccessed(Bitmap bitmap) {
//                reSize.bitmapResize(bitmap,finalViewHolder.imageView);
                    finalViewHolder.imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onFailed(VolleyError error) {

                }

            });
        }
        Log.d("AllKindsGlassesDownAdap", designDesBeen.get(position).getImg());

        return convertView;
    }

    class MyViewHolder {
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.iv_item_allKindsGlasses_down);

        }
    }
}
