package pers.xkr.persistence.sqlSession;

import pers.xkr.persistence.pojo.Configuration;
import pers.xkr.persistence.pojo.MappedStatement;

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
}
