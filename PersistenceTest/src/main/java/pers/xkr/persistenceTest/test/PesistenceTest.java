package pers.xkr.persistenceTest.test;

import org.dom4j.DocumentException;
import org.junit.Test;
import pers.xkr.persistence.io.Resource;
import pers.xkr.persistence.sqlSession.SqlSessionFactory;
import pers.xkr.persistence.sqlSession.SqlSessionFactoryBuilder;
import pers.xkr.persistenceTest.dao.IUserDao;
import pers.xkr.persistenceTest.dao.impl.IUserDaoImpl;
import pers.xkr.persistenceTest.pojo.User;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class PesistenceTest {
    private IUserDao userDao =  new IUserDaoImpl();

    @Test
    public void testFindUserById() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        InputStream streamFromXmlFile = Resource.getStreamFromXmlFile("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory= SqlSessionFactoryBuilder.build(streamFromXmlFile);
        User user = new User();
        user.setId("1");
        user.setUsername("lucy");
        User user1= sqlSessionFactory.openSession().selectOne("userfindUserById",user);
        System.out.println(user1);

        List<User> users = sqlSessionFactory.openSession().selectAll("userfindUsers",user);


        System.out.println(users.size());

    }
}
