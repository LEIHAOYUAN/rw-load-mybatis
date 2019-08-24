package com.lhy.base.interceptor;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MybatisInterceptorConfig {

    @Bean
    public DynamicDatasourceInterceptor dynamicDatasourceInterceptor(SqlSessionFactory sqlSessionFactory) {
        DynamicDatasourceInterceptor rwInterceptor = new DynamicDatasourceInterceptor();
        sqlSessionFactory.getConfiguration().addInterceptor(rwInterceptor);
        return rwInterceptor;
    }


}