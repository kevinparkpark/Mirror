package com.kevin.mirror.purchase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.utils.SlidingMenuView;
import com.kevin.mirror.utils.WindowManagerUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by dllo on 16/7/1.
 */
public class AllAddressesAdapter extends RecyclerView.Adapter<AllAddressesAdapter.MyViewHolder> implements SlidingMenuView.SlidingListener {
    private List<OrderInformationBean.DataBean.ListBean> been;
    private Context context;
    private SlidingMenuView slidingMenuView;
    private NetTool netTool;

    public void setBeen(List<OrderInformationBean.DataBean.ListBean> been) {
        this.been = been;
        notifyDataSetChanged();
    }

    public void closeMenu() {
        if (slidingMenuView != null) {
            slidingMenuView.closeMenu();
        }
    }

    public AllAddressesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_addresses_activity, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        ViewGroup.LayoutParams params = holder.userInformation.getLayoutParams();
        params.width = WindowManagerUtils.getScreenWidthP(context) - 75;
        holder.userInformation.setLayoutParams(params);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        netTool = new NetTool();
        SharedPreferences sp = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        final String token = sp.getString("token", "");
        holder.item_name.setText("收件人:" + been.get(position).getUsername());
        holder.item_address.setText("地址:" + been.get(position).getAddr_info());
        holder.item_phone.setText(been.get(position).getCellphone());
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netTool.deleteAddress(URLValues.DELADDR_URL, token, been.get(position).getAddr_id(), new NetListener() {
                    @Override
                    public void onSuccessed(String result) {
                        Gson gson = new Gson();
                        AddTheAddressesBean bean1 = gson.fromJson(result, AddTheAddressesBean.class);
                        if (bean1.getResult().equals("1")) {
                            netTool.postRequest(URLValues.ADDR_URL, token, "2", "", new NetListener() {
                                @Override
                                public void onSuccessed(String result) {
                                    Gson gson = new Gson();
                                    OrderInformationBean bean = gson.fromJson(result, OrderInformationBean.class);
                                    setBeen(bean.getData().getList());
                                }

                                @Override
                                public void onFailed(VolleyError error) {

                                }
                            });
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(VolleyError error) {

                    }
                });
            }
        });
        holder.document_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTheAddressesActivity.class);
                netTool.addAddress(URLValues.EDITADDR_URL, token, been.get(position).getAddr_id(),
                        been.get(position).getUsername(), been.get(position).getCellphone(),
                        been.get(position).getAddr_info(), new NetListener() {
                            @Override
                            public void onSuccessed(String result) {
                                Gson gson = new Gson();
                                AddTheAddressesBean editBean = gson.fromJson(result, AddTheAddressesBean.class);
                                if (editBean.getResult().equals("1")) {

                                }
                            }

                            @Override
                            public void onFailed(VolleyError error) {

                            }
                        });
                context.startActivity(intent);
            }
        });
        holder.userInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netTool.deleteAddress(URLValues.MRADDRESS_URL, token, been.get(position).getAddr_id(), new NetListener() {
                    @Override
                    public void onSuccessed(String result) {
                        Gson gson = new Gson();
                        AddTheAddressesBean mrBean = gson.fromJson(result, AddTheAddressesBean.class);
                        if (mrBean.getResult().equals("1")) {
                            Toast.makeText(context, "设置默认地址成功", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(context, OrderActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailed(VolleyError error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return been != null && been.size() > 0 ? been.size() : 0;
    }

    @Override
    public void onMenuIsOpen(SlidingMenuView slidingMenuView) {
        this.slidingMenuView = slidingMenuView;
    }

    @Override
    public void onMove(SlidingMenuView slidingMenuView) {
        if (this.slidingMenuView != slidingMenuView) {
            if (this.slidingMenuView != null) {
                this.slidingMenuView.closeMenu();
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView item_name, item_address, item_phone, delete_item;
        private ImageView document_edit;
        private AutoRelativeLayout userInformation;

        public MyViewHolder(View itemView) {
            super(itemView);
            userInformation = (AutoRelativeLayout) itemView.findViewById(R.id.userInformation);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_address = (TextView) itemView.findViewById(R.id.item_address);
            item_phone = (TextView) itemView.findViewById(R.id.item_phone);
            delete_item = (TextView) itemView.findViewById(R.id.delete_item);
            document_edit = (ImageView) itemView.findViewById(R.id.document_edit);

            ((SlidingMenuView) itemView).setSlidingListener(AllAddressesAdapter.this);
        }
    }
}
