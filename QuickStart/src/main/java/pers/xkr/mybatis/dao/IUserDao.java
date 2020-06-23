package pers.xkr.mybatis.dao;

import pers.xkr.mybatis.pojo.User;

import java.util.List;

public interface IUserDao {

    List<User>  findAll();

    boolean insertUser(User user);

    boolean updateUserPasswordById(User user);

    boolean deleteUserById(User user);

}
