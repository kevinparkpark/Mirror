package com.kevin.mirror.loginandregister;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;

/**
 * Created by kevin on 16/6/23.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivClose;
    private EditText etUser, etPs, etCaptcha;
    private TextView tvCaptcha, tvCreate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ivClose = (ImageView) findViewById(R.id.close_register_activity);
        etUser = (EditText) findViewById(R.id.et_user_number_register);
        etPs = (EditText) findViewById(R.id.et_password_first_register);
        etCaptcha = (EditText) findViewById(R.id.et_verification_code_register);
        tvCaptcha = (TextView) findViewById(R.id.tv_send_verification_code_register);
        tvCreate = (TextView) findViewById(R.id.tv_create_account_register);

        ivClose.setOnClickListener(this);
        tvCaptcha.setOnClickListener(this);
        tvCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_register_activity:
                finish();
                break;
            case R.id.tv_send_verification_code_register:
                String sendCode = "http://api101.test.mirroreye.cn/index.php/user/send_code";
                if (etUser.length() == 11) {
                    postSendCode(URLValues.SENDCODE_URL, etUser.toString(), "", "");
//                    Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                    tvCaptcha.setEnabled(false);
                    timerCount();
                } else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_create_account_register:
                String reg = "http://api101.test.mirroreye.cn/index.php/user/reg";
                if (etUser.length() == 11 || etCaptcha.length() == 5 || etPs.length() > 5) {
                    postSendCode(URLValues.REGISTER_URL, etUser.toString(), etCaptcha.toString(), etPs.toString());
                } else if (etPs.length() < 6) {
                    Toast.makeText(this, "密码长度大于5位", Toast.LENGTH_SHORT).show();
                } else if (etUser.length() != 11) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void postSendCode(String url, String phoneNum, String captcha, String ps) {
        NetTool netTool = new NetTool();
        netTool.postLoginOrRegister(url, phoneNum, captcha, ps, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                RegisterBean bean = gson.fromJson(result, RegisterBean.class);
                if (bean.getResult().equals("1")) {
                    Toast.makeText(RegisterActivity.this, bean.getData(), Toast.LENGTH_SHORT).show();
                    Log.d("RegisterActivity", "result1------" + bean.getData());
                } else {
                    Toast.makeText(RegisterActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                    Log.d("RegisterActivity", "result0--------" + bean.getMsg());
                }
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    public void timerCount() {
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCaptcha.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                tvCaptcha.setText("发送验证码");
                tvCaptcha.setEnabled(true);
            }
        }.start();
    }

}
