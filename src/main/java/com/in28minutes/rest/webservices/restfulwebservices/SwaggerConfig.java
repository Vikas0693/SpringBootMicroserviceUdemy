package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact("vIKAS sHARMA", "http://www.fundexpert.in", "vikas04@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO 
	= new ApiInfo("Awesome Api Title", "AWESOME API DOCUMENTATION", "2.1", "urn:tos",  "DEFAULT_CONTACT", "Apache 2.0", "http://www.apach.org/licenses/LICENSE-2.0");
	
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
		new HashSet<String>(Arrays.asList("application/json","application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)//sets info part in http://localhost:8080/v2/api-docs
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)//set produces part in http://localhost:8080/v2/api-docs
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);//set consumes part in http://localhost:8080/v2/api-docs
	}
}
