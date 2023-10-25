//package com.qf.config;
//
//import lombok.Value;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @author : sin
// * @date : 2023/10/26 0:53
// * @Description :
// */
//@Configuration
//@PropertySource("classpath:jdbc.properties")
//@MapperScan(basePackages = {MasterDataSourceConfig.COMMON}, sqlSessionFactoryRef = "masterSqlSessionFactory")
//public class MasterDataSourceConfig {
//    //精确到 *.master及公共目录，与second、third数据源隔离
//
//    static final String COMMON = "com.qf.pojo.mapper";
//    //static final String PACKAGE = "cn.wxt.*.dao.master";
//    static final String MAPPER_LOCATION = "classpath*:mybatis/*.xml";
//
//
//    @Bean(name = "masterDataSource")
//    @Primary
//    public DataSource masterDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName();
//        dataSource.setUrl(url);
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }
//
//    @Bean(name = "masterTransactionManager")
//    @Primary
//    public DataSourceTransactionManager masterTransactionManager() {
//        return new DataSourceTransactionManager(masterDataSource());
//    }
//
//    @Bean(name = "masterSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(masterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
//        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        return sessionFactory.getObject();
//    }
//}
