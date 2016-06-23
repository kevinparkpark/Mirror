package com.kevin.mirror.mainpage.special;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.kevin.mirror.specialtoshare.SpecialActivity;

/**
 * Created by kevin on 16/6/21.
 */
public class SpecialFragment extends BaseFragment {
    private RelativeLayout relativeLayout;
    private TextView tvTitle, tvDetails;
    private RecyclerView recyclerView;
    private SpecialAdapter adapter;
    private SpecialBean specialBean;

    @Override
    public int setLayout() {
        return R.layout.fragment_allkinds;
    }

    @Override
    protected void initView(View view) {
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout_allkinds);
        tvTitle = (TextView) view.findViewById(R.id.tv_allkinds_title);
        tvDetails = (TextView) view.findViewById(R.id.tv_allkinds_details);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_allkinds);

    }

    @Override
    protected void initData() {
        tvTitle.setText("专题分享");
        tvDetails.setText("显示排序为 最新推荐");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener) getActivity()).onMenuClickListener(3);
            }
        });
        adapter = new SpecialAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        NetTool netTool = new NetTool();
        netTool.postRequest(URLValues.STORYLIST_URL, "", "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                specialBean = gson.fromJson(result, SpecialBean.class);
                adapter.setListBeen(specialBean.getData().getList());
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(new FragmentToDetailsOnClickListener() {
                    @Override
                    public void onFragmentToDetailsClickListener(int position) {
                        Intent intent = new Intent(getActivity(), SpecialActivity.class);
                        intent.putExtra("id", specialBean.getData().getList().get(position).getStory_id());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }
}
