package org.xavier.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.type.JdbcType;
import org.xavier.config.properties.DateBaseProperties;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 描述信息：<br/>
 * 数据库配置
 *
 * @author Xavier
 * @version 1.0
 * @date 2018/2/8
 * @since Jdk 1.8
 */


@Configuration
@EnableConfigurationProperties(DateBaseProperties.class)
public class DateBaseConfig {
    @Autowired
    DateBaseProperties dbProperties;

    private final static Logger logger = LoggerFactory.getLogger(DateBaseConfig.class);

    @Bean(name = "mySQLDataSource")
    public DruidDataSource mySQLDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + dbProperties.getHost() + "/" + dbProperties.getDbName() + "?serverTimezone=UTC&useSSL=false&allowMultiQueries=true");
        dataSource.setUsername(dbProperties.getAc());
        dataSource.setPassword(dbProperties.getPw());
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(2);
        dataSource.setMaxWait(5000);
        return dataSource;
    }

    @Bean(name = "mySQLSessionFactory")
    public SqlSessionFactory mySQLSessionFactory(DruidDataSource mySQLDataSource) throws Exception {
        // 这个才能使 Mybatis-plus 生效(否则无法映射数据库操作方法)
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(mySQLDataSource);
        VFS.addImplClass(SpringBootVFS.class);
        String typeAliasesPackage = "org.xavier.domain.po;";
        // 扫描Mybatis所用到的返回entity类型
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // 扫描Mybatis所用到 mapper.xml
            mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/*.xml"));
            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
            configuration.setJdbcTypeForNull(JdbcType.NULL);
            //开启下划线转驼峰(即 PO 和数据表都不带下划线)
            configuration.setMapUnderscoreToCamelCase(false);
            TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
            mybatisSqlSessionFactoryBean.setConfiguration(configuration);
            return mybatisSqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            logger.error("Fail to init mybatis.", e);
            // 主动中断服务
            System.exit(0);
            return null;
        }
    }
}