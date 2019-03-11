package com.niu.jedis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ami on 2019/3/11.
 */
public class MyJedisTest {

    private UserMessage userMessage;
    private Jedis jedis;

    public MyJedisTest() {
        userMessage = new UserMessage();
        userMessage.setId(100L);
        userMessage.setName("test_user");
        userMessage.setAge(20);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("key1", "hello");
        hashMap.put("key2", "world");
        userMessage.setMap(hashMap);
        userMessage.setList(Arrays.asList("tom", "jack"));
    }


    @Before
    public void setUp(){
        jedis = new Jedis("127.0.0.1");
        Jedis jedis2 = RedisConnection.getJedis();
//        System.out.println(jedis2);

    }

    @Test
    public void testString(){
        RedisOps.set("user:1", "sisu");
        String user = RedisOps.get("user:1");
        Assert.assertEquals("sisu", user);
    }

    @Test
    public void testObject(){
        RedisOps.setObject("user:2",new User(2,"lumia"));
        User user = (User)RedisOps.getObject("user:2");
        Assert.assertEquals("lumia", user.getName());
    }


    @Test
    public void testObject2() {
        RedisOps.setJsonString("user:3", new User(3, "xiaoming"));
        User user = (User) RedisOps.getJsonObject("user:3", User.class);
        Assert.assertEquals("xiaoming", user.getName());
    }


    @Test
    public void testString2(){
        jedis.set("name","codehole");
//        System.out.println(jedis.get("name"));
        jedis.mset("name1","boy","name2","girl","name3","unknown");
        List<String> mget = jedis.mget("name1", "name2", "name3");
//        System.out.println(mget);
//        System.out.println(jedis.get("name4"));
//        System.out.println(jedis.exists("name"));
        System.out.println(jedis.del("name"));
//        System.out.println(jedis.exists("name"));
//        System.out.println(jedis.exists("name1"));
//        System.out.println(jedis.exists("name2"));
//        System.out.println(jedis.exists("name","name1"));
//        System.out.println(jedis.exists("name2","name1"));
        System.out.println(jedis.exists("name22","name12"));

    }

    @Test
    public void testList(){
        Jedis jedis = RedisConnection.getJedis();
        jedis.ltrim("books",1,0);
        jedis.rpush("books","python","java","golang");
        Long cnt = jedis.llen("books");
        System.out.println(cnt);
        System.out.println(jedis.lpop("books"));
        System.out.println(jedis.lpop("books"));
        System.out.println(jedis.lpop("books"));
        System.out.println(jedis.lpop("books"));

    }

        @Test
    public void test1() {
        String keys = "name";
        // 删数据
        //jedis.del(keys);
        // 存数据
        jedis.set(keys, "zy");
        // 取数据
        String value = jedis.get(keys);
        System.out.println(value);
    }

    @Test
    public void test2(){
        jedis.set("person".getBytes(), serialize(userMessage));
        byte[] byt=jedis.get("person".getBytes());
        Object obj=unserizlize(byt);
        if(obj instanceof UserMessage){
            System.out.println(obj);
        }
    }

    //序列化
    public static byte[] serialize(Object obj) {
        ObjectOutputStream obi = null;
        ByteArrayOutputStream bai = null;
        try {
            bai = new ByteArrayOutputStream();
            obi = new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt = bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反序列化
    public static Object unserizlize(byte[] byt) {
        ObjectInputStream oii = null;
        ByteArrayInputStream bis = null;
        bis = new ByteArrayInputStream(byt);
        try {
            oii = new ObjectInputStream(bis);
            Object obj = oii.readObject();
            return obj;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
}
