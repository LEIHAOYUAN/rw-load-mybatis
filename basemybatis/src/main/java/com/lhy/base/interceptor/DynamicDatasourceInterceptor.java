package com.lhy.base.interceptor;

import com.lhy.base.DynamicDataSourceHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Properties;

/**
 * 自定义Mybatis拦截器
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDatasourceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            DynamicDataSourceHolder.setDbType(DynamicDataSourceHolder.DB_MASTER);
        } else {
            DynamicDataSourceHolder.setDbType(DynamicDataSourceHolder.DB_SLAVE);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {}
}