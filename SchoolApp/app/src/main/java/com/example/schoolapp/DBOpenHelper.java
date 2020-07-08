package com.example.schoolapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by littlecurl 2018/6/24
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    /**
     * 声明一个AndroidSDK自带的数据库变量db
     */
    private SQLiteDatabase db;

    /**
     * 写一个这个类的构造函数，参数为上下文context，所谓上下文就是这个类所在包的路径
     * 指明上下文，数据库名，工厂默认空值，版本号默认从1开始
     * super(context,"db_test",null,1);
     * 把数据库设置成可写入状态，除非内存已满，那时候会自动设置为只读模式
     * 不过，以现如今的内存容量，估计一辈子也见不到几次内存占满的状态
     * db = getReadableDatabase();
     */
    public DBOpenHelper(Context context){
        super(context,"db_test",null,11);
        db = getReadableDatabase();
    }

    /**
     * 重写两个必须要重写的方法，因为class DBOpenHelper extends SQLiteOpenHelper
     * 而这两个方法是 abstract 类 SQLiteOpenHelper 中声明的 abstract 方法
     * 所以必须在子类 DBOpenHelper 中重写 abstract 方法
     * 想想也是，为啥规定这么死必须重写？
     * 因为，一个数据库表，首先是要被创建的，然后免不了是要进行增删改操作的
     * 所以就有onCreate()、onUpgrade()两个方法
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        /*db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT,"+
                "realname TEXT,"+
                "Class TEXT,"+
                "number TEXT)");*/
        /*db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");*/
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT ," +
                "password TEXT,"+
                "firstname TEXT,"+
                "familyname TEXT,"+
                "Class TEXT,"+
                "number TEXT,"+
                "role TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS children(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "parent_id TEXT ," +
                "name TEXT ," +
                "password TEXT,"+
                "firstname TEXT,"+
                "familyname TEXT,"+
                "Class TEXT,"+
                "number TEXT,"+
                "role TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS info(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT ," +
                "firstname TEXT,"+
                "familyname TEXT,"+
                "role TEXT,"+
                "title TEXT,"+
                "content TEXT,"+
                "date TEXT,"+
                "type TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS children");
        db.execSQL("DROP TABLE IF EXISTS info");
        onCreate(db);
    }
    /**
     * 接下来写自定义的增删改查方法
     * 这些方法，写在这里归写在这里，以后不一定都用
     * add()
     * delete()
     * update()
     * getAllData()
     */
    /*public void add(String name,String password, String realname, String Class, String number){
        db.execSQL("INSERT INTO user (name,password,realname, Class, number) VALUES(?,?,?,?,?)",new Object[]{name,password,realname,Class, number});
    }*/
    /*public void add(String name,String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});
    }*/
    public void add(String name,String password, String firstname, String familyname, String Class, String number, String role){
        db.execSQL("INSERT INTO user (name,password,firstname,familyname, Class, number,role) VALUES(?,?,?,?,?,?,?)",new Object[]{name,password,firstname,
                familyname, Class, number,role});
    }

    public void add_children(String parent_id, String name,String password, String firstname, String familyname, String Class, String number, String role){
        db.execSQL("INSERT INTO children (parent_id,name,password,firstname,familyname, Class, number,role) VALUES(?,?,?,?,?,?,?,?)",new Object[]{parent_id, name,password,firstname,
                familyname, Class, number,role});
    }

    public void add_info(String email, String firstname, String familyname, String role, String title, String content, String date, String type){
        db.execSQL("INSERT INTO info (email,firstname,familyname, role, title,content,date,type) VALUES(?,?,?,?,?,?,?,?)",new Object[]{email,firstname,
                familyname, role, title, content, date,type});
    }

    /*public void delete(String name,String password,String realname, String Class, String number){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }*/
    public void delete(String name,String password,String realname){
        db.execSQL("DELETE FROM user WHERE name = AND password AND realname ="+name+password+realname);
    }
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    /**
     * 前三个没啥说的，都是一套的看懂一个其他的都能懂了
     * 下面重点说一下查询表user全部内容的方法
     * 我们查询出来的内容，需要有个容器存放，以供使用，
     * 所以定义了一个ArrayList类的list
     * 有了容器，接下来就该从表中查询数据了，
     * 这里使用游标Cursor，这就是数据库的功底了，
     * 在Android中我就不细说了，因为我数据库功底也不是很厚，
     * 但我知道，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，
     *                                                     最后是查询出来内容的排序方式："name DESC"
     * 游标定义好了，接下来写一个while循环，让游标从表头游到表尾
     * 在游的过程中把游出来的数据存放到list容器中
     * @return
     */
    /*public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String realname = cursor.getString(cursor.getColumnIndex("realname"));
            String Class = cursor.getString(cursor.getColumnIndex("Class"));
            String number = cursor.getString(cursor.getColumnIndex("number"));

            list.add(new User(name,password,realname,Class,number));
        }
        return list;
    }*/
    /*public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name,password));
        }
        return list;
    }*/
    public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String firstname = cursor.getString(cursor.getColumnIndex("firstname"));

            String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
            String Class = cursor.getString(cursor.getColumnIndex("Class"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String role = cursor.getString(cursor.getColumnIndex("role"));
            list.add(new User(name,password,firstname,familyname,Class,number,role));
        }
        return list;
    }


    public ArrayList<Parent> getAllData_children(){

        ArrayList<Parent> list1 = new ArrayList<Parent>();
        Cursor cursor = db.query("children",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String parent_id = cursor.getString(cursor.getColumnIndex("parent_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String firstname = cursor.getString(cursor.getColumnIndex("firstname"));

            String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
            String Class = cursor.getString(cursor.getColumnIndex("Class"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String role = cursor.getString(cursor.getColumnIndex("role"));
            list1.add(new Parent(parent_id, name,password,firstname,familyname,Class,number,role));
        }
        return list1;
    }


    public ArrayList<Info> getAllData_info(){

        ArrayList<Info> list2 = new ArrayList<Info>();
        Cursor cursor = db.query("info",null,null,null,null,null,"email DESC");
        while(cursor.moveToNext()){
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            String familyname = cursor.getString(cursor.getColumnIndex("familyname"));
            String role = cursor.getString(cursor.getColumnIndex("role"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String type = cursor.getString(cursor.getColumnIndex("type"));

            list2.add(new Info(email,firstname,familyname,role,title,content,date,type));
        }
        return list2;
    }


}
