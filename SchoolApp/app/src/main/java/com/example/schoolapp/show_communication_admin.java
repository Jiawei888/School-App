package com.example.schoolapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class show_communication_admin extends AppCompatActivity{

    private ArrayList<Info> studentList = new ArrayList<Info>();
    private ArrayList<Info> exampleList = new ArrayList<Info>();

    //private DBOpenHelper dbHelper;
    private ListView listView_admin;
    private communicationAdapter_admin adapter; //gai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.communicationinfo_activity_admin);   //gai
        //dbHelper = myDatabaseHelper.getInstance(this);
        //mDBOpenHelper = new DBOpenHelper(this);
        final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);
        //initStudent();//从数据库中检索所有学生信息
        studentList = mDBOpenHelper.getAllData_info(); //gai
        exampleList = mDBOpenHelper.getAllData_info(); //gai
        studentList.clear();

        for (int i = 0; i < exampleList.size(); i++) { //gai
            Info user = exampleList.get(i);
            if (user.gettype().equals("communication"))
            {
                String email = user.getEmail();
                String firstname = user.getFirstname();
                String familyname = user.getFamilytname();
                String role = user.getRole();
                String title = user.gettitle();
                String content = user.getcontent();
                String date = user.getdate();
                String type = user.gettype();
                //studentList.clear();
                studentList.add(new Info(email, firstname, familyname, role, title, content, date, type)); //gai
                //msg = "Match";

            }

        }
        adapter = new communicationAdapter_admin(show_communication_admin.this, R.layout.communication_item_admin, studentList); //gai
        listView_admin = (ListView) findViewById(R.id.communication_view_admin); //gai
        listView_admin.setAdapter(adapter);


        //listView点击事件
        listView_admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Info student = studentList.get(position);//捕获学生实例
                AlertDialog.Builder builder = new AlertDialog.Builder(show_communication_admin.this);
                LayoutInflater factory = LayoutInflater.from(show_communication_admin.this);
                final View textEntryView = factory.inflate(R.layout.communication_info_admin, null);//加载AlertDialog自定义布局  //gai
                builder.setView(textEntryView);
                builder.setTitle("Please select a feature");

                Button selectInfo = (Button) textEntryView.findViewById(R.id.communication_info_select);//查看学生详细信息按钮  //gai
                selectInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //再次弹出一个alertDialog，用于显示详细学生信息
                        AlertDialog.Builder select_builder = new AlertDialog.Builder(show_communication_admin.this);  //gai
                        select_builder.setTitle("Communication Detail");
                        StringBuilder sb = new StringBuilder();

                        sb.append("Email：" + student.getEmail() + "\n");
                        sb.append("First Name：" + student.getFirstname() + "\n");
                        sb.append("Family Name：" + student.getFamilytname() + "\n");
                        sb.append("Identity：" + student.getRole() + "\n");
                        sb.append("Title：" + student.gettitle() + "\n");
                        sb.append("Content：" + student.getcontent() + "\n");
                        sb.append("Date：" + student.getdate() + "\n");

                        select_builder.setMessage(sb.toString());
                        select_builder.create().show();

                    }
                });


                //删除学生信息
                Button delete_info = (Button) textEntryView.findViewById(R.id.communication_info_delete); //gai

                delete_info.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final AlertDialog.Builder delete_builder = new AlertDialog.Builder(show_communication_admin.this); //gai
                        delete_builder.setTitle("Warning！！！！");
                        delete_builder.setMessage("You will delete this communication, this operation is not reversible, please be careful！");

                        delete_builder.setNegativeButton("Cancel", null);
                        delete_builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //BOpenHelper mDBOpenHelper = new DBOpenHelper(this);
                                SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                                db.execSQL("delete from Info where date=?", new String[]{student.getdate()});  //gai
                                studentList.remove(position);//移除
                                adapter.notifyDataSetChanged();//刷新列表

                            }
                        });
                        delete_builder.create().show();

                    }
                });

                //修改学生信息,通过intent传递旧学生信息
                Button update_info = (Button) textEntryView.findViewById(R.id.communication_info_update);  //gai
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到添加学生信息的界面,通过intent传递数据
                        Intent intent = new Intent(show_communication_admin.this, communication_admin.class);  //gai

                        intent.putExtra("haveData", "true");
                        //intent.putExtra("flag","false");
                        intent.putExtra("email", student.getEmail());
                        intent.putExtra("firstname", student.getFirstname());
                        intent.putExtra("familyname", student.getFamilytname());
                        intent.putExtra("role", student.getRole());
                        intent.putExtra("title", student.gettitle());
                        intent.putExtra("content", student.getcontent());
                        intent.putExtra("date", student.getdate());
                        intent.putExtra("type", student.gettype());
                        startActivity(intent);
                        finish();
                        Toast.makeText(show_communication_admin.this, "Please update communication to this parent or parents",
                                Toast.LENGTH_LONG).show();

                    }
                });

                builder.create().show();
            }
        });


    }




}