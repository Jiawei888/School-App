package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonlogin ;
    private Button buttonsignup;
    private EditText id;
    private EditText psw;
    private DBOpenHelper mDBOpenHelper;
    private int flag_rb=1;

    //public static String Name;
    public static int I;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mDBOpenHelper = new DBOpenHelper(this);
        //TextView title = findViewById(R.id.title_login);
        //TextView id1 = findViewById(R.id.user_pwd);
        //TextView password1 = findViewById(R.id.user_pwd);
        buttonlogin = findViewById(R.id.button_login);
        buttonsignup = findViewById(R.id.button_signup);
        id = findViewById(R.id.text_user_id);
        psw = findViewById(R.id.text_user_pwd);
        buttonlogin.setOnClickListener(this);
        buttonsignup.setOnClickListener(this);
        //ImageView soton = findViewById(R.id.studentphoto);
    }


    public void onRadioButtonClicked(View view) {
        RadioButton button = (RadioButton) view;
        boolean isChecked = button.isChecked();
        switch (view.getId()) {
            case R.id.rb_student:
                if (isChecked) {
                    Toast.makeText(loginActivity.this, button.getText(), Toast.LENGTH_SHORT).show();
                    flag_rb=1;
                }
                break;
            case R.id.rb_parent:
                if (isChecked) {
                    Toast.makeText(loginActivity.this, button.getText(), Toast.LENGTH_SHORT).show();
                    flag_rb=2;
                }
                break;
            case R.id.rb_admin:
                if (isChecked) {
                    Toast.makeText(loginActivity.this, button.getText(), Toast.LENGTH_SHORT).show();
                    flag_rb=3;
                }
                break;
            default:
                break;
        }
    }

        public void onClick(View view) {
            switch (view.getId()) {
                // jump to register activity
                case R.id.button_signup:
                    startActivity(new Intent(this, RegisterActivity.class));
                    //finish();
                    break;
                /**
                 * 登录验证：
                 *
                 * 从EditText的对象上获取文本编辑框输入的数据，并把左右两边的空格去掉
                 *  String name = mEtLoginactivityUsername.getText().toString().trim();
                 *  String password = mEtLoginactivityPassword.getText().toString().trim();
                 *  进行匹配验证,先判断一下用户名密码是否为空，
                 *  if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password))
                 *  再进而for循环判断是否与数据库中的数据相匹配
                 *  if (name.equals(user.getName()) && password.equals(user.getPassword()))
                 *  一旦匹配，立即将match = true；break；
                 *  否则 一直匹配到结束 match = false；
                 *
                 *  登录成功之后，进行页面跳转：
                 *
                 *  Intent intent = new Intent(this, MainActivity_Student.class);
                 *  startActivity(intent);
                 *  finish();//销毁此Activity
                 */
                case R.id.button_login:
                    //startActivity(new Intent(this, MainActivity_Student.class));
                    //finish();//销毁此Activity
                    String name = id.getText().toString().trim();
                    String password = psw.getText().toString().trim();
                    User user = null;
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                        ArrayList<User> data = mDBOpenHelper.getAllData();
                        boolean match = false;
                        for (int i = 0; i < data.size(); i++) {
                             user = data.get(i);
                            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                                match = true;
                                //Name = user.getName();
                                I = i;
                                break;
                            } else {
                                match = false;
                            }
                        }
                        if (match) {
                            if(flag_rb==1 && (user.getRole().equals("Student"))){ //Student End
                                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(this, MainActivity_Student.class);
                                intent2.putExtra("name", id.getText().toString().trim());
                                startActivity(intent2);
                                //startActivity(new Intent(this, MainActivity_Student.class));
                                finish();//销毁此Activity
                            }

                            else if (flag_rb==2 && (user.getRole().equals("Parent"))){  //Parent End
                                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(this, MainActivity_Parent.class);
                                intent2.putExtra("name", id.getText().toString().trim());
                                startActivity(intent2);
                                //startActivity(new Intent(this, MainActivity_Student.class));
                                finish();//销毁此Activity
                            }else{
                                Toast.makeText(this, "Please check your identity", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(flag_rb==3 && name.equals("888") && password.equals("888")) //Admin
                        {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(this, MainActivity_Admin.class);
                            //intent2.putExtra("name", id.getText().toString().trim());
                            startActivity(intent2);
                            //startActivity(new Intent(this, MainActivity_Student.class));
                            finish();//销毁此Activity
                        }
                        else {
                            Toast.makeText(this, "The user name or password \n is incorrect, please enter again", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }


}




