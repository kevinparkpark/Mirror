package com.kevin.mirror.purchase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.netinterface.NetListener;

/**
 * Created by dllo on 16/6/28.
 */
public class AddTheAddressesActivity extends Activity implements View.OnClickListener {
    private NetTool netTool;
    private EditText add_the_addresses_name, add_the_addresses_phone, shipping_address;
    private ImageView add_the_addresses_close;
    private TextView commit_addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_addresses);
        add_the_addresses_close = (ImageView) findViewById(R.id.add_the_addresses_close);
        add_the_addresses_name = (EditText) findViewById(R.id.add_the_addresses_name);
        add_the_addresses_phone = (EditText) findViewById(R.id.add_the_addresses_phone);
        shipping_address = (EditText) findViewById(R.id.shipping_address);
        commit_addresses = (TextView) findViewById(R.id.commit_addresses);
        netTool = new NetTool();
        add_the_addresses_close.setOnClickListener(this);
        commit_addresses.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_the_addresses_close:
                Intent intent = new Intent(this, AllAddressesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.commit_addresses:
                SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
                String token = sp.getString("token", "");
                String name = add_the_addresses_name.getText().toString();
                String phone = add_the_addresses_phone.getText().toString();
                String address = shipping_address.getText().toString();
                if (name.length() != 0 && phone.length() != 0 && address.length() != 0) {
                    netTool.addAddress(URLValues.ADDADDR_URL, token, "", name, phone, address, new NetListener() {
                        @Override
                        public void onSuccessed(String result) {
                            Gson gson = new Gson();
                            AddTheAddressesBean bean = gson.fromJson(result, AddTheAddressesBean.class);
                            if (bean.getResult().equals("1")) {
                                Toast.makeText(AddTheAddressesActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(MyApp.context, AllAddressesActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        }

                        @Override
                        public void onFailed(VolleyError error) {
                            Toast.makeText(AddTheAddressesActivity.this, "添加地址失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
