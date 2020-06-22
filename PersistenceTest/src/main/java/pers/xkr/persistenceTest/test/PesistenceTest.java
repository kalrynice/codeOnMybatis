package pers.xkr.persistenceTest.test;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import pers.xkr.persistence.io.Resource;
import pers.xkr.persistence.sqlSession.SqlSessionFactory;
import pers.xkr.persistence.sqlSession.SqlSessionFactoryBuilder;
import pers.xkr.persistenceTest.dao.IUserDao;
//import pers.xkr.persistenceTest.dao.impl.IUserDaoImpl;
import pers.xkr.persistenceTest.pojo.User;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class PesistenceTest {
//    private IUserDao userDao =  new IUserDaoImpl();
    private SqlSessionFactory sqlSessionFactory = null;

    @Before
    public  void getConfig() throws PropertyVetoException, DocumentException {
        InputStream streamFromXmlFile = Resource.getStreamFromXmlFile("sqlMapConfig.xml");
        sqlSessionFactory= SqlSessionFactoryBuilder.build(streamFromXmlFile);
    }


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
    @Test
    public void testFindAll() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        InputStream streamFromXmlFile = Resource.getStreamFromXmlFile("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory= SqlSessionFactoryBuilder.build(streamFromXmlFile);

        List<User> users = sqlSessionFactory.openSession().selectAll("userfindUsers",null);


        System.out.println(users.size());

    }

    @Test
    public void testFindAllByMapper() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        IUserDao userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);


        List<User> allUsers = userDao.findAllUsers();
        //List<User> users = sqlSessionFactory.openSession().selectAll("userfindUsers",null);

        for (User user : allUsers) {
            System.out.println(user);
        }
        System.out.println(allUsers.size());

    }


    @Test
    public void testFindUserByMapper() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        IUserDao userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);
        User user = new User();
        user.setId("1");
        user.setUsername("lucy");

        User user1 = userDao.findUserById(user);

        System.out.println(user1);

    }

    @Test
    public void testInsertUserByMapper() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        IUserDao userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);
        User user = new User();
        user.setId("5");
        user.setUsername("kalry");
        user.setPassword("123");
        boolean b = userDao.insertUser(user);

        System.out.println(b);

    }


    @Test
    public void testupdateUserPassword() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        IUserDao userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);
        User user = new User();
        user.setId("5");
        user.setPassword("kalry");
        boolean b = userDao.updateUserPasswordById(user);

        System.out.println(b);

    }

    @Test
    public void testDeleteUser() throws PropertyVetoException, DocumentException, ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {

        IUserDao userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);
        User user = new User();
        user.setId("5");
        boolean b = userDao.deleteUserById(user);

        System.out.println(b);

    }
}
