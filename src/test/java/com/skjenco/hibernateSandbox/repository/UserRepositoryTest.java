package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.bean.GroupResultBean;
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
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;


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


    @Test
    @Transactional
    public void test2()  {
        List<User> adminUsers = new ArrayList<>();
        List<User> publicUsers = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("user1");
        adminUsers.add(user1);

        User user2 = new User();
        user2.setUserName("user2");
        adminUsers.add(user2);

        User user3 = new User();
        user3.setUserName("user3");
        publicUsers.add(user3);


        UserRole adminRole = new UserRole();
        adminRole.setRoleName("admin");

        UserRole publicRole = new UserRole();
        publicRole.setRoleName("public");

        //Unidirectional relationship
        user1.setUserRole(adminRole);
        user2.setUserRole(adminRole);
        user3.setUserRole(publicRole);
        //set Bidirectional relationship
        adminRole.setUsers(adminUsers);
        publicRole.setUsers(publicUsers);

        userRoleRepository.save(adminRole);
        userRoleRepository.save(publicRole);

        //Show that the two users and the UserRole persisted
        UserRole result = userRoleRepository.findById(adminRole.getId()).get();
        assertEquals(2, result.getUsers().size());

        Query query = entityManager.createQuery("Select count(a.id) from User a");

        Object a = query.getSingleResult();

        List<Object[]> b = userRepository.getCounts();


        List<GroupResultBean> list = userRepository.getCountsToBean();

        System.out.println(b);

        System.out.println(a);

    }



}