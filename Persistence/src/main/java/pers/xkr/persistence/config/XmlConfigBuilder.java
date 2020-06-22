package pers.xkr.persistence.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pers.xkr.persistence.io.Resource;
import pers.xkr.persistence.pojo.Configuration;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XmlConfigBuilder {

    private Configuration configuration;


    public XmlConfigBuilder() {
        configuration = new Configuration();
    }

    public Configuration buildConfiguration(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);

        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");

        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);

        List<Element> selectMapperNodes = rootElement.selectNodes("//mapper");

        for (Element selectMapperNode : selectMapperNodes) {


            String resource = selectMapperNode.attributeValue("resource");
            InputStream streamFromXmlFile = Resource.getStreamFromXmlFile(resource);
            new XMlMapperBuilder(configuration).statementMapperParse(streamFromXmlFile);
        }

        return configuration;
    }
}
