package com.lhy.base;

import com.lhy.base.interceptor.DynamicDataSource;
import com.lhy.base.interceptor.DynamicDataSourceHolder;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库配置
 */
@Configuration
public class DataSourceConfiguration {

    private static final Log log = LogFactory.getLog(DataSourceConfiguration.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;


    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave1DataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Primary
    @Bean(name = "dynamicDatasource")
    public DataSource dynamicDatasource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceHolder.DB_MASTER, masterDataSource);
        targetDataSources.put(DynamicDataSourceHolder.DB_SLAVE, slaveDataSource);
        DynamicDataSource dynamicDS = new DynamicDataSource();
        dynamicDS.setDefaultTargetDataSource(masterDataSource);
        dynamicDS.setTargetDataSources(targetDataSources);
        return dynamicDS;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("dynamicDatasource") DataSource dynamicDatasource) {
        LazyConnectionDataSourceProxy proxy = new LazyConnectionDataSourceProxy(dynamicDatasource);
        return proxy;
    }


}
