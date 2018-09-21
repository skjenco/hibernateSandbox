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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProjectionExampleTest {

    @PersistenceContext
    EntityManager entityManager;


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    @Test
    @Transactional
    public void testShowProjection()  {
        List<User> adminUsers = new ArrayList<>();
        List<User> publicUsers = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("user1");
        publicUsers.add(user1);

        User user2 = new User();
        user2.setUserName("user2");
        publicUsers.add(user2);

        User user3 = new User();
        user3.setUserName("user3");
        adminUsers.add(user3);


        UserRole adminRole = new UserRole();
        adminRole.setRoleName("admin");

        UserRole publicRole = new UserRole();
        publicRole.setRoleName("public");

        //Unidirectional relationship
        user1.setUserRole(publicRole);
        user2.setUserRole(publicRole);
        user3.setUserRole(adminRole);
        //set Bidirectional relationship
        adminRole.setUsers(adminUsers);
        publicRole.setUsers(publicUsers);

        userRoleRepository.save(adminRole);
        userRoleRepository.save(publicRole);

        List<Object[]> counts = userRepository.getCounts();

        List<GroupResultBean> groupList = userRepository.getCountsToBean();

        System.out.println(groupList);


        //Same example using persistenceContext thing using persistence
        Query query = entityManager.createQuery("SELECT new com.skjenco.hibernateSandbox.bean.GroupResultBean(ur.roleName, count(u.id)) from User u left join u.userRole ur group by ur.roleName");

        List<GroupResultBean> groupList2 = (List<GroupResultBean>) query.getResultList();

    }


}
