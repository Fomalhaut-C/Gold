package com.fomalhaut.gold.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fomalhaut.gold.R;
import com.fomalhaut.gold.Table.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity{

    private ImageView register_iv_logo;
    /**
     * 用户名
     */
    private TextInputEditText register_et_user;
    private TextInputLayout textInputLayout;
    /**
     * 密码
     */
    private TextInputEditText register_et_psw;
    private TextInputLayout textInputLayout2;
    /**
     * 再次输入密码
     */
    private TextInputEditText register_et_confirm_psw;
    private TextInputLayout textInputLayout3;
    /**
     * 设置密保手机
     */
    private TextInputEditText register_et_security_phone;
    private TextInputLayout textInputLayout4;
    /**
     * 注册
     */
    private Button register_btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        register_iv_logo = (ImageView) findViewById(R.id.register_iv_logo);
        register_et_user = (TextInputEditText) findViewById(R.id.register_et_user);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        register_et_psw = (TextInputEditText) findViewById(R.id.register_et_psw);
        textInputLayout2 = (TextInputLayout) findViewById(R.id.textInputLayout2);
        register_et_confirm_psw = (TextInputEditText) findViewById(R.id.register_et_confirm_psw);
        textInputLayout3 = (TextInputLayout) findViewById(R.id.textInputLayout3);
        register_et_security_phone = (TextInputEditText) findViewById(R.id.register_et_security_phone);
        textInputLayout4 = (TextInputLayout) findViewById(R.id.textInputLayout4);
        register_btn_register = (Button) findViewById(R.id.register_btn_register);
        register_btn_register.setOnClickListener(v -> {
            String username = register_et_user.getText().toString().trim();
            String password = register_et_psw.getText().toString().trim();
            String confirm = register_et_confirm_psw.getText().toString().trim();
            String phone = register_et_security_phone.getText().toString().trim();
            if (TextUtils.isEmpty(username)){
                Toast.makeText(this, "用户名不可以为空", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(password)){
                Toast.makeText(this, "密码不可以为空", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(confirm)){
                Toast.makeText(this, "再次输入密码不可以为空", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(phone)){
                Toast.makeText(this, "密保手机不可以为空", Toast.LENGTH_SHORT).show();
            }else if (!password.equals(confirm)){
                Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            }else{
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setPhone(phone);
                if (user.save()){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this,"该用户名已被注册，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
