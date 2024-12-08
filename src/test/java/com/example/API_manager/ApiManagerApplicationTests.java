package com.example.API_manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class ApiManagerApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        boolean isPasswordEncoderBeanPresent = applicationContext.containsBean("passwordEncoder");
        System.out.println("BCryptPasswordEncoder Bean Present: " + isPasswordEncoderBeanPresent);
    }
}
