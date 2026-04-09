package com.pknu26.restapi.controller;

import com.pknu26.restapi.entity.Student;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot API";
    }

    @GetMapping("/student")
    public Student getStudent() {
        return new Student("유고", 40);
    }

    @GetMapping("/students")
    public List<Student> getSutdents() {
        return List.of(
            new Student("유고", 40),
            new Student("애슐리", 42),
            new Student("홍길동", 19)
        );
    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable int id) {
        return "User ID: " + id;
    }

    @PostMapping("/student")
    public Student createUser(@RequestBody Student student) {
        logger.info(student.getName()); 
        logger.info(String.valueOf(student.getAge()));
        return student;
    }
}