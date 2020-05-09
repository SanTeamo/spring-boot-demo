package com.santeamo.demo.repository;

import com.santeamo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author santeamo
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}
