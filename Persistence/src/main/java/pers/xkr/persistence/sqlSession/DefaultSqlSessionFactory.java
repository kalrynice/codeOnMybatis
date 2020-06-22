package pers.xkr.persistence.sqlSession;

import org.dom4j.DocumentException;
import pers.xkr.persistence.config.XmlConfigBuilder;
import pers.xkr.persistence.pojo.Configuration;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class DefaultSqlSessionFactory implements SqlSessionFactory{


    private Configuration configuration;


    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
