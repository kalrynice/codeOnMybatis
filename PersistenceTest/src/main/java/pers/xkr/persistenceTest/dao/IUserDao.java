package pers.xkr.persistenceTest.dao;

import pers.xkr.persistenceTest.pojo.User;

import java.util.List;

public interface IUserDao {

    List<User>  findAllUsers();


    User findUserById(String id);
}
