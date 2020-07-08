package com.example.schoolapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

/**
 * 管理员的管理界面
 * Created by he on 2016/9/30.
 */
public class MainActivity_Admin extends Activity {

    private Button select_st;//查询学生信息按钮
    private Button select_pa;
    private Button add_st; //添加学生信息按钮
    private Button event;
    private Button noticeboard;
    private Button communication;
    private Button logout_admin;//强制下线

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_admin);

        select_st = (Button) findViewById(R.id.admin_activity_select);
        select_pa = (Button) findViewById(R.id.admin_activity_select_parent);
        add_st = (Button) findViewById(R.id.admin_activity_add);
        event = (Button) findViewById(R.id.admin_activity_event);
        noticeboard = (Button) findViewById(R.id.admin_activity_notification);
        communication = (Button) findViewById(R.id.admin_activity_communication);

        logout_admin = (Button) findViewById(R.id.logout_admin);
    }



    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.admin_activity_select:
                Intent intent1 = new Intent(MainActivity_Admin.this, select_st_admin.class);
                startActivity(intent1);

                break;

            case R.id.admin_activity_select_parent:
                Intent intent2 = new Intent(MainActivity_Admin.this, select_pa_admin.class);
                startActivity(intent2);
                break;

            case R.id.admin_activity_add:
                Intent intent3 = new Intent(MainActivity_Admin.this, add_student_admin.class);
                intent3.putExtra("haveData","false");
                //intent3.putExtra("flag","false");

                startActivity(intent3);
                break;

            case R.id.admin_activity_event:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                view = View.inflate(this, R.layout.event_choice_admin, null);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setView(view);
                alertDialog.show();
                //builder.setTitle("Menu");

                Button create_event = (Button) view.findViewById(R.id.button_event_send);//create event
                create_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent4 = new Intent(MainActivity_Admin.this, event_admin.class);
                        intent4.putExtra("haveData","false");
                        //intent4.putExtra("flag","false");
                        Toast.makeText(MainActivity_Admin.this, "You send the event to all users \n  not to individual user",
                                Toast.LENGTH_LONG).show();
                        startActivity(intent4);
                        alertDialog.dismiss();
                        //finish();
                    }
                });

                Button update_delete_event = (Button) view.findViewById(R.id.button_event_update);//update event
                update_delete_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent8 = new Intent(MainActivity_Admin.this, show_event_admin.class);

                        //Toast.makeText(MainActivity_Admin.this, "You send the event to all users \n  not to individual user",
                                //Toast.LENGTH_LONG).show();
                        startActivity(intent8);
                        //finish();
                        alertDialog.dismiss();
                    }
                });


                break;

            case R.id.admin_activity_notification:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                view = View.inflate(this, R.layout.notice_choice_admin, null);
                final AlertDialog alertDialog1 = builder1.create();
                alertDialog1.setView(view);
                alertDialog1.show();
                //builder.setTitle("Menu");

                Button create_notice = (Button) view.findViewById(R.id.button_notice_send);//create event
                create_notice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent5 = new Intent(MainActivity_Admin.this, notice_admin.class);
                        intent5.putExtra("haveData","false");
                        //intent4.putExtra("flag","false");
                        Toast.makeText(MainActivity_Admin.this, "You send the notice to all students \n  not to individual student",
                                Toast.LENGTH_LONG).show();
                        startActivity(intent5);
                        //finish();
                        alertDialog1.dismiss();
                    }
                });

                Button update_delete_notice = (Button) view.findViewById(R.id.button_notice_update);//update event
                update_delete_notice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent10 = new Intent(MainActivity_Admin.this, show_notice_admin.class);

                        //Toast.makeText(MainActivity_Admin.this, "You send the event to all users \n  not to individual user",
                        //Toast.LENGTH_LONG).show();
                        startActivity(intent10);
                        //finish();
                        alertDialog1.dismiss();
                    }
                });

                break;


            case R.id.admin_activity_communication:

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                view = View.inflate(this, R.layout.communication_choice_admin, null);
                final AlertDialog alertDialog2 = builder2.create();
                alertDialog2.setView(view);
                alertDialog2.show();
                //builder.setTitle("Menu");

                Button create_communication = (Button) view.findViewById(R.id.button_communication_send);//create event
                create_communication.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent5 = new Intent(MainActivity_Admin.this, communication_admin.class);
                        intent5.putExtra("haveData","false");
                        //intent4.putExtra("flag","false");
                        Toast.makeText(MainActivity_Admin.this, "You send the communication to all parents \n not to individual parent",
                                Toast.LENGTH_LONG).show();
                        startActivity(intent5);
                        //finish();
                        alertDialog2.dismiss();
                    }
                });

                Button update_delete_communication = (Button) view.findViewById(R.id.button_communication_update);//update event
                update_delete_communication.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent10 = new Intent(MainActivity_Admin.this, show_communication_admin.class);

                        //Toast.makeText(MainActivity_Admin.this, "You send the event to all users \n  not to individual user",
                        //Toast.LENGTH_LONG).show();
                        startActivity(intent10);
                        //finish();
                        alertDialog2.dismiss();
                    }
                });

                break;


            /*case R.id.admin_activity_communication:
                Intent intent6 = new Intent(MainActivity_Admin.this, .class);
                startActivity(intent6);
                break;*/

            case R.id.logout_admin:
                Intent intent7 = new Intent(MainActivity_Admin.this,loginActivity.class);
                startActivity(intent7);
                finish();
                break;

        }

    }


}
