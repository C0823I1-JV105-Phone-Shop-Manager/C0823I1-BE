package com.example.c0823l1_be.config;

import com.example.c0823l1_be.entity.Role;
import com.example.c0823l1_be.entity.User;
import com.example.c0823l1_be.repository.RoleRepository;
import com.example.c0823l1_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("ADMIN") == null) {
            roleRepository.save(new Role("ADMIN"));
        }

        if (roleRepository.findByName("USER") == null) {
            roleRepository.save(new Role("USER"));
        }

    }
    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
