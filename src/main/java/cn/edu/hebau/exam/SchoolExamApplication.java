package cn.edu.hebau.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @description: 主启动类
 * @author: Fesine
 * @createTime:2017/10/12 19:27
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/12 19:27
 */

@SpringBootApplication
@EnableWebMvc
public class SchoolExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolExamApplication.class, args);
	}
}
