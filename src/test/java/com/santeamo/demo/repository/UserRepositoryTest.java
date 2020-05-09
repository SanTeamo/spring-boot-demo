package com.santeamo.demo.repository;

import com.santeamo.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;


/**
 * @author santeamo
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserName() {
    }

    @Test
    public void findByUserNameOrEmail() {
    }

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa","aa1","aa@qq.com","aa",formattedDate));
        userRepository.save(new User("bb","bb1","bb@qq.com","bb",formattedDate));
        userRepository.save(new User("cc","cc1","cc@qq.com","cc",formattedDate));


    }
}