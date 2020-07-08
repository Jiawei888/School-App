package com.example.schoolapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonregister;
    private Spinner spinner1;
    private String realCode;
    private ImageView showcode;
    private EditText code;
    private EditText register_email;
    private EditText register_first_name;
    private EditText register_family_name;

    private EditText register_pwd;
    private EditText register_class;
    private EditText register_number;
    private DBOpenHelper mDBOpenHelper;
    public static String flag_role;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        buttonregister = findViewById(R.id.register_button);
        spinner1 = findViewById(R.id.role);
        showcode = findViewById(R.id.showcode);
        code = findViewById(R.id.code);
        register_email = findViewById(R.id.register_email);
        register_first_name = findViewById(R.id.register_first_name);
        register_family_name = findViewById(R.id.register_family_name);
        register_pwd = findViewById(R.id.register_pwd);
        register_class = findViewById(R.id.register_class);
        register_number = findViewById(R.id.register_number);
        flag_role = " ";
        showcode.setOnClickListener(this);
        buttonregister.setOnClickListener(this);
        mDBOpenHelper = new DBOpenHelper(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.USER, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] user = getResources().getStringArray(R.array.USER);
                if(user[pos].equals("Parent")){
                Toast.makeText(RegisterActivity.this, "Your Choice is:"+user[pos], 2000).show();
                    flag_role="Parent";
                }
                else if(user[pos].equals("Student")){
                    Toast.makeText(RegisterActivity.this, "Your Choice is:"+user[pos], 2000).show();
                    flag_role="Student";
                }
                else{
                    flag_role="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        //将验证码用图片的形式显示出来
        showcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_button) { //register
            //获取用户输入的用户名、密码、验证码
            String username = register_email.getText().toString().trim();
            String password = register_pwd.getText().toString().trim();
            String phoneCode = code.getText().toString().toLowerCase();
            String firstname = register_first_name.getText().toString().trim();
            String familyname = register_family_name.getText().toString().trim();

            String Class = register_class.getText().toString().trim();
            String number = register_number.getText().toString().trim();
            SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
            //注册验证
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCode) && !TextUtils.isEmpty(firstname)
                    && !TextUtils.isEmpty(familyname) && !TextUtils.isEmpty(number)) {
                if (flag_role.equals("Parent") || flag_role.equals("Student")) {
                    if (phoneCode.equals(realCode)) {
                        //将用户名和密码加入到数据库中
                        Cursor cursor = db.rawQuery("select name from user where name=? ", new String[]{username});
                        //
                        if (cursor.moveToNext()) {
                            Toast.makeText(RegisterActivity.this, "The user already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            if(flag_role.equals("Student") && !TextUtils.isEmpty(Class))
                            {
                                mDBOpenHelper.add(username, password, firstname, familyname, Class, number, flag_role);
                                Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(this, "Verification passed \n successful registration", Toast.LENGTH_SHORT).show();
                            }
                            else if(flag_role.equals("Parent"))
                            {mDBOpenHelper.add(username, password, firstname, familyname, "0", number, flag_role);
                                Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(this, "Verification passed \n successful registration", Toast.LENGTH_SHORT).show();
                            }
                            else
                            Toast.makeText(this, "Please select your class", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Verification code error \n registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else { Toast.makeText(this, "Please select your identity", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Incomplete information \n registration failed", Toast.LENGTH_SHORT).show();
            }
            /*Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
            startActivity(intent);
            finish();*/
        }
        else if (view.getId() == R.id.showcode) {
            //改变随机验证码的生成
            showcode.setImageBitmap(Code.getInstance().createBitmap());
            realCode = Code.getInstance().getCode().toLowerCase();
        }
    }



}
