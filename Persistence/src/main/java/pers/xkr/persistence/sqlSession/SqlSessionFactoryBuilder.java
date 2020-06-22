package pers.xkr.persistence.sqlSession;

import org.dom4j.DocumentException;
import pers.xkr.persistence.config.XmlConfigBuilder;
import pers.xkr.persistence.pojo.Configuration;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public static SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        Configuration  configuration = new XmlConfigBuilder().buildConfiguration(inputStream);

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return  sqlSessionFactory;
    }
}
