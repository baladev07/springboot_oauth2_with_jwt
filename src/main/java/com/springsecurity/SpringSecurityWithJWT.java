package com.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityWithJWT {

	public static void main(String[] args) {
		try{
			SpringApplication.run(SpringSecurityWithJWT.class, args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
