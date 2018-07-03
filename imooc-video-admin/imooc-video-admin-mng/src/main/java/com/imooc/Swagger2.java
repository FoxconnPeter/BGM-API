package com.imooc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@EnableSwagger2
@Configurable
@ComponentScan(basePackages = {"com.imooc.controller"})

public class Swagger2 extends WebMvcConfigurationSupport {

	/**
	 * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	 */
	@Bean
	public Docket buildDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInf())
				.select()       .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))//controller路径
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInf(){
		return new ApiInfoBuilder()
				.title("SpringMVC中使用Swagger2整合")
				.description("springmvc swagger2")
				.contact(new Contact("peter", "localhost:8080","597058061@qq.com"))
				.build();

	}


}
