package com.lnlr.dynamic.config.druid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author:leihfei
 * @description 从库配置文件
 * @date:Create in 10:27 2018/8/29
 * @email:leihfein@gmail.com
 */
@Configuration
@EnableTransactionManagement//开启事物管理
@EnableJpaRepositories(//自定义数据管理的配置
        //指定EntityManager的创建工厂Bean
        entityManagerFactoryRef = "entityManagerFactorySlave",
        //指定事物管理的Bean
        transactionManagerRef = "transactionManagerSlave",
        //指定管理的实体位置
        basePackages = {"com.lnlr.dynamic.pojo.slave"})
public class SalveConfig {

    @Resource
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name = "entityManagerslave")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryslave(builder).getObject().createEntityManager();
    }

    @Resource
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "entityManagerFactorySlave")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryslave(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slaveDataSource)
                .packages("com.lnlr.dynamic.pojo.slave")
                .persistenceUnit("slavePersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Bean(name = "transactionManagerSlave")
    PlatformTransactionManager transactionManagerslave(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryslave(builder).getObject());
    }

}
