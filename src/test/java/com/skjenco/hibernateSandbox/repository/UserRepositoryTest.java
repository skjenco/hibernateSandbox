package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.model.User;
import com.skjenco.hibernateSandbox.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    @Test
    @Transactional
    public void test()  {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("user1");
        users.add(user1);

        User user2 = new User();
        user2.setUserName("user2");
        users.add(user2);

        UserRole userRole = new UserRole();
        userRole.setRoleName("admin");
        //Unidirectional relationship
        user1.setUserRole(userRole);
        user2.setUserRole(userRole);
        //set Bidirectional relationship
        userRole.setUsers(users);

        userRole = userRoleRepository.save(userRole);

        //Show that the two users and the UserRole persisted
        UserRole result = userRoleRepository.findById(userRole.getId()).get();
        assertEquals(2, result.getUsers().size());





    }


}