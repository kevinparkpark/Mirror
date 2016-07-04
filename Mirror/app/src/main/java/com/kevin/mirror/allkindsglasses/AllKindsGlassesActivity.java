package com.kevin.mirror.allkindsglasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.DB.DBUtils;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.R;
import com.kevin.mirror.base.BaseActivity;
import com.kevin.mirror.loginandregister.LoginActivity;
import com.kevin.mirror.mainpage.glasses.goodshare.GoodShareActivity;
import com.kevin.mirror.netutils.netinterface.ImageNetListener;
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.purchase.OrderActivity;
import com.kevin.mirror.utils.SharedPreferencesUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by dllo on 16/6/22.
 */
public class AllKindsGlassesActivity extends BaseActivity implements View.OnClickListener {
    private NetTool netTool;
    private AutoRelativeLayout layout;
    private ListView downListView, topListView;
    private AllKindsGlassesTopAdapter topAdapter;
    private AllKindsGlassesDownAdapter downAdapter;
    //给里层ListView头布局,绑定组件
    private ImageView shareImg;//分享的图片
    private TextView goodsNameTv;
    private TextView brandTv;
    private TextView infoDesTv;
    private TextView goodsPriceTv;
    private TextView brandTitleTv;
    //是下边返回 试穿那个页面的三个图标的layout
    //设置下面的Layout自动滑动的效果
    private RelativeLayout allKindsGlassesRelayoutLayout;
    private int pos = -1;
    private boolean flag = true;
    //三个按钮
    private ImageView backImg;//返回
    private TextView wearTv;//佩戴图集
    private TextView buyTv;//购买
    private int position;
    public static AllKindsGlassesActivity ALLKINDS;//销毁时使用

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALLKINDS = this;

        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public int mSetContentView() {
        return R.layout.activity_allkindsglasses;
    }

    @Override
    public void initView() {

        downListView = (ListView) findViewById(R.id.lv_down_allKindsGlasses);
        topListView = (ListView) findViewById(R.id.lv_top_allKindsGlasses);
        topAdapter = new AllKindsGlassesTopAdapter(this);
        downAdapter = new AllKindsGlassesDownAdapter(this);
        downListView.setAdapter(downAdapter);
        topListView.setAdapter(topAdapter);
        layout = (AutoRelativeLayout) findViewById(R.id.activity_allKindsGlasses_rLayout);

        allKindsGlassesRelayoutLayout = (RelativeLayout) findViewById(R.id.layout_menu_allKindsGlasses);
        backImg = (ImageView) findViewById(R.id.iv_goBack_menu_allKindsGlasses);
        wearTv = (TextView) findViewById(R.id.tv_wearPic_menu_allKindsGlasses);
        buyTv = (TextView) findViewById(R.id.tv_buy_menu_allKindsGlasses);
        netTool = new NetTool();

    }

    @Override
    public void initData() {

        //设置背景图片(从allKindsFragment)中传过来
//        Intent intent = getIntent();
//        position = intent.getIntExtra("position", 0);
        String imgUrl = getIntent().getStringExtra("imgUrl");

        String goodsId = getIntent().getStringExtra("goods_id");

        netTool.getImage(imgUrl, new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                layout.setBackground(bitmapDrawable);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        //给里层listView 加头布局,并绑定组件
        View downListHeadView = LayoutInflater.from(this).inflate(R.layout.item_allkindsglasses_firstpage, null);
        downListView.addHeaderView(downListHeadView);
        shareImg = (ImageView) downListHeadView.findViewById(R.id.header_allKindsGlasses_share);
        goodsNameTv = (TextView) downListHeadView.findViewById(R.id.tv_allKindsGlasses_firstPage);
        brandTv = (TextView) downListHeadView.findViewById(R.id.tv_allKindsGlasses_brand_firstPage);
        infoDesTv = (TextView) downListHeadView.findViewById(R.id.tv_content_allKindsGlasses_firstPager);
        goodsPriceTv = (TextView) downListHeadView.findViewById(R.id.tv_price_allKindsGlasses_firstPager);
        brandTitleTv = (TextView) downListHeadView.findViewById(R.id.tv_brandTitle_allKindsGlasses);


        shareImg.setOnClickListener(this);
        backImg.setOnClickListener(this);
//        wearTv.setOnClickListener(this);
//        buyTv.setOnClickListener(this);


        //给外层ListView加头布局
        View topListHeadView = LayoutInflater.from(this).inflate(R.layout.item_allkindsglasses_top_list_headview, null);
        topListView.addHeaderView(topListHeadView);
        //给外层ListView加尾部布局
        View topListFlowView = LayoutInflater.from(this).inflate(R.layout.item_allkindsglasses_top_list_flowview, null);
        topListView.addFooterView(topListFlowView);


        netTool.postGoodList(URLValues.GOODSINFO_URL, "", "2", goodsId, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                final AllKindsGlassesDetailsBeaen allKindsBean = gson.fromJson(result, AllKindsGlassesDetailsBeaen.class);//type1的类
                //给外层list设置数据
                List<AllKindsGlassesDetailsBeaen.DataBean.GoodsDataBean> goodsDataBeen =
                        allKindsBean.getData().getGoods_data();
                topAdapter.setGoodsDataBeen(goodsDataBeen);
                //给里层list设置数据
                List<AllKindsGlassesDetailsBeaen.DataBean.DesignDesBean> designDesBeen =
                        allKindsBean.getData().getDesign_des();
                downAdapter.setDesignDesBeen(designDesBeen);
                //给里层inListView加数据
                goodsNameTv.setText(allKindsBean.getData().getGoods_name());
                brandTv.setText(allKindsBean.getData().getBrand());
                infoDesTv.setText(allKindsBean.getData().getInfo_des());
                goodsPriceTv.setText(allKindsBean.getData().getGoods_price());
                brandTitleTv.setText(allKindsBean.getData().getBrand());

                SharedPreferences preferences = getSharedPreferences("information", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("goodsName", allKindsBean.getData().getGoods_name());
                editor.putString("goodsPrice", allKindsBean.getData().getGoods_price());
                editor.putString("goodsPic", allKindsBean.getData().getGoods_pic());
                editor.apply();

                buyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences getsp = getSharedPreferences("token", MODE_PRIVATE);
                        String token = getsp.getString("token", "0");
                        if (token.equals("0")) {
                            startActivity(new Intent(AllKindsGlassesActivity.this, LoginActivity.class));
                        } else {
                            Intent intent = new Intent(MyApp.context, OrderActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                wearTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AllKindsGlassesActivity.this, GoodShareActivity.class);
                        intent.putExtra("goods_id", allKindsBean.getData().getGoods_id());
                        startActivity(intent);
                    }
                });
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
                if (view1 == null) {
                    return;
                }
                //算法计算相对距离
                int scroll = -view1.getTop() + downListView.getPaddingTop() +
                        downListView.getFirstVisiblePosition() * view1.getHeight();
                //设置外层的listView的滑动距离
                topListView.setSelectionFromTop(1, -(int) (scroll * 1.2));
                //增加判断
                if (firstVisibleItem != pos) {
                    pos = firstVisibleItem;
                    //判断设置下面布局 可见与不可见(这里头布局占一个位置,也就是屏幕上出现的List的item为1的时候出现)
                }
                if (firstVisibleItem == 1 && flag) {
                    allKindsGlassesRelayoutLayout.setVisibility(View.VISIBLE);
                    setAnimation();
                    flag = false;
                } else if (firstVisibleItem == 0 && !flag) {
                    setDismissAnimation();
                    flag = true;
                }


            }
        });

        //给里层的listView设置焦点
        topListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return downListView.dispatchTouchEvent(event);
            }
        });

    }


    //设置下面字体平移动画效果(让布局从左面滑到屏幕上)
    private void setAnimation() {
        //平移动画
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1,//设置x轴的起始点
                Animation.RELATIVE_TO_PARENT, 0,//设置x轴的终点
                Animation.RELATIVE_TO_PARENT, 0,//设置y轴的起始点
                Animation.RELATIVE_TO_PARENT, 0);//设置y轴的终点

        translateAnimation.setDuration(250);//(设置出现动画的时间多长) 设置数值越大 动画显示出来的效果所需要的时间越长
        translateAnimation.setRepeatCount(0);//设置为0的意思是让动画滑动一次,若想出现多次动画,设置不同的数字即可
        allKindsGlassesRelayoutLayout.startAnimation(translateAnimation);

    }

    //让布局从屏幕滑到左面(让这三个按钮 消失在布局中)
    public void setDismissAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setDuration(300);
        translateAnimation.setRepeatCount(0);
        allKindsGlassesRelayoutLayout.startAnimation(translateAnimation);
        //给动画设置监听,GONE掉
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //设置布局消失
                allKindsGlassesRelayoutLayout.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.header_allKindsGlasses_share:
                showShare();

                break;

            case R.id.iv_goBack_menu_allKindsGlasses:
                finish();
                break;

        }


    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("非常完美,你值得拥有");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}