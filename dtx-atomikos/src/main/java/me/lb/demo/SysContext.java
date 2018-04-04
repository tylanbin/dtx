package me.lb.demo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
// @EnableTransactionManagement
public class SysContext {
	
    @Primary
    @Bean("ds1")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.one")
    public DataSource ds1() {
        return new AtomikosDataSourceBean();
    }
    
    @Primary
    @Bean("ssf1")
    public SqlSessionFactory ssf1(@Qualifier("ds1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml"));
        bean.setTypeAliasesPackage("me.lb.demo.model");
        return bean.getObject();
    }

    @Primary
    @Bean("sst1")
    public SqlSessionTemplate sst1(@Qualifier("ssf1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    

    @Bean("ds2")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.two")
    public DataSource ds2() {
        return new AtomikosDataSourceBean();
    }
    
    @Bean("ssf2")
    public SqlSessionFactory ssf2(@Qualifier("ds2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml"));
        bean.setTypeAliasesPackage("me.lb.demo.model");
        return bean.getObject();
    }

    @Bean("sst2")
    public SqlSessionTemplate sst2(@Qualifier("ssf2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}