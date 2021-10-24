package com.snsystems.emailAlerts.config;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

//import static com.google.common.collect.Lists.newArrayList;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.snsystems.emailAlerts.models.EmailAlert;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 @Autowired
	  private TypeResolver typeResolver;

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.snsystems.emailAlerts"))
//				.paths(PathSelectors.regex("/api.*"))
//				.build()
//				.apiInfo(apiInformation())
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET, newArrayList(
//						new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error")).build(),
//						new ResponseMessageBuilder().code(403).message("Forbidden!!!!!")
//						.build()));
//	}
	
	@Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select() 
	        .apis(RequestHandlerSelectors.any()) 
	        .paths(PathSelectors.any()) 
	        .build() 
	        .pathMapping("/") 
	        .directModelSubstitute(LocalDate.class, String.class) 
	        .genericModelSubstitutes(ResponseEntity.class)
	        .alternateTypeRules(
	            newRule(typeResolver.resolve(DeferredResult.class,
	                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                typeResolver.resolve(WildcardType.class))) 
	        .useDefaultResponseMessages(false) 
//	        .globalResponses(HttpMethod.GET, 
//	            singletonList(new ResponseBuilder()
//	                .code("500")
//	                .description("500 message")
//	                .representation(MediaType.TEXT_XML)
//	                .apply(r ->
//	                    r.model(m ->
//	                        m.referenceModel(ref ->
//	                            ref.key(k ->
//	                                k.qualifiedModelName(q ->
//	                                    q.namespace("some:namespace")
//	                                        .name("ERROR")))))) 
//	                .build()))
//	        .securitySchemes(singletonList(apiKey())) 
//	        .securityContexts(singletonList(securityContext())) 
	        .apiInfo(apiInformation())
	        .enableUrlTemplating(true) 
//	        .globalRequestParameters(
//	            singletonList(new springfox.documentation.builders.RequestParameterBuilder()
//	                .name("someGlobalParameter")
//	                .description("Description of someGlobalParameter")
//	                .in(ParameterType.QUERY)
//	                .required(true)
//	                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//	                .build()))
//	        .tags(new Tag("Pet Service", "All apis relating to pets")) 
	        .additionalModels(typeResolver.resolve(EmailAlert.class)); 
	  }	

	private ApiInfo apiInformation() {
		return new ApiInfo("Email Alerts REST API", "Email Alerts Spring Boot API",
				"API TOS", "Terms of service", new Contact("Sathya P",
						"www.snsystems.com", "admin@snsystems.com"),
				"License of Email Alerts API",
				"https://www.snsystems.com/license", Collections.emptyList());
	}

}
