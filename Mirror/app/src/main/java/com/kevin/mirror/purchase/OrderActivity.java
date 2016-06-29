package com.kevin.mirror.purchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.mirror.R;

/**
 * Created by dllo on 16/6/28.
 */
public class OrderActivity extends Activity implements View.OnClickListener {
    private ImageView order_close, article_img;
    private TextView order_information, add_address, order_glasses_name, article_price, determine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
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
                break;
            case R.id.determine:
                break;
            default:
        }
    }
}
