package pers.xkr.persistence.sqlSession;

import pers.xkr.persistence.pojo.Configuration;
import pers.xkr.persistence.pojo.MappedStatement;
import pers.xkr.persistence.utils.GenericTokenParser;
import pers.xkr.persistence.utils.ParameterMapping;
import pers.xkr.persistence.utils.ParameterMappingTokenHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor{

    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        //创建连接
        Connection connection = configuration.getDataSource().getConnection();


        String xmlSql = mappedStatement.getSql();
        String parameterType = mappedStatement.getParameterType();
        String resultType = mappedStatement.getResultType();


        //讲语句中的#{}用问号代替  同时通过handler存放#{}中的属性名称 获取数据库执行的sql语句
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parseSql = genericTokenParser.parse(xmlSql);

        PreparedStatement preparedStatement = connection.prepareStatement(parseSql);

        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();


        Class object = getClassType(parameterType);
        for (int i = 0; i < parameterMappings.size(); i++) {
            String content = parameterMappings.get(i).getContent();

            Field declaredField = object.getDeclaredField(content);
            declaredField.setAccessible(true);

            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,o);

        }

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Object> objects = new ArrayList<>();
        Class<?> resultTypeClass = getClassType(resultType);


        while(resultSet.next()){
            Object o = resultTypeClass.newInstance();


            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i < metaData.getColumnCount() ; i++) {
                String columnName = metaData.getColumnName(i);
                Field declaredField = resultTypeClass.getDeclaredField(columnName);
                declaredField.setAccessible(true);
                declaredField.set(o,resultSet.getString(columnName));
            }
            objects.add(o);

        }
        return (List<T>) objects;

        //
    }

    public Class<?> getClassType(String className) throws ClassNotFoundException {
        if(className!=null){
            Class<?> aClass = Class.forName(className);
            return aClass;
        }
        return null;
    }
}
