package com.dorabmon.dao;

import com.dorabmon.dao.user.UserDao;
import com.dorabmon.dao.user.UserDaoImpl;
import com.dorabmon.model.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {


    UserDao userDao = new UserDaoImpl();

    User user = new User("test","123","123","test","2018-09-01",2,"user");


    @org.junit.jupiter.api.BeforeEach
    void setUp(){
         //userDao = new UserDaoImpl();
    }

    @Test
    public void setResultUser() {
    }

    @Test
    public void getUserByID() throws SQLException {

        User user = userDao.FindById(1);
        assertEquals("Yi Ren", user.getStudent_name());
    }

    @Test
    public void insert() throws Exception {
        List<User> before = userDao.FindAll();
        userDao.Insert(user);
        List<User> after = userDao.FindAll();
        assertEquals( after.size(), before.size()+1);

    }

    @Test
    public void update() throws Exception {
        User before = userDao.FindById(7);
        before.setStudent_name("yiren");
        userDao.Update(before);
        User now = userDao.FindById(7);
        assertEquals("yiren", now.getStudent_name());
    }

    @Test
    public void delete() throws Exception {

        userDao.Delete("123@dal.ca");
        assertNull(userDao.FindById(8));

    }

    @Test
    public void findById() throws Exception {

        User u = userDao.FindById(28);
        System.out.println(u.getEmail());

    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = userDao.FindAll();
        assertEquals(3,userList.size());
    }

    @Test
    public void findAll1() throws Exception {
    }

    @Test
    public void setResult() throws Exception {
    }

    @Test
    public void setResult1() throws Exception {
    }
}