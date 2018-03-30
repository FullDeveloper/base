
package com.cache.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 创建时间：2018年3月29日 下午11:57:57
 * 
 * 项目名称：cache-redis
 * 
 * @author zhourunbin
 * 
 * @version 1.0
 * 
 *          Description：
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.cache"},useDefaultFilters = true)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/jsp/function/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		 MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 jsonConverter.setObjectMapper(objectMapper);
		 
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		
		 return jsonConverter;
	}

	// 静态文件
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("addResourceHandlers");
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
	}

}
