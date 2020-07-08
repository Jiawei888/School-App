package com.example.schoolapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 添加学生信息的界面,修改学生信息的界面
 * Created by he on 2016/10/1.
 */
public class add_student_admin extends Activity {

    private EditText email;
    private EditText firstname;
    private EditText familyname;
    private EditText number;
    private EditText password;
    private EditText group;
    private EditText role;

    private String oldID;//用于防治修改信息时将ID也修改了，而原始的有该ID的学生信息还保存在数据库中


    private Button sure;//确定按钮
    private DBOpenHelper dbHelper;
    Intent oldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_student_info_admin);
        final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);

        email = (EditText) findViewById(R.id.register_email_admin);
        firstname = (EditText) findViewById(R.id.register_first_name_admin);
        familyname = (EditText) findViewById(R.id.register_family_name_admin);
        number = (EditText) findViewById(R.id.register_number_admin);
        password = (EditText) findViewById(R.id.register_pwd_admin);
        group = (EditText) findViewById(R.id.register_class_admin);

        //dbHelper = myDatabaseHelper.getInstance(this);

        oldData = getIntent();
        if (oldData.getStringExtra("haveData").equals("true")  ) {
            initInfo();//恢复旧数据
        }

        sure = (Button) findViewById(R.id.update_button_admin);
        //将数据插入数据库
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sex不能为空否则程序崩溃，因为在StudentAdapter中有一个ImageView要设置图片
                //我这里要求id,name,sex都不能为空
                String email_ = email.getText().toString();
                String firstname_ = firstname.getText().toString();
                String familyname_ = familyname.getText().toString();
                String password_ = password.getText().toString();
                String number_ = number.getText().toString();
                String group_ = group.getText().toString();
                String msg = "";
                final boolean[] match = {false};

                ArrayList<User> data = mDBOpenHelper.getAllData();//user数据库
                SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();

                if (!TextUtils.isEmpty(email_) && !TextUtils.isEmpty(firstname_) && !TextUtils.isEmpty(familyname_) &&
                        !TextUtils.isEmpty(password_) && !TextUtils.isEmpty(number_) && !TextUtils.isEmpty(group_))
                {

                    /*SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.beginTransaction();//开启事务
                    db.execSQL("delete from User where name=?", new String[]{oldID});//删除旧数据

                    //判断学号是否重复
                    Cursor cursor = db.rawQuery("select * from User where name=?", new String[]{email_});
                    if (cursor.moveToNext()) {
                        Toast.makeText(add_student_admin.this, "已有学生使用该邮箱,请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        db.execSQL("insert into User(name,password,firstname,familyname,Class,number,role) values(?,?,?,?,?,?,?)", new String[]{email_, password_, firstname_,
                                familyname_, group_, number_,"Student"});
                        db.setTransactionSuccessful();//事务执行成功
                        db.endTransaction();//结束事务
                        Intent intent = new Intent(add_student_admin.this, MainActivity_Admin.class);
                        startActivity(intent);
                        Toast.makeText(add_student_admin.this, "Successful", Toast.LENGTH_SHORT).show();
                    }*/




                    /*inner: for (int j = 0; j < data.size(); j++)
                    {
                        User user_children = data.get(j);
                        if (user_children.getName().equals(email_)) //判断需要绑定的学生信息是否在此之前被存入过children表里面，若存了，则代表已有其他家长添加该孩子，则不可添加
                        {
                            msg = "The student information have been updated or it is a parent account";
                            match[0] =false;
                            break inner;
                        }
                        else
                        {
                            msg="";
                            match[0] =true;
                            //if(j==data.size())
                              //  break inner;
                        }
                    }*/




                    //将用户名和密码加入到数据库中
                    Cursor cursor = db.rawQuery("select name from user where name=? ", new String[]{email_});
                    //用户邮箱是否存在
                    if (cursor.moveToNext()) {
                        if(oldData.getStringExtra("haveData").equals("true"))
                        {
                            /*mDBOpenHelper.add(email_, password_, firstname_, familyname_, group_, number_, "Student");
                            Intent intent = new Intent(add_student_admin.this, select_st_admin.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(add_student_admin.this, "Update successfully", Toast.LENGTH_SHORT).show();*/

                            SQLiteDatabase db1 = mDBOpenHelper.getWritableDatabase();
                            db1.execSQL("update user set password=?, firstname=?, familyname=?, Class=?, number=?" +
                                    " where name=?", new String[]{password_, firstname_, familyname_, group_, number_, email_});
                            Intent intent = new Intent(add_student_admin.this, select_st_admin.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(add_student_admin.this, "Update successfully", Toast.LENGTH_SHORT).show();


                        }
                        else {
                            Toast.makeText(add_student_admin.this, "The user already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                            mDBOpenHelper.add(email_, password_, firstname_, familyname_, group_, number_, "Student");
                            Intent intent = new Intent(add_student_admin.this, MainActivity_Admin.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(add_student_admin.this, "Registered successfully", Toast.LENGTH_SHORT).show();


                    }

                } else {
                    Toast.makeText(add_student_admin.this, "Incomplete Info", Toast.LENGTH_SHORT).show();
                }


                /*if(match[0]) {

                        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                        Cursor cursor = db.rawQuery("select * from user where name=?", new String[]{email_});

                        while (cursor.moveToNext()) {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String password = cursor.getString(cursor.getColumnIndex("password"));
                            String firstname = cursor.getString(cursor.getColumnIndex("firstname"));

                            String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
                            String Class = cursor.getString(cursor.getColumnIndex("Class"));
                            String number = cursor.getString(cursor.getColumnIndex("number"));

                            mDBOpenHelper.add(name, password, firstname, familyname, Class, number, "Student"); //把孩子的个人信息添加到children表中

                        }
                        cursor.close();
                        match[0]=false;
                        //msg = "Yes";
                    //db.setTransactionSuccessful();//事务执行成功
                    //db.endTransaction();//结束事务
                    Intent intent = new Intent(add_student_admin.this, MainActivity_Admin.class);
                    startActivity(intent);
                    Toast.makeText(add_student_admin.this, "Binding successfully",
                                Toast.LENGTH_SHORT).show();

                }

                if(!(msg.equals("")))
                {
                    Toast.makeText(add_student_admin.this, msg, Toast.LENGTH_SHORT).show();
                    msg = "";
                }*/

            }
        });

    }

    //恢复旧数据
    private void initInfo() {


        String oldFirstname = oldData.getStringExtra("firstname");
        firstname.setText(oldFirstname);

        String oldFamilyname = oldData.getStringExtra("familyname");
        familyname.setText(oldFamilyname);

        String oldname = oldData.getStringExtra("name");
        oldID = oldname;
        email.setText(oldname);


        String oldNumber = oldData.getStringExtra("number");
        number.setText(oldNumber);

        String oldPassword = oldData.getStringExtra("password");
        password.setText(oldPassword);

        String oldgroup = oldData.getStringExtra("Class");
        group.setText(oldgroup);

    }


}

