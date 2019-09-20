package com.chicolira.movielist.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chicolira.movielist.util.Constants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)          
			      .select()
			      .apis(RequestHandlerSelectors.basePackage("com.chicolira.movielist.resource"))
			      .paths(PathSelectors.regex(Constants.API_PREFIX + ".*"))
			      .build()
			      .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Movie List API", 
	      "Lists the movies and genres from TMDB", 
	      "1.0", 
	      "Terms of service", 
	      new Contact("Francisco Lira", "https://www.linkedin.com/in/francisco-lira-287364134/?locale=en_US",
	    		  "francisco.lira639@gmail.com"), 
	      "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}
