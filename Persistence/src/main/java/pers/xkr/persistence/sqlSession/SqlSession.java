package pers.xkr.persistence.sqlSession;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {
    <T> T selectOne(String statementId ,Object... params) throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException;


    <T> List<T> selectAll(String statementId , Object... params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;

    boolean insert(String statementId,Object... params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    boolean update(String statementId,Object... params) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException;

    boolean delete(String statementId,Object... params);


    public <T> T getMapper(Class<?> mapperClass);
}
