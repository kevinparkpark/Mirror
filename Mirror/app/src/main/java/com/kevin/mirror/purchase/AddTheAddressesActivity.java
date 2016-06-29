package com.kevin.mirror.purchase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.mirror.R;

/**
 * Created by dllo on 16/6/28.
 */
public class AddTheAddressesActivity extends Activity implements View.OnClickListener {
    private ImageView add_the_addresses_close;
    private EditText add_the_addresses_name, add_the_addresses_phone, shipping_address;
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

        add_the_addresses_close.setOnClickListener(this);
        commit_addresses.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_the_addresses_close:
                finish();
                break;
            case R.id.commit_addresses:
                break;
            default:
        }
    }
}
