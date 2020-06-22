package pers.xkr.persistence.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pers.xkr.persistence.pojo.Configuration;
import pers.xkr.persistence.pojo.MappedStatement;

import java.io.InputStream;
import java.util.List;

public class XMlMapperBuilder {

    private Configuration configuration;


    public XMlMapperBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    public void statementMapperParse(InputStream inputStream) throws DocumentException {
        Document read = new SAXReader().read(inputStream);
        Element rootElement = read.getRootElement();
        List<Element> selectNodes = rootElement.selectNodes("//select");

        String namespace= rootElement.attributeValue("namespace");

        for (Element selectNode : selectNodes) {
            String id = selectNode.attributeValue("id");
            String resultType = selectNode.attributeValue("resultType");
            String parameterType = selectNode.attributeValue("parameterType");
            String textTrim = selectNode.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setNamespace(namespace);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(textTrim);

            configuration.getMappedStatementMap().put(namespace+id,mappedStatement);
        }

    }

}
