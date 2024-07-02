package com.meal.config;

import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	private Logger log=Logger.getLogger("HibernateConfig");
	
	@Bean
	public DataSource dataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/mealmanagement");
	        dataSource.setUsername("root");
	        dataSource.setPassword("root");
	        return dataSource;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean=new  LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		Properties prop=new Properties();
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		prop.put("hibernate.show_sql", "false");
		prop.put("hibernate.format_sql", "true");
		localSessionFactoryBean.setHibernateProperties(prop);
		localSessionFactoryBean.setPackagesToScan("com.meal.model");
		
		return localSessionFactoryBean;
		
	}
		
	@Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
	
	@Bean
	public HibernateTemplate hibernateTemplate() {
		HibernateTemplate ht= new HibernateTemplate(sessionFactory().getObject());
		//ht.setCheckWriteOperations(false);
		return ht;
	}
	
}
