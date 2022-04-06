package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);//CommunityApplication.class相当于配置文件 （自动配置，扫描bean）
//		运行spring应用  （自动创建spring容器）
	}

}
