package common;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @Author: 吴文俊
 * @Date: 2020/9/25 15:57
 */
@Configuration
@MapperScan(basePackages = {"com.example.brilliants.mapper"})
public class MybaitsSqlSessionConfig {
    @Bean(name = "setSqlSessionFactory")
    public SqlSessionFactoryBean setSqlSessionFactory (@Qualifier( "mysqlDataSource") DataSource mysqlDataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
        return bean;
    }


//    @Bean(name = "mysqlSqlSessionTemplate")
//    public SqlSessionTemplate setSqlSessionTemplate(Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
//        SqlSessionFactory sqlSessionFactory;
//        return new SqlSessionTemplate(sqlSessionFactory);

}
