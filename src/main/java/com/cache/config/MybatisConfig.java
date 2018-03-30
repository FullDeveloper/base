
package com.cache.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**  
* 创建时间：2018年3月29日 下午11:57:57  

* 项目名称：cache-redis  

* @author zhourunbin  

* @version 1.0   

* Description： 
*/
@PropertySource({"classpath:jdbc.properties"})
public class MybatisConfig {

	 //设置阿里druid数据源
    @Bean(name="dataSource")
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/zhiping");
        //连接超时时间
        druidDataSource.setMaxWait(10000);
        //最大存活时间
        //druidDataSource.setMaxActive(10000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        //连接池中的最小生存时间
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        //这里建议配置为TRUE，防止取到的连接不可用
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(false);
        //自动提交
        druidDataSource.setDefaultAutoCommit(true);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return  druidDataSource;
    }
	
 // 配置SqlSessionFactory对象
    public SqlSessionFactoryBean getSqSesionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml")); // 这里可以通过mybatis-config.xml 来设置 typeAliasPackage和mapper。
        //这个setMapperLocations 必须把mapper文件放resources里面 不然获取文件
        //扫描mybatis配置文件的
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
       /*  Resource[] mapperLocations = new Resource[] { new ClassPathResource("/com/ouyang/teson/mapper*//**//*.xml") };*/
        //设置映射的bean类
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cache.entity");
        return sqlSessionFactoryBean;
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = getSqSesionFactory();
        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);// 开启驼峰映射
        sqlSessionFactory.getConfiguration().setCacheEnabled(true);
        //sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(true);
        sqlSessionFactory.getConfiguration().setAggressiveLazyLoading(false);
        return sqlSessionFactory;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(this.getDataSource());
        return dataSourceTransactionManager;
    }


    @Bean
    @Scope("prototype")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate template =new SqlSessionTemplate(sqlSessionFactory());
        return template;
    }


    
}
 