package common;

/**
 * @Author: 吴文俊
 * @Date: 2020/9/25 16:43
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class MultiDataSourceConfig {

    //MySQL数据库连接
    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public PoolProperties mysqlDataSourceProperties() {
        return new PoolProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource")
    public DataSource mysqlDataSource(PoolProperties poolProperties) {
        PoolProperties mysqlDataSourceProperties = mysqlDataSourceProperties();
        log.info("mysql datasource: {}", mysqlDataSourceProperties.getUrl());
        return new DataSource(mysqlDataSourceProperties);
    }
    // 数据库事务
    @Bean(name = "mysqlTxManager")
    @Resource
    public PlatformTransactionManager mysqlTxManager(DataSource mysqlDataSource) {
        return new DataSourceTransactionManager(mysqlDataSource);
    }
    //TODO 后续添加其他数据库

}
