package com.kevin.mirror.mainpage.shooping;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.mirror.DB.DBUtils;
import com.kevin.mirror.DB.DbBean;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.R;
import com.kevin.mirror.allkindsglasses.AllKindsGlassesActivity;
import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.mainpage.maininterface.FragmentToDetailsOnClickListener;
import com.kevin.mirror.mainpage.maininterface.MenuOnClickListener;
import com.kevin.mirror.netutils.NetWorkStatus;

import java.util.List;

/**
 * Created by kevin on 16/6/21.
 */
public class ShoppingFragment extends BaseFragment{
    private RelativeLayout relativeLayout;
    private TextView tvTitle,tvDetails;
    private RecyclerView recyclerView;
    private ShoppingAdapter adapter;
    private ImageView ivEmpty;
    private DBUtils dbUtils=new DBUtils();
    @Override
    public int setLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView(View view) {
        relativeLayout= (RelativeLayout) view.findViewById(R.id.relativeLayout_shopping);
        tvTitle= (TextView) view.findViewById(R.id.tv_shopping_title);
        tvDetails= (TextView) view.findViewById(R.id.tv_shopping_details);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_shopping);
        ivEmpty= (ImageView) view.findViewById(R.id.iv_shopping_empty);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的购物车");
        tvDetails.setText("显示排序为 最新推荐");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener)getActivity()).onMenuClickListener(4);
            }
        });
        adapter=new ShoppingAdapter(context);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        //查询数据库
        final List<DbBean> dbBeen=dbUtils.queryAll(DbBean.class);

//        dbUtils.deleteAll(DbBean.class);

        if (dbBeen.size()>0) {
            ivEmpty.setVisibility(View.GONE);
            adapter.setListBeen(dbBeen);
            recyclerView.setAdapter(adapter);
        }
        adapter.setClickListener(new FragmentToDetailsOnClickListener() {
            @Override
            public void onFragmentToDetailsClickListener(int position) {
                if (!NetWorkStatus.isNetworkAvailable(MyApp.context)){
                    Toast.makeText(context, "网络不好用", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, AllKindsGlassesActivity.class);
                    intent.putExtra("goods_id", dbBeen.get(position).getGoodsId());
                    intent.putExtra("imgUrl", dbBeen.get(position).getGoodsUrl());
                    startActivity(intent);
                }
            }
        });
    }
}
