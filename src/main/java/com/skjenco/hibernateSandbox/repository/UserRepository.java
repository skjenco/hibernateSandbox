package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.bean.GroupResultBean;
import com.skjenco.hibernateSandbox.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT ur.roleName, count(u.id) from User u left join u.userRole ur group by ur.roleName")
    List<Object[]> getCounts();


    @Query("SELECT new com.skjenco.hibernateSandbox.bean.GroupResultBean(ur.roleName, count(u.id)) from User u left join u.userRole ur group by ur.roleName")
    List<GroupResultBean> getCountsToBean();


    @Query("SELECT a from User a")
    Stream<User> findalltheusers();

}
