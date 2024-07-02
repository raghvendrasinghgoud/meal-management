package com.meal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.multipart.MultipartResolver;

import com.meal.filters.MultipartRequestFilter;
import com.meal.service.UserService;

@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MultipartResolver multipartResolver;
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userServiceImpl;
	 @Autowired
	    private AccessDeniedHandler accessDeniedHandler;
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
//            .withUser("raghav").password(passwordEncoder().encode("raghav")).authorities("admin")
//            .and()
//            .withUser("ravi").password(passwordEncoder().encode("raghav")).authorities("manager")
//            .and()
//            .withUser("suraj").password(passwordEncoder().encode("raghav")).authorities("user");
    }

	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "admin > manager\nmanager > user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	  http.addFilterBefore(new MultipartRequestFilter(multipartResolver), WebAsyncManagerIntegrationFilter.class)
          .authorizeRequests()
          .antMatchers("/user/users","/user/changePass", "/user/image/*", "/payment/get/*").hasAuthority("manager")
          .antMatchers("/user/changePass","/user/image/*","/consumedMeal/consumedMeals/*","/payment/get/*").hasAuthority("user")
          .antMatchers("/consumedMeal/**").hasAnyAuthority("admin", "manager")
          .antMatchers("/user/**", "/meal/**", "/payment/**", "/role/**").hasAuthority("admin")
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .and()
          .csrf()
          .and()
          .exceptionHandling().accessDeniedHandler(accessDeniedHandler); 

    }
    
//    @Bean
//    public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//        return expressionHandler;
//    }
}
	
	

