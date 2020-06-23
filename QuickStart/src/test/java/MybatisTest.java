import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pers.xkr.mybatis.dao.IUserDao;
import pers.xkr.mybatis.pojo.User;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void getSessionFacory() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    }

    @Test
    public void select() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        List<User> all = mapper.findAll();

        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void insert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId("8");
        user.setUsername("sdfsdf");
        user.setPassword("123");
        System.out.printf(mapper.insertUser(user)+"");
        sqlSession.commit();

    }

    @Test
    public void update() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId("8");
        user.setPassword("sdfsdfsdf");
        System.out.printf(mapper.updateUserPasswordById(user)+"");
        sqlSession.commit();

    }

    @Test
    public void delete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user = new User();
        user.setId("8");

        System.out.printf(mapper.deleteUserById(user)+"");
        sqlSession.commit();

    }

}
