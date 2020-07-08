package com.example.schoolapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Student extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String ID;
    private static Intent intent2;

    public static String text_firstname_st;
    public static String text_email_st;
    public static String text_familyname_st;
    public static String text_Class_st;
    public static String text_number_st;
    public static String text_role_st;



    private ListView listView_student;
    private eventAdapter_student adapter;

    private ListView listView_student_notice;
    private noticeAdapter_student adapter_notice;

    //public static String title_name;

    List<Info> studentList = new ArrayList<Info>();
    List<Info> exampleList = new ArrayList<Info>();

    List<Info> studentList_notice = new ArrayList<Info>();
    List<Info> exampleList_notice = new ArrayList<Info>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        Toolbar toolbar = findViewById(R.id.toolbar);

        TextView year = findViewById(R.id.year);

        TextView number = findViewById(R.id.number);
        Button query = findViewById(R.id.event_query_all);
        Button clear_query = findViewById(R.id.event_clear_query);

        intent2 = getIntent();

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Have a Nice Day!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_personaldata, R.id.nav_noticeboard, R.id.nav_event,
                R.id.nav_setting, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);






    }

    protected void onResume(){
        super.onResume();
        DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);

        TextView email = findViewById(R.id.email);
        TextView firstname = findViewById(R.id.firstname);
        TextView familyname = findViewById(R.id.familyname);
        TextView Class = findViewById(R.id.year);
        TextView number = findViewById(R.id.number);
        TextView role = findViewById(R.id.ROLE);


        //TextView title_email = findViewById(R.id.title_email);
        //TextView title_name = findViewById(R.id.title_name);

        ArrayList<User> data = mDBOpenHelper.getAllData();
        text_email_st = "";
        text_firstname_st = "";
        text_familyname_st = "";
        text_Class_st = "";
        text_number_st = "";
        text_role_st = "";

        User user = data.get(loginActivity.I);

        text_email_st = user.getName();
        text_firstname_st = user.getFirstname();
        text_familyname_st = user.getFamilytname();
        text_Class_st = user.getclass();
        text_number_st = user.getNumber();
        text_role_st = user.getRole();

        email.setText(text_email_st);
        firstname.setText(text_firstname_st);
        familyname.setText(text_familyname_st);
        Class.setText(text_Class_st);
        number.setText(text_number_st);
        role.setText(text_role_st);

        //title_email.setText(text_email);
        //title_name.setText(text_firstname);

    }

    public void show_personal_data_student(View view) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity_Student.this);
            builder.setTitle("Personal Data");
            ID = intent2.getStringExtra("name");//获取传入的学号用于查询详细信息
            StringBuilder sb = new StringBuilder();
            final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);

            SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from user where name=?", new String[]{ID});
            while (cursor.moveToNext()) {
                String email = cursor.getString(cursor.getColumnIndex("name"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String firstname = cursor.getString(cursor.getColumnIndex("firstname"));
                String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
                String group = cursor.getString(cursor.getColumnIndex("Class"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String role = cursor.getString(cursor.getColumnIndex("role"));

                sb.append("Email：" + email + "\n");
                sb.append("Password：" + password + "\n");
                sb.append("First Name：" + firstname + "\n");
                sb.append("Family Name：" + familyname + "\n");
                sb.append("Class：" + group + "\n");
                sb.append("Parent's Number：" + number + "\n");
                sb.append("Identity：" + role + "\n");
            }
            cursor.close();
            builder.setMessage(sb.toString());
            builder.create().show();

    }



    public void setting_pwd_student(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        view = View.inflate(this, R.layout.setting_pwd_pop, null);
        final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);


        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();

        final EditText old_pwd = (EditText) view.findViewById(R.id.old_pwd);
        final EditText new_pwd = (EditText) view.findViewById(R.id.new_pwd);
        final EditText new_pwd2 = (EditText) view.findViewById(R.id.new_pwd2);
        Button button_sure = (Button) view.findViewById(R.id.btsure);
        Button button_cancel = (Button) view.findViewById(R.id.btcen);

        button_sure.setOnClickListener(new View.OnClickListener() {
        String pwd1 = "";
        String pwd2 = "";
        String pwd3 = "";
        String msg = "";
        String text_email="";
        String text_realname="";

            public void onClick(View v) {

                //SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                pwd3 = old_pwd.getText().toString();
                pwd1 = new_pwd.getText().toString();
                pwd2 = new_pwd2.getText().toString();

                if (check()) {

                    ID = intent2.getStringExtra("name");//获取传入的学号用于修改密码
                    SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                    db.execSQL("update user set password=? where name=?", new String[]{pwd2, ID});
                    Toast.makeText(MainActivity_Student.this, "Password changed successfully",
                            Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();

                    /*Intent intent1 = new Intent(MainActivity_Student.this, MainActivity_Student.class);
                    startActivity(intent1);
                    finish();*/

                    //ArrayList<User> data1 = mDBOpenHelper.getAllData();
                    //User user = data1.get(loginActivity.I);
                    //user.setPassword(pwd2);
                    //mDBOpenHelper.updata(pwd2);
                    //text_email = user.getName();
                    //text_realname = user.getrealname();

                    /*ContentValues values2 = new ContentValues();
                    values2.put("password", pwd2);
                    values2.put("name", text_email);
                    values2.put("realname", text_realname);*/

                    /*db.delete("user","name = ?",new String[]{text_email});
                    mDBOpenHelper.add(text_email, pwd2,text_realname);

                    ArrayList<User> data2 = mDBOpenHelper.getAllData();
                    for (int i = 0; i < data2.size(); i++) {
                        User user1 = data2.get(i);
                        if (text_email.equals(user1.getName())) {
                            loginActivity.I = i;
                            Log.i(TAG,"change password loginActivity.I1 = " +i);
                            Log.i(TAG,"email2 = " +user1.getName());
                            break;
                        }
                    }*/

                    //mDBOpenHelper.delete(text_email,pwd3,text_realname);

                    //db.update("user", values2, "password = ?", new String[]{pwd3});
                    //db.replace("user", null,values2);


                } else if (!msg.equals("")) {
                    Toast.makeText(MainActivity_Student.this, msg, Toast.LENGTH_SHORT)
                            .show();
                    msg = "";

                } else if(!(pwd1.equals(pwd2))){
                    Toast.makeText(MainActivity_Student.this, "Your passwords do not match.  \n Please check and try again.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity_Student.this, "Incorrect old password.\n Please check and try again.",
                            Toast.LENGTH_SHORT).show();
                }

            }
            private boolean check () {

            if ("".equals(pwd2) || "".equals(pwd1) || "".equals(pwd3)) {
                msg = "Incomplete message";
                return false;

            } else {
                ArrayList<User> data = mDBOpenHelper.getAllData();
                User user = data.get(loginActivity.I);
                return (pwd1.equals(pwd2)) && (pwd3.equals(user.getPassword())) ;
            }
        }

    });
        button_cancel.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           // TODO Auto-generated method stub
                                           alertDialog.dismiss();
                                       }
                                   });

        }


    

    public void onClick(View view) {

        //TextView textView = findViewById(R.id.query_text_notice);

        DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);


        switch (view.getId()) {
            case R.id.yes_student:
                //Intent intent = new Intent(this, loginActivity.class);
                Intent intent = new Intent(MainActivity_Student.this, loginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.stay_student:
                //Intent intent = new Intent(this, loginActivity.class);
                /*Intent intent1 = new Intent(MainActivity_Student.this, MainActivity_Student.class);
                startActivity(intent1);
                finish();*/
                break;
            case R.id.event_query_all:

                studentList = mDBOpenHelper.getAllData_info();
                exampleList = mDBOpenHelper.getAllData_info();
                studentList.clear();

                for (int i = 0; i < exampleList.size(); i++) { //gai
                    Info user = exampleList.get(i);
                    if (user.gettype().equals("event") && (user.getEmail().equals(intent2.getStringExtra("name")) || user.getEmail().equals("ALL")))
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
                    }
                }

                adapter = new eventAdapter_student(MainActivity_Student.this, R.layout.event_item_student, studentList);
                listView_student = (ListView) findViewById(R.id.list_view_event_student);
                listView_student.setAdapter(adapter);
                Utility.setListViewHeightBasedOnChildren(listView_student);

                listView_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        final Info student = studentList.get(position);//捕获学生实例
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_Student.this);
                        //LayoutInflater factory = LayoutInflater.from(MainActivity_Student.this);
                        //final View textEntryView = factory.inflate(R.layout.event_info_admin, null);//加载AlertDialog自定义布局  //gai
                        //builder.setView(textEntryView);
                        builder.setTitle("Event Detail");

                        StringBuilder sb = new StringBuilder();

                        sb.append("Short Description：" + student.gettitle() + "\n");
                        sb.append("Content：" + student.getcontent() + "\n");
                        sb.append("Date：" + student.getdate() + "\n");

                        if(student.getEmail().equals("ALL"))
                            sb.append("Event Attribute：" + "Mass Event " + "\n");
                        else
                            sb.append("Event Attribute：" + "Private Event" + "\n");

                        builder.setMessage(sb.toString());
                        builder.create().show();

                    }
                });

                /*ArrayList<User> data = mDBOpenHelper.getAllData();
                String text_view_data = "";
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);
                    text_view_data = text_view_data + "\n" + user.getName() + " " + user.getPassword() + " " + user.getFirstname();
                }
                textView.setText(text_view_data);*/
                break;

                //创建游标对象
                /*Cursor cursor = mDBOpenHelper.query("user", new String[]{"name"}, null, null, null, null, null);
                //利用游标遍历所有数据对象
                //为了显示全部，把所有对象连接起来，放到TextView中
                String textview_data = "";
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    textview_data = textview_data + "\n" + name;
                }
                textView.setText(textview_data);*/

            case R.id.query_all_notice:
               // ArrayList<Info> data = mDBOpenHelper.getAllData();
                /*String text_view_data = "";
                for (int i = 0; i < studentList.size(); i++) {
                    Info user = studentList.get(i);
                    text_view_data = text_view_data + "\n" + user.gettitle() + " " + user.getcontent() + " " + user.getdate();
                }
                textView.setText(text_view_data);*/

                studentList_notice = mDBOpenHelper.getAllData_info();
                exampleList_notice = mDBOpenHelper.getAllData_info();
                studentList_notice.clear();

                for (int i = 0; i < exampleList_notice.size(); i++) { //gai
                    Info user = exampleList_notice.get(i);
                    if (user.gettype().equals("notice") && (user.getEmail().equals(intent2.getStringExtra("name")) || user.getEmail().equals("All Students")))
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
                        studentList_notice.add(new Info(email, firstname, familyname, role, title, content, date, type)); //gai
                    }
                }

                adapter_notice = new noticeAdapter_student(MainActivity_Student.this, R.layout.notice_item_student, studentList_notice);
                listView_student_notice = (ListView) findViewById(R.id.list_view_notice_student);
                listView_student_notice.setAdapter(adapter_notice);
                Utility.setListViewHeightBasedOnChildren(listView_student_notice);

                listView_student_notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        final Info student = studentList_notice.get(position);//捕获学生实例
                        AlertDialog.Builder builder_notice = new AlertDialog.Builder(MainActivity_Student.this);
                        //LayoutInflater factory = LayoutInflater.from(MainActivity_Student.this);
                        //final View textEntryView = factory.inflate(R.layout.event_info_admin, null);//加载AlertDialog自定义布局  //gai
                        //builder.setView(textEntryView);
                        builder_notice.setTitle("NoticeBoard Detail");

                        StringBuilder sb = new StringBuilder();

                        sb.append("Title：" + student.gettitle() + "\n");
                        sb.append("Content：" + student.getcontent() + "\n");
                        sb.append("Date：" + student.getdate() + "\n");

                        if(student.getEmail().equals("All Students"))
                            sb.append("Notice Attribute：" + "Mass Notice " + "\n");
                        else
                            sb.append("Notice Attribute：" + "Private Notice" + "\n");

                        builder_notice.setMessage(sb.toString());
                        builder_notice.create().show();

                    }
                });

                break;

            case R.id.clear_query_notice:

                adapter_notice.clear();
                adapter_notice.notifyDataSetChanged();

                break;

            //查询全部按钮下面的清除查询按钮
            case R.id.event_clear_query:
                //listView_student.setAdapter(null);
                /*studentList.clear();
                studentList.add(new Info("", "", "", "", "You have cleared events, Please", "", "query again", "")); //gai
                adapter = new eventAdapter_student(MainActivity_Student.this, R.layout.event_item_student, studentList);
                listView_student = (ListView) findViewById(R.id.list_view_event_student);
                listView_student.setAdapter(adapter);
                listView_student.setOnItemClickListener*/
                adapter.clear();
                adapter.notifyDataSetChanged();
                //event_clear.setText("You have cleared events, Please");
                //event_clear1.setText("query again");

                //textView.setText("");
                //textView.setHint("The query content is empty");
                break;

            default:
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_student, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
