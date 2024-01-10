package com.kenzie.appserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Application {
    @Autowired
    private Cloudinary cloudinary;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

