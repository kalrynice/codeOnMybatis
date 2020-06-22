package pers.xkr.persistence.sqlSession;

import java.io.InputStream;

public interface SqlSessionFactory {

    public SqlSession openSession();
}
