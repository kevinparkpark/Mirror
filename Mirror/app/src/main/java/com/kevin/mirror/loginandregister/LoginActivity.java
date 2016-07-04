package com.kevin.mirror.loginandregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.purchase.OrderActivity;

/**
 * Created by kevin on 16/6/23.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUser, etPs;
    private TextView tvLogin, tvRegister;
    private ImageView ivClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.et_user_number_login);
        etPs = (EditText) findViewById(R.id.et_user_password_login);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvRegister = (TextView) findViewById(R.id.tv_create_account_login);
        ivClose = (ImageView) findViewById(R.id.close_login_activity);

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        ivClose.setOnClickListener(this);

        //监听etps
        etPs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //密码长度大于0设置颜色
                if (etPs.length() > 0) {
                    tvLogin.setBackgroundResource(R.drawable.selector_login_button);
                } else {
                    tvLogin.setBackgroundResource(R.mipmap.login_no_button);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                String log = "http://api101.test.mirroreye.cn/index.php/user/login";
                if (etUser.length() == 11) {
                    postLogin(URLValues.LOGIN_URL, etUser.getText().toString(), etPs.getText().toString());
                } else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_create_account_login:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.close_login_activity:
                finish();
                break;
        }
    }

    //登录post请求
    private void postLogin(String url, String phoneNum, String ps) {
        NetTool netTool = new NetTool();
        netTool.postLogin(url, phoneNum, ps, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                LoginBean bean = gson.fromJson(result, LoginBean.class);

                if (bean.getResult().equals("1")) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    DBUtils dbUtils=new DBUtils();
//                    dbUtils.insertDB(new DbBean(bean.getData().getToken()));

                    SharedPreferences sp = getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", bean.getData().getToken());
                    Log.d("LoginActivity", bean.getData().getToken());
                    editor.commit();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "result0--------" + bean.getMsg());
                }
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }
}
