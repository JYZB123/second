package com.example.second.entity;

import java.util.Date;

public class Teacher {
    private Long uid;

    private Integer id;

    private String name;

    private Integer age;

    private String sex;


    private String origin;

    private String major;
    private String interest;

    private Date birth;

    private Integer salary;


    private String school;
    private Integer seniority;
    private String position;
    private String single;
    private  String child;
    private String weight;
    private String height;

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }



    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }




    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }




    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    private  String hobby;


    public Integer getSeniority(){
        return  seniority;
    }
    public void setSeniority(Integer seniority){
        this.seniority=seniority;
    }
    public String getPosition(){
        return  position;
    }
    public void setPosition(String position){
        this.position=position;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }
}
