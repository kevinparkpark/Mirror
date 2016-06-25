package com.kevin.mirror.allkindsglasses;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseActivity;
import com.kevin.mirror.mainpage.allkinds.AllKindsBean;
import com.kevin.mirror.netutils.ImageNetListener;
import com.kevin.mirror.netutils.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/6/22.
 */
public class AllKindsGlassesActivity extends BaseActivity implements View.OnClickListener {
    private NetTool netTool;
    private AutoRelativeLayout layout;
    private ListView downListView,topListView;
    private AllKindsGlassesTopAdapter topAdapter;
    private AllKindsGlassesDownAdapter downAdapter;
    //给里层ListView头布局,绑定组件
    private ImageView shareImg;//分享的图片
    private TextView goodsNameTv;
    private TextView brandTv;
    private TextView infoDesTv;
    private TextView goodsPriceTv;
    private TextView brandTitleTv;
    //是下边返回 试穿那个页面的三个图标的
    private RelativeLayout allKindsGlassesRelayoutLayout;



    @Override
    public int mSetContentView() {
        return R.layout.activity_allkindsglasses;
    }

    @Override
    public void initView() {

        downListView= (ListView) findViewById(R.id.lv_down_allKindsGlasses);
        topListView = (ListView) findViewById(R.id.lv_top_allKindsGlasses);
        topAdapter = new AllKindsGlassesTopAdapter(this);
        downAdapter = new AllKindsGlassesDownAdapter(this);
        downListView.setAdapter(downAdapter);
        topListView.setAdapter(topAdapter);
        layout = (AutoRelativeLayout) findViewById(R.id.activity_allKindsGlasses_rLayout);

        allKindsGlassesRelayoutLayout= (RelativeLayout) findViewById(R.id.layout_menu_allKindsGlasses);
        netTool = new NetTool();

    }

    @Override
    public void initData() {

        //设置背景图片(从allKindsFragment)中传过来
        Intent intent = getIntent();
        final  int position = intent.getIntExtra("position",0);
        String imgUrl = intent.getStringExtra("imgUrl");
        netTool.getImage(imgUrl, new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
                layout.setBackground(bitmapDrawable);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        //给里层listView 加头布局,并绑定组件
        View downListHeadView = LayoutInflater.from(this).inflate(R.layout.item_allkindsglasses_firstpage,null);
        downListView.addHeaderView(downListHeadView);
        shareImg = (ImageView) downListHeadView.findViewById(R.id.header_allKindsGlasses_share);
        goodsNameTv = (TextView) downListHeadView.findViewById(R.id.tv_allKindsGlasses_firstPage);
        brandTv= (TextView) downListHeadView.findViewById(R.id.tv_allKindsGlasses_brand_firstPage);
        infoDesTv= (TextView) downListHeadView.findViewById(R.id.tv_content_allKindsGlasses_firstPager);
        goodsPriceTv= (TextView) downListHeadView.findViewById(R.id.tv_price_allKindsGlasses_firstPager);
        brandTitleTv = (TextView) downListHeadView.findViewById(R.id.tv_brandTitle_allKindsGlasses);
        shareImg.setOnClickListener(this);

        //给外层ListView加头布局
        View topListHeadView =LayoutInflater.from(this).inflate(R.layout.item_allkindsglasses_top_list_headview,null);
        topListView.addHeaderView(topListHeadView);
        //给外层ListView加尾部布局
        View topListFlowView = LayoutInflater.from(this).inflate(R.layout.item_allkindsglasses_top_list_flowview,null);
        topListView.addFooterView(topListFlowView);



//        //解析数据
//        //请求体
//        String body = "last_time=&device_type=3&page=&token=&version=1.0.1";
//        //请求头
//        Map<String,String> head = new HashMap<>();
//        head.put("Content-Type","application/x-www-form-urlencoded");
//        head.put("Content-Length","51");
//        head.put("Host","api.mirroreye.cn");
//        head.put("Connection","Keep-Alive");
//        head.put("Cookie","PHPSESSID=0q0374jcg8m9so617bu65b1qm3; cp_language=zhUser-Agent");
        netTool.postRequest(URLValues.ALLKINDS_URL, "", "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                AllKindsBean allKindsBean = gson.fromJson(result,AllKindsBean.class);//type1的类
                //给外层list设置数据
                List<AllKindsBean.DataBean.ListBean.DataInfoBean.GoodsDataBean> goodsDataBeen =
                        allKindsBean.getData().getList().get(position).getData_info().getGoods_data();
                topAdapter.setGoodsDataBeen(goodsDataBeen);
                //给里层list设置数据
                List<AllKindsBean.DataBean.ListBean.DataInfoBean.DesignDesBean>designDesBeen=
                        allKindsBean.getData().getList().get(position).getData_info().getDesign_des();
                downAdapter.setDesignDesBeen(designDesBeen);
                //给里层inListView加数据
                goodsNameTv.setText(allKindsBean.getData().getList().get(position).getData_info().getGoods_name());
                brandTv.setText(allKindsBean.getData().getList().get(position).getData_info().getBrand());
                infoDesTv.setText(allKindsBean.getData().getList().get(position).getData_info().getInfo_des());
                goodsPriceTv.setText(allKindsBean.getData().getList().get(position).getData_info().getGoods_price());
                brandTitleTv.setText(allKindsBean.getData().getList().get(position).getData_info().getBrand());
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        //里层listView滑动监听
        downListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //参考View 里面的ListView 的第一个item
                View view1 = downListView.getChildAt(1);
                if (view1==null){
                    return;
                }
                //算法计算相对距离
                int scroll = -view1.getTop() + downListView.getPaddingTop() +
                        downListView.getFirstVisiblePosition() * view1.getHeight();
                //设置外层的listView的滑动距离
                topListView.setSelectionFromTop(1,  -(int)(scroll * 1.2));

            }
        });

        //给里层的listView设置焦点
        topListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return topListView.dispatchTouchEvent(event);
            }
        });

    }

    //设置动画

    private void setAnimation(){
        //平移动画
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,
                -1,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_PARENT,0);
        translateAnimation.setDuration(250);
        translateAnimation.setRepeatCount(0);
        allKindsGlassesRelayoutLayout.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //对动画进行监听,设置消失
                allKindsGlassesRelayoutLayout.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_goBack_menu_allKindsGlasses:
                finish();
                break;
            case R.id.tv_wearPic_menu_allKindsGlasses:

                //应该是跳转 暂时没有写
                break;
            case R.id.tv_buy_menu_allKindsGlasses:

                //购买之后跳转 看是否登录
                break;
        }


    }
}