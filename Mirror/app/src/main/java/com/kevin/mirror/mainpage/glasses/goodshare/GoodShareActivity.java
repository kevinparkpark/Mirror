package com.kevin.mirror.mainpage.glasses.goodshare;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.mainpage.sunglasses.SunGlassesBean;
import com.kevin.mirror.netutils.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.VolleySingleton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 16/6/24.
 */
public class GoodShareActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageView iv1, iv2, iv3, iv4, iv5, ivImg, ivPlay;
    private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();
    private int position;
    private ProgressBar progressBar;
    private SunGlassesBean sunGlassesBean;
    private String videoUrl,videoImg;
    private ArrayList<String> imgList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodshare);
        position = getIntent().getIntExtra("position", 0);

        videoView = (VideoView) findViewById(R.id.vv_goodshare_vv);
        iv1 = (ImageView) findViewById(R.id.iv_goodshare_img1);
        iv2 = (ImageView) findViewById(R.id.iv_goodshare_img2);
        iv3 = (ImageView) findViewById(R.id.iv_goodshare_img3);
        iv4 = (ImageView) findViewById(R.id.iv_goodshare_img4);
        iv5 = (ImageView) findViewById(R.id.iv_goodshare_img5);
        ivImg = (ImageView) findViewById(R.id.iv_goodshare_vv_bac);
        ivPlay = (ImageView) findViewById(R.id.iv_goodshare_vv_play);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_goodshare);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        NetTool netTool = new NetTool();
        netTool.postRequest(URLValues.GOODSLIST_URL, "", "2", "", new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                sunGlassesBean = gson.fromJson(result, SunGlassesBean.class);
                List<SunGlassesBean.DataBean.ListBean.WearVideoBean> wearVideoBeen=
                        sunGlassesBean.getData().getList().get(position).getWear_video();

                progressBar.setVisibility(View.GONE);

                        imgList=new ArrayList<>();
                for (SunGlassesBean.DataBean.ListBean.WearVideoBean videoBean : wearVideoBeen) {
                    if (videoBean.getType().equals("8")){
                        videoUrl=videoBean.getData();
                    }else if (videoBean.getType().equals("9")){
                        videoImg=videoBean.getData();
                    }else {
                        imgList.add(videoBean.getData());
                    }
                }
                Log.d("GoodShareActivity", "imgList.size():" + imgList.size());
                videoView.setMediaController(new MediaController(GoodShareActivity.this));
                    videoView.setVideoURI(Uri.parse(videoUrl));
                imageLoader.get(videoImg
                        ,ImageLoader.getImageListener(ivImg,R.mipmap.img_gray_color,R.mipmap.img_gray_color));

                if (imgList.size()>4){
                imageLoader.get(imgList.get(imgList.size()-5)
                        , ImageLoader.getImageListener(iv1, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                imageLoader.get(imgList.get(imgList.size()-4)
                        , ImageLoader.getImageListener(iv2, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                imageLoader.get(imgList.get(imgList.size()-3)
                        , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                imageLoader.get(imgList.get(imgList.size()-2)
                        , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                imageLoader.get(imgList.get(imgList.size()-1)
                        , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                }else if (imgList.size()==4){
                    imageLoader.get(imgList.get(imgList.size()-4)
                            , ImageLoader.getImageListener(iv2, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size()-3)
                            , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size()-2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size()-1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                }else if (imgList.size()==3){
                    imageLoader.get(imgList.get(imgList.size()-3)
                            , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size()-2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size()-1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                }else if (imgList.size()==2){
                    imageLoader.get(imgList.get(imgList.size()-2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size()-1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                }
                iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size()-5),iv1);
                    }
                });
                iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size()-4),iv2);
                    }
                });
                iv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size()-3),iv3);
                    }
                });
                iv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size()-2),iv4);
                    }
                });
                iv5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size()-1),iv5);
                    }
                });
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
                LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linearlayout_goodeshare);
                linearLayout.setVisibility(View.GONE);
            }
        });

    }
    private void toDetailsPic(String url,ImageView iv){
        Intent intent=new Intent(this,ShareDetailsActivity.class);
        intent.putExtra("url",url);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(
                    this,iv,"sharedView").toBundle());
        }else {
            startActivity(intent);
        }
    }
}
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Document doc = Jsoup.parse(new URL(url), 5000);
////            Document doc= Jsoup.connect("http://api.mirroreye.cn/index.php/goodweb/info?id=iOvQp1465978771").get();
////            Document doc1=Jsoup.parse(doc.toString());
//                    Elements div = doc.select("body");
//                    Document divdoc = Jsoup.parse(div.toString());
//                    Elements elements = divdoc.getElementsByTag("img");
//                    Elements elementsVideo = divdoc.getElementsByTag("video");
//                    String vvLink = elementsVideo.select("video").attr("src").trim();
//                    String vvImg=elementsVideo.select("video").attr("poster").trim();
//
//                    Message msg = new Message();
//                    msg.what = 100;
//                    msg.obj = elements;
//                    handler.sendMessage(msg);
//
//                    Message msgVv = new Message();
//                    msgVv.what = 200;
//                    msgVv.obj = vvLink;
//                    handler.sendMessage(msgVv);
//
//                    Message msgVvImg=new Message();
//                    msgVvImg.what=300;
//                    msgVvImg.obj=vvImg;
//                    handler.sendMessage(msgVvImg);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                if (msg.what == 100) {
//                    elementsResult = (Elements) msg.obj;
//
//                    progressBar.setVisibility(View.GONE);
////                    imageLoader.get(elementsResult.get(elementsResult.size() - 5).select("img").attr("src").trim()
////                            , ImageLoader.getImageListener(iv1, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
//                    imageLoader.get(elementsResult.get(elementsResult.size() - 4).select("img").attr("src").trim()
//                            , ImageLoader.getImageListener(iv2, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
//                    imageLoader.get(elementsResult.get(elementsResult.size() - 3).select("img").attr("src").trim()
//                            , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
//                    imageLoader.get(elementsResult.get(elementsResult.size() - 2).select("img").attr("src").trim()
//                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
//                    imageLoader.get(elementsResult.get(elementsResult.size() - 1).select("img").attr("src").trim()
//                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
//                }
//                if (msg.what == 200) {
//                    String link = (String) msg.obj;
//                    Log.d("GoodShareActivity", "vv---------" + link);
//                    videoView.setMediaController(new MediaController(GoodShareActivity.this));
//                    videoView.setVideoURI(Uri.parse(link));
//
////                    videoView.setVideoURI(Uri.parse(link));
////                    MediaController controller=new MediaController(GoodShareActivity.this);
////                    videoView.setMediaController(controller);
////                    controller.setMediaPlayer(videoView);
//
//                }
//                if (msg.what==300){
//                    String link= (String) msg.obj;
//                    imageLoader.get(link,ImageLoader.getImageListener(
//                            ivImg,R.mipmap.img_gray_color,R.mipmap.img_gray_color));
//                }
//                return false;
//            }
//        });
