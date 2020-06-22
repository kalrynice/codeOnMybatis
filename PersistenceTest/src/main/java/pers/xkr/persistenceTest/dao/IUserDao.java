package pers.xkr.persistenceTest.dao;

import pers.xkr.persistenceTest.pojo.User;

import java.util.List;

public interface IUserDao {

    List<User>  findAllUsers();


    User findUserById(User user);

    boolean insertUser(User user);

    boolean updateUserPasswordById(User user);

    boolean deleteUserById(User user);

}
