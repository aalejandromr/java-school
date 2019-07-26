package com.lambdaschool.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;

//@EnableWebMvc //Overwriting some of the automatic responses
//@EnableJpaAuditing
@SpringBootApplication
public class SchoolApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SchoolApplication.class, args);
    }

}
