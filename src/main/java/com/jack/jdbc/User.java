package com.jack.jdbc;

import com.jack.jdbc.jdbc.annonation.JDBCField;
import com.jack.jdbc.jdbc.annonation.JDBCTable;

@JDBCTable("t_user")
public class User {

    @JDBCField("nickname")
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
