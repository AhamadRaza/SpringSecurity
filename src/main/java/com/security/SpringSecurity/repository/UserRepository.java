package com.security.SpringSecurity.repository;

import com.security.SpringSecurity.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo,Long>{
    @Query(value = "select u from  UserInfo as u where u.email=?1 and u.enabled=?2")
    UserInfo findEmailIgnoreCase(String username, boolean enabled);
}