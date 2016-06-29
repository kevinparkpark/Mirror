package com.kevin.mirror.purchase;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.mirror.R;

/**
 * Created by dllo on 16/6/28.
 */
public class AllAddressesActivity extends Activity implements View.OnClickListener {
    private ImageView all_addresses_close;
    private TextView add_the_addresses;
    private RecyclerView all_addresses_rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_addresses);
        all_addresses_close = (ImageView) findViewById(R.id.all_addresses_close);
        add_the_addresses = (TextView) findViewById(R.id.add_the_addresses);
        all_addresses_rcv = (RecyclerView) findViewById(R.id.all_addresses_rcv);

        all_addresses_close.setOnClickListener(this);
        add_the_addresses.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_addresses_close:
                break;
            case R.id.add_the_addresses:
                break;
            default:
        }
    }
}
