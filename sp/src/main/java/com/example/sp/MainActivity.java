package com.example.sp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SP_NAME = "LOGIN_SP";
    private static final String USERNAME_KEY = "USERNAME";
    private static final String PASSWORD_KEY = "PASSWORD";

    //用户名
    private EditText etnUserName = null;
    //密码
    private EditText etnPwd = null;
    //登陆按钮
    private Button btnLogin = null;
    //
    private SharedPreferences sharedPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSP();
        initComponent();
        setListener();
        initView();
    }

    /**
     * 初始化界面
     * 由于这就是一个登陆界面。
     * 也就是来个自动登陆吧。
     * 注意！注意！注意！存在安全隐患，这里只是展示SP的使用，正式项目望三思。
     */
    private void initView() {
        final String userName = sharedPreferences.getString(USERNAME_KEY,"");
        final String pwd = sharedPreferences.getString(PASSWORD_KEY,"");

        if (userName.equals(pwd) && userName.equals("")) {
            return;
        }
        else {
            etnUserName.setText(userName);
            etnPwd.setText(pwd);
            Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 初始化SP数据库
     */
    private void initSP() {
        sharedPreferences = getSharedPreferences(SP_NAME,MODE_PRIVATE);
    }

    /**
     * 设置点击监听
     */
    private void setListener() {
        btnLogin.setOnClickListener(v -> {
            String pwd = etnPwd.getText().toString();
            String userName = etnUserName.getText().toString();
            if (userName.equals("root") && pwd.equals("123456")){
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USERNAME_KEY,userName);
                editor.putString(PASSWORD_KEY,pwd);
                editor.apply();
                Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "登陆失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * find所有的控件
     */
    private void initComponent() {
        etnUserName = findViewById(R.id.etn_username);
        etnPwd = findViewById(R.id.etn_pwd);
        btnLogin = findViewById(R.id.btn_login);
    }
}