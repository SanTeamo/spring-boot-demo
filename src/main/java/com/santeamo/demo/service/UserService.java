package com.santeamo.demo.service;

import com.santeamo.demo.model.User;
import com.santeamo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title
 * ClassName com.santeamo.demo.service.UserService.java
 * Description
 *
 * @author santeamo
 * @date  2020-01-15 上午 10:57
 * @version V1.0
 */
public class UserService {


    @Autowired
    UserRepository userRepository;

    public void deleteById(Long id) {
        d(id);
    }

    @Transactional
    public void d(Long id) {
        userRepository.deleteById(id);
    }
}
