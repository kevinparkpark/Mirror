package com.kevin.mirror.allkindsglasses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by dllo on 16/6/21.
 */
public class AllKindsGlassesAdapter extends BaseAdapter {
    private Context context;
    private AllKindsGlassesBean allKindsGlassesBean;

    public AllKindsGlassesAdapter(Context context) {
        this.context = context;
    }

    public void setAllKindsGlassesBean(AllKindsGlassesBean allKindsGlassesBean) {
        this.allKindsGlassesBean = allKindsGlassesBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return allKindsGlassesBean!=null?allKindsGlassesBean.getData().getList().size():0;
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
        return null;
    }


    class ViewHolder{

        public ViewHolder(View itemView){

        }
    }
}
