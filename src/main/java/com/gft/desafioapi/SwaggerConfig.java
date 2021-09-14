package com.gft.desafioapi;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gft.desafioapi.entities.Usuario;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gft.desafioapi"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Usuario.class)
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                    .name("Authorization")
                                    .description("Header para Token JWT")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build()))
                					.apiInfo(metaInfo());
    }

	
	 private ApiInfo metaInfo() {
	 
	 ApiInfo apiInfo = new ApiInfo( "Desafio API REST",
	 "API REST de gerenciamento judiciário", "1.0", "Terms of Service", new
	 Contact("Zandra Monteiro", "https://br.linkedin.com/in/zandramonteiro",
	 "zandra.monteiro@gft.com"), "Apache License Version 2.0",
	 "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>() );
	 
	 return apiInfo; }
	

}
