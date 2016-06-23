package com.kevin.mirror.allkindsglasses;

import android.view.View;
import android.widget.ListView;

import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/21.
 */
public class AllKindsGlassesFragment extends BaseFragment {
    private ListView listViewOne,listViewTwo;
    private AllKindsGlassesOneAdapter adapterOne;
    private AllKindsGlassesTwoAdapter adapterTwo;
    private ArrayList<String>arrayList;


    @Override
    public int setLayout() {
        return R.layout.fragment_allkindsglasses;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }
}
