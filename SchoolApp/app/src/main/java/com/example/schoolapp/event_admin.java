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

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class event_admin extends Activity {

    private EditText title;
    private EditText content;
    private EditText date;
    private Button event_button_admin;

    private DBOpenHelper mDBOpenHelper;
    private String oldID;//用于防治修改信息时将ID也修改了，而原始的有该ID的学生信息还保存在数据库中

    private String oldemail;
    private String oldFirstname;
    private String oldFamilyname;
    private String oldrole;
    private String oldtitle;
    private String oldcontent;
    private String olddate;
    private String oldtype;
    private Button sure;//确定按钮
    Intent oldData;
    private String havedata;
    private String date_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.event_admin);
        //final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);

        mDBOpenHelper = new DBOpenHelper(this);
        title = findViewById(R.id.register_event_admin);
        content = findViewById(R.id.register_content_admin);
        //event_button_admin.setOnClickListener(this);
        oldData = getIntent();
        havedata = oldData.getStringExtra("haveData");

        date_flag = oldData.getStringExtra("date");
        //flag = oldData.getStringExtra("flag");
       // if (oldData.getStringExtra("haveDate").equals("true")) {
            initInfo();//恢复旧数据
        //}

        sure = (Button) findViewById(R.id.event_button_admin);
        sure.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){

                    String title_text = title.getText().toString().trim();
                    String content_text = content.getText().toString().trim();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");// HH:mm:ss
                    Date date = new Date(System.currentTimeMillis());
                    String date_text = simpleDateFormat.format(date);

                    SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();

                    if (!TextUtils.isEmpty(title_text) && !TextUtils.isEmpty(content_text)) {

                        if(date_flag != null && date_flag.equals("Null"))  //enter this activity from student/parent
                        {
                            mDBOpenHelper.add_info(oldemail, oldFirstname, oldFamilyname, oldrole, title_text, content_text, date_text, oldtype);

                        }
                        else {//enter this activity from event list
                            if (havedata.equals("true"))
                            {
                                SQLiteDatabase db1 = mDBOpenHelper.getWritableDatabase();
                                //from event list
                                {
                                    //db1.execSQL("update info set firstname=?, familyname=?, role=?, title=?, content=?, date=?, type=?" +
                                    //" where email=?", new String[]{oldFirstname, oldFamilyname, oldrole, title_text, content_text, date_text, oldtype, oldemail});
                                    db1.execSQL("update info set email=?, firstname=?, familyname=?, role=?, title=?, content=?, date=?, type=?" +
                                            " where date=?", new String[]{oldemail, oldFirstname, oldFamilyname, oldrole, title_text, content_text, date_text, oldtype, olddate});

                                    //Toast.makeText(event_admin.this, "Update successfully", Toast.LENGTH_SHORT).show();
                                }

                            } else { //enter this activity from main page


                                mDBOpenHelper.add_info("ALL", "ALL", "ALL", "ALL", title_text, content_text, date_text, "event");

                            }

                        }

                        Intent intent = new Intent(event_admin.this, MainActivity_Admin.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(event_admin.this, "Create or update Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(event_admin.this, "Do not send a empty event", Toast.LENGTH_SHORT).show();
                    }



        }
        });
    }
    //恢复旧数据
    private void initInfo() {


        oldFirstname = oldData.getStringExtra("firstname");

        oldFamilyname = oldData.getStringExtra("familyname");

        oldemail = oldData.getStringExtra("email");
        oldID = oldemail;

        oldrole = oldData.getStringExtra("role");

        oldtitle = oldData.getStringExtra("title");
        title.setText(oldtitle);

        oldcontent = oldData.getStringExtra("content");
        content.setText(oldcontent);

        olddate = oldData.getStringExtra("date");
        //group.setText(olddate);

        oldtype = oldData.getStringExtra("type");

    }

}
