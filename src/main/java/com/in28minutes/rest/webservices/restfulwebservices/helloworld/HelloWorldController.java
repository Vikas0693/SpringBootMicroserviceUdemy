package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	//autowired will look for any bean in spring context that has implementation of MessageSource 
	//sine we created new bean spring will pick up that
	@Autowired
	private MessageSource messageSource;
	
	//@GetMapping(path="/hello-world")
	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s",name));
	}

	//Consumer pass string in Accept-Language and spring converts it into proper Locale object for us
	//if Accept-Language is not passed locale will be null and default value will be used which we have set in RestfulWebServicesApplication
	//by setting default locale in RestfulWebServicesApplication so that we dont have to set again and again in different services like this
	//but we are still setting request header in parameter.For each service we would have to add parameter.So spring has solution.See below for same name function
	/*@GetMapping(path="/hello-world-internationalization")
	public String helloWorldInternationalized(@RequestHeader(value="Accept-Language", required=false) Locale locale) {
		System.out.println("locale = "+locale+" language  ="+locale.getLanguage()+" country ="+locale.getCountry());
		//message resource will read default property file as we defined in RestfulWebServicesApplication.messageSource
		return messageSource.getMessage("good.morning.message", null, locale);
	}*/
	//instead get Locale from spring managed localeContext
	@GetMapping(path="/hello-world-internationalization")
	public String helloWorldInternationalized(@RequestHeader(value="Accept-Language", required=false) Locale locale) {
		//message resource will read default property file as we defined in RestfulWebServicesApplication.messageSource
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
