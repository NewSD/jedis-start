package com.niu.jedis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ami on 2019/3/11.
 */
public class UserMessage implements Serializable{

    private Long id;
    private String name;
    private Integer age;
    private Map<String,Object> map ;
    private List<Object> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", map=" + map +
                ", list=" + list +
                '}';
    }
}
