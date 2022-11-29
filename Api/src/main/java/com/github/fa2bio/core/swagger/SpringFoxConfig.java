package com.github.fa2bio.core.swagger;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.fasterxml.classmate.TypeResolver;
import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.KitchenModel;
import com.github.fa2bio.api.model.OderAbstractModel;
import com.github.fa2bio.api.swaggeropenapi.controller.KitchenControllerSwagger;
import com.github.fa2bio.api.swaggeropenapi.controller.OrderControllerSwagger;
import com.github.fa2bio.api.swaggeropenapi.model.PageableModelOpenApi;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SuppressWarnings("deprecation")
@EnableSwagger2
@Configuration
public class SpringFoxConfig {
	
	@SuppressWarnings("deprecation")
	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, KitchenModel.class),
						KitchenControllerSwagger.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, OderAbstractModel.class),
						OrderControllerSwagger.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Cities", "Manage the cities"),
						new Tag("Groups", "Manage user groups"),
						new Tag("Kitchens", "Manage the Kitchens"),
						new Tag("Payment methods", "Manage the payment methods"),
						new Tag("Orders", "Manage the orders"),
						new Tag("Restaurants", "Manage the restaurants"),
						new Tag("States", "Manage the states"),
						new Tag("Products", "Manage the products"),
						new Tag("Users", "Manage the users"),
						new Tag("Statistics", "Statistics of Delivery-API"));
	}
	
	@SuppressWarnings("deprecation")
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Internal server error")
					.responseModel(new ModelRef("Problem"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Resource has no representation that could be accepted by the consumer")
					.build()
			);
	}
	
	@SuppressWarnings("deprecation")
	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Invalid request (client error)")
					.responseModel(new ModelRef("Problem"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Internal server error")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Resource has no representation that could be accepted by the consumer")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Request refused because the body is in an unsupported format")
					.responseModel(new ModelRef("Problem"))
					.build()
			);
	}
	
	@SuppressWarnings("deprecation")
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Invalid request (client error)")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Internal server error")
					.responseModel(new ModelRef("Problemm"))
					.build()
			);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("DELIVERY API")
				.description("Open API for customers and restaurants")
				.version("1")
				.contact(new Contact("Fa2bio", "https://www.github.com/Fa2bio", "fa2bio.s0@gmail.com"))
				.build();
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}