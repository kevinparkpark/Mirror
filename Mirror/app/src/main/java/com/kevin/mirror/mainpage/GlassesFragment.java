package com.kevin.mirror.mainpage;

import android.view.View;
import android.widget.RelativeLayout;

import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseFragment;

/**
 * Created by kevin on 16/6/21.
 */
public class GlassesFragment extends BaseFragment{
    private RelativeLayout relativeLayout;
    @Override
    public int setlayout() {
        return R.layout.fragment_allkinds;
    }

    @Override
    protected void initView(View view) {
        relativeLayout= (RelativeLayout) view.findViewById(R.id.relativeLayout_allkinds);
    }

    @Override
    protected void initData() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuOnClickListener)getActivity()).onMenuClickListener(1);
            }
        });
    }
}
