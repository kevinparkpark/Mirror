package com.kevin.mirror.specialtoshare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kevin.mirror.R;

/**
 * Created by dllo on 16/6/21.
 */
public class SpecialActivity extends Activity implements View.OnClickListener {
    private ImageView closeIv, shareIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        closeIv = (ImageView) findViewById(R.id.close);
        shareIv = (ImageView) findViewById(R.id.share);
        closeIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.share:
                break;
            default:
        }
    }
}
