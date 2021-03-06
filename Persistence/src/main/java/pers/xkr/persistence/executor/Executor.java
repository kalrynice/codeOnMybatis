package pers.xkr.persistence.executor;

import pers.xkr.persistence.pojo.Configuration;
import pers.xkr.persistence.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    <T> List<T> query(Configuration configuration, MappedStatement mappedStatement,Object ...params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException;

    boolean update(Configuration configuration, MappedStatement mappedStatement,Object ...params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException;

   }
