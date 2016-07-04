package com.kevin.mirror.purchase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.VolleySingleton;
import com.kevin.mirror.netutils.netinterface.NetListener;

/**
 * Created by dllo on 16/6/28.
 */
public class OrderActivity extends Activity implements View.OnClickListener {
    private ImageView order_close, article_img;
    private TextView order_information, add_address, order_glasses_name, article_price, determine;
    private OrderInformationBean bean;
    private NetTool netTool;
    private ImageLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        loader = VolleySingleton.getInstance().getImageLoader();
        bean = new OrderInformationBean();
        netTool = new NetTool();
        order_close = (ImageView) findViewById(R.id.order_close);
        article_img = (ImageView) findViewById(R.id.article_img);
        order_information = (TextView) findViewById(R.id.order_information);
        add_address = (TextView) findViewById(R.id.add_address);
        order_glasses_name = (TextView) findViewById(R.id.order_glasses_name);
        article_price = (TextView) findViewById(R.id.article_price);
        determine = (TextView) findViewById(R.id.determine);

        order_close.setOnClickListener(this);
        add_address.setOnClickListener(this);
        determine.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("information", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.apply();
        String goodsName = preferences.getString("goodsName", "");
        String goodsPrice = preferences.getString("goodsPrice", "");
        String goodsPic = preferences.getString("goodsPic", "");

        order_glasses_name.setText(goodsName);
        article_price.setText("¥" + " " + goodsPrice);
        loader.get(goodsPic, ImageLoader.getImageListener(article_img,
                R.color.colorGray, R.color.colorGray));
        SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
        String token = sp.getString("token", "");

        netTool.postRequest(URLValues.ADDR_URL, token, "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                bean = gson.fromJson(result, OrderInformationBean.class);
                for (int i = 0; i < bean.getData().getList().size(); i++) {
                    switch (bean.getData().getList().get(i).getIf_moren()) {
                        case "1":
                            order_information.setText("收件人: " + bean.getData().getList().get(i).getUsername() + "\n"
                                    + "地址: " + bean.getData().getList().get(i).getAddr_info() + "\n"
                                    + bean.getData().getList().get(i).getCellphone());
                            break;
                        case "2":
                            break;
                    }
                }
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_close:
                finish();
                break;
            case R.id.add_address:
                Intent intent = new Intent(this, AllAddressesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.determine:
                break;
            default:
        }
    }
}
