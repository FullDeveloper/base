
package com.cache.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**  
* 创建时间：2018年3月29日 下午11:57:57  

* 项目名称：cache-redis  

* @author zhourunbin  

* @version 1.0   

* Description： 
*/
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.cache"})
@Import(MybatisConfig.class)
public class ApplicationConfig {

	@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 跟@MapperScan(basePackages = { "com.ouyang.teson.dao" }) 等同
        //如果通过web.xml 加载servlet的话，可能找不到映射对象 建议用注解
        mapperScannerConfigurer.setBasePackage("com.cache.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
	
}
 