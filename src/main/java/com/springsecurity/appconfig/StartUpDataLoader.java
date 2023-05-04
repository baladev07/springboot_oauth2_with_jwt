package com.springsecurity.appconfig;

import com.springsecurity.model.RoleEntity;
import com.springsecurity.model.UserEntity;
import com.springsecurity.repository.RolesRepository;
import com.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class StartUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static boolean isPopulated = false;

    @Autowired RolesRepository rolesRepository;

    @Autowired UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(isPopulated || userRepository.findAll().iterator().hasNext()) return;

        var admin  = populateDefaultRolesIfNotExists(RoleEntity.ROLE_ADMIN);
        var user = populateDefaultRolesIfNotExists(RoleEntity.ROLE_USER);


        createUserIfNotExist("user1","user1@gmail.com",user,"user1");
        createUserIfNotExist("user2","user2@gmail.com",admin,"user2");

        isPopulated = true;


    }

    public void createUserIfNotExist(String name, String email, RoleEntity roleEntity, String password)
    {
        UserEntity userEntity = userRepository.findByName(name);
        if(userEntity ==null)
        {
            userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setEmail(email);
            userEntity.setRoleEntity(roleEntity);
            userEntity.setPassword(password);
            userRepository.save(userEntity);
        }
    }


    public RoleEntity populateDefaultRolesIfNotExists(final String name)
    {
        RoleEntity roleEntity = rolesRepository.findByName(name);
        if(roleEntity ==null)
        {
            roleEntity = new RoleEntity(name);
            rolesRepository.save(roleEntity);
        }
        return roleEntity;
    }
}
