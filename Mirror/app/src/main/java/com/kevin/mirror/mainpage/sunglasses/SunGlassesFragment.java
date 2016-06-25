package com.kevin.mirror.mainpage.sunglasses;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.mainpage.FragmentToDetailsOnClickListener;
import com.kevin.mirror.mainpage.MenuOnClickListener;
import com.kevin.mirror.netutils.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;

/**
 * Created by kevin on 16/6/21.
 */
public class SunGlassesFragment extends BaseFragment{
    private RelativeLayout relativeLayout;
    private TextView tvTitle,tvDetails;
    private SunGlassesAdapter adapter;
    private RecyclerView recyclerView;
    private SunGlassesBean sunGlassesBean;
    @Override
    public int setLayout() {
        return R.layout.fragment_allkinds;
    }

    @Override
    protected void initView(View view) {
        relativeLayout= (RelativeLayout) view.findViewById(R.id.relativeLayout_allkinds);
        tvTitle= (TextView) view.findViewById(R.id.tv_allkinds_title);
        tvDetails= (TextView) view.findViewById(R.id.tv_allkinds_details);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_allkinds);
    }

    @Override
    protected void initData() {
        tvTitle.setText("浏览太阳镜");
        tvDetails.setText("显示排序为 最新推荐");

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener)getActivity()).onMenuClickListener(2);
            }
        });
        adapter=new SunGlassesAdapter(context);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        NetTool netTool=new NetTool();
        netTool.postRequest(URLValues.GOODSLIST_URL, "", "2","268", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson=new Gson();
                sunGlassesBean=gson.fromJson(result,SunGlassesBean.class);
                adapter.setListBeen(sunGlassesBean.getData().getList());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        adapter.setClickListener(new FragmentToDetailsOnClickListener() {
            @Override
            public void onFragmentToDetailsClickListener(int position) {

            }
        });
    }
}
