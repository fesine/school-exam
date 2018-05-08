package cn.edu.hebau.exam.config;

import cn.edu.hebau.exam.dao.DaoServiceImpl;
import cn.edu.hebau.exam.dao.IDaoService;
import com.fesine.dao.interceptor.MysqlInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;


/**
 * @description: mybatis配置
 * @author: Fesine
 * @createTime:2017/10/9 21:23
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2017/10/9 21:23
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Autowired
    private DataSource dataSource;

    /**
     * 创建sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new
                PathMatchingResourcePatternResolver();
        Resource[] res = resolver.getResources(mapperLocations);
        sqlSessionFactory.setMapperLocations(res);
        sqlSessionFactory.setDataSource(dataSource);
        Interceptor[] interceptors = {new MysqlInterceptor()};
        sqlSessionFactory.setPlugins(interceptors);
        return sqlSessionFactory;
    }

    //@Bean
    //public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    //    return new SqlSessionTemplate(sqlSessionFactory);
    //}
    //
    //@Bean
    //public DaoSupport daoSupport(SqlSessionTemplate sqlSessionTemplate) {
    //    DaoSupportImpl daoSupport = new DaoSupportImpl();
    //    daoSupport.setSqlSessionTemplate(sqlSessionTemplate);
    //    return daoSupport;
    //}

    @Bean
    public IDaoService daoService(SqlSessionFactory sqlSessionFactory) {
        DaoServiceImpl daoService = new DaoServiceImpl();
        daoService.setSqlSessionFactory(sqlSessionFactory);
        return daoService;
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
