package com.EBookShop.Config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages="com.EBookShop")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource({"classpath:security-persistence-mysql.properties","classpath:persistence-mysql.properties"})
public class Config implements WebMvcConfigurer{
	
	@Autowired
	private Environment env; 
	
	@Bean
	public DataSource myDataSource() {
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
		try {
		 myDataSource.setDriverClass("com.mysql.jdbc.Driver");
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
	
		myDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		myDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		myDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		myDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
	 
		return myDataSource;
	}
	
	private Properties getHibernateProperties() {
		 // set hibernate properties
		 Properties props = new Properties();
		 props.setProperty("hibernate.dialect",
		env.getProperty("hibernate.dialect"));
		 props.setProperty("hibernate.show_sql",
		env.getProperty("hibernate.show_sql"));
		 return props;
	} 
	
	@Bean
	@PersistenceContext(type=PersistenceContextType.EXTENDED) 
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
	 HibernateTransactionManager txManager = new HibernateTransactionManager();
	 txManager.setSessionFactory(sessionFactory);
	 return txManager;
	}
	
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
		}catch(PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		
		securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		securityDataSource.setUser(env.getProperty("security.jdbc.user"));
		securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
		
		securityDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.initialPoolSize")));
		securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.minPoolSize")));
		securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.maxPoolSize")));
		securityDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("security.connection.pool.maxIdleTime")));
		return securityDataSource; 
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver; 
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	       configurer.enable();
	   }

	

	

}
