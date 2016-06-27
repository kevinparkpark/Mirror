package com.kevin.mirror.mainpage.glasses;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.R;
import com.kevin.mirror.allkindsglasses.AllKindsGlassesActivity;
import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.mainpage.maininterface.FragmentToDetailsOnClickListener;
import com.kevin.mirror.mainpage.maininterface.MenuOnClickListener;
import com.kevin.mirror.mainpage.sunglasses.SunGlassesBean;
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.NetWorkStatus;
import com.kevin.mirror.netutils.URLValues;

/**
 * Created by kevin on 16/6/21.
 */
public class GlassesFragment extends BaseFragment{
    private RelativeLayout relativeLayout;
    private TextView tvTitle,tvDetails;
    private RecyclerView recyclerView;
    private GlassesAdapter adapter;
    private SunGlassesBean glassesBean;
    @Override
    public int setLayout() {
        return R.layout.fragment_allkinds;
    }

    @Override
    protected void initView(View view) {
        relativeLayout= (RelativeLayout) view.findViewById(R.id.relativeLayout_allkinds);
        tvTitle= (TextView) view.findViewById(R.id.tv_allkinds_title);
        tvDetails= (TextView) view.findViewById(R.id.tv_allkinds_details);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_allkinds);
    }

    @Override
    protected void initData() {
        tvTitle.setText("浏览平光镜");
        tvDetails.setText("显示排序为 最新推荐");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener)getActivity()).onMenuClickListener(1);
            }
        });
        adapter = new GlassesAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        NetTool netTool = new NetTool();
        netTool.postRequest(URLValues.GOODSLIST_URL, "", "2","269", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                glassesBean = gson.fromJson(result, SunGlassesBean.class);

                adapter.setListBeen(glassesBean.getData().getList());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        adapter.setClickListener(new FragmentToDetailsOnClickListener() {
            @Override
            public void onFragmentToDetailsClickListener(int position) {
                if (!NetWorkStatus.isNetworkAvailable(MyApp.context)){
                    Toast.makeText(context, "网络不好用", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, AllKindsGlassesActivity.class);
                    intent.putExtra("goods_id", glassesBean.getData().getList().get(position).getGoods_id());
                    intent.putExtra("imgUrl", glassesBean.getData().getList().get(position).getGoods_img());
                    startActivity(intent);
                }
            }
        });
    }
}
