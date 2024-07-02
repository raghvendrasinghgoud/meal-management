package com.meal.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


public class MultipartRequestFilter implements Filter {

	private MultipartResolver multipartResolver;
	
	public MultipartRequestFilter(MultipartResolver multipartResolver){
		this.multipartResolver=multipartResolver;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;

	        // Check if the request is multipart
	        if (multipartResolver.isMultipart(httpRequest)) {
	            MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(httpRequest);

	            System.out.println("Handling multipart request");
	            multipartRequest.getParameterMap().forEach((key, value) -> {
	                System.out.println(key + ": " + String.join(", ", value));
	            });

	            // Proceed with the multipart request
	            chain.doFilter(multipartRequest, response);
	        } else {
	            // Log regular parameters
	            Enumeration<String> parameterNames = httpRequest.getParameterNames();
	            while (parameterNames.hasMoreElements()) {
	                String paramName = parameterNames.nextElement();
	                System.out.println(paramName + ": " + httpRequest.getParameter(paramName));
	            }
	            chain.doFilter(request, response);

	}
	}

}
