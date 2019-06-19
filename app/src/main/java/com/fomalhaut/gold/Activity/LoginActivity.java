package com.fomalhaut.gold.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fomalhaut.gold.MainActivity;
import com.fomalhaut.gold.R;
import com.fomalhaut.gold.Table.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView login_iv_logo;
    /**
     * 用户名
     */
    private TextInputEditText login_et_user;
    private TextInputLayout textInputLayout;
    /**
     * 密码
     */
    private TextInputEditText login_et_psw;
    private TextInputLayout textInputLayout2;
    /**
     * 登录
     */
    private Button login_btn_login;
    /**
     * 忘记密码
     */
    private TextView login_tv_forgetpsw;
    /**
     * 用户注册
     */
    private TextView login_tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.getDatabase();
        initView();
    }

    private void initView() {
        login_iv_logo = (ImageView) findViewById(R.id.register_iv_logo);
        login_et_user = (TextInputEditText) findViewById(R.id.register_et_user);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        login_et_psw = (TextInputEditText) findViewById(R.id.register_et_psw);
        textInputLayout2 = (TextInputLayout) findViewById(R.id.textInputLayout2);
        login_btn_login = (Button) findViewById(R.id.register_btn_register);
        login_btn_login.setOnClickListener(this);
        login_tv_forgetpsw = (TextView) findViewById(R.id.login_tv_forgetpsw);
        login_tv_forgetpsw.setOnClickListener(this);
        login_tv_register = (TextView) findViewById(R.id.login_tv_register);
        login_tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_register:
                String username = login_et_user.getText().toString().trim();
                String password = login_et_psw.getText().toString().trim();
                List<User> login = LitePal.where("username = ? and password = ?", username, password).find(User.class);
                List<User> users = LitePal.where("username = ?", username).find(User.class);
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(this, "用户名不可以为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password)){
                    Toast.makeText(this, "密码不可以为空", Toast.LENGTH_SHORT).show();
                }else if (login.size() > 0){
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else if (users.size() > 0){
                    Toast.makeText(this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_tv_forgetpsw:
                Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            default:
                break;
        }
    }
}
