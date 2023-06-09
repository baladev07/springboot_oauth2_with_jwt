package com.springsecurity.repository;

import com.springsecurity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {


    UserEntity findByEmail(String email);

    UserEntity findByName(String name);

    boolean existsByEmailIgnoreCase(String email);


}
