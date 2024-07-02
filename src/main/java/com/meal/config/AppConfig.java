package com.meal.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.meal.formatter.GenderFormatter;
import com.meal.formatter.IDTypeFormatter;

@EnableTransactionManagement
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@Import(value = HibernateConfig.class)
@ComponentScan(basePackages = {"com.meal"})
public class AppConfig implements WebMvcConfigurer{

	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver vr=new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/view/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setMaxUploadSizePerFile(5242880); // 5MB
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms=new ResourceBundleMessageSource();
		ms.setBasename("message");
		return ms;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean=new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/");
        
    }

	@Override
	public void addFormatters(FormatterRegistry registry) {

		System.out.println("we are inside formatter");
		
		//registry.addFormatter(new DateFormatter());
		registry.addFormatter(new GenderFormatter());
		registry.addFormatter(new IDTypeFormatter());
	}
	
	
}
