package com.niu.jedis;

import java.io.Serializable;

/**
 * Created by ami on 2019/3/11.
 */
public class User implements Serializable{
    private static final long serialVersionUID = -3210884885630038713L;
    private int id;
    private String name;
    public User(){
    }
    public User(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
