package com.example.c0823l1_be.config;

import com.example.c0823l1_be.entity.Role;
import com.example.c0823l1_be.entity.Staff;
import com.example.c0823l1_be.entity.User;
import com.example.c0823l1_be.repository.RoleRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import com.example.c0823l1_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("ADMIN") == null) {
            roleRepository.save(new Role("ADMIN"));
        }

        if (roleRepository.findByName("STAFF") == null) {
            roleRepository.save(new Role("STAFF"));
        }
        if(userRepository.findByUsername("admin").isEmpty()){
            User admin = new User();
            Staff staff = new Staff();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(roleRepository.findByName("ADMIN"));
            userRepository.save(admin);
        }
        if(staffRepository.findByUser_Username("admin") == null){
            User admin = userRepository.findByUsername("admin").get();
            Staff staff = new Staff();
            staff.setFullName("admin");
            staff.setAddress("admin");
            staff.setDob("11/11/1111");
            staff.setPhoneNumber("0123456789");
            staff.setUser(admin);
            staffRepository.save(staff);
        }
        if(userRepository.findByUsername("staff").isEmpty()){
            User admin = new User();
            Staff staff = new Staff();
            admin.setUsername("staff");
            admin.setPassword(passwordEncoder.encode("staff123"));
            admin.setRole(roleRepository.findByName("STAFF"));
            userRepository.save(admin);
        }
        if(staffRepository.findByUser_Username("staff") == null){
            User admin = userRepository.findByUsername("staff").get();
            Staff staff = new Staff();
            staff.setFullName("staff");
            staff.setAddress("staff");
            staff.setDob("11/11/1111");
            staff.setPhoneNumber("0123456789");
            staff.setUser(admin);
            staffRepository.save(staff);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}