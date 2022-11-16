package com.example.usermanage;

import com.example.usermanage.dto.ResponseUserData;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManageApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean("responseUserList")
    public List<ResponseUserData> courses() {
        return new ArrayList<ResponseUserData>();
    }

    @Bean("responseUser")
    public ResponseUserData userCourseInfor() {
        return new ResponseUserData();
    }
}
