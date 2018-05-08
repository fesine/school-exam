package cn.edu.hebau.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 类描述
 * @author: Fesine
 * @createTime:2017/10/12 19:27
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/12 19:27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fesine.crm"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("员工考勤系统 RESTful APIs")
                .description("浙江农信员工考勤系统 V1.0 http://www.fesine.com/")
                .termsOfServiceUrl("http://www.fesine.com/")
                .version("1.0")
                .build();
    }
}
