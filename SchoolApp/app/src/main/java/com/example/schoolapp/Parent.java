package com.example.schoolapp;
/**
 * Created by littlecurl 2018/6/24
 */

public class Parent {
    private String parent_id;
    private String name;            //用户名
    private String password;        //密码
    private String firstname;
    private String familyname;
    private String Class;
    private String number;
    private String role;

    public Parent(String parent_id, String name, String password, String firstname, String familyname, String Class, String number, String role) {
        this.parent_id = parent_id;
        this.name = name;
        this.password = password;
        this.firstname = firstname;
        this.familyname = familyname;
        this.Class = Class;
        this.number = number;
        this.role = role;
    }
    public String getParent_id() {
        return parent_id;
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
        return "children{" +
                "parent_id='" + parent_id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", Class='" + Class + '\'' +
                ", number='" + number + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

