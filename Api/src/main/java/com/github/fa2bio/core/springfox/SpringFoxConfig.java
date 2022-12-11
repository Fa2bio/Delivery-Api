package com.github.fa2bio.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.fasterxml.classmate.TypeResolver;
import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.api.model.OrderrAbstractModel;
import com.github.fa2bio.api.swaggeropenapi.controller.KitchenControllerSwagger;
import com.github.fa2bio.api.swaggeropenapi.controller.OrderControllerSwagger;
import com.github.fa2bio.api.swaggeropenapi.model.PageableModelSwagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SpringFoxConfig {
	
	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.github.fa2bio.api"))
					.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)
				
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelSwagger.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, KitchenModel.class),
						KitchenControllerSwagger.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, OrderrAbstractModel.class),
						OrderControllerSwagger.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Cities", "Manage the cities"),
						new Tag("Groups", "Manage user groups"),
						new Tag("Kitchens", "Manage the Kitchens"),
						new Tag("Payment Methods", "Manage the payment methods"),
						new Tag("Orders", "Manage the orders"),
						new Tag("Restaurants", "Manage the restaurants"),
						new Tag("States", "Manage the states"),
						new Tag("Products", "Manage the products"),
						new Tag("Users", "Manage the users"),
						new Tag("Permissions", "Manage the Permissions"),
						new Tag("Statistics", "Statistics of Delivery-API"));
						
	}
	
	private List<Response> globalGetResponseMessages(){
		return Arrays.asList(
				new ResponseBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.description("Internal server error")
					.build(),
				new ResponseBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.toString())
					.description("Resource has no representation that could be accepted by the consumer")
					.build()
				);
	}
	
	private List<Response> globalPostPutResponseMessages(){
		return Arrays.asList(
			new ResponseBuilder()
				.code(HttpStatus.BAD_REQUEST.toString())
				.description("Invalid request (client error)")
				.build(),
			new ResponseBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
				.description("Internal server error")
				.build(),
			new ResponseBuilder()
				.code(HttpStatus.NOT_ACCEPTABLE.toString())
				.description("Resource has no representation that could be accepted by the consumer")
				.build(),
			new ResponseBuilder()
				.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString())
				.description("Request refused because the body is in an unsupported format")
				.build()
			);
	}
	
	private List<Response> globalDeleteResponseMessages(){
		return Arrays.asList(
				new ResponseBuilder()
					.code(HttpStatus.BAD_REQUEST.toString())
					.description("Invalid request (client error)")
					.build(),
					new ResponseBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.description("Internal server error")
					.build()
				
				
				);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("DELIVERY API")
				.description("Open API for customers and restaurants")
				.version("1")
				.contact(new Contact("Fa2bio", "https://www.github.com/Fa2bio", "fabio.s0@hotmail.com"))
				.build();
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
