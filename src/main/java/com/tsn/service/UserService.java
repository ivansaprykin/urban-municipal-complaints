package com.tsn.service;

import com.tsn.dao.RoleDaoImpl;
import com.tsn.dao.UserDao;
import com.tsn.model.Application;
import com.tsn.model.Role;
import com.tsn.model.User;
import com.tsn.util.PasswordHash;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    UserDao userDao = new UserDao();

    public void saveUser(User user) {

        PasswordHash.createNewSalt();
        user.setPasswordMd5(PasswordHash.createHash(user.getPassword()));
        user.setSaltForPassword(PasswordHash.currentSalt);

        RoleDaoImpl roleDao = new RoleDaoImpl();
        Role userRole = roleDao.getRoleById(2);
        user.setRole(userRole);

        user.setBuildingAddress("ул. Вакуленчука 26");

        user.setApplications(new ArrayList<Application>());

        userDao.saveUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public User getUserByLogin(String userLogin) {
        return userDao.getUserByLogin(userLogin);
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

}
