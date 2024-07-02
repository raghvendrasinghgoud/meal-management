package com.meal.config;


import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class FrontController implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("runnig dispatcher servlet");
		Logger log=Logger.getLogger("FrontController");
		
		log.info("web app initializer is running");
		
		AnnotationConfigWebApplicationContext wap=new AnnotationConfigWebApplicationContext();
		wap.register(AppConfig.class);
		
		//creating dispatcher servlet
		
		DispatcherServlet ds=new DispatcherServlet(wap);
		
		ServletRegistration.Dynamic sr=	servletContext.addServlet("ds", ds);
		
		sr.addMapping("/");
		
		sr.setLoadOnStartup(1);
		//sr.setInitParameter("enableLoggingRequestDetails", "true");
		

	}

	
}
