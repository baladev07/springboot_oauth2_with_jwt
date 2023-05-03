package com.springsecurity.repository;

import com.springsecurity.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<RoleEntity,Integer> {

    RoleEntity findByName(String name);

}
