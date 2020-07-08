package com.example.schoolapp;
/**
 * Created by littlecurl 2018/6/24
 */

public class Info {
    private String email;            //用户名
    private String firstname;
    private String familyname;
    private String role;
    private String title;
    private String content;
    private String date;
    private String type;

    public Info(String email, String firstname, String familyname, String role, String title, String content, String date, String type) {
        this.email = email;
        this.firstname = firstname;
        this.familyname = familyname;
        this.role = role;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }
    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getFamilytname() {
        return familyname;
    }
    public String getRole() {
        return role;
    }
    public String gettitle() {
        return title;
    }
    public String getcontent() {
        return content;
    }
    public String getdate() {
        return date;
    }
    public String gettype() { return type;
    }

    @Override
    public String toString() {
        return "children{" +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", role='" + role + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

