package com.example.schoolapp;
/**
 * Created by littlecurl 2018/6/24
 */
/*public class User {
    private String name;            //用户名
    private String password;        //密码
    private String realname;
    private String Class;
    private String number;
    public User(String name, String password, String realname, String Class, String number) {
        this.name = name;
        this.password = password;
        this.realname = realname;
        this.Class = Class;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getrealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getclass() {
        return Class;
    }
    public void setclass(String Class) {
        this.Class = Class;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", Class='" + Class + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}*/

public class User {
    private String name;            //用户名
    private String password;        //密码
    private String firstname;
    private String familyname;
    private String Class;
    private String number;
    private String role;

    public User(String name, String password, String firstname, String familyname, String Class, String number, String role) {
        this.name = name;
        this.password = password;
        this.firstname = firstname;
        this.familyname = familyname;
        this.Class = Class;
        this.number = number;
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getFamilytname() {
        return familyname;
    }

    public String getclass() {
        return Class;
    }
    public String getNumber() {
        return number;
    }
    public String getRole() {
        return role;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", Class='" + Class + '\'' +
                ", number='" + number + '\'' +
                ", role='" + role + '\'' +
                '}';
    }






/*public class User {
    private String name;            //用户名
    private String password;        //密码
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/
}

