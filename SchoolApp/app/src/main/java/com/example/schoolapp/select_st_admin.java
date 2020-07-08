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

public class select_st_admin extends AppCompatActivity{

    private List<User> studentList = new ArrayList<User>();
    private List<User> exampleList = new ArrayList<User>();

    //private DBOpenHelper dbHelper;
    private ListView listView_admin;
    private StudentAdapter_admin adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.studentinfo_activity_admin);
        //dbHelper = myDatabaseHelper.getInstance(this);
        final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);
        //initStudent();//从数据库中检索所有学生信息
        studentList = mDBOpenHelper.getAllData();
        exampleList = mDBOpenHelper.getAllData();
        studentList.clear();

        for (int i = 0; i < exampleList.size(); i++) {
            User user = exampleList.get(i);
            if (user.getRole().equals("Student")) {
                String name = user.getName();
                String password = user.getPassword();
                String firstname = user.getFirstname();

                String familyname = user.getFamilytname();
                String Class = user.getclass();
                String number = user.getNumber();
                String role = user.getRole();
                //studentList.clear();
                studentList.add(new User(name, password, firstname, familyname, Class, number, role));
                //msg = "Match";

            }

        }
        adapter = new StudentAdapter_admin(select_st_admin.this, R.layout.child_item_admin, studentList);
        listView_admin = (ListView) findViewById(R.id.list_view_admin);
        listView_admin.setAdapter(adapter);


        //listView点击事件
        listView_admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final User student = studentList.get(position);//捕获学生实例
                AlertDialog.Builder builder = new AlertDialog.Builder(select_st_admin.this);
                LayoutInflater factory = LayoutInflater.from(select_st_admin.this);
                final View textEntryView = factory.inflate(R.layout.student_info_admin, null);//加载AlertDialog自定义布局
                builder.setView(textEntryView);
                builder.setTitle("Student Menu");

                Button selectInfo = (Button) textEntryView.findViewById(R.id.student_info_select);//查看学生详细信息按钮
                selectInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //再次弹出一个alertDialog，用于显示详细学生信息
                        AlertDialog.Builder select_builder = new AlertDialog.Builder(select_st_admin.this);
                        select_builder.setTitle("Student Detail");
                        StringBuilder sb = new StringBuilder();

                        sb.append("Email：" + student.getName() + "\n");
                        sb.append("Password：" + student.getPassword() + "\n");
                        sb.append("First Name：" + student.getFirstname() + "\n");
                        sb.append("Family Name：" + student.getFamilytname() + "\n");
                        sb.append("Contact Number：" + student.getNumber() + "\n");
                        sb.append("Identity：" + student.getRole() + "\n");

                        select_builder.setMessage(sb.toString());
                        select_builder.create().show();

                    }
                });


                //删除学生信息
                Button delete_info = (Button) textEntryView.findViewById(R.id.student_info_delete);

                delete_info.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final AlertDialog.Builder delete_builder = new AlertDialog.Builder(select_st_admin.this);
                        delete_builder.setTitle("Warning！！！！");
                        delete_builder.setMessage("You will delete the student information, this operation is not reversible, please be careful！");

                        delete_builder.setNegativeButton("Cancel", null);
                        delete_builder.setPositiveButton("Comfirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //BOpenHelper mDBOpenHelper = new DBOpenHelper(this);
                                SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                                SQLiteDatabase db1 = mDBOpenHelper.getWritableDatabase();
                                db.execSQL("delete from User where name=?", new String[]{student.getName()});

                                db1.execSQL("delete from children where name=?", new String[]{student.getName()});
                                studentList.remove(position);//移除
                                adapter.notifyDataSetChanged();//刷新列表

                            }
                        });
                        delete_builder.create().show();

                    }
                });


                Button event_info = (Button) textEntryView.findViewById(R.id.student_info_event);

                event_info.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(select_st_admin.this, event_admin.class);
                        //intent.putExtra("flag","true");
                        //intent.putExtra("haveDate","false");
                        intent.putExtra("email", student.getName());
                        intent.putExtra("firstname", student.getFirstname());
                        intent.putExtra("familyname", student.getFamilytname());
                        intent.putExtra("role", student.getRole());
                        intent.putExtra("title", "Null");
                        intent.putExtra("content", "Null");
                        intent.putExtra("date", "Null");
                        intent.putExtra("type", "event");
                        startActivity(intent);
                        Toast.makeText(select_st_admin.this, "You only send the event to this student \n  not to all users",
                                Toast.LENGTH_LONG).show();


                    }
                });

                //send noticeboard
                Button notice_info = (Button) textEntryView.findViewById(R.id.student_info_notification);

                notice_info.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(select_st_admin.this, notice_admin.class);
                        //intent.putExtra("flag","true");
                        //intent.putExtra("haveDate","false");
                        intent.putExtra("email", student.getName());
                        intent.putExtra("firstname", student.getFirstname());
                        intent.putExtra("familyname", student.getFamilytname());
                        intent.putExtra("role", student.getRole());
                        intent.putExtra("title", "Null");
                        intent.putExtra("content", "Null");
                        intent.putExtra("date", "Null");
                        intent.putExtra("type", "notice");
                        startActivity(intent);
                        Toast.makeText(select_st_admin.this, "You only send the notice to this student \n  not to all students",
                                Toast.LENGTH_LONG).show();


                    }
                });



                //修改学生信息,通过intent传递旧学生信息

                //Modify the student information and pass the old student information through the intent
                Button update_info = (Button) textEntryView.findViewById(R.id.student_info_update);
                update_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到添加学生信息的界面,通过intent传递数据
                        //Jump to the interface to add student information and pass the data through the intent
                        Intent intent = new Intent(select_st_admin.this, add_student_admin.class);
                        //intent.putExtra("flag","true");

                        intent.putExtra("haveData", "true");
                        intent.putExtra("name", student.getName());
                        intent.putExtra("firstname", student.getFirstname());
                        intent.putExtra("familyname", student.getFamilytname());
                        intent.putExtra("number", student.getNumber());
                        intent.putExtra("password", student.getPassword());
                        intent.putExtra("role", student.getRole());
                        intent.putExtra("Class", student.getclass());
                        startActivity(intent);
                        Toast.makeText(select_st_admin.this, "If you change the email \n it will create a new account",
                                Toast.LENGTH_LONG).show();

                    }
                });

                builder.create().show();
            }
        });


    }




}