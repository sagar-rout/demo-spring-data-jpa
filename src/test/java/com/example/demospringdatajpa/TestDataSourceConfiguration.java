package com.example.demospringdatajpa;

import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;

import javax.sql.DataSource;

@TestConfiguration
class TestDataSourceConfiguration implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        if (bean instanceof DataSource) {
            ChainListener listener = new ChainListener();
            SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
            loggingListener.setQueryLogEntryCreator(new DefaultQueryLogEntryCreator());

            listener.addListener(loggingListener);
            listener.addListener(new DataSourceQueryCountListener());
            result = ProxyDataSourceBuilder
                    .create((DataSource) bean)
                    .name("DS-Proxy")
                    .logQueryBySlf4j("DEBUG")
                    .listener(listener)
                    .build();
        }
        return result;
    }
}