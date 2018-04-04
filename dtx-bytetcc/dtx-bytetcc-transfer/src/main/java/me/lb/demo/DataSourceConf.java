package me.lb.demo;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.bytesoft.bytejta.supports.jdbc.LocalXADataSource;
import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@Import(SpringCloudConfiguration.class)
public class DataSourceConf {

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		LocalXADataSource dataSource = new LocalXADataSource();
		dataSource.setDataSource(this.dbcpDataSource());
		return dataSource;
	}

	public DataSource dbcpDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/sb_cloud?useUnicode=true&amp;characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setMaxTotal(50);
		ds.setInitialSize(20);
		ds.setMaxWaitMillis(60000);
		ds.setMinIdle(6);
		ds.setLogAbandoned(true);
		ds.setRemoveAbandonedOnBorrow(true);
		ds.setRemoveAbandonedOnMaintenance(true);
		ds.setRemoveAbandonedTimeout(1800);
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		ds.setValidationQuery("select 'x' ");
		ds.setValidationQueryTimeout(1);
		ds.setTimeBetweenEvictionRunsMillis(30000);
		ds.setNumTestsPerEvictionRun(20);
		return ds;
	}

}