package com.kevin.mirror.mainpage.allkinds;

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
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.NetWorkStatus;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.specialtoshare.SpecialActivity;

/**
 * Created by kevin on 16/6/21.
 */
public class AllKindsFragment extends BaseFragment {
    private AllKindsBean allKindsBean;
    private AllKind2Bean allKind2Bean;
    private TextView tvTitle, tvDetails;
    private AllKindsRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_allkinds;
    }

    @Override
    protected void initView(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_allkinds_title);
        tvDetails = (TextView) view.findViewById(R.id.tv_allkinds_details);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_allkinds);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout_allkinds);
    }

    @Override
    protected void initData() {
        adapter = new AllKindsRecyclerViewAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        tvTitle.setText("浏览所有分类");
        tvDetails.setText("显示排序为 最新推荐");

        NetTool netTool = new NetTool();
        netTool.postRequest(URLValues.ALLKINDS_URL, "", "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                allKindsBean = gson.fromJson(result, AllKindsBean.class);

                adapter.setAllKindsBeen(allKindsBean.getData().getList());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        //type2 data
        netTool.postRequest(URLValues.ALLKINDS_URL, "", "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                allKind2Bean = gson.fromJson(result, AllKind2Bean.class);
                adapter.setAllKinds2Been(allKind2Bean.getData().getList());
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener) getActivity()).onMenuClickListener(0);
            }
        });
        //type区分 点击事件
        adapter.setClickListener(new FragmentToDetailsOnClickListener() {
            @Override
            public void onFragmentToDetailsClickListener(int position) {
                if (!NetWorkStatus.isNetworkAvailable(MyApp.context)){
                    Toast.makeText(context, "网络不好用", Toast.LENGTH_SHORT).show();
                }else if (allKindsBean.getData().getList().get(position).getType().equals("1")) {
                    Intent intent = new Intent(context, AllKindsGlassesActivity.class);
                    intent.putExtra("imgUrl", allKindsBean.getData().getList().
                            get(position).getData_info().getGoods_img());
                    intent.putExtra("goods_id", allKindsBean.getData().getList().
                            get(position).getData_info().getGoods_id());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, SpecialActivity.class);
                    intent.putExtra("id", allKind2Bean.getData().getList().get(position).
                            getData_info().getStory_id());
                    startActivity(intent);
                }
            }
        });
    }


}
