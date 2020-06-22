package pers.xkr.persistence.sqlSession;

import pers.xkr.persistence.executor.Executor;
import pers.xkr.persistence.executor.SimpleExecutor;
import pers.xkr.persistence.pojo.Configuration;
import pers.xkr.persistence.pojo.MappedStatement;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession{

    private Executor executor = new SimpleExecutor();
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<T> query = selectAll(statementId,params);

        if(query.size() == 1){
            return query.get(0);
        }else {
            return null;
        }
    }

    @Override
    public <T> List<T> selectAll(String statementId, Object... params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);

        return executor.query(configuration,mappedStatement,params);
    }

    @Override
    public boolean insert(String statementId, Object... params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);

        return executor.update(configuration,mappedStatement,params);
    }

    @Override
    public boolean update(String statementId, Object... params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);

        return executor.update(configuration,mappedStatement,params);

    }

    @Override
    public boolean delete(String statementId, Object... params) {
        return false;
    }


    @Override
    public <T> T getMapper(Class<?>  mapperClass) {
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{mapperClass},new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //取到全限类名和方法名作为statementId
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + methodName;

                MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
                String opertType = mappedStatement.getOpertType();

                //获取参数
                Type genericReturnType = method.getGenericReturnType();
                if("select".equals(opertType)){
                    // 泛型类型参数化
                    if (genericReturnType instanceof ParameterizedType) {
                        List<Object> objects = selectAll(statementId, args);
                        return objects;

                    } else {
                        return selectOne(statementId, args);
                    }
                }else if("update".equals(opertType)){
                    return update(statementId,args);

                }else if ("insert".equals(opertType)){
                   return update(statementId,args);
                }else if("delete".equals(opertType)){
                    return update(statementId,args);

                }
                return null;
            }
        });

        return (T) proxy;
    }
}
