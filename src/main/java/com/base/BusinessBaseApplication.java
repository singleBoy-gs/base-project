package com.base;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Single Minded
 * @create 2022-08-07 15:45:09
 * @Description
 * @since 1.0
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = {"com.base.**.mapper"})
public class BusinessBaseApplication implements CommandLineRunner {

	@Value("${server.port}")
	private int port;

	public static void main(String[] args) {
		try {
			SpringApplication.run(BusinessBaseApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("项目启动完成，访问地址是：http://localhost:"+port+"/");
		log.info("Api文档访问地址如下：http://localhost:" + port + "/doc.html");
	}
}
