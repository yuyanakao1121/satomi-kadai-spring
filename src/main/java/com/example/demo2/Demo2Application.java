package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Demo2Application {

	@RequestMapping("/")
	String home(){
		return "Hi! welcome to Springboot!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

}
