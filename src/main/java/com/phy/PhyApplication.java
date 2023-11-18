package com.phy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PhyApplication {

	public static void main(String[] args) throws IOException {

		// 시작시 실행환경설정 선택
//		String mode = BeforeRun.beforeRun();
//		SpringApplication app = new SpringApplication(PhyApplication.class);
//		app.setAdditionalProfiles(mode);
//		app.run(args);
		System.out.println("인코딩 테스트 문구.");
		SpringApplication.run(PhyApplication.class, args);
	}

}
