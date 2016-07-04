package com.kevin.mirror.purchase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.netinterface.NetListener;

/**
 * Created by dllo on 16/6/28.
 */
public class AllAddressesActivity extends Activity implements View.OnClickListener {
    private ImageView all_addresses_close;
    private TextView add_the_addresses;
    private RecyclerView all_addresses_rcv;
    private NetTool netTool;
    private AllAddressesAdapter allAddressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_addresses);
        netTool = new NetTool();
        allAddressesAdapter = new AllAddressesAdapter(this);
        all_addresses_close = (ImageView) findViewById(R.id.all_addresses_close);
        add_the_addresses = (TextView) findViewById(R.id.add_the_addresses);
        all_addresses_rcv = (RecyclerView) findViewById(R.id.all_addresses_rcv);

        all_addresses_close.setOnClickListener(this);
        add_the_addresses.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
        String token = sp.getString("token", "");
        netTool.postRequest(URLValues.ADDR_URL, token, "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                OrderInformationBean bean = gson.fromJson(result, OrderInformationBean.class);
                allAddressesAdapter.setBeen(bean.getData().getList());
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        all_addresses_rcv.setLayoutManager(manager);
        all_addresses_rcv.setAdapter(allAddressesAdapter);

        all_addresses_rcv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                allAddressesAdapter.closeMenu();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                allAddressesAdapter.closeMenu();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
        String token = sp.getString("token", "");
        netTool.postRequest(URLValues.ADDR_URL, token, "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                OrderInformationBean bean = gson.fromJson(result, OrderInformationBean.class);
                allAddressesAdapter.setBeen(bean.getData().getList());
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_addresses_close:
                Intent intent1 = new Intent(this, OrderActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.add_the_addresses:
                Intent intent = new Intent(this, AddTheAddressesActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
