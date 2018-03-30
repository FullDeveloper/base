
package com.cache.initial;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cache.config.ApplicationConfig;
import com.cache.config.WebMvcConfig;

/**  
* 创建时间：2018年3月29日 下午11:57:57  

* 项目名称：cache-redis  

* @author zhourunbin  

* @version 1.0   

* Description： 
*/
public class WebContextInitital extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

}
 