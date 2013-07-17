package com.gmail.at.zhuikov.aleksandr.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		context.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
				.addMappingForUrlPatterns(null, false, "/*");
		
		context.addFilter("OpenEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class)
				.addMappingForUrlPatterns(null, false, "*");
		
		context.addFilter("HiddenHttpMethodFilter", HiddenHttpMethodFilter.class)
				.addMappingForUrlPatterns(null, false, "*");
		
		registerRootSpringContext(context);
		registerSpringMvcContext(context);
	}

	private void registerSpringMvcContext(ServletContext context) {
		XmlWebApplicationContext mvc = new XmlWebApplicationContext();
		mvc.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
		
		Dynamic mvcRegistration = context.addServlet("mvc", new DispatcherServlet(mvc));
		mvcRegistration.addMapping("/");
		mvcRegistration.setLoadOnStartup(1);
	}

	private void registerRootSpringContext(ServletContext context) {
		XmlWebApplicationContext root = new XmlWebApplicationContext();
		root.setConfigLocation("/WEB-INF/spring/root-context.xml");
		context.addListener(new ContextLoaderListener(root));
	}
}
