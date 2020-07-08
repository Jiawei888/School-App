package com.example.schoolapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

public class MainActivity_Parent extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String ID;
    private static Intent intent2;

    public static String text_firstname_pa;
    public static String text_email_pa;
    public static String text_familyname_pa;
    //public static String text_Class;
    public static String text_number_pa;
    public static String text_role_pa;

    private static String flag,flag1;
    private List<Parent> studentList = new ArrayList<Parent>();
    private List<Parent> exampleList = new ArrayList<Parent>();

    private ListView listView;
    private StudentAdapter_parent adapter;



    private ListView listView_parent_event;
    private eventAdapter_parent adapter_event;

    private ListView listView_parent_communication;
    private communicationAdapter_parent adapter_communication;

    //public static String title_name;

    List<Info> studentList_event = new ArrayList<Info>();
    List<Info> exampleList_event = new ArrayList<Info>();

    List<Info> studentList_communication = new ArrayList<Info>();
    List<Info> exampleList_communication = new ArrayList<Info>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);
        Toolbar toolbar = findViewById(R.id.toolbar);TextView year = findViewById(R.id.year);

        TextView number = findViewById(R.id.number);
        //Button query = findViewById(R.id.query_all);
        //Button clear_query = findViewById(R.id.clear_query);

        intent2 = getIntent();

        flag="";
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_personaldata_parent, R.id.nav_communication_parent, R.id.nav_event_parent,
                R.id.nav_setting_parent, R.id.nav_children_parent, R.id.nav_logout_parent)
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

        ArrayList<User> data = mDBOpenHelper.getAllData();
        text_email_pa = "";
        text_firstname_pa = "";
        text_familyname_pa = "";
        //text_Class = "";
        text_number_pa = "";
        text_role_pa = "";

        User user = data.get(loginActivity.I);

        text_email_pa = user.getName();
        text_firstname_pa = user.getFirstname();
        text_familyname_pa = user.getFamilytname();
        //text_Class = user.getclass();
        text_number_pa = user.getNumber();
        text_role_pa = user.getRole();

        email.setText(text_email_pa);
        firstname.setText(text_firstname_pa);
        familyname.setText(text_familyname_pa);
        //Class.setText(text_Class);
        number.setText(text_number_pa);
        role.setText(text_role_pa);

    }

    public void show_personal_data_parent(View view) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity_Parent.this);
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
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String role = cursor.getString(cursor.getColumnIndex("role"));

            sb.append("Email：" + email + "\n");
            sb.append("Password：" + password + "\n");
            sb.append("First Name：" + firstname + "\n");
            sb.append("Family Name：" + familyname + "\n");
            sb.append("Contact Number：" + number + "\n");
            sb.append("Identity：" + role + "\n");
        }
        cursor.close();
        builder.setMessage(sb.toString());
        builder.create().show();

    }



    public void  bind_student_info(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        view = View.inflate(this, R.layout.blind_children_pop, null);
        final boolean[] match = {false};

        final DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();

        final EditText parent_password = (EditText) view.findViewById(R.id.parent_password);
        final EditText children_id = (EditText) view.findViewById(R.id.children_id);

        Button button_sure = (Button) view.findViewById(R.id.confirm_blind);
        Button button_cancel = (Button) view.findViewById(R.id.cancel_blind);
        ID = intent2.getStringExtra("name");
        button_sure.setOnClickListener(new View.OnClickListener() {
            String pa_password = "";
            String st_id = "";
            String msg = "";

            public void onClick(View v) {

                //SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                pa_password = parent_password.getText().toString();
                st_id = children_id.getText().toString();

                ArrayList<User> data = mDBOpenHelper.getAllData();//user数据库
                ArrayList<Parent> data_children = mDBOpenHelper.getAllData_children();//children数据库

                SQLiteDatabase db2 = mDBOpenHelper.getWritableDatabase();

                User user1;
                User user = data.get(loginActivity.I);
                if ("".equals(pa_password) || "".equals(st_id)) {
                    msg = "Incomplete message";

                } else {
                    //User user = data.get(loginActivity.I);

                    outer: for (int i = 0; i < data.size(); i++) { //寻找user数据里面有没有对应的学生id
                        user1 = data.get(i);

                        if (user1.getName().equals(st_id)) {
                            if(user1.getRole().equals("Student"))
                            {

                               /*flag = st_id; //为了给children表输入第一个数据，防止出bug
                                SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                                Cursor cursor = db.rawQuery("select * from user where name=?", new String[]{flag});

                                while (cursor.moveToNext()) {
                                    String name = cursor.getString(cursor.getColumnIndex("name"));
                                    String password = cursor.getString(cursor.getColumnIndex("password"));
                                    String firstname = cursor.getString(cursor.getColumnIndex("firstname"));

                                    String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
                                    String Class = cursor.getString(cursor.getColumnIndex("Class"));
                                    String number = cursor.getString(cursor.getColumnIndex("number"));
                                    String role = cursor.getString(cursor.getColumnIndex("role"));
                                    String parent_id=ID;

                                    mDBOpenHelper.add_children(parent_id, name, password, firstname, familyname, Class, number, role); //把孩子的个人信息添加到children表中

                                }
                                cursor.close();
                                msg = "Yes";
                                Toast.makeText(MainActivity_Parent.this, "Binding successfully",
                                        Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                                break outer;*/

                                int amount=0;
                                Cursor c = db2.rawQuery("select * from children", null);
                                amount=c.getCount();
                                if(amount == 0)
                                    mDBOpenHelper.add_children("", "", "", "", "", "", "", ""); //把孩子的个人信息添加到children表中





                                inner: for (int j = 0; j < data_children.size(); j++)
                                {
                                    Parent user_children = data_children.get(j);
                                    if (user_children.getName().equals(st_id)) //判断需要绑定的学生信息是否在此之前被存入过children表里面，若存了，则代表已有其他家长添加该孩子，则不可添加
                                    {
                                        msg = "The student information may have been \n bound by you or someone else";
                                        match[0] =false;
                                        break outer;
                                    }
                                    else
                                     {
                                            msg="";
                                            match[0] =true;
                                            if(j==data_children.size())
                                                break outer;
                                     }
                                }


                            }
                            else
                            {
                                msg = "Bind Student Only, Not Parent";
                                break outer;
                            }

                        } else {
                            msg = "Student ID unavailable.\n Please check and try again.";
                            //msg = "fuck";
                            //match[0] = false;
                        }
                    }
                }

                if(match[0]) {
                    if (pa_password.equals(user.getPassword())) {
                        flag = st_id;
                        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                        Cursor cursor = db.rawQuery("select * from user where name=?", new String[]{flag});

                        while (cursor.moveToNext()) {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String password = cursor.getString(cursor.getColumnIndex("password"));
                            String firstname = cursor.getString(cursor.getColumnIndex("firstname"));

                            String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
                            String Class = cursor.getString(cursor.getColumnIndex("Class"));
                            String number = cursor.getString(cursor.getColumnIndex("number"));
                            String role = cursor.getString(cursor.getColumnIndex("role"));
                            String parent_id = ID;

                            mDBOpenHelper.add_children(parent_id, name, password, firstname, familyname, Class, number, role); //把孩子的个人信息添加到children表中

                        }
                        cursor.close();
                        match[0]=false;
                        msg = "Yes";
                        Toast.makeText(MainActivity_Parent.this, "Binding successfully",
                                Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();



                    } else {
                        msg = "Incorrect password.\n Please check and try again";

                    }
                }

                if(!(msg.equals("")))
                {
                    Toast.makeText(MainActivity_Parent.this, msg, Toast.LENGTH_SHORT).show();
                    msg = "";
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

    public void setting_pwd_parent(View view) {


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
                    Toast.makeText(MainActivity_Parent.this, "Password changed successfully",
                            Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();

                } else if (!msg.equals("")) {
                    Toast.makeText(MainActivity_Parent.this, msg, Toast.LENGTH_SHORT)
                            .show();
                    msg = "";

                } else if(!(pwd1.equals(pwd2))){
                    Toast.makeText(MainActivity_Parent.this, "Your passwords do not match.  \n Please check and try again.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity_Parent.this, "Incorrect old password.\n Please check and try again.",
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
                    return (pwd1.equals(pwd2)) && (pwd3.equals(user.getPassword()));
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

        //TextView textView_event = findViewById(R.id.query_event_text_parent);
        //TextView textView_children = findViewById(R.id.table1);
        DBOpenHelper mDBOpenHelper = new DBOpenHelper(this);
        TextView example = findViewById(R.id.email_table);
        listView = (ListView) findViewById(R.id.list_view);
        ID = intent2.getStringExtra("name");

        switch (view.getId()) {
            case R.id.yes_parent:
                //Intent intent = new Intent(this, loginActivity.class);
                Intent intent = new Intent(MainActivity_Parent.this, loginActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.stay_parent:
                //Intent intent = new Intent(this, loginActivity.class);
                /*Intent intent1 = new Intent(MainActivity_Student.this, MainActivity_Student.class);
                startActivity(intent1);
                finish();*/
                break;

            case R.id.event_query_parent:
                /*ArrayList<User> data = mDBOpenHelper.getAllData();
                String text_view_data = "";
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);
                    text_view_data = text_view_data + "\n" + user.getName() + " " + user.getPassword() + " " + user.getFirstname();
                }
                textView_event.setText(text_view_data);*/
                /*ArrayList<Parent> data = mDBOpenHelper.getAllData_children();
                String text_view_data = "";
                for (int i = 0; i < data.size(); i++) {
                    Parent user = data.get(i);
                    text_view_data = text_view_data + "\n" + user.getName() + " " + user.getPassword() + " " + user.getFirstname();
                }
                textView_event.setText(text_view_data);*/

                studentList_event = mDBOpenHelper.getAllData_info();
                exampleList_event = mDBOpenHelper.getAllData_info();
                studentList_event.clear();

                for (int i = 0; i < exampleList_event.size(); i++) { //gai
                    Info user = exampleList_event.get(i);
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
                        studentList_event.add(new Info(email, firstname, familyname, role, title, content, date, type)); //gai
                    }
                }

                adapter_event = new eventAdapter_parent(MainActivity_Parent.this, R.layout.event_item_parent, studentList_event);
                listView_parent_event = (ListView) findViewById(R.id.list_view_event_parent);
                listView_parent_event.setAdapter(adapter_event);
                //Utility.setListViewHeightBasedOnChildren(listView_parent_event);

                listView_parent_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        final Info student = studentList_event.get(position);//捕获学生实例
                        AlertDialog.Builder builder_event = new AlertDialog.Builder(MainActivity_Parent.this);
                        //LayoutInflater factory = LayoutInflater.from(MainActivity_Student.this);
                        //final View textEntryView = factory.inflate(R.layout.event_info_admin, null);//加载AlertDialog自定义布局  //gai
                        //builder.setView(textEntryView);
                        builder_event.setTitle("Event Detail");

                        StringBuilder sb = new StringBuilder();

                        sb.append("Short Description：" + student.gettitle() + "\n");
                        sb.append("Content：" + student.getcontent() + "\n");
                        sb.append("Date：" + student.getdate() + "\n");

                        if(student.getEmail().equals("ALL"))
                            sb.append("Event Attribute：" + "Mass Event " + "\n");
                        else
                            sb.append("Event Attribute：" + "Private Event" + "\n");

                        builder_event.setMessage(sb.toString());
                        builder_event.create().show();

                    }
                });

                break;

            case R.id.event_clear_parent:

                adapter_event.clear();
                adapter_event.notifyDataSetChanged();
                /*SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                textView_event.setText("");
                textView_event.setHint("The query content is empty");
                db.execSQL("DELETE FROM children");*/


                break;
                /*Cursor cursor = mDBOpenHelper.query("user", new String[]{"name"}, null, null, null, null, null);
                //利用游标遍历所有数据对象
                //为了显示全部，把所有对象连接起来，放到TextView中
                String textview_data = "";
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    textview_data = textview_data + "\n" + name;
                }
                textView.setText(textview_data);*/

            //查询全部按钮下面的清除查询按钮

            /*case R.id.bind_student_info:
                textView_children.setText("");
                textView_children.setHint("bind_student_info");
                break;*/

            case R.id.query_all_communication_parent:
                studentList_communication = mDBOpenHelper.getAllData_info();
                exampleList_communication = mDBOpenHelper.getAllData_info();
                studentList_communication.clear();

                for (int i = 0; i < exampleList_communication.size(); i++) { //gai
                    Info user = exampleList_communication.get(i);
                    if (user.gettype().equals("communication") && (user.getEmail().equals(intent2.getStringExtra("name")) || user.getEmail().equals("All Parents")))
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
                        studentList_communication.add(new Info(email, firstname, familyname, role, title, content, date, type)); //gai
                    }
                }

                adapter_communication = new communicationAdapter_parent(MainActivity_Parent.this, R.layout.communication_item_parent, studentList_communication);
                listView_parent_communication = (ListView) findViewById(R.id.list_view_communication_parent);
                listView_parent_communication.setAdapter(adapter_communication);
                //Utility.setListViewHeightBasedOnChildren(listView_parent_event);

                listView_parent_communication.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        final Info student = studentList_communication.get(position);//捕获学生实例
                        AlertDialog.Builder builder_communication = new AlertDialog.Builder(MainActivity_Parent.this);
                        //LayoutInflater factory = LayoutInflater.from(MainActivity_Student.this);
                        //final View textEntryView = factory.inflate(R.layout.event_info_admin, null);//加载AlertDialog自定义布局  //gai
                        //builder.setView(textEntryView);
                        builder_communication.setTitle("Communication Detail");

                        StringBuilder sb = new StringBuilder();

                        sb.append("Title：" + student.gettitle() + "\n");
                        sb.append("Content：" + student.getcontent() + "\n");
                        sb.append("Date：" + student.getdate() + "\n");

                        if(student.getEmail().equals("All Parents"))
                            sb.append("Communication Attribute：" + "Mass Communication " + "\n");
                        else
                            sb.append("Communication Attribute：" + "Private Communication" + "\n");

                        builder_communication.setMessage(sb.toString());
                        builder_communication.create().show();

                    }
                });

                break;


            case R.id.clear_query_communication_parent:

                adapter_communication.clear();
                adapter_communication.notifyDataSetChanged();
                /*SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
                textView_event.setText("");
                textView_event.setHint("The query content is empty");
                db.execSQL("DELETE FROM children");*/


                break;
            case R.id.query_children:


                String msg = "";
                studentList.clear();
                //studentList = mDBOpenHelper.getAllData_children();
                exampleList = mDBOpenHelper.getAllData_children();
                for (int i = 0; i < exampleList.size(); i++) {
                    Parent user = exampleList.get(i);
                    if (ID.equals(user.getParent_id()))
                    {

                        String name = user.getName();
                        String password = user.getPassword();
                        String firstname = user.getFirstname();

                        String familyname = user.getFamilytname();
                        String Class = user.getclass();
                        String number = user.getNumber();
                        String role = user.getRole();
                        String parent_id = user.getParent_id();
                        //studentList.clear();
                        studentList.add(new Parent(parent_id,name,password,firstname,familyname,Class,number,role));
                        //msg = "Match";
                        msg = user.getParent_id();

                    } else {
                        //studentList.clear();
                        msg = "Not Match";
                        //continue;
                    }
                    //msg = ID;

                }
                Toast.makeText(MainActivity_Parent.this, msg, Toast.LENGTH_SHORT).show();
                msg = "";

                adapter = new StudentAdapter_parent(MainActivity_Parent.this, R.layout.child_item_parent, studentList);
                listView.setAdapter(adapter);


                break;

            case R.id.clear_query_children:
                ArrayList<Parent> show = mDBOpenHelper.getAllData_children();
                show.clear();
                adapter = new StudentAdapter_parent(MainActivity_Parent.this, R.layout.child_item_parent, show);
                listView.setAdapter(adapter);

                break;

            default:
                break;

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_parent, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
