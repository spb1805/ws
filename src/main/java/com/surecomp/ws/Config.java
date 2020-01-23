package com.surecomp.ws;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * The suite of the application configuration classes
 * 
 */
@EnableWs
@Configuration
//@PropertySource("classpath:/com/surecomp/config/application.properties")
//@Import({ DataSourceConfig.class, JpaConfig.class })
@ComponentScan(basePackages = { "com.surecomp.*, com.allnett.*", "netimex.*" })
public class Config extends WsConfigurerAdapter {
	/*
	 * @Bean public static PropertySourcesPlaceholderConfigurer
	 * propertySourcesPlaceholderConfigurer() { return new
	 * PropertySourcesPlaceholderConfigurer(); }
	 */
	
	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);

		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}
}
